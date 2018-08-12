package my.edu.umk.pams.intake.common.service;

import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;

import java.util.List;

public interface CommonService {

	// ====================================================================================================
	// GRADUATE CENTER
	// ====================================================================================================

	InGraduateCenter findGraduateCenterById(Long id);

	InGraduateCenter findGraduateCenterByCode(String code);

	List<InGraduateCenter> findGraduateCenters();

	List<InGraduateCenter> findGraduateCenters(String filter, Integer offset, Integer limit);

	Integer countGraduateCenter(String filter);

	void saveGraduateCenter(InGraduateCenter graduateCenter);

	void updateGraduateCenter(InGraduateCenter graduateCenter);

	void removeGraduateCenter(InGraduateCenter graduateCenter);

	// ====================================================================================================
	// COUNTRY CODE
	// ====================================================================================================

	InCountryCode findCountryCodeById(Long id);

	InCountryCode findCountryCodeByCode(String code);

	List<InCountryCode> findCountryCodes();

	List<InCountryCode> findCountryCodes(String filter, Integer offset, Integer limit);

	Integer countCountryCode(String filter);

	void saveCountryCode(InCountryCode countryCode);

	void updateCountryCode(InCountryCode countryCode);

	void removeCountryCode(InCountryCode countryCode);

	// ====================================================================================================
	// STATE CODE
	// ====================================================================================================

	InStateCode findStateCodeById(Long id);

	InStateCode findStateCodeByCode(String code);

	List<InStateCode> findStateCodes();

	List<InStateCode> findStateCodes(Integer offset, Integer limit);

	List<InStateCode> findStateCodes(String filter, Integer offset, Integer limit);

	List<InStateCode> findStateCodes(InCountryCode countryCode, Integer offset, Integer limit);

	List<InStateCode> findStateCodes(String filter, InCountryCode countryCode, Integer offset, Integer limit);

	Integer countStateCode();

	Integer countStateCode(String filter);

	Integer countStateCode(InCountryCode countryCode);

	Integer countStateCode(String filter, InCountryCode countryCode);

	void saveStateCode(InStateCode stateCode);

	void updateStateCode(InStateCode stateCode);

	void removeStateCode(InStateCode stateCode);

	// ====================================================================================================
	// DISTRICT CODE
	// ====================================================================================================

	InDistrictCode findDistrictCodeById(Long id);

	InDistrictCode findDistrictCodeByCode(String code);

	List<InDistrictCode> findDistrictCodes(InStateCode stateCod, Integer offset, Integer limit);

	List<InDistrictCode> findDistrictCodes();

	List<InDistrictCode> findDistrictCodes(String filter, Integer offset, Integer limit);

	List<InDistrictCode> findDistrictCodes(String filter, InStateCode stateCode, Integer offset, Integer limit);

	Integer countDistrictCode(InStateCode stateCode);

	Integer countDistrictCode(String filter, InStateCode stateCode);

	void saveDistrictCode(InDistrictCode districtCode);

	void updateDistrictCode(InDistrictCode districtCode);

	void removeDistrictCode(InDistrictCode districtCode);

	// ====================================================================================================
	// CITY CODE
	// ====================================================================================================

	InCityCode findCityCodeById(Long id);

	InCityCode findCityCodeByCode(String code);

	List<InCityCode> findCityCodes(InStateCode stateCode, Integer offset, Integer limit);

	List<InCityCode> findCityCodes(String filter, InStateCode stateCode, Integer offset, Integer limit);

	Integer countCityCode(InStateCode stateCode);

	Integer countCityCode(String filter, InStateCode stateCode);

	void saveCityCode(InCityCode cityCode);

	void updateCityCode(InCityCode cityCode);

	void removeCityCode(InCityCode cityCode);

	// ====================================================================================================
	// FACULTY CODE
	// ====================================================================================================

	InFacultyCode findFacultyCodeById(Long id);

	InFacultyCode findFacultyCodeByCode(String code);

	List<InFacultyCode> findFacultyCodes();

	List<InFacultyCode> findFacultyCodes(String filter, Integer offset, Integer limit);

	Integer countFacultyCode();

	Integer countFacultyCode(String filter);

	void saveFacultyCode(InFacultyCode facultyCode);

	void updateFacultyCode(InFacultyCode facultyCode);

	void removeFacultyCode(InFacultyCode facultyCode);

