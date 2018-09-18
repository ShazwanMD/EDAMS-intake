package my.edu.umk.pams.intake.application.service;

import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import my.edu.umk.pams.intake.IntakeConstants;
import my.edu.umk.pams.intake.application.dao.InIntakeApplicationDao;
import my.edu.umk.pams.intake.application.model.InAttachment;
import my.edu.umk.pams.intake.application.model.InAttachmentType;
import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InBidType;
import my.edu.umk.pams.intake.application.model.InContact;
import my.edu.umk.pams.intake.application.model.InContactType;
import my.edu.umk.pams.intake.application.model.InEducation;
import my.edu.umk.pams.intake.application.model.InEmployment;
import my.edu.umk.pams.intake.application.model.InEmploymentType;
import my.edu.umk.pams.intake.application.model.InGuarantor;
import my.edu.umk.pams.intake.application.model.InGuarantorType;
import my.edu.umk.pams.intake.application.model.InGuardian;
import my.edu.umk.pams.intake.application.model.InGuardianType;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.model.InInvolvement;
import my.edu.umk.pams.intake.application.model.InLanguage;
import my.edu.umk.pams.intake.application.model.InReferee;
import my.edu.umk.pams.intake.application.model.InResult;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.common.model.InPromoCode;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.integration.InAutoLoginToken;
import my.edu.umk.pams.intake.security.integration.NonSerializableSecurityContext;
import my.edu.umk.pams.intake.security.service.SecurityService;
import my.edu.umk.pams.intake.system.model.InEmailQueue;
import my.edu.umk.pams.intake.system.model.InEmailQueueImpl;
import my.edu.umk.pams.intake.system.model.InEmailQueueStatus;
import my.edu.umk.pams.intake.system.service.SystemService;

/**
 * @author PAMS
 */
