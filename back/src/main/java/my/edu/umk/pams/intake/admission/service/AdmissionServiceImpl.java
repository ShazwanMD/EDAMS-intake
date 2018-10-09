package my.edu.umk.pams.intake.admission.service;

import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_REFERENCE_NO;
import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_APPLICATION_REFERENCE_NO;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.task.Task;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.edu.umk.pams.connector.payload.AddressPayload;
import my.edu.umk.pams.connector.payload.CandidatePayload;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.NationalityCodePayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;
import my.edu.umk.pams.connector.payload.ResidencyCodePayload;
import my.edu.umk.pams.connector.payload.StudyCenterPayload;
import my.edu.umk.pams.connector.payload.StudyModePayload;
import my.edu.umk.pams.connector.payload.UserPayload;
import my.edu.umk.pams.intake.IntakeConstants;
import my.edu.umk.pams.intake.admission.dao.InCandidateDao;
import my.edu.umk.pams.intake.admission.event.CandidateAcceptedEvent;
import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.admission.model.InCandidateImpl;
import my.edu.umk.pams.intake.admission.model.InCandidateStatus;
import my.edu.umk.pams.intake.admission.selection.SelectionStrategyHelper;
import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InFacultyCode;
import my.edu.umk.pams.intake.common.model.InNationalityCode;
import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.model.InProgramFieldCode;
import my.edu.umk.pams.intake.common.model.InResidencyCode;
import my.edu.umk.pams.intake.common.model.InStudyCenterCode;
import my.edu.umk.pams.intake.common.model.InStudyMode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.core.InFlowState;
import my.edu.umk.pams.intake.identity.model.InGroup;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InStudyModeOffering;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.integration.InPermission;
import my.edu.umk.pams.intake.security.service.AccessService;
import my.edu.umk.pams.intake.security.service.SecurityService;
import my.edu.umk.pams.intake.system.model.InEmailQueue;
import my.edu.umk.pams.intake.system.model.InEmailQueueImpl;
import my.edu.umk.pams.intake.system.model.InEmailQueueStatus;
import my.edu.umk.pams.intake.system.service.SystemService;
import my.edu.umk.pams.intake.workflow.service.WorkflowConstants;
import my.edu.umk.pams.intake.workflow.service.WorkflowService;

/**
 * @author PAMS
 */
@Transactional
@Service("admissionService")
public class AdmissionServiceImpl implements AdmissionService {
	private static final Logger LOG = LoggerFactory.getLogger(AdmissionServiceImpl.class);

	@Autowired
	private InCandidateDao candidateDao;

	@Autowired
	private SelectionStrategyHelper selectionStrategyHelper;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private AccessService accessService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private WorkflowService workflowService;

	// ====================================================================================================
	// INTAKE, INTAKE APPLICATION
	// ====================================================================================================

	@Deprecated
	@Override
	public void processIntake(InIntake intake) {
		// preselect
		selectionStrategyHelper.select(intake);
		// pickup all preselection application
		// todo(uda) : InBidStatus.SELECTED
		List<InIntakeApplication> applications = applicationService.findIntakeApplications(intake);
		for (InIntakeApplication application : applications) {
			preSelectIntakeApplication(application);
		}
	}