	// ====================================================================================================
	// STUDY CENTER CODE
	// ====================================================================================================

	InStudyCenterCode findStudyCenterCodeById(Long id);

	InStudyCenterCode findStudyCenterCodeByCode(String code);

	List<InStudyCenterCode> findStudyCenterCodes();

	List<InStudyCenterCode> findStudyCenterCodes(String filter, Integer offset, Integer limit);

	Integer countStudyCenterCode();

	Integer countStudyCenterCode(String filter);

	void saveStudyCenterCode(InStudyCenterCode studyCenterCode);

	void updateStudyCenterCode(InStudyCenterCode studyCenterCode);

	void removeStudyCenterCode(InStudyCenterCode studyCenterCode);

	// ====================================================================================================
	// VENUE CODE
	// ====================================================================================================

	InVenueCode findVenueCodeById(Long id);

	InVenueCode findVenueCodeByCode(String code);

	List<InVenueCode> findVenueCodes();

	List<InVenueCode> findVenueCodes(String filter, Integer offset, Integer limit);

	Integer countVenueCode();

	Integer countVenueCode(String filter);

	void saveVenueCode(InVenueCode venueCode);

	void updateVenueCode(InVenueCode venueCode);

	void removeVenueCode(InVenueCode venueCode);

	// ====================================================================================================
	// BANK CODE
	// ====================================================================================================

	InBankCode findBankCodeById(Long id);

	InBankCode findBankCodeByCode(String code);

	List<InBankCode> findBankCodes();

	List<InBankCode> findBankCodes(String filter, Integer offset, Integer limit);

	Integer countBankCode();

	Integer countBankCode(String filter);

	void saveBankCode(InBankCode bankCode);

	void updateBankCode(InBankCode bankCode);

	void removeBankCode(InBankCode bankCode);

	// ====================================================================================================
	// PROGRAM CODE
	// ====================================================================================================

	InProgramCode findProgramCodeById(Long id);

	InProgramCode findProgramCodeByCode(String code);

	List<InProgramCode> findProgramCodes();

	List<InProgramCode> findProgramCodes(InFacultyCode facultyCode);

	List<InProgramCode> findProgramCodes(InGraduateCenter graduateCenter);

	List<InProgramCode> findProgramCodes(InFacultyCode facultyCode, InProgramLevel programLevel);

	List<InProgramCode> findProgramCodes(InGraduateCenter graduateCenter, InProgramLevel programLevel);

	List<InProgramCode> findProgramCodes(String filter, Integer offset, Integer limit);

	List<InProgramCode> findProgramCodesByProgramLevel(InProgramLevel inProgramLevel, String filter, Integer offset,
			Integer limit);

	Integer countProgramCode();

	Integer countProgramCode(String filter);

	Integer countProgramCode(InFacultyCode facultyCode);

	Integer countProgramCode(InGraduateCenter graduateCenter);

	Integer countProgramCode(InFacultyCode facultyCode, InProgramLevel programLevel);

	Integer countProgramCode(InGraduateCenter graduateCenter, InProgramLevel programLevel);

	void saveProgramCode(InProgramCode programCode);

	void updateProgramCode(InProgramCode programCode);

	void removeProgramCode(InProgramCode programCode);

	// ====================================================================================================
	// FIELD CODE
	// ====================================================================================================

	InFieldCode findFieldCodeById(Long id);

	InFieldCode findFieldCodeByCode(String code);

	List<InFieldCode> findFieldCodes();

	List<InFieldCode> findFieldCodes(Integer offset, Integer limit);

	List<InFieldCode> findFieldCodes(String filter, Integer offset, Integer limit);

	Integer countFieldCode();

	Integer countFieldCode(String filter);

	void saveFieldCode(InFieldCode fieldCode);

	void updateFieldCode(InFieldCode fieldCode);

	void removeFieldCode(InFieldCode fieldCode);

	// ====================================================================================================
	// RELIGION CODE
	// ====================================================================================================

	InReligionCode findReligionCodeById(Long id);

	InReligionCode findReligionCodeByCode(String code);

	List<InReligionCode> findReligionCodes();

	List<InReligionCode> findReligionCodes(String filter, Integer offset, Integer limit);

	Integer countReligionCode();

	Integer countReligionCode(String filter);

	void saveReligionCode(InReligionCode religionCode);

	void updateReligionCode(InReligionCode religionCode);

