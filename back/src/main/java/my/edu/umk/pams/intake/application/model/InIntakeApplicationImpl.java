package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.core.InMetadata;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InApplicantImpl;
import my.edu.umk.pams.intake.policy.model.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity(name = "InIntakeApplication")
@Table(name = "IN_INTK_APLN")
public class InIntakeApplicationImpl implements InIntakeApplication {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_IN_INTK_APLN")
	@SequenceGenerator(name = "SQ_IN_INTK_APLN", sequenceName = "SQ_IN_INTK_APLN", allocationSize = 1)
	private Long id;
	
	public InIntakeApplicationImpl() {
		super();
		setGuardianType(InGuardianType.NOT_APPLICABLE);
	}

	@NotNull
	@Column(name = "RANK", nullable = false)
	private Integer rank = 0;

	@NotNull
	@Column(name = "MERIT", nullable = false)
	private BigDecimal merit = BigDecimal.ZERO;

	@NotNull
	@Column(name = "REFERENCE_NO", unique = true, nullable = false)
	private String referenceNo;

	@OneToOne(targetEntity = InPromoCodeImpl.class)
	@JoinColumn(name = "PROMO_CODE_ID", unique = true)
	private InPromoCode promoCode;

	@NotNull
	@Column(name = "RESEARCH_TITLE", nullable = false)
	private String researchTitle = "N/A";

	@NotNull
	@Column(name = "NAME", nullable = false)
	private String name;

