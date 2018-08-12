package my.edu.umk.pams.intake.web.module.common.controller;

import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.core.InMetaObject;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.web.module.application.vo.FieldCode;
import my.edu.umk.pams.intake.web.module.common.vo.*;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;
import my.edu.umk.pams.intake.web.module.core.vo.MetaState;
import my.edu.umk.pams.intake.web.module.policy.controller.PolicyTransformer;
import my.edu.umk.pams.intake.web.module.policy.vo.SupervisorOffering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PAMS
 */
@Component("commonTransformer")
public class CommonTransformer {

	@Autowired
	private PolicyTransformer policyTransformer;

	// ====================================================================================================
	// GRADUATE CENTER
	// ====================================================================================================

	public GraduateCenter toGraduateCenterVo(InGraduateCenter e) {
		GraduateCenter vo = new GraduateCenter();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<GraduateCenter> toGraduateCenterVos(List<InGraduateCenter> e) {
		List<GraduateCenter> vos = e.stream().map((e1) -> toGraduateCenterVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// STUDY MODE
	// ====================================================================================================

	public StudyMode toStudyModeVo(InStudyMode e) {
		StudyMode vo = new StudyMode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		vo.setPrefix(e.getPrefix());
		return vo;
	}

	public List<StudyMode> toStudyModeVos(List<InStudyMode> e) {
		List<StudyMode> vos = e.stream().map((e1) -> toStudyModeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// GENDER CODE
	// ====================================================================================================

	public GenderCode toGenderCodeVo(InGenderCode e) {
		if (null == e)
			return null;
		GenderCode vo = new GenderCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());

		return vo;
	}

	public List<GenderCode> toGenderCodeVos(List<InGenderCode> e) {
		List<GenderCode> vos = e.stream().map((e1) -> toGenderCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// VENUE CODE
	// ====================================================================================================

	public VenueCode toVenueCodeVo(InVenueCode e) {
		if (null == e)
			return null;
		VenueCode vo = new VenueCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setStartTimeEn(e.getStartTimeEn());
		vo.setStartTimeMs(e.getStartTimeMs());
		vo.setEndTimeEn(e.getEndTimeEn());
		vo.setEndTimeMs(e.getEndTimeMs());
		vo.setRegistrationDate(e.getRegistrationDate());
		vo.setRegistrationLocation(e.getRegistrationLocation());

		return vo;
	}

	public List<VenueCode> toVenueCodeVos(List<InVenueCode> e) {
		List<VenueCode> vos = e.stream().map((e1) -> toVenueCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// BANK CODE
	// ====================================================================================================

	public BankCode toBankCodeVo(InBankCode e) {
		BankCode vo = new BankCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setName(e.getName());
		return vo;
	}

	public List<BankCode> toBankCodeVos(List<InBankCode> e) {
		List<BankCode> vos = e.stream().map((e1) -> toBankCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// PROGRAM CODE
	// ====================================================================================================

	public ProgramCode toProgramCodeVo(InProgramCode e) {
		ProgramCode vo = new ProgramCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		vo.setGraduateCenter(toGraduateCenterVo(e.getGraduateCenter()));
		vo.setProgramLevel(policyTransformer.toProgramLevelVo(e.getProgramLevel()));
		vo.setFacultyCode(toFacultyCodeVo(e.getFacultyCode()));
		return vo;
	}

	public List<ProgramCode> toProgramCodeVos(List<InProgramCode> e) {
		List<ProgramCode> vos = e.stream().map((e1) -> toProgramCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// FIELD CODE
	// ====================================================================================================
	public FieldCode toFieldCodeVo(InFieldCode e) {
		FieldCode vo = new FieldCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		/* vo.setGraduateCenter(toGraduateCenterVo(e.getGraduateCenter())); */
		return vo;
	}

	public List<FieldCode> toFieldCodeVos(List<InFieldCode> e) {
		List<FieldCode> vos = e.stream().map((e1) -> toFieldCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}
	// ====================================================================================================
	// PROGRAM FIELD CODE
	// ====================================================================================================

	public ProgramFieldCode toProgramFieldCodeVo(InProgramFieldCode e) {
		ProgramFieldCode vo = new ProgramFieldCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setProgramCode(toProgramCodeVo(e.getProgramCode()));
		vo.setFieldCode(toFieldCodeVo(e.getFieldCode()));
		vo.setFacultyCode(toFacultyCodeVo(e.getFacultyCode()));
		return vo;
	}

	public List<ProgramFieldCode> toProgramFieldCodeVos(List<InProgramFieldCode> e) {
		List<ProgramFieldCode> vos = e.stream().map((e1) -> toProgramFieldCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// FACULTY CODE
	// ====================================================================================================

	public FacultyCode toFacultyCodeVo(InFacultyCode e) {
		FacultyCode vo = new FacultyCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<FacultyCode> toFacultyCodeVos(List<InFacultyCode> e) {
		List<FacultyCode> vos = e.stream().map((e1) -> toFacultyCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// SUPERVISOR CODE
	// ====================================================================================================

	public SupervisorCode toSupervisorCodeVo(InSupervisorCode e) {
		SupervisorCode vo = new SupervisorCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setName(e.getName());
		vo.setTitle(e.getTitle());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<SupervisorCode> toSupervisorCodeVos(List<InSupervisorCode> e) {
		List<SupervisorCode> vos = e.stream().map((e1) -> toSupervisorCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// SUPERVISOR OFFERING
	// ====================================================================================================

	public SupervisorOffering toSupervisorOfferingVo(InSupervisorOffering e) {
		SupervisorOffering vo = new SupervisorOffering();
		vo.setId(e.getId());
		vo.setSupervisorCode(this.toSupervisorCodeVo(e.getSupervisorCode()));
		vo.setProgramLevel(policyTransformer.toProgramLevelVo(e.getProgramLevel()));
		return vo;
	}

	public List<SupervisorOffering> toSupervisorOfferingVos(List<InSupervisorOffering> e) {
		List<SupervisorOffering> vos = e.stream().map((e1) -> toSupervisorOfferingVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// SCHOOL CODE
	// ====================================================================================================

	public SchoolCode toSchoolCodeVo(InSchoolCode e) {
		SchoolCode vo = new SchoolCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescription(e.getDescription());
		return vo;
	}

	public List<SchoolCode> toSchoolCodeVos(List<InSchoolCode> e) {
		List<SchoolCode> vos = e.stream().map((e1) -> toSchoolCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// MARITAL_CODE
	// ====================================================================================================

	public MaritalCode toMaritalCodeVo(InMaritalCode e) {
		if (null == e)
			return null;
		MaritalCode vo = new MaritalCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionEn(e.getDescriptionEn());
		vo.setDescriptionMs(e.getDescriptionMs());
		return vo;
	}

	public List<MaritalCode> toMaritalCodeVos(List<InMaritalCode> e) {
		List<MaritalCode> vos = e.stream().map((e1) -> toMaritalCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// RELIGION CODE
	// ====================================================================================================

	public ReligionCode toReligionCodeVo(InReligionCode e) {
		if (null == e)
			return null;
		ReligionCode vo = new ReligionCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<ReligionCode> toReligionCodeVos(List<InReligionCode> e) {
		List<ReligionCode> vos = e.stream().map((e1) -> toReligionCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// COUNTRY_CODE
	// ====================================================================================================

	public CountryCode toCountryCodeVo(InCountryCode e) {
		if (null == e)
			return null;
		CountryCode vo = new CountryCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<CountryCode> toCountryCodeVos(List<InCountryCode> e) {
		List<CountryCode> vos = e.stream().map((e1) -> toCountryCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// STATE_CODE
	// ====================================================================================================

	public StateCode toStateCodeVo(InStateCode e) {
		if (null == e)
			return null;
		StateCode vo = new StateCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		vo.setCountryCode(toCountryCodeVo(e.getCountryCode()));
		return vo;
	}

	public List<StateCode> toStateCodeVos(List<InStateCode> e) {
		List<StateCode> vos = e.stream().map((e1) -> toStateCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// RACE_CODE
	// ====================================================================================================

	public RaceCode toRaceCodeVo(InRaceCode e) {
		if (null == e)
			return null;
		RaceCode vo = new RaceCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<RaceCode> toRaceCodeVos(List<InRaceCode> e) {
		List<RaceCode> vos = e.stream().map((e1) -> toRaceCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// ETHNICITY CODE
	// ====================================================================================================

	public EthnicityCode toEthnicityCodeVo(InEthnicityCode e) {
		if (null == e)
			return null;
		EthnicityCode vo = new EthnicityCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<EthnicityCode> toEthnicityCodeVos(List<InEthnicityCode> e) {
		List<EthnicityCode> vos = e.stream().map((e1) -> toEthnicityCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// NATIONALITY CODE
	// ====================================================================================================

	public NationalityCode toNationalityCodeVo(InNationalityCode e) {
		if (null == e)
			return null;
		NationalityCode vo = new NationalityCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<NationalityCode> toNationalityCodeVos(List<InNationalityCode> e) {
		List<NationalityCode> vos = e.stream().map((e1) -> toNationalityCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// PROMO CODE
	// ====================================================================================================

	public PromoCode toPromoCodeVo(InPromoCode e) {
		if (null == e)
			return null;
		PromoCode vo = new PromoCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		return vo;
	}

	public List<PromoCode> toPromoCodeVos(List<InPromoCode> e) {
		List<PromoCode> vos = e.stream().map((e1) -> toPromoCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// DUN_CODE
	// ====================================================================================================

	public DunCode toDunCodeVo(InDunCode e) {
		DunCode vo = new DunCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescription(e.getDescription());
		// vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<DunCode> toDunCodeVos(List<InDunCode> e) {
		List<DunCode> vos = e.stream().map((e1) -> toDunCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// DISTRICT_CODE
	// ====================================================================================================

	public DistrictCode toDistrictCodeVo(InDistrictCode e) {
		DistrictCode vo = new DistrictCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescription(e.getDescription());
		return vo;
	}

	public List<DistrictCode> toDistrictCodeVos(List<InDistrictCode> e) {
		List<DistrictCode> vos = e.stream().map((e1) -> toDistrictCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// DISABILITY CODE
	// ====================================================================================================

	public DisabilityCode toDisabilityCodeVo(InDisabilityCode e) {
		DisabilityCode vo = new DisabilityCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<DisabilityCode> toDisabilityCodeVos(List<InDisabilityCode> e) {
		List<DisabilityCode> vos = e.stream().map((e1) -> toDisabilityCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// RESIDENCY_CODE
	// ====================================================================================================

	public ResidencyCode toResidencyCodeVo(InResidencyCode e) {
		if (null == e)
			return null;
		ResidencyCode vo = new ResidencyCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<ResidencyCode> toResidencyCodeVos(List<InResidencyCode> e) {
		List<ResidencyCode> vos = e.stream().map((e1) -> toResidencyCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}
	// ====================================================================================================
	// STUDY CENTER CODE
	// ====================================================================================================

	public StudyCenterCode toStudyCenterCodeVo(InStudyCenterCode e) {
		if (null == e)
			return null;
		StudyCenterCode vo = new StudyCenterCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<StudyCenterCode> toStudyCenterCodeVos(List<InStudyCenterCode> e) {
		List<StudyCenterCode> vos = e.stream().map((e1) -> toStudyCenterCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// PARLIAMENT CODE
	// ====================================================================================================

	public ParliamentCode toParliamentCodeVo(InParliamentCode e) {
		ParliamentCode vo = new ParliamentCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescription(e.getDescription());
		return vo;
	}

	public List<ParliamentCode> toParliamentCodeVos(List<InParliamentCode> e) {
		List<ParliamentCode> vos = e.stream().map((e1) -> toParliamentCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// LANGUAGE CODE
	// ====================================================================================================

	public LanguageCode toLanguageCodeVo(InLanguageCode e) {
		LanguageCode vo = new LanguageCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<LanguageCode> toLanguageCodeVos(List<InLanguageCode> e) {
		List<LanguageCode> vos = e.stream().map((e1) -> toLanguageCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// SUBJECT CODE
	// ====================================================================================================

	public SubjectCode toSubjectCodeVo(InSubjectCode e) {
		SubjectCode vo = new SubjectCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<SubjectCode> toSubjectCodeVos(List<InSubjectCode> e) {
		List<SubjectCode> vos = e.stream().map((e1) -> toSubjectCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// Grade CODE
	// ====================================================================================================

	public GradeCode toGradeCodeVo(InGradeCode e) {
		GradeCode vo = new GradeCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescription(e.getDescription());
		vo.setOrdinal(e.getOrdinal());
		return vo;
	}

	public List<GradeCode> toGradeCodeVos(List<InGradeCode> e) {
		List<GradeCode> vos = e.stream().map((e1) -> toGradeCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	public void decorateMeta(InMetaObject metaObject, MetaObject vo) {
		vo.setMetaState(MetaState.get(metaObject.getMetadata().getState().ordinal()));
		vo.setCreatedDate(metaObject.getMetadata().getCreatedDate());
		vo.setModifiedDate(metaObject.getMetadata().getModifiedDate());
		vo.setDeletedDate(metaObject.getMetadata().getDeletedDate());
	}

	// ====================================================================================================
	// EMPLOYMENT TYPE CODE
	// ====================================================================================================

	public EmploymentTypeCode toEmploymentTypeCodeVo(InEmploymentTypeCode e) {
		if (null == e)
			return null;
		EmploymentTypeCode vo = new EmploymentTypeCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<EmploymentTypeCode> toEmploymentTypeCodeVos(List<InEmploymentTypeCode> e) {
		List<EmploymentTypeCode> vos = e.stream().map((e1) -> toEmploymentTypeCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// EMPLOYMENT SECTOR CODE
	// ====================================================================================================

	public EmploymentSectorCode toEmploymentSectorCodeVo(InEmploymentSectorCode e) {
		if (null == e)
			return null;
		EmploymentSectorCode vo = new EmploymentSectorCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<EmploymentSectorCode> toEmploymentSectorCodeVos(List<InEmploymentSectorCode> e) {
		List<EmploymentSectorCode> vos = e.stream().map((e1) -> toEmploymentSectorCodeVo(e1))
				.collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// GUARDIAN TYPE CODE
	// ====================================================================================================

	public GuardianTypeCode toGuardianTypeCodeVo(InGuardianTypeCode e) {
		if (null == e)
			return null;
		GuardianTypeCode vo = new GuardianTypeCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setDescriptionMs(e.getDescriptionMs());
		vo.setDescriptionEn(e.getDescriptionEn());
		return vo;
	}

	public List<GuardianTypeCode> toGuardianTypeCodeVos(List<InGuardianTypeCode> e) {
		List<GuardianTypeCode> vos = e.stream().map((e1) -> toGuardianTypeCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// SPM RESULT
	// ====================================================================================================

	public SpmResult toSpmResultVo(InSpmResult e) {
		if (null == e)
			return null;
		SpmResult vo = new SpmResult();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setAggregate(e.getAggregate());
		vo.setGraduationYear(e.getGraduationYear());
		vo.setGradeCode(this.toGradeCodeVo(e.getGradeCode()));
		vo.setSubjectCode(this.toSubjectCodeVo(e.getSubjectCode()));
		return vo;
	}

	public List<SpmResult> toSpmResultVos(List<InSpmResult> e) {
		List<SpmResult> vos = e.stream().map((e1) -> toSpmResultVo(e1)).collect(Collectors.toList());
		return vos;
	}

	// ====================================================================================================
	// SPM SUBJECT CODE
	// ====================================================================================================

	public SpmSubjectCode toSpmSubjectCodeVo(InSpmSubjectCode e) {
		SpmSubjectCode vo = new SpmSubjectCode();
		vo.setId(e.getId());
		vo.setCode(e.getCode());
		vo.setSubjectCode(this.toSubjectCodeVo(e.getSubjectCode()));
		return vo;
	}

	public List<SpmSubjectCode> toSpmSubjectCodeVos(List<InSpmSubjectCode> e) {
		List<SpmSubjectCode> vos = e.stream().map((e1) -> toSpmSubjectCodeVo(e1)).collect(Collectors.toList());
		return vos;
	}
}