	void removeReligionCode(InReligionCode religionCode);

	// ====================================================================================================
	// SUBJECT CODE
	// ====================================================================================================

	InSubjectCode findSubjectCodeById(Long id);

	InSubjectCode findSubjectCodeByCode(String code);

	List<InSubjectCode> findSubjectCodes();

	List<InSubjectCode> findSubjectCodes(Integer offset, Integer limit);

	List<InSubjectCode> findSubjectCodes(String filter, Integer offset, Integer limit);

	Integer countSubjectCode();

	Integer countSubjectCode(String filter);

	void saveSubjectCode(InSubjectCode subjectCode);

	void updateSubjectCode(InSubjectCode subjectCode);

	void removeSubjectCode(InSubjectCode subjectCode);

	// ====================================================================================================
	// GRADE CODE
	// ====================================================================================================

	InGradeCode findGradeCodeById(Long id);

	InGradeCode findGradeCodeByCode(String code);

	List<InGradeCode> findGradeCodes();

	List<InGradeCode> findGradeCodes(Integer offset, Integer limit);

	List<InGradeCode> findGradeCodes(String filter, Integer offset, Integer limit);

	Integer countGradeCode();

	Integer countGradeCode(String filter);

	void saveGradeCode(InGradeCode gradeCode);

	void updateGradeCode(InGradeCode gradeCode);

	void removeGradeCode(InGradeCode gradeCode);

	// ====================================================================================================
	// GENDER CODE
	// ====================================================================================================

	InGenderCode findGenderCodeById(Long id);

	InGenderCode findGenderCodeByCode(String code);

	List<InGenderCode> findGenderCodes();

	List<InGenderCode> findGenderCodes(String filter, Integer offset, Integer limit);

	Integer countGenderCode();

	Integer countGenderCode(String filter);

	void saveGenderCode(InGenderCode genderCode);

	void updateGenderCode(InGenderCode genderCode);

	void removeGenderCode(InGenderCode genderCode);

	// ====================================================================================================
	// BUMI CODE
	// ====================================================================================================

	InBumiCode findBumiCodeById(Long id);

	InBumiCode findBumiCodeByCode(String code);

	List<InBumiCode> findBumiCodes();

	List<InBumiCode> findBumiCodes(String filter, Integer offset, Integer limit);

	Integer countBumiCode();

	Integer countBumiCode(String filter);

	void saveBumiCode(InBumiCode bumiCode);

	void updateBumiCode(InBumiCode bumiCode);

	void removeBumiCode(InBumiCode bumiCode);

	// ====================================================================================================
	// RACE CODE
	// ====================================================================================================

	InRaceCode findRaceCodeById(Long id);

	InRaceCode findRaceCodeByCode(String code);

	List<InRaceCode> findRaceCodes();

	List<InRaceCode> findRaceCodes(String filter, Integer offset, Integer limit);

	Integer countRaceCode();

	Integer countRaceCode(String filter);

	void saveRaceCode(InRaceCode raceCode);

	void updateRaceCode(InRaceCode raceCode);

	void removeRaceCode(InRaceCode raceCode);

	// ====================================================================================================
	// ETHNICITY CODE
	// ====================================================================================================

	InEthnicityCode findEthnicityCodeById(Long id);

	InEthnicityCode findEthnicityCodeByCode(String code);

	List<InEthnicityCode> findEthnicityCodes();

	List<InEthnicityCode> findEthnicityCodes(String filter, Integer offset, Integer limit);

	Integer countEthnicityCode();

	Integer countEthnicityCode(String filter);

	void saveEthnicityCode(InEthnicityCode ethnicityCode);

	void updateEthnicityCode(InEthnicityCode ethnicityCode);

	void removeEthnicityCode(InEthnicityCode ethnicityCode);

	// ====================================================================================================
	// MARITAL CODE
	// ====================================================================================================

	InMaritalCode findMaritalCodeById(Long id);

	InMaritalCode findMaritalCodeByCode(String code);

	List<InMaritalCode> findMaritalCodes();

	List<InMaritalCode> findMaritalCodes(String filter, Integer offset, Integer limit);

	Integer countMaritalCode();

	Integer countMaritalCode(String filter);

	void saveMaritalCode(InMaritalCode maritalCode);

	void updateMaritalCode(InMaritalCode maritalCode);

	void removeMaritalCode(InMaritalCode maritalCode);