@Transactional
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	@Autowired
	private InIntakeApplicationDao intakeApplicationDao;
	
	@Autowired
	private PolicyService policyService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SystemService systemService;
	
    @Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
	private SessionFactory sessionFactory;
	public static final BigDecimal MERIT_FACTOR = new BigDecimal(0.05);

	@Override
	public void calculateApplicantMerit(InIntake intake) {
		List<InIntakeApplication> applications = findIntakeApplications(intake);
		for (InIntakeApplication application : applications) {
			BigDecimal merit = BigDecimal.ZERO;
			BigDecimal merit1 = BigDecimal.ZERO;
			BigDecimal resultBac = BigDecimal.ZERO;

			List<InEmployment> employments = application.getEmployments();
			if (application.getEmployments().isEmpty()) {

				List<InResult> results = application.getResults();
				LOG.debug("results {}", results);

				for (InResult result : results) {
					if ((result.getResultType() == InResultType.BACHELOR)
							|| (result.getResultType() == InResultType.BACHELOR_EQUIVALENT)) {
						resultBac = result.getResultNumeric();
						merit = resultBac;
					}
					LOG.debug("result2: {}", resultBac);
					LOG.debug("merit2: {}", merit);
				}
			} else {
				for (InEmployment employment : employments) {
					LOG.debug("employment: {}", employment.getEmployer());
					
					// if current, we don't have end date
					if (!employment.isCurrent() == true) {

						LocalDate start = LocalDate.fromDateFields(employment.getStartDate());
						LocalDate end = LocalDate.fromDateFields(employment.getEndDate());
						Period period = new Period(start, end);
						merit1 = merit1.add(BigDecimal.valueOf(period.getYears()));
						merit = merit1.multiply(MERIT_FACTOR);

						List<InResult> results = application.getResults();
						for (InResult result : results) {
							if ((result.getResultType() == InResultType.BACHELOR)
									|| (result.getResultType() == InResultType.BACHELOR_EQUIVALENT)) {
								resultBac = result.getResultNumeric();
								merit = merit.add(resultBac);
							}
						}
					}
					// if current, we have end date
					else if (!employment.isCurrent() == false) {
						LocalDate start = LocalDate.fromDateFields(employment.getStartDate());
						LocalDate end = LocalDate.fromDateFields(new Date());
						Period period = new Period(start, end);
						merit1 = merit1.add(BigDecimal.valueOf(period.getYears()));
						merit = merit1.multiply(MERIT_FACTOR);

						List<InResult> results = application.getResults();
						for (InResult result : results) {
							if ((result.getResultType() == InResultType.BACHELOR)
									|| (result.getResultType() == InResultType.BACHELOR_EQUIVALENT)) {
								resultBac = result.getResultNumeric();
								merit = merit.add(resultBac);
							}
							LOG.debug("result: {}", resultBac);
							LOG.debug("merit: {}", merit);
						}
					}
				}
			}
			application.setMerit(merit);
			updateIntakeApplication(application);
		}

	}
	

	@Override
	public String applyIntake(InIntake intake, InIntakeApplication application) throws Exception {
		if (hasApplied(intake, application.getApplicant()))
			throw new Exception("Applicant has applied");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intakeSession", intake.getSession());
		map.put("programLevel", intake.getProgramLevel());
		String generatedReferenceNo = systemService
				.generateFormattedReferenceNo(IntakeConstants.INTAKE_APPLICATION_REFERENCE_NO, map);

		application.setReferenceNo(generatedReferenceNo);
		application.setBidStatus(InBidStatus.DRAFTED);
		application.setIntake(intake);
		intakeApplicationDao.save(application, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		return generatedReferenceNo;
	}

	@Override
	public void submitIntakeApplication(InIntake intake, InIntakeApplication application) {
		LOG.debug("intake: {}", intake.getReferenceNo());
		LOG.debug("intake application: {}", application.getReferenceNo());
		application.setBidStatus(InBidStatus.SUBMITTED);
		updateIntakeApplication(application);
	}

	@Override
	public void withdrawIntakeApplication(InIntake intake, InIntakeApplication application) {
		LOG.debug("intake: {}", intake.getReferenceNo());
		LOG.debug("intake application: {}", application.getReferenceNo());
		application.setBidStatus(InBidStatus.WITHDRAW);
		updateIntakeApplication(application);
	}

	@Override
	public void draftedIntakeApplication(InIntake intake, InIntakeApplication application) {
		LOG.debug("intake: {}", intake.getReferenceNo());
		LOG.debug("intake application: {}", application.getReferenceNo());
		application.setBidStatus(InBidStatus.DRAFTED);
		updateIntakeApplication(application);
	}

	@Override
	public void rejectIntakeApplication(InIntake intake, InIntakeApplication application) {
		LOG.debug("intake: {}", intake.getReferenceNo());
		LOG.debug("intake application: {}", application);
		updateIntakeApplication(application);
	}

	@Override
	public void selectIntakeApplication(InIntake intake, InIntakeApplication application) {
		LOG.debug("intake: {}", intake.getReferenceNo());
		LOG.debug("intake application: {}", application.getReferenceNo());
		updateIntakeApplication(application);
	}

	@Override
	public void verifyInternationalApplications(InIntake intake, InIntakeApplication application) {
		application.setVerified(true);
		updateIntakeApplication(application);
	}

	@Override
	public void addResult(InIntakeApplication application, InResult result) {
		intakeApplicationDao.addResult(application, result, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateResult(InIntakeApplication application, InResult result) {
		intakeApplicationDao.updateResult(application, result, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteResult(InIntakeApplication application, InResult result) {
		intakeApplicationDao.deleteResult(application, result, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateIntakeApplication(InIntakeApplication application) {
		intakeApplicationDao.update(application, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addEducation(InIntakeApplication application, InEducation education) {
		intakeApplicationDao.addEducation(application, education, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addEmployment(InIntakeApplication application, InEmployment employment) {
		LOG.debug("employment.getCurrent :" + employment.isCurrent());
		
		intakeApplicationDao.addEmployment(application, employment, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	
	@Override
	public void addLanguage(InIntakeApplication application, InLanguage language) {
		intakeApplicationDao.addLanguage(application, language, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addInvolvement(InIntakeApplication application, InInvolvement involvement) {
		intakeApplicationDao.addInvolvement(application, involvement, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteLanguage(InIntakeApplication application, InLanguage language) {
		intakeApplicationDao.deleteLanguage(application, language, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateLanguage(InIntakeApplication application, InLanguage language) {
		intakeApplicationDao.updateLanguage(application, language, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteEmployment(InIntakeApplication application, InEmployment employment) {
		intakeApplicationDao.deleteEmployment(application, employment, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateEmployment(InIntakeApplication application, InEmployment employment) {
		intakeApplicationDao.updateEmployment(application, employment, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteReferee(InIntakeApplication application, InReferee referee) {
		intakeApplicationDao.deleteReferee(application, referee, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateReferee(InIntakeApplication application, InReferee referee) {
		intakeApplicationDao.updateReferee(application, referee, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addContact(InIntakeApplication application, InContact contact) {
		intakeApplicationDao.addContact(application, contact, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteContact(InIntakeApplication application, InContact contact) {
		intakeApplicationDao.deleteContact(application, contact, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addGuarantor(InIntakeApplication application, InGuarantor guarantor) {
		intakeApplicationDao.addGuarantor(application, guarantor, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteGuarantor(InIntakeApplication application, InGuarantor guarantor) {
		intakeApplicationDao.deleteGuarantor(application, guarantor, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addGuardian(InIntakeApplication application, InGuardian guardian) {
		intakeApplicationDao.addGuardian(application, guardian, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteGuardian(InIntakeApplication application, InGuardian guardian) {
		intakeApplicationDao.deleteGuardian(application, guardian, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void updateGuardian(InIntakeApplication application, InGuardian guardian) {
		intakeApplicationDao.updateGuardian(application, guardian, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addReferee(InIntakeApplication application, InReferee referee) {
		intakeApplicationDao.addReferee(application, referee, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addAttachment(InIntakeApplication application, InAttachment attachment) {
		this.checkAttachment(application, attachment);
		intakeApplicationDao.addAttachment(application, attachment, securityService.getCurrentUser());
	}

	@Override
	public void deleteAttachment(InIntakeApplication application, InAttachment attachment) {
		this.unCheckAttachment(application, attachment);
		intakeApplicationDao.deleteAttachment(application, attachment, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public InApplicant findApplicant(InIntakeApplication application) {
		return intakeApplicationDao.findApplicant(application);
	}

	@Override
	public List<InApplicant> findApplicants(InIntake intake) {
		return intakeApplicationDao.findApplicants(intake);
	}

	@Override
	public InIntakeApplication findIntakeApplicationById(Long id) {
		return intakeApplicationDao.findById(id);
	}

	@Override
	public InGuardian findGuardianById(Long id) {
		return intakeApplicationDao.findGuardianById(id);
	}

	@Override
	public InGuarantor findGuarantorById(Long id) {
		return intakeApplicationDao.findGuarantorById(id);
	}

	@Override
	public InEmployment findEmploymentById(Long id) {
		return intakeApplicationDao.findEmploymentById(id);
	}

	@Override
	public InReferee findRefereeById(Long id) {
		return intakeApplicationDao.findRefereeById(id);
	}

	@Override
	public InLanguage findLanguageById(Long id) {
		return intakeApplicationDao.findLanguageById(id);
	}

	@Override
	public InAttachment findAttachmentById(Long id) {
		return intakeApplicationDao.findAttachmentById(id);
	}

	@Override
	public InContact findContactById(Long id) {
		return intakeApplicationDao.findContactById(id);
	}

	@Override
	public InGuardian findGuardianByType(InGuardianType guardianType, InIntakeApplication application) {
		return intakeApplicationDao.findGuardianByType(guardianType, application);
	}

	@Override
	public InGuarantor findGuarantorByType(InGuarantorType guarantorType, InIntakeApplication application) {
		return intakeApplicationDao.findGuarantorByType(guarantorType, application);
	}

	@Override
	public InContact findContactByType(InContactType contactType, InIntakeApplication application) {
		return intakeApplicationDao.findContactByType(contactType, application);
	}

	@Override
	public InIntakeApplication findIntakeApplicationByReferenceNo(String referenceNo) {
		return intakeApplicationDao.findByReferenceNo(referenceNo);
	}

	@Override
	public InIntakeApplication findIntakeApplicationByNricNoOrPassportNo(String identityNo) {
		return intakeApplicationDao.findByNricNoOrPassportNo(identityNo);
	}

	@Override
	public InIntakeApplication findIntakeApplicationByIntakeAndApplicant(InIntake intake, InApplicant applicant) {
		return intakeApplicationDao.findByIntakeAndApplicant(intake, applicant);
	}
	
	@Override
	public InIntakeApplication findIntakeApplicationByApplicant(InApplicant applicant) {
		return intakeApplicationDao.findByApplicant(applicant);
	}

	@Override
	public boolean isIntakeApplicationExists(InIntake intake, InApplicant applicant) {
		return intakeApplicationDao.isIntakeApplicationExists(intake, applicant);
	}

	@Override
	public boolean isPromoCodeEntered(InPromoCode promoCode) {
		return intakeApplicationDao.isPromoCodeEntered(promoCode);
	}

	@Override
	public InResult findResultById(Long id) {
		return intakeApplicationDao.findResultById(id);
	}

	@Override
	public InResult findResult(InIntakeApplication application, InResultType resultType) {
		return intakeApplicationDao.findResult(application, resultType);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplicationsByPaidStatus(InIntake intake, Boolean paid) {
		return intakeApplicationDao.findIntakeApplicationsByPaidStatus(intake, paid);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplicationsByVerificationStatus(InIntake intake, InBidStatus status,
			Boolean verification) {
		return intakeApplicationDao.findIntakeApplicationsByVerificationStatus(intake, status, verification);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplications(InApplicant applicant) {
		return intakeApplicationDao.find(applicant);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplications(InApplicant applicant, InBidStatus bidStatus) {
		return intakeApplicationDao.find(applicant, bidStatus);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplications(InIntake intake) {
		return intakeApplicationDao.find(intake);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplications(InIntake intake, InBidStatus status) {
		return intakeApplicationDao.find(intake, status);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplicationsByStatusVerify(InIntake intake, InBidStatus status) {
		return intakeApplicationDao.findStatusVerify(intake, status);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplications(InIntake intake, Integer offset, Integer limit) {
		return intakeApplicationDao.find(intake, offset, limit);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplications(String filter, InIntake intake, Integer offset,
			Integer limit) {
		return intakeApplicationDao.find(filter, intake, offset, limit);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplications(String filter, InBidType bidType, InIntake intake,
			Integer offset, Integer limit) {
		return intakeApplicationDao.find(filter, bidType, intake, offset, limit);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplicationsOrderedByMerit(InIntake intake,InBidStatus status) {
		return intakeApplicationDao.findByOrderedMerit(intake,status);
	}

	@Override
	public List<InIntakeApplication> findIntakeApplicationsOrderedByRank(InIntake intake) {
		return intakeApplicationDao.findByOrderedRank(intake);
	}

	@Override
	public List<InResult> findResults(InIntakeApplication application) {
		return intakeApplicationDao.findResults(application);
	}

	@Override
	public List<InEducation> findEducations(InIntakeApplication application) {
		return intakeApplicationDao.findEducations(application);
	}

	@Override
	public List<InEmployment> findEmployments(InIntakeApplication application) {
		return intakeApplicationDao.findEmployments(application);
	}

	@Override
	public List<InLanguage> findLanguages(InIntakeApplication application) {
		return intakeApplicationDao.findLanguages(application);
	}

	@Override
	public List<InAttachment> findAttachments(InIntakeApplication application) {
		return intakeApplicationDao.findAttachments(application);
	}

	@Override
	public List<InReferee> findReferees(InIntakeApplication application) {
		return intakeApplicationDao.findReferees(application);
	}

	@Override
	public List<InInvolvement> findInvolvements(InIntakeApplication application) {
		return intakeApplicationDao.findInvolvements(application);
	}

	@Override
	public List<InGuardian> findGuardians(InIntakeApplication application) {
		return intakeApplicationDao.findGuardians(application);
	}

	@Override
	public List<InGuarantor> findGuarantors(InIntakeApplication application) {
		return intakeApplicationDao.findGuarantors(application);
	}

	@Override
	public List<InContact> findContacts(InIntakeApplication application) {
		return intakeApplicationDao.findContacts(application);
	}

	@Override
	public List<InProgramOffering> findProgramOfferings(InIntakeApplication application) {
		return policyService.findProgramOfferings(application.getIntake());
	}

	@Override
	public Integer countIntakeApplication(InIntake intake) {
		return intakeApplicationDao.count(intake);
	}

	@Override
	public Integer countIntakeApplication(String filter, InIntake intake) {
		return intakeApplicationDao.count(filter, intake);
	}

	@Override
	public Integer countIntakeApplication(String filter, InBidType bidType, InIntake intake) {
		return intakeApplicationDao.count(filter, bidType, intake);
	}

	@Override
	public boolean hasEducation(InIntakeApplication application) {
		return intakeApplicationDao.hasEducation(application);
	}

	@Override
	public boolean hasEmployment(InIntakeApplication application) {
		return intakeApplicationDao.hasEmployment(application);
	}

	@Override
	public boolean hasLanguage(InIntakeApplication application) {
		return intakeApplicationDao.hasLanguage(application);
	}

	@Override
	public boolean hasReferee(InIntakeApplication application) {
		return intakeApplicationDao.hasReferee(application);
	}

	@Override
	public boolean hasApplied(InIntake intake, InApplicant applicant) {
		return intakeApplicationDao.hasApplied(intake, applicant);
	}

	@Override
	public boolean hasInvolvement(InIntakeApplication application) {
		return intakeApplicationDao.hasInvolvement(application);
	}

	@Override
	public boolean hasResult(InIntakeApplication application, InResultType resultType) {
		return intakeApplicationDao.hasResult(application, resultType);
	}

	@Override
	public void copyAddressApplication(InIntakeApplication application) {

		InIntakeApplication officialAddress = new InIntakeApplicationImpl();
		// update new official address

		officialAddress.setOfficialAddress1(application.getOfficialAddress1());
		officialAddress.setOfficialAddress2(application.getOfficialAddress2());
		officialAddress.setOfficialAddress3(application.getOfficialAddress3());
		officialAddress.setOfficialPostcode(application.getOfficialPostcode());
		officialAddress.setOfficialStateCode(application.getOfficialStateCode());
		officialAddress.setOfficialCountryCode(application.getOfficialCountryCode());
		// application.setCopiedAddress(true);
		updateIntakeApplication(application);

		// copy updated official address to mailing address
		application.setMailingAddress1(application.getOfficialAddress1());
		application.setMailingAddress2(application.getOfficialAddress2());
		application.setMailingAddress3(application.getOfficialAddress3());
		application.setMailingPostcode(application.getOfficialPostcode());
		application.setMailingStateCode(application.getOfficialStateCode());
		application.setMailingCountryCode(application.getOfficialCountryCode());
		// application.setCopiedAddress(true);
		updateIntakeApplication(application);

	}

	@Override
	public void checkAttachment(InIntakeApplication application, InAttachment attachment) {

		if (attachment.getAttachmentType() == InAttachmentType.SPM) {
			application.setSpmResultAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.STPM) {
			application.setStpmResultAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.DIPLOMA) {
			application.setDiplomaResultAttached(true);
		}
		else if (attachment.getAttachmentType() == InAttachmentType.DIPLOMA_EQUIVALENT) {
			application.setDiplomaResultAttached(true);
		}
		else if (attachment.getAttachmentType() == InAttachmentType.BACHELOR) {
			application.setBachelorResultAttached(true);
		}
		else if (attachment.getAttachmentType() == InAttachmentType.BACHELOR_EQUIVALENT) {
			application.setBachelorResultAttached(true);
		} 		
		else if (attachment.getAttachmentType() == InAttachmentType.SPONSOR) {
			application.setSponsorLetterAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.REFEREE_FORM) {
			application.setRefereeFormAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.BANK_STATEMENT) {
			application.setBankStatementAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.PROCESSING_FEE) {
			application.setProcessingFeeAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.RESEARCH_PROPOSAL) {
			application.setResearchProposalAttached(true);
		} 
		else if (attachment.getAttachmentType() == InAttachmentType.PHD) {
			application.setPhdResultAttached(true);
		} 
		else if (attachment.getAttachmentType() == InAttachmentType.PHD_EQUIVALENT) {
			application.setPhdResultAttached(true);
		}
		else if (attachment.getAttachmentType() == InAttachmentType.IELTS) {
			application.setIeltsResultAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.TOEFL) {
			application.setToeflResultAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.LANGUAGE_RESULT) {
			application.setLanguageResultAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.IC_COPY) {
			application.setIcCopyAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.PASSPORT_COPY) {
			application.setPassportCopyAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.STAM) {
			application.setStamResultAttached(true);
		} else if (attachment.getAttachmentType() == InAttachmentType.MUET) {
			application.setMuetResultAttached(true);
		} 
		else if (attachment.getAttachmentType() == InAttachmentType.MASTER) {
			application.setMasterResultAttached(true);
		} 
		else if (attachment.getAttachmentType() == InAttachmentType.MASTER_EQUIVALENT) {
			application.setMasterResultAttached(true);
		} 
		else if (attachment.getAttachmentType() == InAttachmentType.PASSPORT_IMAGE) {
			application.setPassportImageAttached(true);
		} 
		else if (attachment.getAttachmentType() == InAttachmentType.EMPLOYMENT_VERIFICATION) {
			application.setEmploymentVerificationAttached(true);
		} 
		else if (attachment.getAttachmentType() == InAttachmentType.APEL_CERTIFICATE) {
			application.setApelCertificateAttached(true);
		} 
		
		
		this.updateIntakeApplication(application);

	}

	@Override
	public void unCheckAttachment(InIntakeApplication application, InAttachment attachment) {

		if (attachment.getAttachmentType() == InAttachmentType.SPM) {
			application.setSpmResultAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.STPM) {
			application.setStpmResultAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.DIPLOMA) {
			application.setDiplomaResultAttached(false);
		}

		else if (attachment.getAttachmentType() == InAttachmentType.DIPLOMA_EQUIVALENT) {
			application.setDiplomaResultAttached(false);
		}

		else if (attachment.getAttachmentType() == InAttachmentType.BACHELOR) {
			application.setBachelorResultAttached(false);
		}

		else if (attachment.getAttachmentType() == InAttachmentType.BACHELOR_EQUIVALENT) {
			application.setBachelorResultAttached(false);
		}
		else if (attachment.getAttachmentType() == InAttachmentType.MASTER) {
			application.setMasterResultAttached(false);
		}
		else if (attachment.getAttachmentType() == InAttachmentType.MASTER_EQUIVALENT) {
			application.setMasterResultAttached(false);
		}
		
		else if (attachment.getAttachmentType() == InAttachmentType.SPONSOR) {
			application.setSponsorLetterAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.REFEREE_FORM) {
			application.setRefereeFormAttached(false);
		} 
		
		else if (attachment.getAttachmentType() == InAttachmentType.PHD) {
			application.setPhdResultAttached(false);
		}
		else if (attachment.getAttachmentType() == InAttachmentType.PHD_EQUIVALENT) {
			application.setPhdResultAttached(false);		
		}
		else if (attachment.getAttachmentType() == InAttachmentType.BANK_STATEMENT) {
			application.setBankStatementAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.PROCESSING_FEE) {
			application.setProcessingFeeAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.RESEARCH_PROPOSAL) {
			application.setResearchProposalAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.IELTS) {
			application.setIeltsResultAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.TOEFL) {
			application.setToeflResultAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.LANGUAGE_RESULT) {
			application.setLanguageResultAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.IC_COPY) {
			application.setIcCopyAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.PASSPORT_COPY) {
			application.setPassportCopyAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.STAM) {
			application.setStamResultAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.MUET) {
			application.setMuetResultAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.PASSPORT_IMAGE) {
			application.setPassportImageAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.EMPLOYMENT_VERIFICATION) {
			application.setEmploymentVerificationAttached(false);
		} else if (attachment.getAttachmentType() == InAttachmentType.APEL_CERTIFICATE) {
			application.setApelCertificateAttached(false);
		}

		this.updateIntakeApplication(application);

	}


	@Override
	public void submitNotification(InIntakeApplication application) {
		LOG.debug("Start submit email");
		//Log In
//		 SecurityContext sc = loginAsSystem();    
		 
		 //Create email body
		 InEmailQueue email = new InEmailQueueImpl();
		 String subject = "Email Notification";
		 String body = "Your application has been submitted and will be process, Thank you.";
		 email.setTo(application.getEmail());
		 email.setSubject(subject);
		 email.setBody(body);
		 
		 //Sent Email
		 
		 email.setCode("EQ/" + System.currentTimeMillis());
		 email.setQueueStatus(InEmailQueueStatus.QUEUED);
		 systemService.saveEmailQueue(email);
		 
		 LOG.debug("Email Queue:{}",email.getTo());
		 
		 //Logout
//		 logoutAsSystem(sc);

	}
	
    private SecurityContext loginAsSystem() {
        SecurityContext savedCtx = SecurityContextHolder.getContext();
        InAutoLoginToken authenticationToken = new InAutoLoginToken("root");
        Authentication authed = authenticationManager.authenticate(authenticationToken);
        SecurityContext system = new NonSerializableSecurityContext();
        system.setAuthentication(authed);
        SecurityContextHolder.setContext(system);
        return savedCtx;
    }

    private void logoutAsSystem(SecurityContext context) {
        SecurityContextHolder.setContext(context);
    }

}
