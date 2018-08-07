package my.edu.umk.pams.intake.web.module.application.controller;

import my.edu.umk.pams.intake.admission.dao.InCandidateDao;
import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.admission.model.InCandidateImpl;
import my.edu.umk.pams.intake.admission.model.InCandidateStatus;
import my.edu.umk.pams.intake.admission.service.AdmissionService;
import my.edu.umk.pams.intake.application.model.*;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InPromoCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.core.InFlowState;
import my.edu.umk.pams.intake.identity.model.InActor;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InApplicantImpl;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.model.InUserImpl;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InStudyModeOffering;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.integration.InAutoLoginToken;
import my.edu.umk.pams.intake.security.service.SecurityService;
import my.edu.umk.pams.intake.web.module.application.vo.*;
import my.edu.umk.pams.intake.web.module.common.controller.CommonTransformer;
import my.edu.umk.pams.intake.web.module.policy.controller.PolicyTransformer;
import my.edu.umk.pams.intake.web.module.policy.vo.Intake;
import my.edu.umk.pams.intake.web.module.policy.vo.ProgramOffering;
import my.edu.umk.pams.intake.web.module.policy.vo.StudyModeOffering;
import my.edu.umk.pams.intake.web.module.policy.vo.SupervisorOffering;
import my.edu.umk.pams.intake.web.module.registration.vo.UserRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.faces.application.Application;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private PolicyTransformer policyTransformer;

	@Autowired
	private ApplicationTransformer applicationTransformer;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AdmissionService admissionService;

	@Autowired
	private InCandidateDao candidateDao;

	@Autowired
	private CommonTransformer commonTransformer;

	// ====================================================================================================
	// INTAKE
	// ====================================================================================================

	@RequestMapping(value = "/intakes", method = RequestMethod.GET)
	public ResponseEntity<List<Intake>> findIntakes() {
		List<InIntake> intakes = policyService.findIntakes();
		return new ResponseEntity<List<Intake>>(policyTransformer.toIntakeVos(intakes), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/state/{state}", method = RequestMethod.GET)
	public ResponseEntity<List<Intake>> findIntakesByState(@PathVariable String state) {
		List<InIntake> intakes = policyService.findIntakesByFlowState(InFlowState.valueOf(state));
		return new ResponseEntity<List<Intake>>(policyTransformer.toIntakeVos(intakes), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/{referenceNo}", method = RequestMethod.GET)
	public ResponseEntity<Intake> findIntakeByReferenceNo(@PathVariable String referenceNo) {
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		return new ResponseEntity<Intake>(policyTransformer.toIntakeVo(intake), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/candidates/{referenceNo}", method = RequestMethod.GET)
	public ResponseEntity<Intake> findIntakeByCandidateReferenceNo(@PathVariable String referenceNo) {
		InCandidate candidate = admissionService.findCandidateByReferenceNo(referenceNo);

		InIntake intake = policyService.findIntakeByReferenceNo(candidate.getIntake().getReferenceNo());
		return new ResponseEntity<Intake>(policyTransformer.toIntakeVo(intake), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/{referenceNo}/apply", method = RequestMethod.POST)
	public ResponseEntity<String> applyIntake(@PathVariable String referenceNo) {
		// user & applicant
		InUser currentUser = securityService.getCurrentUser();
		InActor actor = currentUser.getActor();
		InApplicant applicant = identityService.findApplicantById(actor.getId());
		if (actor instanceof InApplicant)
			applicant = (InApplicant) actor;

		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		if (applicationService.isIntakeApplicationExists(intake, applicant)) {
			throw new IllegalArgumentException("You have already applied for an intake in this year");
		} else {
			InIntakeApplication application = new InIntakeApplicationImpl();
			application.setName(applicant.getName());
			application.setEmail(applicant.getEmail());
			application.setCredentialNo(applicant.getIdentityNo());
			application.setApplicant(applicant);

			String intakeApplicationReferenceNo = null;
			try {
				intakeApplicationReferenceNo = applicationService.applyIntake(intake, application);
				LOG.debug("application referenceNo: " + intakeApplicationReferenceNo);
			} catch (Exception e) {
				throw new IllegalArgumentException("Error in applying intake");
			}
			return new ResponseEntity<String>(intakeApplicationReferenceNo, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/intakes/{referenceNo}/intakeApplications", method = RequestMethod.GET)
	public ResponseEntity<List<IntakeApplication>> findIntakeApplicationsByIntake(@PathVariable String referenceNo) {
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		List<InIntakeApplication> applications = applicationService.findIntakeApplications(intake);
		return new ResponseEntity<List<IntakeApplication>>(applicationTransformer.toIntakeApplicationVos(applications),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/{referenceNo}/intakeApplications/bidStatus/{bidStatus}", method = RequestMethod.GET)
	public ResponseEntity<List<IntakeApplication>> findIntakeApplicationsByIntakeAndBidStatus(
			@PathVariable String referenceNo, @PathVariable String bidStatus) {
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		InBidStatus status = InBidStatus.valueOf(bidStatus);
		List<InIntakeApplication> applications = applicationService.findIntakeApplicationsOrderedByMerit(intake,
				status);
		// List<InIntakeApplication> applications =
		// applicationService.findIntakeApplications(intake, status);
		return new ResponseEntity<List<IntakeApplication>>(applicationTransformer.toIntakeApplicationVos(applications),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/{referenceNo}/intakeApplications/bidStatus/{bidStatus}/verify/false", method = RequestMethod.GET)
	public ResponseEntity<List<IntakeApplication>> findVerifyInternationalIntakeApplicationsByIntakeAndBidStatus(
			@PathVariable String referenceNo, @PathVariable String bidStatus) {
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		InBidStatus status = InBidStatus.valueOf(bidStatus);
		boolean verify = false;
		List<InIntakeApplication> applications = applicationService.findIntakeApplicationsByVerificationStatus(intake,
				status, verify);
		return new ResponseEntity<List<IntakeApplication>>(applicationTransformer.toIntakeApplicationVos(applications),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/{referenceNo}/programOfferings", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramOffering>> findProgramOfferings(@PathVariable String referenceNo) {
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		List<InProgramOffering> programOfferings = policyService.findProgramOfferings(intake);
		return new ResponseEntity<List<ProgramOffering>>(policyTransformer.toProgramOfferingVos(programOfferings),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/{referenceNo}/supervisorOfferings", method = RequestMethod.GET)
	public ResponseEntity<List<SupervisorOffering>> findSupervisorOfferings(@PathVariable String referenceNo) {
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		List<InSupervisorOffering> supervisorOfferings = policyService.findSupervisorOfferings(intake);
		return new ResponseEntity<List<SupervisorOffering>>(
				policyTransformer.toSupervisorOfferingVos(supervisorOfferings), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakes/{referenceNo}/studyModeOfferings", method = RequestMethod.GET)
	public ResponseEntity<List<StudyModeOffering>> findStudyModeOfferings(@PathVariable String referenceNo) {
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
		List<InStudyModeOffering> studyModeOfferings = policyService.findStudyModeOfferings(intake);
		return new ResponseEntity<List<StudyModeOffering>>(policyTransformer.toStudyModeOfferingVos(studyModeOfferings),
				HttpStatus.OK);
	}

	// ====================================================================================================
	// INTAKE APPLICATION
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplication/{referenceNo}", method = RequestMethod.GET)
	public ResponseEntity<IntakeApplication> findIntakeApplicationByReferenceNo(@PathVariable String referenceNo) {
		InIntakeApplication intakeApplication = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		LOG.debug("candidate intake ref {} ref NO ", intakeApplication.getReferenceNo());
		return new ResponseEntity<IntakeApplication>(applicationTransformer.toIntakeApplicationVo(intakeApplication),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplication/candidate/{referenceNo}", method = RequestMethod.GET)
	public ResponseEntity<IntakeApplication> findIntakeApplicationByCandidate(@PathVariable String referenceNo) {
		InCandidate candidate = admissionService.findCandidateByReferenceNo(referenceNo);
		LOG.debug("candidate {} ref NO ", referenceNo);
		LOG.debug("candidate {} ref NO ", candidate.getApplication().getReferenceNo());
		InIntakeApplication intakeApplication = applicationService
				.findIntakeApplicationByReferenceNo(candidate.getApplication().getReferenceNo());
		return new ResponseEntity<IntakeApplication>(applicationTransformer.toIntakeApplicationVo(intakeApplication),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateIntakeApplication(@PathVariable String referenceNo,
			@RequestBody IntakeApplication vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		application.setResearchTitle(vo.getResearchTitle());
		application.setName(vo.getName());
		application.setPhone(vo.getPhone());
		application.setMobile(vo.getMobile());
		application.setFax(vo.getFax());
		application.setEmail(vo.getEmail());
		application.setAge(vo.getAge());
		application.setMerit(vo.getMerit());
		application.setPassportExpDate(vo.getPassExpDate());
		application.setCredentialNo(vo.getCredentialNo());
		application.setBirthDate(vo.getBirthDate());
		application.setSelfSponsored(vo.getSelfSponsored());
		application.setSponsored(vo.getSponsored());
		application.setPaid(vo.getProcessingFeeAttached());
		application.setDeclared(vo.getDeclared());
		application.setPlaceOfBirth(vo.getPlaceOfBirth());
		
		// guardian
		application.setGuardianName(vo.getGuardianName());
		application.setGuardianIdentityNo(vo.getGuardianIdentityNo());
		application.setGuardianPhoneNo(vo.getGuardianPhoneNo());
		application.setGuardianAddress1(vo.getGuardianAddress1());
		application.setGuardianAddress2(vo.getGuardianAddress2());
		application.setGuardianAddress3(vo.getGuardianAddress3());
		application.setGuardianType(InGuardianType.get(vo.getGuardianType().ordinal()));
		application.setGuardianPostcode(vo.getGuardianPostcode());
		if (null != vo.getGuardianState())
			application.setGuardianState(commonService.findStateCodeById(vo.getGuardianState().getId()));
		if (null != vo.getGuardianCountryCode())
			application.setGuardianCountryCode(commonService.findCountryCodeById(vo.getGuardianCountryCode().getId()));
		

		// employment
		application.setPosition(vo.getPosition());
		application.setIncome(vo.getIncome());
		application.setEmployerAddress1(vo.getEmployerAddress1());
		application.setEmployerAddress2(vo.getEmployerAddress2());
		application.setEmployerAddress3(vo.getEmployerAddress3());
		application.setEmployerNo(vo.getEmployerNo());
		
		application.setEmployerPostcode(vo.getEmployerPostcode());
		if (null != vo.getEmployerState())
			application.setEmployerState(commonService.findStateCodeById(vo.getEmployerState().getId()));
		if (null != vo.getEmploymentSectorCode())
			application.setEmploymentSectorCode(
					commonService.findEmploymentSectorCodeById(vo.getEmploymentSectorCode().getId()));
		if (null != vo.getEmploymentTypeCode())
			application.setEmploymentTypeCode(
					commonService.findEmploymentTypeCodeById(vo.getEmploymentTypeCode().getId()));

		// check list
		application.setSpmResultAttached(vo.getSpmResultAttached());
		application.setStpmResultAttached(vo.getStpmResultAttached());
		application.setDiplomaResultAttached(vo.getDiplomaResultAttached());
		application.setBachelorResultAttached(vo.getBachelorResultAttached());
		application.setMasterResultAttached(vo.getMasterResultAttached());
		application.setPhdResultAttached(vo.getPhdResultAttached());
		application.setToeflResultAttached(vo.getToeflResultAttached());
		application.setIeltsResultAttached(vo.getIeltsResultAttached());
		application.setMuetResultAttached(vo.getMuetResultAttached());
		application.setStamResultAttached(vo.getStamResultAttached());
		application.setLanguageResultAttached(vo.getLanguageResultAttached());
		application.setProcessingFeeAttached(vo.getProcessingFeeAttached());
		application.setBankStatementAttached(vo.getBankStatementAttached());
		application.setRefereeFormAttached(vo.getRefereeFormAttached());
		application.setResearchProposalAttached(vo.getResearchProposalAttached());
		application.setSponsorLetterAttached(vo.getSponsorLetterAttached());
		application.setIcCopyAttached(vo.getIcCopyAttached());
		application.setPassportCopyAttached(vo.getPassportCopyAttached());
		application.setPassportImageAttached(vo.getPassportImageAttached());
		application.setApelCertificateAttached(vo.getApelCertificateAttached());
		application.setEmploymentVerificationAttached(vo.getEmploymentVerificationAttached());

		// mailing address
		application.setMailingAddress1(vo.getMailingAddress1());
		application.setMailingAddress2(vo.getMailingAddress2());
		application.setMailingAddress3(vo.getMailingAddress3());
		application.setMailingPostcode(vo.getMailingPostcode());
		if (null != vo.getMailingStateCode())
			application.setMailingStateCode(commonService.findStateCodeById(vo.getMailingStateCode().getId()));
		if (null != vo.getMailingCountryCode())
			application.setMailingCountryCode(commonService.findCountryCodeById(vo.getMailingCountryCode().getId()));

		// official address
		application.setOfficialAddress1(vo.getOfficialAddress1());
		application.setOfficialAddress2(vo.getOfficialAddress2());
		application.setOfficialAddress3(vo.getOfficialAddress3());
		application.setOfficialPostcode(vo.getOfficialPostcode());
		if (null != vo.getOfficialStateCode())
			application.setOfficialStateCode(commonService.findStateCodeById(vo.getOfficialStateCode().getId()));
		if (null != vo.getOfficialCountryCode())
			application.setOfficialCountryCode(commonService.findCountryCodeById(vo.getOfficialCountryCode().getId()));

		// one to many
		if (null != vo.getGenderCode())
			application.setGenderCode(commonService.findGenderCodeById(vo.getGenderCode().getId()));
		if (null != vo.getReligionCode())
			application.setReligionCode(commonService.findReligionCodeById(vo.getReligionCode().getId()));
		if (null != vo.getEthnicityCode())
			application.setEthnicityCode(commonService.findEthnicityCodeById(vo.getEthnicityCode().getId()));
		if (null != vo.getMaritalCode())
			application.setMaritalCode(commonService.findMaritalCodeById(vo.getMaritalCode().getId()));
		if (null != vo.getRaceCode())
			application.setRaceCode(commonService.findRaceCodeById(vo.getRaceCode().getId()));
		if (null != vo.getNationalityCode())
			application.setNationalityCode(commonService.findNationalityCodeById(vo.getNationalityCode().getId()));
		if (null != vo.getStudyCenterCode())
			application.setStudyCenterCode(commonService.findStudyCenterCodeById(vo.getStudyCenterCode().getId()));
		if (null != vo.getResidencyCode())
			application.setResidencyCode(commonService.findResidencyCodeById(vo.getResidencyCode().getId()));
		applicationService.updateIntakeApplication(application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/copy", method = RequestMethod.POST)
	public ResponseEntity<String> copyAddressApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		application.setCopiedAddress(true);
		applicationService.copyAddressApplication(application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/submit", method = RequestMethod.POST)
	public ResponseEntity<String> submitIntakeApplication(@PathVariable String referenceNo,
			@RequestBody IntakeApplication vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InIntake intake = application.getIntake();
		updateIntakeApplication(referenceNo, vo);
		applicationService.submitIntakeApplication(intake, application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/promoCode/{promoCode}", method = RequestMethod.PUT)
	public ResponseEntity<String> promoCodeIntakeApplication(@PathVariable String referenceNo,
			@PathVariable String promoCode, @RequestBody IntakeApplication vo) {
		LOG.debug(" vo.getPromoCode().getCode() : " + vo.getReferenceNo());
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InPromoCode getPromoCode = commonService.findPromoCodeByCode(promoCode);

		if (getPromoCode == null) {
			throw new IllegalArgumentException("Your Promo Code Does Not Exists");

		} else {
			if (applicationService.isPromoCodeEntered(getPromoCode)) {
				throw new IllegalArgumentException("Your Promo Code Is Invalid");
			} else {
				application.setPromoCode(getPromoCode);
				applicationService.updateIntakeApplication(application);
			}
		}

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/select", method = RequestMethod.PUT)
	public ResponseEntity<String> selectIntakeApplication(@PathVariable String referenceNo,
			@RequestBody IntakeApplication vo) {
		System.out.println("vo.getReason() select");
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InIntake intake = application.getIntake();
		application.setBidStatus(InBidStatus.SELECTED);
		if (vo.getResidencyCode().getCode().equals("B") || vo.getResidencyCode().getCode().equals("C")) {
			application.setVerified(true);
		}

			InCandidate candidate = new InCandidateImpl();
			candidate.setSourceNo(UUID.randomUUID().toString());
			candidate.setAuditNo(UUID.randomUUID().toString());
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
			candidate.setAuditNo(application.getIntake().getAuditNo() + application.getApplicant().getIdentityNo());
			candidate.setReferenceNo(application.getIntake().getReferenceNo());
			candidate.setCancelComment(application.getIntake().getCancelComment());
			candidate.setSourceNo(application.getIntake().getSourceNo());
			candidate.setDescriptionEn(application.getIntake().getDescriptionEn());
			candidate.setDescriptionMs(application.getIntake().getDescriptionMs());
			candidateDao.save(candidate, securityService.getCurrentUser());

			admissionService.startCandidateTask(candidate);

			applicationService.selectIntakeApplication(intake, application);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/verify", method = RequestMethod.PUT)
	public ResponseEntity<String> verifyIntakeApplication(@PathVariable String referenceNo,
			@RequestBody IntakeApplication vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InIntake intake = application.getIntake();
		application.setVerified(true);
		applicationService.selectIntakeApplication(intake, application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/reject", method = RequestMethod.PUT)
	public ResponseEntity<String> rejectIntakeApplication(@PathVariable String referenceNo,
			@RequestBody IntakeApplication vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		application.setReason(vo.getReason());
		System.out.println("vo.getReason() reject:" + vo.getReason());
		application.setBidStatus(InBidStatus.REJECTED);
		InIntake intake = application.getIntake();
		applicationService.rejectIntakeApplication(intake, application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/programOfferingSelection", method = RequestMethod.POST)
	public ResponseEntity<String> submitIntakeApplication(@PathVariable String referenceNo,
			@RequestBody ProgramOffering vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InProgramOffering programOffering = policyService.findProgramOfferingById(vo.getId());
		application.setProgramSelection(programOffering);
		applicationService.updateIntakeApplication(application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/programLevel/{levelCode}/supervisorOfferings", method = RequestMethod.GET)
	public ResponseEntity<List<SupervisorOffering>> findSupervisorOfferingsByProgramLevel(
			@PathVariable String levelCode) {
		InProgramLevel inProgramLevel = policyService.findProgramLevelByCode(levelCode);
		return new ResponseEntity<List<SupervisorOffering>>(
				commonTransformer.toSupervisorOfferingVos(
						commonService.findSupervisorOfferingsByProgramLevel(inProgramLevel, "%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/studyModeOfferingSelection", method = RequestMethod.POST)
	public ResponseEntity<String> submitIntakeApplication(@PathVariable String referenceNo,
			@RequestBody StudyModeOffering vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InStudyModeOffering studyModeOffering = policyService.findStudyModeOfferingById(vo.getId());
		application.setStudyModeSelection(studyModeOffering);
		applicationService.updateIntakeApplication(application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/supervisorOfferingSelection", method = RequestMethod.POST)
	public ResponseEntity<String> submitIntakeApplication(@PathVariable String referenceNo,
			@RequestBody SupervisorOffering vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InSupervisorOffering supervisorOffering = policyService.findSupervisorOfferingById(vo.getId());
		application.setSupervisorSelection(supervisorOffering);
		applicationService.updateIntakeApplication(application);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ====================================================================================================
	// EMPLOYMENTS
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplications/{referenceNo}/employments", method = RequestMethod.GET)
	public ResponseEntity<List<Employment>> findEmploymentsByIntakeApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InEmployment> employments = applicationService.findEmployments(application);
		return new ResponseEntity<List<Employment>>(applicationTransformer.toEmploymentVos(employments), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/employments", method = RequestMethod.POST)
	public ResponseEntity<String> addEmployment(@PathVariable String referenceNo, @RequestBody Employment vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InEmployment employment = new InEmploymentImpl();
		employment.setEmployer(vo.getEmployer());
		employment.setDesignation(vo.getDesignation());
		employment.setStartDate(vo.getStartDate());
		employment.setEndDate(vo.getEndDate());
		employment.setEmploymentType(InEmploymentType.get(vo.getEmploymentType().ordinal()));
		System.out.println("vo.getEmploymentType().ordinal() :" + vo.getEmploymentType().ordinal());
		if (vo.getEmploymentType().ordinal() == 0) {
			employment.setCurrent(true);
		} else {
			employment.setCurrent(false);
		}
		applicationService.addEmployment(application, employment);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/employments/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteEmployment(@PathVariable String referenceNo, @PathVariable Long id) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InEmployment employment = applicationService.findEmploymentById(id);
		applicationService.deleteEmployment(application, employment);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/employments/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateEmployment(@PathVariable String referenceNo, @PathVariable Long id,
			@RequestBody Employment vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InEmployment employment = applicationService.findEmploymentById(id);
		employment.setEmployer(vo.getEmployer());
		employment.setDesignation(vo.getDesignation());
		employment.setStartDate(vo.getStartDate());
		employment.setEndDate(vo.getEndDate());
		employment.setCurrent(false);
		applicationService.updateEmployment(application, employment);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// ====================================================================================================
	// LANGUAGES
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplications/{referenceNo}/languages", method = RequestMethod.GET)
	public ResponseEntity<List<Language>> findLanguagesByIntakeApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InLanguage> languages = applicationService.findLanguages(application);
		return new ResponseEntity<List<Language>>(applicationTransformer.toLanguageVos(languages), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/languages", method = RequestMethod.POST)
	public ResponseEntity<String> addLanguage(@PathVariable String referenceNo, @RequestBody Language vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InLanguage language = new InLanguageImpl();
		language.setOral(vo.getOral());
		language.setWritten(vo.getWritten());
		language.setLanguageCode(commonService.findLanguageCodeById(vo.getLanguageCode().getId()));
		applicationService.addLanguage(application, language);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/languages/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateLanguage(@PathVariable String referenceNo, @PathVariable Long id,
			@RequestBody Language vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InLanguage language = applicationService.findLanguageById(id);
		language.setOral(vo.getOral());
		language.setWritten(vo.getWritten());
		language.setLanguageCode(commonService.findLanguageCodeById(vo.getLanguageCode().getId()));
		applicationService.updateLanguage(application, language);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/languages/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteLanguage(@PathVariable String referenceNo, @PathVariable Long id) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InLanguage language = applicationService.findLanguageById(id);
		applicationService.deleteLanguage(application, language);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// ====================================================================================================
	// EDUCATIONS
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplications/{referenceNo}/educations", method = RequestMethod.GET)
	public ResponseEntity<List<Education>> findEducationsByIntakeApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InEducation> educations = applicationService.findEducations(application);
		return new ResponseEntity<List<Education>>(applicationTransformer.toEducationVos(educations), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/educations", method = RequestMethod.POST)
	public ResponseEntity<String> addEducation(@PathVariable String referenceNo, @RequestBody Education vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InEducation education = new InEducationImpl();
		education.setProvider(vo.getProvider());
		education.setStartDate(vo.getStartDate());
		education.setEndDate(vo.getEndDate());
		education.setCurrent(false);
		applicationService.addEducation(application, education);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// ATTACHMENTS
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplications/{referenceNo}/attachments", method = RequestMethod.GET)
	public ResponseEntity<List<Attachment>> findAttachmentsByIntakeApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InAttachment> attachments = applicationService.findAttachments(application);
		return new ResponseEntity<List<Attachment>>(applicationTransformer.toAttachmentVos(attachments), HttpStatus.OK);
	}

	// note:
	// http://www.codejava.net/coding/upload-files-to-database-with-spring-mvc-and-hibernate
	@RequestMapping(value = "/intakeApplications/{referenceNo}/attachments", method = RequestMethod.POST)
	public ResponseEntity<String> addAttachment(@PathVariable String referenceNo,
			@RequestParam("file") MultipartFile file, @RequestParam("attachmentType") String attachmentType) {
		LOG.debug("files is empty? : {}", file.isEmpty());
		LOG.debug("name: {}", file.getName());
		LOG.debug("original file name: {}", file.getOriginalFilename());
		LOG.debug("content type: {}", file.getContentType());
		LOG.debug("size: {}", file.getSize());
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		try {
			if (!file.isEmpty()) {
				InAttachment attachment = new InAttachmentImpl();
				attachment.setMimeType("application/pdf"); // todo(switch)
				attachment.setName(file.getOriginalFilename());
				attachment.setSize(file.getSize());
				attachment.setBytes(file.getBytes());
				attachment.setAttachmentType(InAttachmentType.valueOf(attachmentType));
				applicationService.addAttachment(application, attachment);
			}
		} catch (IOException e) {
			return new ResponseEntity<String>("Failed", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/addAndCheckAttachments", method = RequestMethod.POST)
	public ResponseEntity<String> addAndCheckAttachment(@PathVariable String referenceNo,
			@RequestParam("file") MultipartFile file, @RequestParam("attachmentType") String attachmentType) {
		LOG.debug("files is empty? : {}", file.isEmpty());
		LOG.debug("name: {}", file.getName());
		LOG.debug("original file name: {}", file.getOriginalFilename());
		LOG.debug("content type: {}", file.getContentType());
		LOG.debug("size: {}", file.getSize());
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);

		String mimeType = file.getContentType();
		if (mimeType.contentEquals("application/pdf")) {
			try {
				if (!file.isEmpty()) {
					InAttachment attachment = new InAttachmentImpl();
					attachment.setMimeType("application/pdf"); // todo(switch)
					attachment.setName(file.getOriginalFilename());
					attachment.setSize(file.getSize());
					attachment.setBytes(file.getBytes());
					attachment.setAttachmentType(InAttachmentType.valueOf(attachmentType));
					applicationService.addAttachment(application, attachment);
					// applicationService.checkAttachment(application,
					// attachment);
				}
			}

			catch (IOException e) {
				return new ResponseEntity<String>("Failed", HttpStatus.OK);
			}
		} else {
			LOG.debug("Error");
		}
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/attachments/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAttachment(@PathVariable String referenceNo, @PathVariable Long id) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InAttachment attachment = applicationService.findAttachmentById(id);
		applicationService.deleteAttachment(application, attachment);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/download/attachment/{id}", method = RequestMethod.GET)
	public ResponseEntity downloadAttachment(@PathVariable Long id) {
		InAttachment attachment = applicationService.findAttachmentById(id);
		ByteArrayResource resource = null;
		resource = new ByteArrayResource(attachment.getBytes());

		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + attachment.getName())
				.body(resource);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/attachment/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> checkAttachment(@PathVariable String referenceNo,
			@RequestParam("file") MultipartFile file, @RequestParam("attachmentType") String attachmentType) {

		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InAttachment> attachments = applicationService.findAttachments(application);
		for (InAttachment attachment : attachments) {
			applicationService.checkAttachment(application, attachment);
		}
		return new ResponseEntity<String>("Success", HttpStatus.OK);

	}

	// ====================================================================================================
	// REFEREES
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplications/{referenceNo}/referees", method = RequestMethod.GET)
	public ResponseEntity<List<Referee>> findRefereesByIntakeApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InReferee> referees = applicationService.findReferees(application);
		return new ResponseEntity<List<Referee>>(applicationTransformer.toRefereeVos(referees), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/referees", method = RequestMethod.POST)
	public ResponseEntity<String> addReferee(@PathVariable String referenceNo, @RequestBody Referee vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InReferee referee = new InRefereeImpl();
		referee.setName(vo.getName());
		referee.setOfficeAddrs(vo.getOfficeAddrs());
		referee.setOccupation(vo.getOccupation());
		referee.setPhoneNo(vo.getPhoneNo());
		referee.setType(InRefereeType.get(vo.getRefereeType().ordinal()));
		applicationService.addReferee(application, referee);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/referees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateReferee(@PathVariable String referenceNo, @PathVariable Long id,
			@RequestBody Referee vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InReferee referee = applicationService.findRefereeById(id);
		referee.setName(vo.getName());
		referee.setOfficeAddrs(vo.getOfficeAddrs());
		referee.setOccupation(vo.getOccupation());
		referee.setPhoneNo(vo.getPhoneNo());
		referee.setType(InRefereeType.get(vo.getRefereeType().ordinal()));
		applicationService.updateReferee(application, referee);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/referees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteReferee(@PathVariable String referenceNo, @PathVariable Long id) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InReferee referee = applicationService.findRefereeById(id);
		applicationService.deleteReferee(application, referee);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// ====================================================================================================
	// RESULT
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplications/{referenceNo}/results", method = RequestMethod.GET)
	public ResponseEntity<List<Result>> findResultsByIntakeApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InResult> results = applicationService.findResults(application);
		return new ResponseEntity<List<Result>>(applicationTransformer.toResultVos(results), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/results", method = RequestMethod.POST)
	public ResponseEntity<String> addResult(@PathVariable String referenceNo, @RequestBody Result vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InResult result = new InResultImpl();
		result.setResultType(InResultType.get(vo.getResultType().ordinal()));
		result.setField(vo.getField());
		result.setName(vo.getName());
		result.setGraduationYear(vo.getGraduationYear());
		result.setMalayResult(vo.getMalayResult());
		result.setEnglishResult(vo.getEnglishResult());
		result.setResultAlphanumeric(vo.getResultAlphanumeric());
		result.setResultNumeric(vo.getResultNumeric());
		applicationService.addResult(application, result);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/results/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateResult(@PathVariable String referenceNo, @PathVariable Long id,
			@RequestBody Result vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InResult result = applicationService.findResultById(id);
		result.setResultType(InResultType.get(vo.getResultType().ordinal()));
		result.setField(vo.getField());
		result.setName(vo.getName());
		result.setGraduationYear(vo.getGraduationYear());
		result.setMalayResult(vo.getMalayResult());
		result.setEnglishResult(vo.getEnglishResult());
		result.setResultAlphanumeric(vo.getResultAlphanumeric());
		result.setResultNumeric(vo.getResultNumeric());
		applicationService.updateResult(application, result);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/results/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteResult(@PathVariable String referenceNo, @PathVariable Long id) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InResult result = applicationService.findResultById(id);
		applicationService.deleteResult(application, result);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// ====================================================================================================
	// CANDIDATE
	// ====================================================================================================
	@RequestMapping(value = "/intakeApplications/{referenceNo}/processCandidate", method = RequestMethod.POST)
	public ResponseEntity<IntakeApplication> processCandidate(@PathVariable String referenceNo) {
		InUser currentUser = securityService.getCurrentUser();
		InActor actor = currentUser.getActor();
		InApplicant applicant = identityService.findApplicantById(actor.getId());
		if (actor instanceof InApplicant)
			applicant = (InApplicant) actor;

		InIntakeApplication generatedApplication = null;
		InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);

		try {
			InIntakeApplication application = new InIntakeApplicationImpl();
			application.setName(applicant.getName());
			application.setEmail(applicant.getEmail());
			application.setApplicant(applicant);
			String intakeApplicationReferenceNo = applicationService.applyIntake(intake, application);
			LOG.debug("application referenceNo: " + intakeApplicationReferenceNo);
			generatedApplication = applicationService.findIntakeApplicationByReferenceNo(intakeApplicationReferenceNo);
		} catch (Exception e) {
			return new ResponseEntity<IntakeApplication>(new IntakeApplication(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<IntakeApplication>(applicationTransformer.toIntakeApplicationVo(generatedApplication),
				HttpStatus.OK);
	}

	// ====================================================================================================
	// GUARDIANS
	// ====================================================================================================

	@RequestMapping(value = "/intakeApplications/{referenceNo}/guardians", method = RequestMethod.GET)
	public ResponseEntity<List<Guardian>> findGuardiansByIntakeApplication(@PathVariable String referenceNo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		List<InGuardian> guardians = applicationService.findGuardians(application);
		return new ResponseEntity<List<Guardian>>(applicationTransformer.toGuardianVos(guardians), HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/guardians", method = RequestMethod.POST)
	public ResponseEntity<String> addGuardian(@PathVariable String referenceNo, @RequestBody Guardian vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InGuardian guardian = new InGuardianImpl();
		guardian.setName(vo.getName());
		guardian.setSalary(vo.getSalary());
		guardian.setGuardianAddress1(vo.getGuardianAddress1());
		guardian.setGuardianAddress2(vo.getGuardianAddress2());
		guardian.setGuardianAddress3(vo.getGuardianAddress3());
		guardian.setGuardianPostcode(vo.getGuardianPostcode());
		guardian.setGuardianNo(vo.getGuardianNo());
		guardian.setType(InGuardianType.get(vo.getGuardianType().ordinal()));
		applicationService.addGuardian(application, guardian);

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/guardians/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteGuardian(@PathVariable String referenceNo, @PathVariable Long id) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InGuardian guardian = applicationService.findGuardianById(id);
		applicationService.deleteGuardian(application, guardian);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/intakeApplications/{referenceNo}/guardians/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateGuardian(@PathVariable String referenceNo, @PathVariable Long id,
			@RequestBody Guardian vo) {
		InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo);
		InGuardian guardian = applicationService.findGuardianById(id);
		guardian.setName(vo.getName());
		guardian.setSalary(vo.getSalary());
		guardian.setGuardianAddress1(vo.getGuardianAddress1());
		guardian.setGuardianAddress2(vo.getGuardianAddress2());
		guardian.setGuardianAddress3(vo.getGuardianAddress3());
		guardian.setGuardianPostcode(vo.getGuardianPostcode());
		guardian.setGuardianNo(vo.getGuardianNo());
		guardian.setType(InGuardianType.get(vo.getGuardianType().ordinal()));
		applicationService.updateGuardian(application, guardian);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// ====================================================================================================
	// PRIVATE METHODS
	// ====================================================================================================
}