	// ====================================================================================================
	// LANGUAGE CODE
	// ====================================================================================================

	InLanguageCode findLanguageCodeById(Long id);

	InLanguageCode findLanguageCodeByCode(String code);

	List<InLanguageCode> findLanguageCodes();

	List<InLanguageCode> findLanguageCodes(String filter, Integer offset, Integer limit);

	Integer countLanguageCode();

	Integer countLanguageCode(String filter);

	void saveLanguageCode(InLanguageCode languageCode);

	void updateLanguageCode(InLanguageCode languageCode);

	void removeLanguageCode(InLanguageCode languageCode);

	// ====================================================================================================
	// DEPENDENCY CODE
	// ====================================================================================================

	InDependencyCode findDependencyCodeById(Long id);

	InDependencyCode findDependencyCodeByCode(String code);

	List<InDependencyCode> findDependencyCodes();

	List<InDependencyCode> findDependencyCodes(String filter, Integer offset, Integer limit);

	Integer countDependencyCode();

	Integer countDependencyCode(String filter);

	void saveDependencyCode(InDependencyCode dependencyCode);

	void updateDependencyCode(InDependencyCode dependencyCode);

	void removeDependencyCode(InDependencyCode dependencyCode);

	// ====================================================================================================
	// DISABILITY CODE
	// ====================================================================================================

	InDisabilityCode findDisabilityCodeById(Long id);

	InDisabilityCode findDisabilityCodeByCode(String code);

	List<InDisabilityCode> findDisabilityCodes();

	List<InDisabilityCode> findDisabilityCodes(String filter, Integer offset, Integer limit);

	Integer countDisabilityCode();

	Integer countDisabilityCode(String filter);

	void saveDisabilityCode(InDisabilityCode disabilityCode);

	void updateDisabilityCode(InDisabilityCode disabilityCode);

	void removeDisabilityCode(InDisabilityCode disabilityCode);

	// ====================================================================================================
	// NATIONALITY CODE
	// ====================================================================================================

	InNationalityCode findNationalityCodeById(Long id);

	InNationalityCode findNationalityCodeByCode(String code);

	List<InNationalityCode> findNationalityCodes();

	List<InNationalityCode> findNationalityCodes(String filter, Integer offset, Integer limit);

	Integer countNationalityCode();

	Integer countNationalityCode(String filter);

	void saveNationalityCode(InNationalityCode nationalityCode);

	void updateNationalityCode(InNationalityCode nationalityCode);

	void removeNationalityCode(InNationalityCode nationalityCode);

	// ====================================================================================================
	// RESIDENCY CODE
	// ====================================================================================================

	InResidencyCode findResidencyCodeById(Long id);

	InResidencyCode findResidencyCodeByCode(String code);

	List<InResidencyCode> findResidencyCodes();

	List<InResidencyCode> findResidencyCodes(String filter, Integer offset, Integer limit);

	Integer countResidencyCode();

	Integer countResidencyCode(String filter);

	void saveResidencyCode(InResidencyCode residencyCode);

	void updateResidencyCode(InResidencyCode residencyCode);

	void removeResidencyCode(InResidencyCode residencyCode);

	// ====================================================================================================
	// SCHOOL CODE
	// ====================================================================================================

	InSchoolCode findSchoolCodeById(Long id);

	InSchoolCode findSchoolCodeByCode(String code);

	List<InSchoolCode> findSchoolCodes(String filter, Integer offset, Integer limit);

	Integer countSchoolCode(String filter);

	void saveSchoolCode(InSchoolCode schoolCode);

	void updateSchoolCode(InSchoolCode schoolCode);

	void removeSchoolCode(InSchoolCode schoolCode);

	// ====================================================================================================
	// INVOLVEMENT TYPE CODE
	// ====================================================================================================

	InInvolvementTypeCode findInvolvementTypeCodeById(Long id);

	InInvolvementTypeCode findInvolvementTypeCodeByCode(String code);

	List<InInvolvementTypeCode> findInvolvementTypeCodes();

	List<InInvolvementTypeCode> findInvolvementTypeCodes(Integer offset, Integer limit);

	List<InInvolvementTypeCode> findInvolvementTypeCodes(String filter, Integer offset, Integer limit);

	Integer countInvolvementTypeCode();

	Integer countInvolvementTypeCode(String filter);

	void saveInvolvementTypeCode(InInvolvementTypeCode involvementtypeCode);

