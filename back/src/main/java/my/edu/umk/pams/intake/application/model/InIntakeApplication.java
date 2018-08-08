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
    
    InPromoCode getPromoCode();

    String getResearchTitle();

    void setResearchTitle(String researchTitle);

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

    String getReason();

    void setReason(String reason);

    String getPaymentSourceNo();

    void setPaymentSourceNo(String paymentSourceNo);

    Boolean isPaid();

    void setPaid(Boolean paid);

    Boolean isDeclared();

    void setDeclared(Boolean declared); 
    
    Boolean isCopiedAddress();

    void setCopiedAddress(Boolean copyAddressed); 
    
    Boolean isSponsored();

    void setSponsored(Boolean sponsored);

    Boolean isSelfSponsored();

    void setSelfSponsored(Boolean selfSponsored);

    Boolean isProcessingFeeAttached();

    void setProcessingFeeAttached(Boolean processingFeeAttached);

    Boolean isLanguageResultAttached();

    void setLanguageResultAttached(Boolean languageResultAttached);

    Boolean isToeflResultAttached();

    void setToeflResultAttached(Boolean toeflResultAttached);

    Boolean isIeltsResultAttached();

    void setIeltsResultAttached(Boolean ieltsResultAttached);

    Boolean isStpmResultAttached();

    void setStpmResultAttached(Boolean stpmResultAttached);

    Boolean isDiplomaResultAttached();

    void setDiplomaResultAttached(Boolean diplomaResultAttached);

    Boolean isBachelorResultAttached();

    void setBachelorResultAttached(Boolean bachelorResultAttached);
    
    Boolean isMasterResultAttached();

    void setMasterResultAttached(Boolean masterResultAttached);
    
    Boolean isPhdResultAttached();

    void setPhdResultAttached(Boolean phdResultAttached);

    Boolean isResearchProposalAttached();

    void setResearchProposalAttached(Boolean researchProposalAttached);

    Boolean isIcCopyAttached();

	void setIcCopyAttached(Boolean icCopyAttached);

	Boolean isPassportCopyAttached();

	void setPassportCopyAttached(Boolean passportCopyAttached);

    Boolean isSponsorLetterAttached();

    void setSponsorLetterAttached(Boolean sponsorLetterAttached);

    Boolean isBankStatementAttached();

    void setBankStatementAttached(Boolean bankStatementAttached);

    Boolean isRefereeFormAttached();

    void setRefereeFormAttached(Boolean refereeFormAttached);

    Boolean isSpmResultAttached();

    void setSpmResultAttached(Boolean spmResultAttached);

    Boolean isVerified();

    void setVerified(Boolean verified);

    InBidType getBidType();

    void setBidType(InBidType bidType);

    InBidStatus getBidStatus();

    void setBidStatus(InBidStatus status);

    InBidResponse getBidResponse();

    void setBidResponse(InBidResponse response);

    // address
    String getMailingAddress1();

    void setMailingAddress1(String mailingAddresss1);

    String getMailingAddress2();

    void setMailingAddress2(String mailingAddresss2);

    String getMailingAddress3();

    void setMailingAddress3(String mailingAddresss3);

    InCountryCode getMailingCountryCode();

    void setMailingCountryCode(InCountryCode mailingCountryCode);

    InStateCode getMailingStateCode();

    void setMailingStateCode(InStateCode mailingStateCode);

    String getOfficialAddress1();

    void setOfficialAddress1(String officialAddresss1);

    String getOfficialAddress2();

    void setOfficialAddress2(String officialAddresss2);

    String getOfficialAddress3();

    void setOfficialAddress3(String officialAddresss3);
    
    String getOfficialPostcode();

    void setOfficialPostcode(String officialPostcode);
    
    String getMailingPostcode();

    void setMailingPostcode(String mailingPostcode);

    InCountryCode getOfficialCountryCode();

    void setOfficialCountryCode(InCountryCode officialCountryCode);

    InStateCode getOfficialStateCode();

    void setOfficialStateCode(InStateCode officialStateCode);

    InBankCode getBankCode();

    void setBankCode(InBankCode bankCode);

    InNationalityCode getNationalityCode();

    void setNationalityCode(InNationalityCode nationality);

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
    
    InStudyCenterCode getStudyCenterCode();

    void setStudyCenterCode(InStudyCenterCode studyCenterCode);

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

    List<InLanguage> getLanguages();

    void setLanguages(List<InLanguage> languages);

    void setReferees(List<InReferee> referees);

    List<InReferee> getReferees();

	Boolean isStamResultAttached();
	
	void setStamResultAttached(Boolean stamResultAttached);

	Boolean isMuetResultAttached();

	void setMuetResultAttached(Boolean muetResultAttached);

	void setPromoCode(InPromoCode promoCode);
	
	String getPlaceOfBirth();

    void setPlaceOfBirth(String placeOfBirth);
    
    String getPosition();

    void setPosition(String position);
    
    String getIncome();

    void setIncome(String income);
    
    InEmploymentSectorCode getEmploymentSectorCode();

    void setEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode);
    
    InEmploymentTypeCode getEmploymentTypeCode();
    
    void setEmploymentTypeCode (InEmploymentTypeCode employmentTypeCode);
    
    String getEmployerAddress1();
    
    void setEmployerAddress1(String employerAddress1);
    
    String getEmployerAddress2();
    
    void setEmployerAddress2(String employerAddress2);
    
    String getEmployerAddress3();
    
    void setEmployerAddress3(String employerAddress3);
    
    String getEmployerPostcode();
    
    void setEmployerPostcode(String employerPostcode);
    
    InStateCode getEmployerState();

    void setEmployerState(InStateCode employerState);
    
    String getEmployerNo();
    
    void setEmployerNo(String employerNo);
    
    Boolean isPassportImageAttached();

    void setPassportImageAttached(Boolean passportImageAttached);
    
    Boolean isEmploymentVerificationAttached();

    void setEmploymentVerificationAttached(Boolean employmentVerificationAttached);
    
    Boolean isApelCertificateAttached();

    void setApelCertificateAttached(Boolean apelCertificateAttached);
    
    // guardian
	
	String getGuardianName();
    
    void setGuardianName(String guardianName); 
    
    String getGuardianIdentityNo();
    
    void setGuardianIdentityNo(String guardianIdentityNo); 
    
    String getGuardianPhoneNo();
    
    void setGuardianPhoneNo(String guardianphoneNo); 
    
	String getGuardianAddress1();
	    
	void setGuardianAddress1(String guardianAddress1);
	    
	String getGuardianAddress2();
	    
	void setGuardianAddress2(String guardianAddress2);
	    
	String getGuardianAddress3();
	    
	void setGuardianAddress3(String guardianAddress3);
	    
	String getGuardianPostcode();
	    
	void setGuardianPostcode(String guardianPostcode);
	    
	InStateCode getGuardianState();

	void setGuardianState(InStateCode guardianState);
	
	InCountryCode getGuardianCountryCode();

	void setGuardianCountryCode(InCountryCode guardianCountryCode);
	
	InGuardianTypeCode getGuardianTypeCode();

	void setGuardianTypeCode(InGuardianTypeCode guardianTypeCode);


    
}

