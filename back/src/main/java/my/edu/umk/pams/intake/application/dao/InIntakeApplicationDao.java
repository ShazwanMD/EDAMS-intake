package my.edu.umk.pams.intake.application.dao;

import my.edu.umk.pams.intake.application.model.*;
import my.edu.umk.pams.intake.common.model.InPromoCode;
import my.edu.umk.pams.intake.core.GenericDao;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.model.InIntake;

import java.util.List;

public interface InIntakeApplicationDao extends GenericDao<Long, InIntakeApplication> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    InIntakeApplication findByReferenceNo(String referenceNo);

    InIntakeApplication findByNricNoOrPassportNo(String identityNo);

    InIntakeApplication findByIntakeAndApplicant(InIntake intake, InApplicant applicant);
    
    InIntakeApplication findByApplicant(InApplicant applicant);
    
    InIntakeApplication findByAddress(String officialAddress1);

    InResult findResultById(Long id);

    InResult findResult(InIntakeApplication application, InResultType resultType);

    InGuarantor findGuarantorById(Long id);

    InGuardian findGuardianById(Long id);

    InContact findContactById(Long id);

    InEmployment findEmploymentById(Long id);

    InReferee findRefereeById(Long id);

    InLanguage findLanguageById(Long id);

    InAttachment findAttachmentById(Long id);

    InGuarantor findGuarantorByType(InGuarantorType type, InIntakeApplication application);

    InGuardian findGuardianByType(InGuardianType guardianType, InIntakeApplication application);

    InContact findContactByType(InContactType type, InIntakeApplication application);

    InApplicant findApplicant(InIntakeApplication application);

    List<InIntakeApplication> find(InIntake intake);

    List<InIntakeApplication> find(InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> find(String filter, InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> find(String filter, InBidType bidType, InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> find(InApplicant applicant);

    List<InIntakeApplication> find(InApplicant applicant, InBidStatus bidStatus);

    List<InIntakeApplication> findByOrderedMerit(InIntake intake, InBidStatus bidStatus);

    List<InIntakeApplication> findByOrderedRank(InIntake intake);

    List<InIntakeApplication> find(InIntake intake, InBidStatus bidStatus);

    List<InIntakeApplication> findIntakeApplicationsByPaidStatus(InIntake intake, Boolean paid);

    List<InIntakeApplication> findIntakeApplicationsByVerificationStatus(InIntake intake , InBidStatus bidStatus, Boolean verification);

    List<InApplicant> findApplicants(InIntake intake);

    List<InResult> findResults(InIntakeApplication application);

    List<InEducation> findEducations(InIntakeApplication application);

    List<InEmployment> findEmployments(InIntakeApplication application);

    List<InLanguage> findLanguages(InIntakeApplication application);

    List<InAttachment> findAttachments(InIntakeApplication application);

    List<InReferee> findReferees(InIntakeApplication application);

    List<InInvolvement> findInvolvements(InIntakeApplication application);

    List<InGuarantor> findGuarantors(InIntakeApplication application);

    List<InGuardian> findGuardians(InIntakeApplication application);

    List<InContact> findContacts(InIntakeApplication application);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(InIntake intake);

    Integer count(String filter, InIntake intake);

    Integer count(String filter, InBidType bidType, InIntake intake);

    boolean hasResult(InIntakeApplication application, InResultType resultType);

    boolean hasEducation(InIntakeApplication application);

    boolean hasEmployment(InIntakeApplication application);

    boolean hasLanguage(InIntakeApplication application);

    boolean hasInvolvement(InIntakeApplication application);

    boolean hasReferee(InIntakeApplication application);

    boolean hasAttachment(InIntakeApplication application);

    boolean hasApplied(InIntake intake, InApplicant applicant);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addResult(InIntakeApplication application, InResult result, InUser user);
    
    void updateResult(InIntakeApplication application, InResult result, InUser user);

    void deleteResult(InIntakeApplication application, InResult result, InUser user);

    void addEducation(InIntakeApplication application, InEducation education, InUser user);

    void deleteEducation(InIntakeApplication application, InEducation education, InUser user);

    void addEmployment(InIntakeApplication application, InEmployment employment, InUser user);

    void deleteEmployment(InIntakeApplication application, InEmployment employment, InUser user);

    void addLanguage(InIntakeApplication application, InLanguage language, InUser user);

    void deleteLanguage(InIntakeApplication application, InLanguage language, InUser user);

    void addInvolvement(InIntakeApplication application, InInvolvement involvement, InUser user);

    void deleteInvolvement(InIntakeApplication application, InInvolvement involvement, InUser user);

    void addGuardian(InIntakeApplication application, InGuardian guardian, InUser user);

    void deleteGuardian(InIntakeApplication application, InGuardian guardian, InUser user);

    void updateGuardian(InIntakeApplication application, InGuardian guardian, InUser user);
    
    void addGuarantor(InIntakeApplication application, InGuarantor guarantor, InUser user);

    void deleteGuarantor(InIntakeApplication application, InGuarantor guarantor, InUser user);

    void addContact(InIntakeApplication application, InContact contact, InUser user);

    void deleteContact(InIntakeApplication application, InContact contact, InUser user);

    void addAttachment(InIntakeApplication application, InAttachment attachment, InUser currentUser);

    void deleteAttachment(InIntakeApplication application, InAttachment attachment, InUser user);

    void addReferee(InIntakeApplication application, InReferee referee, InUser currentUser);

    void deleteReferee(InIntakeApplication application, InReferee referee, InUser user);

    void updateReferee(InIntakeApplication application, InReferee referee, InUser user);

    void updateEmployment(InIntakeApplication application, InEmployment employment, InUser user);

    void updateLanguage(InIntakeApplication application, InLanguage language, InUser user);
    
	List<InIntakeApplication> findStatusVerify(InIntake intake, InBidStatus bidStatus);

	boolean isIntakeApplicationExists(InIntake intake, InApplicant applicant);

	boolean isPromoCodeEntered(InPromoCode promoCode);



}