	void updateInvolvementTypeCode(InInvolvementTypeCode involvementtypeCode);

	void removeInvolvementTypeCode(InInvolvementTypeCode involvementtypeCode);

	// ====================================================================================================
	// INVOLVEMENT LEVEL CODE
	// ====================================================================================================

	InInvolvementLevelCode findInvolvementLevelCodeById(Long id);

	InInvolvementLevelCode findInvolvementLevelCodeByCode(String code);

	List<InInvolvementLevelCode> findInvolvementLevelCodes();

	List<InInvolvementLevelCode> findInvolvementLevelCodes(Integer offset, Integer limit);

	List<InInvolvementLevelCode> findInvolvementLevelCodes(String filter, Integer offset, Integer limit);

	Integer countInvolvementLevelCode();

	Integer countInvolvementLevelCode(String filter);

	void saveInvolvementLevelCode(InInvolvementLevelCode involvementlevelCode);

	void updateInvolvementLevelCode(InInvolvementLevelCode involvementlevelCode);

	void removeInvolvementLevelCode(InInvolvementLevelCode involvementlevelCode);

	// ====================================================================================================
	// INVOLVEMENT TITLE CODE
	// ====================================================================================================

	InInvolvementTitleCode findInvolvementTitleCodeById(Long id);

	InInvolvementTitleCode findInvolvementTitleCodeByCode(String code);

	List<InInvolvementTitleCode> findInvolvementTitleCodes();

	List<InInvolvementTitleCode> findInvolvementTitleCodes(Integer offset, Integer limit);

	List<InInvolvementTitleCode> findInvolvementTitleCodes(String filter, Integer offset, Integer limit);

	Integer countInvolvementTitleCode();

	Integer countInvolvementTitleCode(String filter);

	void saveInvolvementTitleCode(InInvolvementTitleCode involvementtitleCode);

	void updateInvolvementTitleCode(InInvolvementTitleCode involvementtitleCode);

	void removeInvolvementTitleCode(InInvolvementTitleCode involvementtitleCode);

	// ====================================================================================================
	// EMPLOYMENT FIELD CODE
	// ====================================================================================================

	InEmploymentFieldCode findEmploymentFieldCodeById(Long id);

	InEmploymentFieldCode findEmploymentFieldCodeByCode(String code);

	List<InEmploymentFieldCode> findEmploymentFieldCodes();

	List<InEmploymentFieldCode> findEmploymentFieldCodes(Integer offset, Integer limit);

	List<InEmploymentFieldCode> findEmploymentFieldCodes(String filter, Integer offset, Integer limit);

	Integer countEmploymentFieldCode();

	Integer countEmploymentFieldCode(String filter);

	void saveEmploymentFieldCode(InEmploymentFieldCode employmentfieldCode);

	void updateEmploymentFieldCode(InEmploymentFieldCode employmentfieldCode);

	void removeEmploymentFieldCode(InEmploymentFieldCode employmentfieldCode);

	// ====================================================================================================
	// EMPLOYMENT LEVEL CODE
	// ====================================================================================================

	InEmploymentLevelCode findEmploymentLevelCodeById(Long id);

	InEmploymentLevelCode findEmploymentLevelCodeByCode(String code);

	List<InEmploymentLevelCode> findEmploymentLevelCodes();

	List<InEmploymentLevelCode> findEmploymentLevelCodes(Integer offset, Integer limit);

	List<InEmploymentLevelCode> findEmploymentLevelCodes(String filter, Integer offset, Integer limit);

	Integer countEmploymentLevelCode();

	Integer countEmploymentLevelCode(String filter);

	void saveEmploymentLevelCode(InEmploymentLevelCode employmentlevelCode);

	void updateEmploymentLevelCode(InEmploymentLevelCode employmentlevelCode);

	void removeEmploymentLevelCode(InEmploymentLevelCode employmentlevelCode);

	// ====================================================================================================
	// DUN CODE
	// ====================================================================================================

	InDunCode findDunCodeById(Long id);

	InDunCode findDunCodeByCode(String code);

	List<InDunCode> findDunCodes();

	List<InDunCode> findDunCodes(String filter, Integer offset, Integer limit);

	Integer countDunCode();

	Integer countDunCode(String filter);

	void saveDunCode(InDunCode DunCode);

	void updateDunCode(InDunCode DunCode);

	void removeDunCode(InDunCode DunCode);