	@Override
	public void preSelectIntakeApplication(InIntakeApplication application) {
		// create candidate
		InCandidate candidate = new InCandidateImpl();
		candidate.setIntake(application.getIntake());
		candidate.setName(application.getName());
		candidate.setIdentityNo(application.getCredentialNo());
		candidate.setEmail(application.getEmail());
		candidate.setStudyModeSelection(application.getStudyModeSelection());
		candidate.setStatus(InCandidateStatus.SELECTED);
		candidate.setProgramSelection(application.getProgramSelection());
		candidate.setSupervisorSelection(application.getSupervisorSelection());
		candidate.setRegistration(false);
		candidateDao.save(candidate, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(candidate);

		// give permission to faculty group
		// example: GRP_FCTY_A01
		InProgramCode programCode = candidate.getProgramSelection().getProgramCode();
		InFacultyCode facultyCode = programCode.getFacultyCode();
		InGroup facultyGroup = identityService.findGroupByName("GRP_FCTY_" + facultyCode.getCode());
		accessService.grantPermission(candidate, facultyGroup, InPermission.VIEW);
	}

	@Override
	public void preSelectCandidate(InCandidate candidate) {
		candidate.setStatus(InCandidateStatus.PREAPPROVED);
		candidateDao.update(candidate, securityService.getCurrentUser());
	}

	@Override
	public void selectCandidate(InCandidate candidate) {
		candidate.setStatus(InCandidateStatus.APPROVED);
		candidateDao.update(candidate, securityService.getCurrentUser());
	}

	@Override
	public void rejectCandidate(InCandidate candidate) {
		candidate.setStatus(InCandidateStatus.REJECTED);
		candidateDao.update(candidate, securityService.getCurrentUser());
	}

	@Override
	public void processIntakeSelection(InIntake intake) {
		List<InIntakeApplication> applications = applicationService.findIntakeApplicationsByStatusVerify(intake,
				InBidStatus.SELECTED); // todo: , InBidStatus.SELECTED);
		for (InIntakeApplication application : applications) {
			postToCandidate(application);
		}
	}

	@Override
	public void registerCandidates(InIntake intake, List<InCandidate> candidates) {
		// create candidate
		candidates = this.findCandidatesByStatus(intake, InCandidateStatus.ACCEPTED); // note:
																						// accepted
		for (InCandidate candidate : candidates) {
			// verifyUser candidate status to true
			candidate.setRegistration(true);
			candidateDao.save(candidate, securityService.getCurrentUser());
		}
	}

	@Override
	public void rejectIntakeApplication(InIntakeApplication application) {
		// don't create candidate
	}

	@Override
	public void withdrawSelectedCandidate(InIntake intake, InCandidate candidate) {
		candidate.setStatus(InCandidateStatus.WITHDRAWN);
		updateSelectedCandidate(candidate);
	}

	@Override
	public void updateSelectedCandidate(InCandidate candidate) {
		candidateDao.update(candidate, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();

	}

	// ====================================================================================================
	// CANDIDATE
	// ====================================================================================================
	@Override
	public InCandidate findCandidateByIntakeApplicationReferenceNo(String referenceNo) {
		return candidateDao.findCandidateByIntakeApplicationReferenceNo(referenceNo);
	}

	@Override
	public InCandidate findCandidateById(Long id) {
		return candidateDao.findById(id);
	}

	@Override
	public InCandidate findCandidateByIdentityNo(String identityNo) {
		return candidateDao.findByIdentityNo(identityNo);
	}

	@Override
	public InCandidate findCandidateByMatricNo(String matricNo) {
		return candidateDao.findCandidateByMatricNo(matricNo);
	}

	@Override
	public InCandidate findCandidateByIntakeApplication(InIntakeApplication intakeApplication) {
		return candidateDao.findCandidateByIntakeApplication(intakeApplication);
	}

	@Override
	public InCandidate findCandidateByReferenceNo(String referenceNo) {
		return candidateDao.findByReferenceNo(referenceNo);
	}

	@Override
	public List<InCandidate> findCandidates(InIntake intake) {
		return candidateDao.find(intake);
	}

	@Override
	public List<InCandidate> findCandidates(InIntake intake, Integer offset, Integer limit) {
		return candidateDao.find(intake, offset, limit);
	}

	@Override
	public List<InCandidate> findCandidatesByStatus(InIntake intake, InCandidateStatus status) {
		return candidateDao.find(intake, status);
	}

	@Override
	public List<InCandidate> findCandidatesByFlowStates(InFlowState... flowStates) {
		return candidateDao.findByFlowStates(flowStates);
	}

	@Override
	public List<InCandidate> findCandidatesAcceptOffered(InIntake intake, InCandidateStatus status) {
		return candidateDao.findAcceptCandidate(intake, status);
	}

	@Override
	public Integer countCandidate(InIntake intake) {
		return candidateDao.count(intake);
	}

	@Override
	public Integer countCandidate(InIntake intake, InCandidateStatus candidateStatus) {
		return candidateDao.count(intake, candidateStatus);
	}

	@Override
	public void preapproveCandidate(InCandidate candidate) {
		InStudyMode studyMode = commonService.findStudyModeByCode("F");
		InStudyModeOffering studyModeOffering = policyService
				.findStudyModeOfferingByIntakeAndStudyMode(candidate.getIntake(), studyMode);
		candidate.setStudyModeSelection(studyModeOffering); // FULL
		candidate.setStatus(InCandidateStatus.PREAPPROVED);
		candidateDao.save(candidate, securityService.getCurrentUser());
	}

	@Override
	public void approveCandidate(InCandidate candidate) {
		InStudyMode studyMode = commonService.findStudyModeByCode("F");
		InStudyModeOffering studyModeOffering = policyService
				.findStudyModeOfferingByIntakeAndStudyMode(candidate.getIntake(), studyMode);
		candidate.setStudyModeSelection(studyModeOffering); // FULL
		candidate.setStatus(InCandidateStatus.APPROVED);
		candidateDao.save(candidate, securityService.getCurrentUser());
	}

	@Override
	public void offerCandidate(InCandidate candidate) {
		// start offering process
		candidate.setStatus(InCandidateStatus.OFFERED);
		candidateDao.update(candidate, securityService.getCurrentUser());
	}

	@Override
	public void acceptCandidate(InCandidate candidate) {
		// start acception process
		candidate.setAcception(true);
		candidate.setStatus(InCandidateStatus.ACCEPTED);

		String generatedMatricNo = generateMatricNumber(candidate);
		if (candidate.getMatricNo() == null)
			candidate.setMatricNo(generatedMatricNo);

		candidateDao.update(candidate, securityService.getCurrentUser());
	}

	@Override
	public void declinedCandidate(InCandidate candidate) {
		// start declined process
		candidate.setAcception(false);
		candidateDao.update(candidate, securityService.getCurrentUser());
	}

	@Override
	public void registerCandidate(InCandidate candidate) {
		candidate.setStatus(InCandidateStatus.REGISTERED);
		candidateDao.update(candidate, securityService.getCurrentUser());

		LOG.debug("start candidate payload");
		// payload

		InProgramCode programCode = candidate.getProgramSelection().getProgramCode();

		CandidatePayload payload = new CandidatePayload();
		payload.setName(candidate.getName());
		payload.setMatricNo(candidate.getMatricNo());
		payload.setEmail(candidate.getEmail());

		ProgramCodePayload programCodePayload = new ProgramCodePayload();
		programCodePayload.setCode(programCode.getCode());
		programCodePayload.setDescriptionEn(programCode.getDescriptionEn());
		programCodePayload.setDescriptionMs(programCode.getDescriptionMs());
		payload.setProgramCode(programCodePayload);

		InFacultyCode facultyCode = programCode.getFacultyCode();

		FacultyCodePayload facultyCodePayload = new FacultyCodePayload();
		facultyCodePayload.setCode(facultyCode.getCode());
		facultyCodePayload.setDescription(facultyCode.getDescriptionMs());
		payload.setFacultyCode(facultyCodePayload);
		payload.setProgramLevel(programCode.getProgramLevel().getCode());
		payload.setGender(candidate.getApplication().getGenderCode().getCode());
		payload.setReligion(candidate.getApplication().getReligionCode().getCode());
		payload.setMartialStatus(candidate.getApplication().getMaritalCode().getCode());
		payload.setRace(candidate.getApplication().getRaceCode().getCode());

		InUser user = identityService.findUserByEmail(candidate.getEmail());
		LOG.debug("user Email:{}", user.getEmail());

		// User
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername(user.getEmail());
		LOG.debug("Username:{}", user.getEmail());
		userPayload.setEmail(user.getEmail());
		userPayload.setPassword(user.getPassword());
		LOG.debug("Password:{}", user.getPassword());
		userPayload.setRealName(user.getRealName());
		LOG.debug("Realname:{}", user.getRealName());
		userPayload.setNric(candidate.getIdentityNo());
		LOG.debug("Identity Number:{}", candidate.getIdentityNo());
		payload.setUserPayload(userPayload);

		// if( application != null)
		InIntakeApplication application = candidate.getApplication();
		payload.setMobile(application.getMobile());
		payload.setFax(application.getFax());
		payload.setFacultyCode(facultyCodePayload);
		payload.setProgramCode(programCodePayload);
		// <program_code>-CHRT-<academic_session_code>

		String cohortCode = facultyCode.getCode() + "-" + programCode.getProgramLevel().getCode() + "-"
				+ programCode.getCode() + "-CHRT-" + candidate.getIntake().getSession().getCode();

		payload.setCohortCode(cohortCode);

		AddressPayload primaryAddress = new AddressPayload();
		primaryAddress.setAddress1(application.getOfficialAddress1());
		primaryAddress.setAddress2(application.getOfficialAddress2());
		primaryAddress.setAddress3(application.getOfficialAddress3());
		primaryAddress.setPostcode(application.getOfficialPostcode());
		primaryAddress.setStateCode(application.getOfficialStateCode().getCode());
		primaryAddress.setCountryCode(application.getOfficialCountryCode().getCode());
		payload.setPrimaryAddress(primaryAddress);

		AddressPayload secondaryAddress = new AddressPayload();
		secondaryAddress.setAddress1(application.getMailingAddress1());
		secondaryAddress.setAddress2(application.getMailingAddress2());
		secondaryAddress.setAddress3(application.getMailingAddress3());
		secondaryAddress.setPostcode(application.getMailingPostcode());
		secondaryAddress.setStateCode(application.getMailingStateCode().getCode());
		secondaryAddress.setCountryCode(application.getMailingCountryCode().getCode());
		payload.setSecondaryAddress(secondaryAddress);

		// todo: supevisor, studymode, cohort, address etc, etc
		InStudyMode studyMode = candidate.getStudyModeSelection().getStudyMode();
		StudyModePayload studyModePayload = new StudyModePayload();
		studyModePayload.setCode(studyMode.getCode());
		payload.setStudyMode(studyModePayload);

		// NationalityCodePayload
		InNationalityCode nationalityCode = application.getNationalityCode();
		NationalityCodePayload nationalityCodePayload = new NationalityCodePayload();
		nationalityCodePayload.setCode(nationalityCode.getCode());
		nationalityCodePayload.setDescriptionEn(nationalityCode.getDescriptionEn());
		nationalityCodePayload.setDescriptionMs(nationalityCode.getDescriptionMs());
		payload.setNationalityCode(nationalityCodePayload);

		// ResidencyCodePayload
		InResidencyCode residencyCode = application.getResidencyCode();
		ResidencyCodePayload residencyCodePayload = new ResidencyCodePayload();
		residencyCodePayload.setCode(residencyCode.getCode());
		residencyCodePayload.setDescriptionEn(residencyCode.getDescriptionEn());
		residencyCodePayload.setDescriptionMs(residencyCode.getDescriptionMs());
		payload.setResidencyCode(residencyCodePayload);

		// StudyCenterCodePayload

		InStudyCenterCode studyCenter = candidate.getApplication().getStudyCenterCode();
		LOG.debug("studyCenter:{}", studyCenter);
		StudyCenterPayload studyCenterCodePayload = new StudyCenterPayload();
		studyCenterCodePayload.setCode(studyCenter.getCode());
		studyCenterCodePayload.setDescriptionEn(studyCenter.getDescriptionEn());
		studyCenterCodePayload.setDescriptionMs(studyCenter.getDescriptionMs());
		payload.setStudyCenter(studyCenterCodePayload);

		CandidateAcceptedEvent event = new CandidateAcceptedEvent(payload);
		applicationContext.publishEvent(event);

		LOG.debug("finish candidate payload");
	}

	@Override
	public void broadcastResult(InIntake intake) {
		List<InCandidate> candidates = this.findCandidatesByStatus(intake, InCandidateStatus.OFFERED);
		for (InCandidate candidate : candidates) {

			// notify candidate
			InEmailQueue emailQueue = new InEmailQueueImpl();
			emailQueue.setCode("EQ/" + System.currentTimeMillis());
			emailQueue.setTo(candidate.getEmail());
			emailQueue.setSubject("in process");
			emailQueue.setQueueStatus(InEmailQueueStatus.QUEUED);
			systemService.saveEmailQueue(emailQueue);
		}
	}

	private void postToCandidate(InIntakeApplication application) {
		InCandidate candidate = new InCandidateImpl();
		candidate.setIntake(application.getIntake());
		candidate.setName(application.getName());
		candidate.setIdentityNo(application.getCredentialNo());
		candidate.setEmail(application.getEmail());
		candidate.setStudyModeSelection(application.getStudyModeSelection());
		candidate.setStatus(InCandidateStatus.SELECTED);
		candidate.setProgramSelection(application.getProgramSelection());
		candidate.setSupervisorSelection(application.getSupervisorSelection());
		candidate.setRegistration(false);
		candidate.setApplication(application);
		candidate.setAuditNo(application.getIntake().getAuditNo());
		candidate.setReferenceNo(application.getIntake().getReferenceNo());
		candidate.setCancelComment(application.getIntake().getCancelComment());
		candidate.setSourceNo(application.getIntake().getSourceNo());
		candidate.setDescriptionEn(application.getIntake().getDescriptionEn());
		candidate.setDescriptionMs(application.getIntake().getDescriptionMs());
		candidateDao.save(candidate, securityService.getCurrentUser());

		// //Checking By GraduateCenter
		// if(application.getIntake().getGraduateCenter().getCode().equals("CPS")){
		// LOG.debug("GraduateCenter CPS");
		//
		// candidate.setSourceNo(UUID.randomUUID().toString());
		// candidate.setAuditNo(UUID.randomUUID().toString());
		//
		// startCandidateTask(candidate);
		// }

	}

	@Override
	public boolean isMatricNoExists(String matricNo) {
		return this.isMatricNoExists(matricNo);
	}

	private String generateMatricNumber(InCandidate candidate) {
		// generate matric no
		Map<String, Object> map = new HashMap<String, Object>();
		InFacultyCode facultyCode = candidate.getProgramSelection().getProgramCode().getFacultyCode();
		InIntakeSession session = candidate.getProgramSelection().getIntake().getSession();

		String intakeLevel = null;

		Boolean Apel = candidate.getApplication().isApelCertificateAttached();
		Boolean Diploma = candidate.getApplication().isDiplomaResultAttached();
		Boolean Spm = candidate.getApplication().isSpmResultAttached();
		Boolean Stpm = candidate.getApplication().isStpmResultAttached();
		Boolean Stam = candidate.getApplication().isStamResultAttached();

		if (Apel == true) {
			intakeLevel = "F";

		} else if (Apel == true && Diploma == true && Spm == true && Stpm == true || Stam == true) {
			intakeLevel = "F";

		} else if (Apel == true && Diploma == true) {
			intakeLevel = "F";

		} else if (Apel == true && Spm == true) {
			intakeLevel = "F";

		} else if (Apel == true && Stpm == true) {
			intakeLevel = "F";

		} else if (Diploma == true) {
			intakeLevel = "B";

		} else if (Diploma == true && Spm == true && Stpm == true || Stam == true) {
			intakeLevel = "B";

		} else if (Diploma == true && Stpm == true) {
			intakeLevel = "B";

		} else if (Diploma == true && Spm == true) {
			intakeLevel = "B";

		} else if (Spm == true || Stpm == true) {
			intakeLevel = "A";

		} else {
			LOG.debug("not Attach");
			intakeLevel = "NA";
		}

		InProgramLevel programLevel = candidate.getProgramSelection().getProgramCode().getProgramLevel();
		InStudyMode studyMode = candidate.getStudyModeSelection().getStudyMode();

		map.put("facultyCode", facultyCode);
		map.put("studyMode", studyMode);
		map.put("intakeLevel", intakeLevel);
		map.put("intakeSession", session);

		String generatedMatricNo = systemService.generateFormattedReferenceNo(IntakeConstants.CANDIDATE_MATRIC_NO, map);

		return generatedMatricNo;
	}

	// ====================================================================================================
	// CANDIDATE
	// ====================================================================================================

	@Override
	public String startCandidateTask(InCandidate candidate) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("intakeSession", candidate.getIntake().getSession());
		LOG.debug("Session:{}", candidate.getIntake().getSession());

		map.put("programLevel", candidate.getIntake().getProgramLevel());
		LOG.debug("programLevel:{}", candidate.getIntake().getProgramLevel());

		String refNo = systemService.generateFormattedReferenceNo(INTAKE_APPLICATION_REFERENCE_NO, map);
		// save and process
		candidate.setReferenceNo(refNo);
		candidateDao.saveOrUpdate(candidate, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(candidate);
		LOG.debug("Ref_No:{}", refNo);

		// trigger workflow
		workflowService.processWorkflow(candidate, prepareVariable(candidate));
		LOG.debug("After trigger workflow:{}");
		LOG.debug("Processing Candidate with refNo {}", new Object[] { refNo });
		return refNo;
	}

	@Override
	public InCandidate findCandidateByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return candidateDao.findById((Long) map.get(IntakeConstants.CANDIDATE_ID));
	}

	@Override
	public Task findCandidateTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
	}

	// Find Assigned Candidate Tasks
	@Override
	public List<Task> findAssignedCandidateTasks(Integer offset, Integer limit) {
		return workflowService.findAssignedTasks(InCandidate.class.getName(), offset, limit);
	}

	// Find Pooled Candidate Tasks
	@Override
	public List<Task> findPooledCandidateTasks(Integer offset, Integer limit) {
		return workflowService.findPooledTasks(InCandidate.class.getName(), offset, limit);
	}

	private Map<String, Object> prepareVariable(InCandidate candidate) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(IntakeConstants.CANDIDATE_ID, candidate.getId());
		map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
		map.put(WorkflowConstants.REFERENCE_NO, candidate.getReferenceNo());
		map.put(WorkflowConstants.REMOVE_DECISION, false);
		map.put(WorkflowConstants.CANCEL_DECISION, false);
		LOG.debug("Candidate_id:{}", candidate.getId());
		return map;

	}
}
