package my.edu.umk.pams.intake.application.service;

import my.edu.umk.pams.intake.application.model.*;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InStudyMode;
import my.edu.umk.pams.intake.policy.model.InStudyModeType;

import java.util.List;

public interface ApplicationService {

    //====================================================================================================
    // INTAKE APPLICATION
    //====================================================================================================

    void draftIntakeApplication(InIntake intake, InIntakeApplication application);

    void updateIntakeApplication(InIntakeApplication application);

    void submitIntakeApplication(InIntake intake, InIntakeApplication application);

    void addEmployment(InIntakeApplication application, InEmployment employment);

    void addInvolvement(InIntakeApplication application, InInvolvement involvement);

    void addAddress(InIntakeApplication application, InAddress address);

    void deleteAddress(InIntakeApplication application, InAddress address);

    void addContact(InIntakeApplication application, InContact contact);

    void deleteContact(InIntakeApplication application, InContact contact);

    void addGuarantor(InIntakeApplication application, InGuarantor guarantor);

    void deleteGuarantor(InIntakeApplication application, InGuarantor guarantor);

    void addGuardian(InIntakeApplication application, InGuardian guardian);

    void deleteGuardian(InIntakeApplication application, InGuardian guardian);
    
    
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

    InGuardian findGuardianById(Long id);

    InGuarantor findGuarantorById(Long id);

    InAddress findAddressById(Long id);

    InContact findContactById(Long id);

    InGuardian findGuardianByType(InGuardianType guardianType, InIntakeApplication application);
    
	InStudyMode findStudyModeType(InStudyModeType studymodeType, InIntakeApplication application);

    InGuarantor findGuarantorByType(InGuarantorType guarantorType, InIntakeApplication application);

    InAddress findAddressByType(InAddressType addressType, InIntakeApplication application);

    InContact findContactByType(InContactType contactType, InIntakeApplication application);

    List<InIntakeApplication> findIntakeApplications(InIntake intake);

    List<InIntakeApplication> findIntakeApplications(InIntake intake, InBidStatus status);

    List<InIntakeApplication> findIntakeApplications(InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> findIntakeApplications(String filter, InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> findIntakeApplications(String filter, InBidType bidType, InIntake intake, Integer offset, Integer limit);

    List<InIntakeApplication> findIntakeApplicationsOrderedByMerit(InIntake intake);

    List<InIntakeApplication> findIntakeApplicationsOrderedByRank(InIntake intake);

    List<InEmployment> findEmployments(InIntakeApplication application);

    List<InInvolvement> findInvolvements(InIntakeApplication application);

    List<InGuardian> findGuardians(InIntakeApplication application);
    
    List<InStudyMode> findStudyModeType (InStudyModeType studymodeType );

    List<InGuarantor> findGuarantors(InIntakeApplication application);

    List<InContact> findContacts(InIntakeApplication application);

    List<InAddress> findAddresses(InIntakeApplication application);

    Integer countIntakeApplication(InIntake intake);

    Integer countIntakeApplication(String filter, InIntake intake);

    Integer countIntakeApplication(String filter, InBidType bidType, InIntake intake);

    boolean hasEmployment(InIntakeApplication application);

    boolean hasInvolvement(InIntakeApplication application);



	
}