	// ====================================================================================================
	// PARLIAMENT CODE
	// ====================================================================================================

	InParliamentCode findParliamentCodeById(Long id);

	InParliamentCode findParliamentCodeByCode(String code);

	List<InParliamentCode> findParliamentCodes();

	List<InParliamentCode> findParliamentCodes(String filter, Integer offset, Integer limit);

	Integer countParliamentCode();

	Integer countParliamentCode(String filter);

	void saveParliamentCode(InParliamentCode ParliamentCode);

	void updateParliamentCode(InParliamentCode ParliamentCode);

	void removeParliamentCode(InParliamentCode ParliamentCode);

	// ====================================================================================================
	// SUPERVISOR CODE
	// ====================================================================================================

	InSupervisorCode findSupervisorCodeById(Long id);

	InSupervisorCode findSupervisorCodeByCode(String code);

	InSupervisorCode findSupervisorCodeByName(String name);

	List<InSupervisorCode> findSupervisorCodes();

	// todo: find supervisorcode by faculty
	List<InSupervisorCode> findSupervisorCodes(InFacultyCode facultyCode);

	List<InSupervisorCode> findSupervisorCodes(String filter, Integer offset, Integer limit);

	Integer countSupervisorCode();

	// todo: count supervisorcode by faculty
	Integer countSupervisorCode(InFacultyCode facultyCode);

	Integer countSupervisorCode(String filter);

	void saveSupervisorCode(InSupervisorCode supervisorCode);

	void updateSupervisorCode(InSupervisorCode supervisorCode);

	void removeSupervisorCode(InSupervisorCode supervisorCode);

	// ====================================================================================================
	// SUPERVISOR OFFERING
	// ====================================================================================================

	InSupervisorOffering findSupervisorOfferingById(Long id);

	InSupervisorOffering findSupervisorOfferingByCode(String offering);

	List<InSupervisorOffering> findSupervisorOfferings();

	// todo: find supervisoroffering by faculty
	List<InSupervisorOffering> findSupervisorOfferings(InFacultyCode facultyCode);

	List<InSupervisorOffering> findSupervisorOfferings(String filter, Integer offset, Integer limit);

	List<InSupervisorOffering> findSupervisorOfferingsByProgramLevel(InProgramLevel inProgramLevel, String filter,
			Integer offset, Integer limit);

	Integer countSupervisorOffering();

	// todo: count supervisoroffering by faculty
	// Integer countSupervisorOffering(InFacultyOffering facultyOffering);

	Integer countSupervisorOffering(String filter);

	void saveSupervisorOffering(InSupervisorOffering supervisorOffering);

	void updateSupervisorOfferings(InSupervisorOffering supervisorOffering);

	void removeSupervisorOfferings(InSupervisorOffering supervisorOffering);

	// ====================================================================================================
	// STUDY MODE
	// ====================================================================================================

	InStudyMode findStudyModeById(Long id);

	InStudyMode findStudyModeByCode(String code);

	List<InStudyMode> findStudyModes();

	List<InStudyMode> findStudyModes(String filter, Integer offset, Integer limit);

	Integer countStudyMode();

	Integer countStudyMode(String filter);

	void saveStudyMode(InStudyMode studyMode);

	void updateStudyMode(InStudyMode studyMode);

	void removeStudyMode(InStudyMode studyMode);

	InPromoCode findPromoCodeByCode(String code);

	boolean isPromoCodeExists(String code);

	boolean isFacultyCodeExists(String code);

	boolean isSupervisorCodeExists(String name);

	InProgramFieldCode findProgramFieldCodeById(Long id);

	InProgramFieldCode findProgramFieldCodeByCode(String code);

	List<InProgramFieldCode> findProgramFieldCodes();

	List<InProgramFieldCode> findProgramFieldCodes(InGraduateCenter graduateCenter);

	List<InProgramFieldCode> findProgramFieldCodes(InFacultyCode facultyCode, InProgramLevel programLevel);

	List<InProgramFieldCode> findProgramFieldCodes(InGraduateCenter graduateCenter, InProgramLevel programLevel);

	List<InProgramFieldCode> findProgramFieldCodes(InFacultyCode facultyCode);

	List<InProgramFieldCode> findProgramFieldCodes(String filter, Integer offset, Integer limit);

	List<InProgramFieldCode> findProgramFieldCodesByProgramLevel(InProgramLevel inProgramLevel);