	@NotNull
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "MOBILE")
	private String mobile;

	@Column(name = "FAX")
	private String fax;

	@NotNull
	@Column(name = "CREDENTIAL_NO")
	private String credentialNo;

	@Column(name = "OKU_NO")
	private String okuNo;

	@NotNull
	@Column(name = "PAYMENT_SOURCE_NO")
	private String paymentSourceNo;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "PLACE_OF_BIRTH")
	private String placeOfBirth;

	@NotNull
	@Column(name = "AGE")
	private Integer age = 0;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "PASSPORT_NO")
	private String passport;

	@Column(name = "PASSPORT_EXPDATE")
	private Date passExpDate;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "VISA_TYPE", nullable = false)
	private InVisaType visaType = InVisaType.NON_APPLICABLE;
	

	@Column(name = "PAID")
	private Boolean paid = false;

	@Column(name = "DECLARED")
	private Boolean declared = false;

	@Column(name = "COPY_ADDRESSED")
	private Boolean copyAddressed = false;

	@Column(name = "SPONSORED")
	private Boolean sponsored = false;

	@Column(name = "SELF_SPONSORED")
	private Boolean selfSponsored = false;

	@Column(name = "SPM_RESULT_ATTACHED")
	private Boolean spmResultAttached = false;

	@Column(name = "PROCESSING_FEE_ATTACHED")
	private Boolean processingFeeAttached = false;

	@Column(name = "LANGUAGE_RESULT_ATTACHED")
	private Boolean languageResultAttached = false;

	@Column(name = "TOEFL_RESULT_ATTACHED")
	private Boolean toeflResultAttached = false;

	@Column(name = "IELTS_RESULT_ATTACHED")
	private Boolean ieltsResultAttached = false;

	@Column(name = "STPM_RESULT_ATTACHED")
	private Boolean stpmResultAttached = false;

	@Column(name = "STAM_RESULT_ATTACHED")
	private Boolean stamResultAttached = false;

	@Column(name = "DIPLOMA_RESULT_ATTACHED")
	private Boolean diplomaResultAttached = false;

	@Column(name = "BACHELOR_RESULT_ATTACHED")
	private Boolean bachelorResultAttached = false;

	@Column(name = "MASTER_RESULT_ATTACHED")
	private Boolean masterResultAttached = false;

	@Column(name = "PHD_RESULT_ATTACHED")
	private Boolean phdResultAttached = false;

	@Column(name = "MUET_RESULT_ATTACHED")
	private Boolean muetResultAttached = false;

	@Column(name = "RESEARCH_PROPOSAL_ATTACHED")
	private Boolean researchProposalAttached = false;

	@Column(name = "SPONSOR_LETTER_ATTACHED")
	private Boolean sponsorLetterAttached = false;

	@Column(name = "REFEREE_FORM_ATTACHED")
	private Boolean refereeFormAttached = false;

	@Column(name = "BANK_STATEMENT_ATTACHED")
	private Boolean bankStatementAttached = false;

	@Column(name = "IC_COPY_ATTACHED")
	private Boolean icCopyAttached = false;

	@Column(name = "PASSPORT_COPY_ATTACHED")
	private Boolean passportCopyAttached = false;

	@Column(name = "VERIFIED", nullable = false)
	private Boolean verified = false;

	@Column(name = "PASSPORT_IMAGE_ATTACHED")
	private Boolean passportImageAttached = false;

	@Column(name = "EMPLOYMENT_VERIFICATION_ATTACHED")
	private Boolean employmentVerificationAttached = false;

	@Column(name = "APEL_CERTIFICATE_ATTACHED")
	private Boolean apelCertificateAttached = false;

	// address
	@Column(name = "MAILING_ADDRESS1")
	private String mailingAddress1;

	@Column(name = "MAILING_ADDRESS2")
	private String mailingAddress2;

	@Column(name = "MAILING_ADDRESS3")
	private String mailingAddress3;

	@Column(name = "MAILING_POSTCODE")
	private String mailingPostcode;

	@ManyToOne(targetEntity = InCountryCodeImpl.class)
	@JoinColumn(name = "MAILING_COUNTRY_CODE_ID")
	private InCountryCode mailingCountryCode;

	@ManyToOne(targetEntity = InStateCodeImpl.class)
	@JoinColumn(name = "MAILING_STATE_CODE_ID")
	private InStateCode mailingStateCode;

	@Column(name = "OFFICIAL_ADDRESS1")
	private String officialAddress1;

	@Column(name = "OFFICIAL_ADDRESS2")
	private String officialAddress2;

	@Column(name = "OFFICIAL_ADDRESS3")
	private String officialAddress3;

	@Column(name = "OFFICIAL_POSTCODE")
	private String officialPostcode;

	@ManyToOne(targetEntity = InCountryCodeImpl.class)
	@JoinColumn(name = "OFFICIAL_COUNTRY_CODE_ID")
	private InCountryCode officialCountryCode;

	@ManyToOne(targetEntity = InStateCodeImpl.class)
	@JoinColumn(name = "OFFICIAL_STATE_CODE_ID")
	private InStateCode officialStateCode;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "BID_TYPE", nullable = false)
	private InBidType bidType = InBidType.FIRST;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "BID_STATUS")
	private InBidStatus bidStatus = InBidStatus.NEW;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "BID_RESPONSE")
	private InBidResponse bidResponse = InBidResponse.NEW;

	@ManyToOne(targetEntity = InDisabilityCodeImpl.class)
	@JoinColumn(name = "DISABILITY_CODE_ID")
	private InDisabilityCode disabilityCode;

	@ManyToOne(targetEntity = InRaceCodeImpl.class)
	@JoinColumn(name = "RACE_CODE_ID")
	private InRaceCode raceCode;

	@ManyToOne(targetEntity = InEthnicityCodeImpl.class)
	@JoinColumn(name = "ETHNICITY_CODE_ID")
	private InEthnicityCode ethnicityCode;

	@ManyToOne(targetEntity = InGenderCodeImpl.class)
	@JoinColumn(name = "GENDER_CODE_ID")
	private InGenderCode genderCode;

	@ManyToOne(targetEntity = InReligionCodeImpl.class)
	@JoinColumn(name = "RELIGION_CODE_ID")
	private InReligionCode religionCode;

	// todo(ashraf): do we need this?
	@ManyToOne(targetEntity = InBankCodeImpl.class)
	@JoinColumn(name = "BANK_CODE_ID")
	private InBankCode bankCode;

	@ManyToOne(targetEntity = InNationalityCodeImpl.class)
	@JoinColumn(name = "NATIONALITY_CODE_ID")
	private InNationalityCode nationalityCode;

	@ManyToOne(targetEntity = InMaritalCodeImpl.class)
	@JoinColumn(name = "MARITAL_CODE_ID")
	private InMaritalCode maritalCode;

	@ManyToOne(targetEntity = InResidencyCodeImpl.class)
	@JoinColumn(name = "RESIDENCY_CODE_ID")
	private InResidencyCode residencyCode;

	// note: can draft without choosing
	@ManyToOne(targetEntity = InProgramOfferingImpl.class)
	@JoinColumn(name = "PROGRAM_SELECTION_ID", nullable = true)
	private InProgramOffering programSelection;

	// note: can draft without choosing
	@ManyToOne(targetEntity = InSupervisorOfferingImpl.class)
	@JoinColumn(name = "SUPERVISOR_SELECTION_ID", nullable = true)
	private InSupervisorOffering supervisorSelection;

	// note: can draft without choosing
	@ManyToOne(targetEntity = InStudyModeOfferingImpl.class)
	@JoinColumn(name = "STUDY_MODE_SELECTION_ID", nullable = true)
	private InStudyModeOffering studyModeSelection;

	// note: can draft without choosing
	@ManyToOne(targetEntity = InStudyCenterCodeImpl.class)
	@JoinColumn(name = "STUDY_CENTER_CODE_ID", nullable = true)
	private InStudyCenterCode studyCenterCode;

	@ManyToOne(targetEntity = InIntakeImpl.class)
	@JoinColumn(name = "INTAKE_ID")
	private InIntake intake;

	@ManyToOne(targetEntity = InApplicantImpl.class)
	@JoinColumn(name = "APPLICANT_ID")
	private InApplicant applicant;

	// occupation info

	@Column(name = "POSITION")
	private String position;

	@Column(name = "INCOME")
	private String income;

	@ManyToOne(targetEntity = InEmploymentSectorCodeImpl.class)
	@JoinColumn(name = "EMPLOYMENT_SECTOR_CODE_ID")
	private InEmploymentSectorCode employmentSectorCode;

	@ManyToOne(targetEntity = InEmploymentTypeCodeImpl.class)
	@JoinColumn(name = "EMPLOYMENT_TYPE_CODE_ID")
	private InEmploymentTypeCode employmentTypeCode;

	@Column(name = "EMPLOYER_ADDRESS1")
	private String employerAddress1;

	@Column(name = "EMPLOYER_ADDRESS2")
	private String employerAddress2;

	@Column(name = "EMPLOYER_ADDRESS3")
	private String employerAddress3;

	@Column(name = "EMPLOYER_POSTCODE")
	private String employerPostcode;

	@ManyToOne(targetEntity = InStateCodeImpl.class)
	@JoinColumn(name = "EMPLOYER_STATE_ID")
	private InStateCode employerState;

	@Column(name = "EMPLOYER_NO")
	private String employerNo;
	
	// guardian
	
	@Column(name = "GUARDIAN_NAME")
	private String guardianName;
	
	@Column(name = "GUARDIAN_IDENTITY_NO" )
	private String guardianIdentityNo;
	
	@Column(name = "GUARDIAN_PHONE_NO")
	private String guardianPhoneNo;
	
	@Column(name = "GUARDIAN_ADDRESS1")
	private String guardianAddress1;

	@Column(name = "GUARDIAN_ADDRESS2")
	private String guardianAddress2;

	@Column(name = "GUARDIAN_ADDRESS3")
	private String guardianAddress3;

	@Column(name = "GUARDIAN_POSTCODE")
	private String guardianPostcode;

	@ManyToOne(targetEntity = InStateCodeImpl.class)
	@JoinColumn(name = "GUARDIAN_STATE_ID")
	private InStateCode guardianState;
	
	@ManyToOne(targetEntity = InCountryCodeImpl.class)
	@JoinColumn(name = "GUARDIAN_COUNTRY_CODE_ID")
	private InCountryCode guardianCountryCode;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "GUARDIAN_TYPE")
	private InGuardianType guardianType;


	@OneToMany(targetEntity = InResultImpl.class, mappedBy = "application")
	private List<InResult> results;

	@OneToMany(targetEntity = InInvolvementImpl.class, mappedBy = "application")
	private List<InInvolvement> involvements;

	@OneToMany(targetEntity = InEducationImpl.class, mappedBy = "application")
	private List<InEducation> educations;

	@OneToMany(targetEntity = InEmploymentImpl.class, mappedBy = "application")
	private List<InEmployment> employments;

	@OneToMany(targetEntity = InLanguageImpl.class, mappedBy = "application")
	private List<InLanguage> languages;

	@OneToMany(targetEntity = InGuardianImpl.class, mappedBy = "application")
	private List<InGuardian> guardians;

	@OneToMany(targetEntity = InGuarantorImpl.class, mappedBy = "application")
	private List<InGuarantor> guarantors;

	@OneToMany(targetEntity = InRefereeImpl.class, mappedBy = "application")
	private List<InReferee> referees;

	@OneToMany(targetEntity = InAttachmentImpl.class, mappedBy = "application")
	private List<InAttachment> attachments;

	@Embedded
	private InMetadata metadata;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getRank() {
		return rank;
	}

	@Override
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public BigDecimal getMerit() {
		return merit;
	}

	@Override
	public void setMerit(BigDecimal merit) {
		this.merit = merit;
	}

	@Override
	public String getReferenceNo() {
		return referenceNo;
	}

	@Override
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	@Override
	public InPromoCode getPromoCode() {
		return promoCode;
	}

	@Override
	public void setPromoCode(InPromoCode promoCode) {
		this.promoCode = promoCode;
	}

	@Override
	public String getResearchTitle() {
		return researchTitle;
	}

	@Override
	public void setResearchTitle(String researchTitle) {
		this.researchTitle = researchTitle;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String getMobile() {
		return mobile;
	}

	@Override
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String getFax() {
		return fax;
	}

	@Override
	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public Integer getAge() {
		return age;
	}

	@Override
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public Date getBirthDate() {
		return birthDate;
	}

	@Override
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	@Override
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	@Override
	public String getIncome() {
		return income;
	}

	@Override
	public void setIncome(String income) {
		this.income = income;
	}

	@Override
	public String getCredentialNo() {
		return credentialNo;
	}

	@Override
	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	@Override
	public String getOkuNo() {
		return okuNo;
	}

	@Override
	public void setOkuNo(String okuNo) {
		this.okuNo = okuNo;
	}

	@Override
	public String getPaymentSourceNo() {
		return paymentSourceNo;
	}

	@Override
	public void setPaymentSourceNo(String paymentSourceNo) {
		this.paymentSourceNo = paymentSourceNo;
	}

	@Override
	public String getReason() {
		return reason;
	}

	@Override
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public Boolean isPaid() {
		return paid;
	}

	@Override
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	@Override
	public Boolean isSponsored() {
		return sponsored;
	}

	@Override
	public void setSponsored(Boolean sponsored) {
		this.sponsored = sponsored;
	}

	@Override
	public Boolean isSelfSponsored() {
		return selfSponsored;
	}

	@Override
	public void setSelfSponsored(Boolean selfSponsored) {
		this.selfSponsored = selfSponsored;
	}

	@Override
	public Boolean isSpmResultAttached() {
		return spmResultAttached;
	}

	@Override
	public void setSpmResultAttached(Boolean spmResultAttached) {
		this.spmResultAttached = spmResultAttached;
	}

	@Override
	public Boolean isProcessingFeeAttached() {
		return processingFeeAttached;
	}

	@Override
	public void setProcessingFeeAttached(Boolean processingFeeAttached) {
		this.processingFeeAttached = processingFeeAttached;
	}

	@Override
	public Boolean isLanguageResultAttached() {
		return languageResultAttached;
	}

	@Override
	public void setLanguageResultAttached(Boolean languageResultAttached) {
		this.languageResultAttached = languageResultAttached;
	}

	@Override
	public Boolean isToeflResultAttached() {
		return toeflResultAttached;
	}

	@Override
	public void setToeflResultAttached(Boolean toeflResultAttached) {
		this.toeflResultAttached = toeflResultAttached;
	}

	@Override
	public Boolean isIeltsResultAttached() {
		return ieltsResultAttached;
	}

	@Override
	public void setIeltsResultAttached(Boolean ieltsResultAttached) {
		this.ieltsResultAttached = ieltsResultAttached;
	}

	@Override
	public Boolean isStpmResultAttached() {
		return stpmResultAttached;
	}

	@Override
	public void setStpmResultAttached(Boolean stpmResultAttached) {
		this.stpmResultAttached = stpmResultAttached;
	}

	@Override
	public Boolean isStamResultAttached() {
		return stamResultAttached;
	}

	@Override
	public void setStamResultAttached(Boolean stamResultAttached) {
		this.stamResultAttached = stamResultAttached;
	}

	@Override
	public Boolean isDiplomaResultAttached() {
		return diplomaResultAttached;
	}

	@Override
	public void setDiplomaResultAttached(Boolean diplomaResultAttached) {
		this.diplomaResultAttached = diplomaResultAttached;
	}

	@Override
	public Boolean isBachelorResultAttached() {
		return bachelorResultAttached;
	}

	@Override
	public void setBachelorResultAttached(Boolean bachelorResultAttached) {
		this.bachelorResultAttached = bachelorResultAttached;
	}

	@Override
	public Boolean isPhdResultAttached() {
		return phdResultAttached;
	}

	@Override
	public void setPhdResultAttached(Boolean phdResultAttached) {
		this.phdResultAttached = phdResultAttached;
	}

	@Override
	public Boolean isMasterResultAttached() {
		return masterResultAttached;
	}

	@Override
	public void setMasterResultAttached(Boolean masterResultAttached) {
		this.masterResultAttached = masterResultAttached;
	}

	@Override
	public Boolean isIcCopyAttached() {
		return icCopyAttached;
	}

	@Override
	public void setIcCopyAttached(Boolean icCopyAttached) {
		this.icCopyAttached = icCopyAttached;
	}

	@Override
	public Boolean isPassportCopyAttached() {
		return passportCopyAttached;
	}

	@Override
	public void setPassportCopyAttached(Boolean passportCopyAttached) {
		this.passportCopyAttached = passportCopyAttached;
	}

	@Override
	public Boolean isMuetResultAttached() {
		return muetResultAttached;
	}

	@Override
	public void setMuetResultAttached(Boolean muetResultAttached) {
		this.muetResultAttached = muetResultAttached;
	}

	@Override
	public Boolean isResearchProposalAttached() {
		return researchProposalAttached;
	}

	@Override
	public void setResearchProposalAttached(Boolean researchProposalAttached) {
		this.researchProposalAttached = researchProposalAttached;
	}

	@Override
	public Boolean isSponsorLetterAttached() {
		return sponsorLetterAttached;
	}

	@Override
	public void setSponsorLetterAttached(Boolean sponsorLetterAttached) {
		this.sponsorLetterAttached = sponsorLetterAttached;
	}

	@Override
	public Boolean isBankStatementAttached() {
		return bankStatementAttached;
	}

	@Override
	public void setBankStatementAttached(Boolean bankStatementAttached) {
		this.bankStatementAttached = bankStatementAttached;
	}

	@Override
	public Boolean isRefereeFormAttached() {
		return refereeFormAttached;
	}

	@Override
	public void setRefereeFormAttached(Boolean refereeFormAttached) {
		this.refereeFormAttached = refereeFormAttached;
	}

	@Override
	public Boolean isVerified() {
		return verified;
	}

	@Override
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	@Override
	public InBidType getBidType() {
		return bidType;
	}

	@Override
	public void setBidType(InBidType bidType) {
		this.bidType = bidType;
	}

	@Override
	public InBidStatus getBidStatus() {
		return bidStatus;
	}

	@Override
	public void setBidStatus(InBidStatus bidStatus) {
		this.bidStatus = bidStatus;
	}

	@Override
	public InBidResponse getBidResponse() {
		return bidResponse;
	}

	@Override
	public void setBidResponse(InBidResponse bidResponse) {
		this.bidResponse = bidResponse;
	}

	public Boolean isDeclared() {
		return declared;
	}

	public void setDeclared(Boolean declared) {
		this.declared = declared;
	}

	public Boolean isCopiedAddress() {
		return copyAddressed;
	}

	public void setCopiedAddress(Boolean copyAddressed) {
		this.copyAddressed = copyAddressed;
	}

	public String getMailingAddress1() {
		return mailingAddress1;
	}

	public void setMailingAddress1(String mailingAddress1) {
		this.mailingAddress1 = mailingAddress1;
	}

	public String getMailingAddress2() {
		return mailingAddress2;
	}

	public void setMailingAddress2(String mailingAddress2) {
		this.mailingAddress2 = mailingAddress2;
	}

	public String getMailingAddress3() {
		return mailingAddress3;
	}

	public void setMailingAddress3(String mailingAddress3) {
		this.mailingAddress3 = mailingAddress3;
	}

	public InCountryCode getMailingCountryCode() {
		return mailingCountryCode;
	}

	public void setMailingCountryCode(InCountryCode mailingCountryCode) {
		this.mailingCountryCode = mailingCountryCode;
	}

	public InStateCode getMailingStateCode() {
		return mailingStateCode;
	}

	public void setMailingStateCode(InStateCode mailingStateCode) {
		this.mailingStateCode = mailingStateCode;
	}

	public String getOfficialAddress1() {
		return officialAddress1;
	}

	public void setOfficialAddress1(String officialAddress1) {
		this.officialAddress1 = officialAddress1;
	}

	public String getOfficialAddress2() {
		return officialAddress2;
	}

	public void setOfficialAddress2(String officialAddress2) {
		this.officialAddress2 = officialAddress2;
	}

	public String getOfficialAddress3() {
		return officialAddress3;
	}

	public void setOfficialAddress3(String officialAddress3) {
		this.officialAddress3 = officialAddress3;
	}

	public String getMailingPostcode() {
		return mailingPostcode;
	}

	public void setMailingPostcode(String mailingPostcode) {
		this.mailingPostcode = mailingPostcode;
	}

	public String getOfficialPostcode() {
		return officialPostcode;
	}

	public void setOfficialPostcode(String officialPostcode) {
		this.officialPostcode = officialPostcode;
	}

	public InCountryCode getOfficialCountryCode() {
		return officialCountryCode;
	}

	public void setOfficialCountryCode(InCountryCode officialCountryCode) {
		this.officialCountryCode = officialCountryCode;
	}

	public InStateCode getOfficialStateCode() {
		return officialStateCode;
	}

	public void setOfficialStateCode(InStateCode officialStateCode) {
		this.officialStateCode = officialStateCode;
	}

	@Override
	public InBankCode getBankCode() {
		return bankCode;
	}

	@Override
	public void setBankCode(InBankCode bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public InResidencyCode getResidencyCode() {
		return residencyCode;
	}

	@Override
	public void setResidencyCode(InResidencyCode residenceCode) {
		this.residencyCode = residenceCode;
	}

	@Override
	public InMaritalCode getMaritalCode() {
		return maritalCode;
	}

	@Override
	public void setMaritalCode(InMaritalCode maritalCode) {
		this.maritalCode = maritalCode;
	}

	@Override
	public InNationalityCode getNationalityCode() {
		return nationalityCode;
	}

	@Override
	public void setNationalityCode(InNationalityCode nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	@Override
	public InRaceCode getRaceCode() {
		return raceCode;
	}

	@Override
	public void setRaceCode(InRaceCode raceCode) {
		this.raceCode = raceCode;
	}

	@Override
	public InEthnicityCode getEthnicityCode() {
		return ethnicityCode;
	}

	@Override
	public void setEthnicityCode(InEthnicityCode ethnicityCode) {
		this.ethnicityCode = ethnicityCode;
	}

	@Override
	public InGenderCode getGenderCode() {
		return genderCode;
	}

	@Override
	public void setGenderCode(InGenderCode genderCode) {
		this.genderCode = genderCode;
	}

	@Override
	public InDisabilityCode getDisabilityCode() {
		return disabilityCode;
	}

	@Override
	public void setDisabilityCode(InDisabilityCode disabilityCode) {
		this.disabilityCode = disabilityCode;
	}

	@Override
	public InReligionCode getReligionCode() {
		return religionCode;
	}

	@Override
	public void setReligionCode(InReligionCode religionCode) {
		this.religionCode = religionCode;
	}

	@Override
	public String getPassportNo() {
		return passport;
	}

	@Override
	public void setPassportNo(String passport) {
		this.passport = passport;
	}

	@Override
	public Date getPassportExpDate() {
		return passExpDate;
	}

	@Override
	public void setPassportExpDate(Date passExpDate) {
		this.passExpDate = passExpDate;
	}

	@Override
	public InVisaType getVisaType() {
		return visaType;
	}

	@Override
	public void setVisaType(InVisaType visaType) {
		this.visaType = visaType;
	}
	

	@Override
	public InProgramOffering getProgramSelection() {
		return programSelection;
	}

	@Override
	public void setProgramSelection(InProgramOffering programSelection) {
		this.programSelection = programSelection;
	}

	public InSupervisorOffering getSupervisorSelection() {
		return supervisorSelection;
	}

	public void setSupervisorSelection(InSupervisorOffering supervisorSelection) {
		this.supervisorSelection = supervisorSelection;
	}

	@Override
	public InStudyModeOffering getStudyModeSelection() {
		return studyModeSelection;
	}

	@Override
	public void setStudyModeSelection(InStudyModeOffering studyModeSelection) {
		this.studyModeSelection = studyModeSelection;
	}

	@Override
	public InStudyCenterCode getStudyCenterCode() {
		return studyCenterCode;
	}

	@Override
	public void setStudyCenterCode(InStudyCenterCode studyCenterCode) {
		this.studyCenterCode = studyCenterCode;
	}

	@Override
	public InEmploymentSectorCode getEmploymentSectorCode() {
		return employmentSectorCode;
	}

	@Override
	public void setEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode) {
		this.employmentSectorCode = employmentSectorCode;
	}

	@Override
	public InIntake getIntake() {
		return intake;
	}

	@Override
	public void setIntake(InIntake intake) {
		this.intake = intake;
	}

	@Override
	public InApplicant getApplicant() {
		return applicant;
	}

	@Override
	public void setApplicant(InApplicant applicant) {
		this.applicant = applicant;
	}

	@Override
	public List<InEducation> getEducations() {
		return educations;
	}

	@Override
	public void setEducations(List<InEducation> educations) {
		this.educations = educations;
	}

	@Override
	public List<InInvolvement> getInvolvements() {
		return involvements;
	}

	@Override
	public void setInvolvements(List<InInvolvement> involvementExperiences) {
		this.involvements = involvementExperiences;
	}

	@Override
	public List<InEmployment> getEmployments() {
		return employments;
	}

	@Override
	public void setEmployments(List<InEmployment> employments) {
		this.employments = employments;
	}

	@Override
	public List<InLanguage> getLanguages() {
		return languages;
	}

	@Override
	public void setLanguages(List<InLanguage> languages) {
		this.languages = languages;
	}

	@Override
	public List<InGuardian> getGuardians() {
		return guardians;
	}

	@Override
	public void setGuardians(List<InGuardian> guardians) {
		this.guardians = guardians;
	}

	@Override
	public List<InGuarantor> getGuarantors() {
		return guarantors;
	}

	@Override
	public void setGuarantors(List<InGuarantor> guarantors) {
		this.guarantors = guarantors;
	}

	@Override
	public List<InReferee> getReferees() {
		return referees;
	}

	@Override
	public void setReferees(List<InReferee> referees) {
		this.referees = referees;
	}

	@Override
	public List<InResult> getResults() {
		return results;
	}

	@Override
	public void setResults(List<InResult> results) {
		this.results = results;
	}

	@Override
	public List<InAttachment> getAttachments() {
		return attachments;
	}

	@Override
	public void setAttachments(List<InAttachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public InMetadata getMetadata() {
		return metadata;
	}

	@Override
	public void setMetadata(InMetadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public Class<?> getInterfaceClass() {
		return InIntakeApplication.class;
	}

	@Override
	public String getPosition() {
		return position;
	}

	@Override
	public void setPosition(String position) {
		this.position = position;

	}

	@Override
	public String getEmployerAddress1() {
		return employerAddress1;
	}

	@Override
	public void setEmployerAddress1(String employerAddress1) {
		this.employerAddress1 = employerAddress1;

	}

	@Override
	public String getEmployerAddress2() {
		return employerAddress2;
	}

	@Override
	public void setEmployerAddress2(String employerAddress2) {
		this.employerAddress2 = employerAddress2;
	}

	@Override
	public String getEmployerAddress3() {
		return employerAddress3;
	}

	@Override
	public void setEmployerAddress3(String employerAddress3) {
		this.employerAddress3 = employerAddress3;

	}

	@Override
	public String getEmployerPostcode() {
		return employerPostcode;
	}

	@Override
	public void setEmployerPostcode(String employerPostcode) {
		this.employerPostcode = employerPostcode;

	}

	@Override
	public InStateCode getEmployerState() {
		return employerState;
	}

	@Override
	public void setEmployerState(InStateCode employerState) {
		this.employerState = employerState;

	}

	@Override
	public String getEmployerNo() {
		return employerNo;
	}

	@Override
	public void setEmployerNo(String employerNo) {
		this.employerNo = employerNo;
	}

	@Override
	public InEmploymentTypeCode getEmploymentTypeCode() {
		return employmentTypeCode;
	}

	@Override
	public void setEmploymentTypeCode(InEmploymentTypeCode employmentTypeCode) {
		this.employmentTypeCode = employmentTypeCode;

	}

	@Override
	public Boolean isPassportImageAttached() {
		return passportImageAttached;
	}

	@Override
	public void setPassportImageAttached(Boolean passportImageAttached) {
		this.passportImageAttached = passportImageAttached;
	}

	@Override
	public Boolean isEmploymentVerificationAttached() {
		return employmentVerificationAttached;
	}

	@Override
	public void setEmploymentVerificationAttached(Boolean employmentVerificationAttached) {
		this.employmentVerificationAttached = employmentVerificationAttached;
	}
	
	@Override
	public Boolean isApelCertificateAttached() {
		return apelCertificateAttached;
	}

	@Override
	public void setApelCertificateAttached(Boolean apelCertificateAttached) {
		this.apelCertificateAttached = apelCertificateAttached;
	}

	@Override
	public String getGuardianName() {
		return guardianName;
	}

	@Override
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
		
	}
	
	@Override
	public String getGuardianIdentityNo() {
		return guardianIdentityNo;
	}

	@Override
	public void setGuardianIdentityNo(String guardianIdentityNo) {
	     this.guardianIdentityNo = guardianIdentityNo;
	}
	
	@Override
	public String getGuardianPhoneNo() {
		return guardianPhoneNo;
	}

	@Override
	public void setGuardianPhoneNo(String guardianPhoneNo) {
	     this.guardianPhoneNo = guardianPhoneNo;
	}

	@Override
	public String getGuardianAddress1() {
		
		return guardianAddress1;
	}

	@Override
	public void setGuardianAddress1(String guardianAddress1) {
		this.guardianAddress1 =guardianAddress1;
		
	}

	@Override
	public String getGuardianAddress2() {
		
		return guardianAddress2;
	}

	@Override
	public void setGuardianAddress2(String guardianAddress2) {
		this.guardianAddress2 =guardianAddress2;
		
	}

	@Override
	public String getGuardianAddress3() {
	
		return guardianAddress3;
	}

	@Override
	public void setGuardianAddress3(String guardianAddress3) {
		this.guardianAddress3 = guardianAddress3;
		
	}

	@Override
	public String getGuardianPostcode() {
		
		return guardianPostcode;
	}

	@Override
	public void setGuardianPostcode(String guardianPostcode) {
	this.guardianPostcode = guardianPostcode;
		
	}

	@Override
	public InStateCode getGuardianState() {
		
		return guardianState;
	}

	@Override
	public void setGuardianState(InStateCode guardianState) {
		this. guardianState =  guardianState;
	}

	@Override
	public InCountryCode getGuardianCountryCode() {
		
		return guardianCountryCode;
	}

	@Override
	public void setGuardianCountryCode(InCountryCode guardianCountryCode) {
		this.guardianCountryCode = guardianCountryCode;
	}
	
	@Override
	public InGuardianType getGuardianType() {
		return guardianType;
	}

	@Override
	public void setGuardianType(InGuardianType guardianType) {
		this.guardianType = guardianType;
	}
	
	
	

}
