package my.edu.umk.pams.intake.application.service;

import my.edu.umk.pams.intake.application.model.*;
import my.edu.umk.pams.intake.common.model.InPromoCode;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;

import java.util.List;

public interface ApplicationService {

    //====================================================================================================
    // INTAKE APPLICATION
    //====================================================================================================

    void calculateApplicantMerit(InIntake intake);

    String applyIntake(InIntake intake, InIntakeApplication application) throws Exception;

    void updateIntakeApplication(InIntakeApplication application);

    void submitIntakeApplication(InIntake intake, InIntakeApplication application);

    void withdrawIntakeApplication(InIntake intake, InIntakeApplication application);

    void draftedIntakeApplication(InIntake intake, InIntakeApplication application);

    void selectIntakeApplication(InIntake intake, InIntakeApplication application);

    void verifyInternationalApplications(InIntake intake, InIntakeApplication application);

    void rejectIntakeApplication(InIntake intake, InIntakeApplication application);

    void addResult(InIntakeApplication application, InResult result);
    
    void updateResult(InIntakeApplication application, InResult result);
    
    void deleteResult(InIntakeApplication application, InResult result);

    void addEducation(InIntakeApplication application, InEducation education);

    void addEmployment(InIntakeApplication application, InEmployment employment);

    void updateEmployment(InIntakeApplication application, InEmployment employment);

    void updateLanguage(InIntakeApplication application, InLanguage language);

    void addReferee(InIntakeApplication application, InReferee referee);

    void addLanguage(InIntakeApplication application, InLanguage language);

    void addInvolvement(InIntakeApplication application, InInvolvement involvement);

    void deleteLanguage(InIntakeApplication application, InLanguage language);

    void deleteEmployment(InIntakeApplication application, InEmployment employment);

    void deleteReferee(InIntakeApplication application, InReferee referee);

    void updateReferee(InIntakeApplication application, InReferee referee);

    void addContact(InIntakeApplication application, InContact contact);

    void deleteContact(InIntakeApplication application, InContact contact);

    void addGuarantor(InIntakeApplication application, InGuarantor guarantor);

    void deleteGuarantor(InIntakeApplication application, InGuarantor guarantor);

    void addGuardian(InIntakeApplication application, InGuardian guardian);

    void deleteGuardian(InIntakeApplication application, InGuardian guardian);
    
    void updateGuardian(InIntakeApplication application, InGuardian guardian);

    void addAttachment(InIntakeApplication application, InAttachment attachment);

    void deleteAttachment(InIntakeApplication application, InAttachment attachment);


    //====================================================================================================
    // APPLICANT
    //====================================================================================================

    InApplicant findApplicant(InIntakeApplication application);

    List<InApplicant> findApplicants(InIntake intake);

    //====================================================================================================
    // INTAKE APPLICATION
    //====================================================================================================

    InIntakeApplication findIntakeApplicationById(Long id);

    InIntakeApplication findIntakeApplicationByReferenceNo(String referenceNo);

    InIntakeApplication findIntakeApplicationByNricNoOrPassportNo(String identityNo);

    InIntakeApplication findIntakeApplicationByIntakeAndApplicant(InIntake intake, InApplicant applicant);
    
    InIntakeApplication findIntakeApplicationByApplicant(InApplicant applicant);

    InResult findResultById(Long id);

    InResult findResult(InIntakeApplication application, InResultType resultType);

    InGuardian findGuardianById(Long id);

    InGuarantor findGuarantorById(Long id);

    InEmployment findEmploymentById(Long id);

    InReferee findRefereeById(Long id);

    InLanguage findLanguageById(Long id);

    InAttachment findAttachmentById(Long id);

    InContact findContactById(Long id);

    InGuardian findGuardianByType(InGuardianType guardianType, InIntakeApplication application);

    InGuarantor findGuarantorByType(InGuarantorType guarantorType, InIntakeApplication application);

    InContact findContactByType(InContactType contactType, InIntakeApplication application);

    List<InIntakeApplication> findIntakeApplications(InApplicant applicant);

    List<InIntakeApplication> findIntakeApplications(InApplicant applicant, InBidStatus bidStatus);

    List<InIntakeApplication> findIntakeApplications(InIntake intake);

    List<InIntakeApplication> findIntakeApplications(InIntake intake, InBidStatus status);

    List<InIntakeApplication> findIntakeApplications(InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> findIntakeApplications(String filter, InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> findIntakeApplications(String filter, InBidType bidType, InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> findIntakeApplicationsOrderedByMerit(InIntake intake,InBidStatus status);

    List<InIntakeApplication> findIntakeApplicationsOrderedByRank(InIntake intake);

    List<InIntakeApplication> findIntakeApplicationsByPaidStatus(InIntake intake, Boolean paid);

    List<InIntakeApplication> findIntakeApplicationsByVerificationStatus(InIntake intake, InBidStatus status, Boolean verifiction);

    List<InResult> findResults(InIntakeApplication application);

    List<InEducation> findEducations(InIntakeApplication application);

    List<InEmployment> findEmployments(InIntakeApplication application);

    List<InLanguage> findLanguages(InIntakeApplication application);

    List<InAttachment> findAttachments(InIntakeApplication application);

    List<InReferee> findReferees(InIntakeApplication application);

    List<InInvolvement> findInvolvements(InIntakeApplication application);

    List<InGuardian> findGuardians(InIntakeApplication application);

    List<InGuarantor> findGuarantors(InIntakeApplication application);

    List<InContact> findContacts(InIntakeApplication application);

    List<InProgramOffering> findProgramOfferings(InIntakeApplication application);

    Integer countIntakeApplication(InIntake intake);

    Integer countIntakeApplication(String filter, InIntake intake);

    Integer countIntakeApplication(String filter, InBidType bidType, InIntake intake);

    boolean hasResult(InIntakeApplication application, InResultType resultType);

    boolean hasEducation(InIntakeApplication application);

    boolean hasEmployment(InIntakeApplication application);

    boolean hasLanguage(InIntakeApplication application);

    boolean hasInvolvement(InIntakeApplication application);

    boolean hasReferee(InIntakeApplication application);

    boolean hasApplied(InIntake intake, InApplicant applicant);

	List<InIntakeApplication> findIntakeApplicationsByStatusVerify(InIntake intake, InBidStatus status);

	boolean isIntakeApplicationExists(InIntake intake, InApplicant applicant);

	void copyAddressApplication(InIntakeApplication application);

	void checkAttachment(InIntakeApplication application, InAttachment attachment);

	boolean isPromoCodeEntered(InPromoCode promoCode);

	void unCheckAttachment(InIntakeApplication application, InAttachment attachment);

//	void changeAddress(InIntakeApplication application);
	
	 
	
}

