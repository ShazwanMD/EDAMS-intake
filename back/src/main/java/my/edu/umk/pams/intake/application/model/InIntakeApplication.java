package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.core.InMetaObject;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InStudyModeOffering;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface InIntakeApplication extends InMetaObject {

    Integer getRank();

    void setRank(Integer rank);

    BigDecimal getMerit();

    void setMerit(BigDecimal merit);

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getName();

    void setName(String name);

    String getCredentialNo();

    void setCredentialNo(String credentialNo);

    String getEmail();

    void setEmail(String email);

    String getPhone();

    void setPhone(String phone);

    String getMobile();

    void setMobile(String mobile);

    String getFax();

    void setFax(String fax);

    Date getBirthDate();

    void setBirthDate(Date birthDate);

    Integer getAge();

    void setAge(Integer age);

    String getOkuNo();

    void setOkuNo(String okuNo);

    InMaritalCode getMaritalCode();

    void setMaritalCode(InMaritalCode maritalCode);

    InSchoolCode getSchoolCode();

    void setSchoolCode(InSchoolCode schoolType);

    String getSchoolName();

    void setSchoolName(String schoolName);

    Integer getSchoolBatch();

    void setSchoolBatch(Integer schoolBatch);

    String getReason();

    void setReason(String reason);

    String getPaymentSourceNo();

    void setPaymentSourceNo(String paymentSourceNo);

    Boolean isPaid();

    void setPaid(Boolean paid);

    Boolean isSponsored();

    void setSponsored(Boolean sponsored);

    Boolean isSelfSponsored();

    void setSelfSponsored(Boolean selfSponsored);

    Boolean isVerified();

    void setVerified(Boolean verified);

    InBidType getBidType();

    void setBidType(InBidType bidType);

    InBidStatus getBidStatus();

    void setBidStatus(InBidStatus status);

    InBidResponse getBidResponse();

    void setBidResponse(InBidResponse response);

    InBankCode getBankCode();

    void setBankCode(InBankCode bankCode);

    InNationalityCode getNationalityCode();

    void setNationalityCode(InNationalityCode nationality);

    InDependencyCode getDependencyCode();

    void setDependencyCode(InDependencyCode dependence);

    InResidencyCode getResidencyCode();

    void setResidencyCode(InResidencyCode residence);

    InDisabilityCode getDisabilityCode();

    void setDisabilityCode(InDisabilityCode disabilityCode);

    InRaceCode getRaceCode();

    void setRaceCode(InRaceCode raceCode);

    InEthnicityCode getEthnicityCode();

    void setEthnicityCode(InEthnicityCode ethnicity);

    InReligionCode getReligionCode();

    void setReligionCode(InReligionCode religion);

    InGenderCode getGenderCode();

    void setGenderCode(InGenderCode genderCode);

    InProgramOffering getProgramSelection();

    void setProgramSelection(InProgramOffering programSelection);

    InSupervisorOffering getSupervisorSelection();

    void setSupervisorSelection(InSupervisorOffering SupervisorSelection);

    InStudyModeOffering getStudyModeSelection();

    void setStudyModeSelection(InStudyModeOffering studyModeSelection);

    // NOTE: optional, international student
    String getPassportNo();

    void setPassportNo(String passportNo);

    // NOTE: optional, international student
    Date getPassportExpDate();

    void setPassportExpDate(Date passExpDate);

    // NOTE: international student
    InVisaType getVisaType();

    void setVisaType(InVisaType visaType);

    InIntake getIntake();

    void setIntake(InIntake intake);

    InApplicant getApplicant();

    void setApplicant(InApplicant applicant);

    List<InAddress> getAddresses();

    void setAddresses(List<InAddress> addresses);
    
    List<InBachelorResult> getBachelorResults();

    void setBachelorResults(List<InBachelorResult> bachelorResults);

    List<InGuardian> getGuardians();

    void setGuardians(List<InGuardian> guardians);

    List<InGuarantor> getGuarantors();

    void setGuarantors(List<InGuarantor> guarantors);

    List<InResult> getResults();

    void setResults(List<InResult> documents);

    List<InAttachment> getAttachments();

    void setAttachments(List<InAttachment> documents);

    List<InEducation> getEducations();

    void setEducations(List<InEducation> educations);

    List<InInvolvement> getInvolvements();

    void setInvolvements(List<InInvolvement> involvementExperiences);

    List<InEmployment> getEmployments();

    void setEmployments(List<InEmployment> employments);

    List<InSpmResult> getSpmResults();

    void setSpmResults(List<InSpmResult> spmResults);

    void setReferees(List<InReferee> referees);

    List<InReferee> getReferees();


}