	Integer countProgramFieldCode();

	Integer countProgramFieldCode(String filter);

	Integer countProgramFieldCode(InFacultyCode facultyCode);

	Integer countProgramFieldCode(InGraduateCenter graduateCenter);

	Integer countProgramFieldCode(InFacultyCode facultyCode, InProgramLevel programLevel);

	Integer countProgramFieldCode(InGraduateCenter graduateCenter, InProgramLevel programLevel);

	void saveProgramFieldCode(InProgramFieldCode programCode);

	void updateProgramFieldCode(InProgramFieldCode programCode);

	void removeProgramFieldCode(InProgramFieldCode programCode);

	// ====================================================================================================
	// EMPLOYMENT TYPE CODE
	// ====================================================================================================

	InEmploymentTypeCode findEmploymentTypeCodeById(Long id);

	InEmploymentTypeCode findEmploymentTypeCodeByCode(String code);

	List<InEmploymentTypeCode> findEmploymentTypeCodes();

	List<InEmploymentTypeCode> findEmploymentTypeCodes(String filter, Integer offset, Integer limit);

	Integer countEmploymentTypeCode();

	Integer countEmploymentTypeCode(String filter);

	void saveEmploymentTypeCode(InEmploymentTypeCode employmentTypeCode);

	void updateEmploymentTypeCode(InEmploymentTypeCode employmentTypeCode);

	void removeEmploymentTypeCode(InEmploymentTypeCode employmentTypeCode);

	// ====================================================================================================
	// EMPLOYMENT SECTOR CODE
	// ====================================================================================================

	InEmploymentSectorCode findEmploymentSectorCodeById(Long id);

	InEmploymentSectorCode findEmploymentSectorCodeByCode(String code);

	List<InEmploymentSectorCode> findEmploymentSectorCodes();

	List<InEmploymentSectorCode> findEmploymentSectorCodes(Integer offset, Integer limit);

	List<InEmploymentSectorCode> findEmploymentSectorCodes(String filter, Integer offset, Integer limit);

	Integer countEmploymentSectorCode();

	Integer countEmploymentSectorCode(String filter);

	void saveEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode);

	void updateEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode);

	void removeEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode);

	// ====================================================================================================
	// GUARDIAN TYPE CODE
	// ====================================================================================================

	InGuardianTypeCode findGuardianTypeCodeById(Long id);

	InGuardianTypeCode findGuardianTypeCodeByCode(String code);

	List<InGuardianTypeCode> findGuardianTypeCodes();

	List<InGuardianTypeCode> findGuardianTypeCodes(String filter, Integer offset, Integer limit);

	Integer countGuardianTypeCode();

	Integer countGuardianTypeCode(String filter);

	void saveGuardianTypeCode(InGuardianTypeCode guardianTypeCode);

	void updateGuardianTypeCode(InGuardianTypeCode guardianTypeCode);

	void removeGuardianTypeCode(InGuardianTypeCode guardianTypeCode);

	// ====================================================================================================
	// SPM RESULT
	// ====================================================================================================

	InSpmResult findSpmResultById(Long id);

	InSpmResult findSpmResultByCode(String code);

	List<InSpmResult> findSpmResults();

	List<InSpmResult> findSpmResults(String filter, Integer offset, Integer limit);

	Integer countSpmResult();

	void saveSpmResult(InSpmResult spmResult);

	void updateSpmResult(InSpmResult spmResult);

	void removeSpmResult(InSpmResult spmResult);

	// ====================================================================================================
	// SPM SUBJECT CODE
	// ====================================================================================================

	InSpmSubjectCode findSpmSubjectCodeById(Long id);

	InSpmSubjectCode findSpmSubjectCodeByCode(String code);

	List<InSpmSubjectCode> findSpmSubjectCodes();

	List<InSpmSubjectCode> findSpmSubjectCodes(Integer offset, Integer limit);

	List<InSpmSubjectCode> findSpmSubjectCodes(String filter, Integer offset, Integer limit);

	Integer countSpmSubjectCode();

	Integer countSpmSubjectCode(String filter);

	void saveSpmSubjectCode(InSpmSubjectCode spmSubjectCode);

	void updateSpmSubjectCode(InSpmSubjectCode spmSubjectCode);

	void removeSpmSubjectCode(InSpmSubjectCode spmSubjectCode);


}
