package my.edu.umk.pams.intake.common.service;

import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;
import my.edu.umk.pams.connector.payload.ProgramLevelPayload;
import my.edu.umk.pams.intake.common.dao.*;
import my.edu.umk.pams.intake.common.event.ProgramFieldCodeAddedEvent;
import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;
import my.edu.umk.pams.intake.security.service.SecurityService;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	private InGraduateCenterDao graduateCenterDao;

	@Autowired
	private InPromoCodeDao promoCodeDao;

	@Autowired
	private InCountryCodeDao countryCodeDao;

	@Autowired
	private InStateCodeDao stateCodeDao;

	@Autowired
	private InCityCodeDao cityCodeDao;

	@Autowired
	private InDistrictCodeDao districtCodeDao;

	@Autowired
	private InProgramCodeDao programCodeDao;

	@Autowired
	private InFieldCodeDao fieldCodeDao;

	@Autowired
	private InGradeCodeDao gradeCodeDao;

	@Autowired
	private InSubjectCodeDao subjectCodeDao;

	@Autowired
	private InMaritalCodeDao maritalCodeDao;

	@Autowired
	private InLanguageCodeDao languageCodeDao;

	@Autowired
	private InDependencyCodeDao dependencyCodeDao;

	@Autowired
	private InDisabilityCodeDao disabilityCodeDao;

	@Autowired
	private InBankCodeDao bankCodeDao;

	@Autowired
	private InGenderCodeDao genderCodeDao;

	@Autowired
	private InBumiCodeDao bumiCodeDao;

	@Autowired
	private InRaceCodeDao raceCodeDao;

	@Autowired
	private InEthnicityCodeDao ethnicityCodeDao;

	@Autowired
	private InNationalityCodeDao nationalityCodeDao;

	@Autowired
	private InResidencyCodeDao residencyCodeDao;

	@Autowired
	private InSchoolCodeDao schoolCodeDao;

	@Autowired
	private InReligionCodeDao religionCodeDao;

	@Autowired
	private InResidencyCodeDao residenceCodeDao;

	@Autowired
	private InFacultyCodeDao facultyCodeDao;

	@Autowired
	private InStudyCenterCodeDao studyCenterCodeDao;

	@Autowired
	private InVenueCodeDao venueCodeDao;

	@Autowired
	private InInvolvementTypeCodeDao involvementTypeCodeDao;

	@Autowired
	private InInvolvementLevelCodeDao involvementLevelCodeDao;

	@Autowired
	private InInvolvementTitleCodeDao involvementTitleCodeDao;

	@Autowired
	private InEmploymentFieldCodeDao employmentFieldCodeDao;

	@Autowired
	private InEmploymentLevelCodeDao employmentLevelCodeDao;

	@Autowired
	private InEmploymentSectorCodeDao employmentSectorCodeDao;

	@Autowired
	private InEmploymentTypeCodeDao employmentTypeCodeDao;

	@Autowired
	private InGuardianTypeCodeDao guardianTypeCodeDao;

	@Autowired
	private InStudyModeDao studyModeDao;

	@Autowired
	private InDunCodeDao dunCodeDao;

	@Autowired
	private InParliamentCodeDao parliamentCodeDao;

	@Autowired
	private InSupervisorCodeDao supervisorCodeDao;

	@Autowired
	private InProgramFieldCodeDao programFieldCodeDao;

	@Autowired
	private InSupervisorOfferingDao supervisorOfferingDao;
	
	@Autowired
	private InSpmSubjectCodeDao spmSubjectCodeDao;

	@Autowired
	private InSpmResultDao spmResultDao;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ApplicationContext applicationContext;

	// ====================================================================================================
	// GRADUATE CENTER
	// ====================================================================================================

	@Override
	public InGraduateCenter findGraduateCenterById(Long id) {
		return graduateCenterDao.findById(id);
	}

	@Override
	public InGraduateCenter findGraduateCenterByCode(String code) {
		return graduateCenterDao.findByCode(code);
	}

	@Override
	public List<InGraduateCenter> findGraduateCenters() {
		return graduateCenterDao.find();
	}

	@Override
	public List<InGraduateCenter> findGraduateCenters(String filter, Integer offset, Integer limit) {
		return graduateCenterDao.find(filter, offset, limit);
	}

	@Override
	public Integer countGraduateCenter(String filter) {
		return graduateCenterDao.count(filter);
	}

	@Override
	public void saveGraduateCenter(InGraduateCenter graduateCenter) {
		graduateCenterDao.save(graduateCenter, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateGraduateCenter(InGraduateCenter graduateCenter) {
		graduateCenterDao.update(graduateCenter, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeGraduateCenter(InGraduateCenter graduateCenter) {
		graduateCenterDao.remove(graduateCenter, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// COUNTRY CODE
	// ====================================================================================================

	@Override
	public InCountryCode findCountryCodeById(Long id) {
		return countryCodeDao.findById(id);
	}

	@Override
	public InCountryCode findCountryCodeByCode(String code) {
		return countryCodeDao.findByCode(code);
	}

	@Override
	public List<InCountryCode> findCountryCodes() {
		return countryCodeDao.find();
	}

	@Override
	public List<InCountryCode> findCountryCodes(String filter, Integer offset, Integer limit) {
		return countryCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countCountryCode(String filter) {
		return countryCodeDao.count(filter);
	}

	@Override
	public void saveCountryCode(InCountryCode countryCode) {
		countryCodeDao.save(countryCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateCountryCode(InCountryCode countryCode) {
		countryCodeDao.update(countryCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeCountryCode(InCountryCode countryCode) {
		countryCodeDao.remove(countryCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// STATE CODE
	// ====================================================================================================

	@Override
	public InStateCode findStateCodeById(Long id) {
		return stateCodeDao.findById(id);
	}

	@Override
	public InStateCode findStateCodeByCode(String code) {
		return stateCodeDao.findByCode(code);
	}

	@Override
	public List<InStateCode> findStateCodes() {
		return stateCodeDao.find(0, Integer.MAX_VALUE);
	}

	@Override
	public List<InStateCode> findStateCodes(Integer offset, Integer limit) {
		return stateCodeDao.find(offset, limit);
	}

	@Override
	public List<InStateCode> findStateCodes(String filter, Integer offset, Integer limit) {
		return stateCodeDao.find(filter, offset, limit);
	}

	@Override
	public List<InStateCode> findStateCodes(InCountryCode countryCode, Integer offset, Integer limit) {
		return stateCodeDao.find(countryCode, offset, limit);
	}

	@Override
	public List<InStateCode> findStateCodes(String filter, InCountryCode countryCode, Integer offset, Integer limit) {
		return stateCodeDao.find(filter, countryCode, offset, limit);
	}

	@Override
	public Integer countStateCode() {
		return stateCodeDao.count();
	}

	@Override
	public Integer countStateCode(String filter) {
		return stateCodeDao.count(filter);
	}

	@Override
	public Integer countStateCode(InCountryCode countryCode) {
		return stateCodeDao.count(countryCode);
	}

	@Override
	public Integer countStateCode(String filter, InCountryCode countryCode) {
		return stateCodeDao.count(filter, countryCode);
	}

	@Override
	public void saveStateCode(InStateCode stateCode) {
		stateCodeDao.save(stateCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateStateCode(InStateCode stateCode) {
		stateCodeDao.update(stateCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeStateCode(InStateCode stateCode) {
		stateCodeDao.remove(stateCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// DISTRICT CODE
	// ====================================================================================================

	@Override
	public InDistrictCode findDistrictCodeById(Long id) {
		return districtCodeDao.findById(id);
	}

	@Override
	public InDistrictCode findDistrictCodeByCode(String code) {
		return districtCodeDao.findByCode(code);
	}

	@Override
	public List<InDistrictCode> findDistrictCodes(InStateCode stateCode, Integer offset, Integer limit) {
		return districtCodeDao.find(offset, limit);
	}

	@Override
	public List<InDistrictCode> findDistrictCodes(String filter, Integer offset, Integer limit) {
		return districtCodeDao.find(filter, offset, limit);
	}

	@Override
	public List<InDistrictCode> findDistrictCodes(String filter, InStateCode stateCode, Integer offset, Integer limit) {
		return districtCodeDao.find(filter, offset, limit);
	}

	@Override
	public List<InDistrictCode> findDistrictCodes() {
		return districtCodeDao.find();
	}

	@Override
	public Integer countDistrictCode(InStateCode stateCode) {
		return districtCodeDao.count();
	}

	@Override
	public Integer countDistrictCode(String filter, InStateCode stateCode) {
		return districtCodeDao.count(filter);
	}

	@Override
	public void saveDistrictCode(InDistrictCode districtCode) {
		districtCodeDao.save(districtCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateDistrictCode(InDistrictCode districtCode) {
		districtCodeDao.update(districtCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeDistrictCode(InDistrictCode districtCode) {
		districtCodeDao.remove(districtCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// PROMO CODE
	// ====================================================================================================

	@Override
	public InPromoCode findPromoCodeByCode(String code) {
		return promoCodeDao.findByCode(code);
	}

	@Override
	public boolean isPromoCodeExists(String code) {
		return promoCodeDao.isExists(code);
	}

	// ====================================================================================================
	// CITY CODE
	// ====================================================================================================

	@Override
	public InCityCode findCityCodeById(Long id) {
		return cityCodeDao.findById(id);
	}

	@Override
	public InCityCode findCityCodeByCode(String code) {
		return cityCodeDao.findByCode(code);
	}

	@Override
	public List<InCityCode> findCityCodes(InStateCode stateCode, Integer offset, Integer limit) {
		return cityCodeDao.find(stateCode, offset, limit);
	}

	@Override
	public List<InCityCode> findCityCodes(String filter, InStateCode stateCode, Integer offset, Integer limit) {
		return cityCodeDao.find(filter, stateCode, offset, limit);
	}

	@Override
	public Integer countCityCode(InStateCode stateCode) {
		return cityCodeDao.count(stateCode);
	}

	@Override
	public Integer countCityCode(String filter, InStateCode stateCode) {
		return cityCodeDao.count(filter, stateCode);
	}

	@Override
	public void saveCityCode(InCityCode cityCode) {
		cityCodeDao.save(cityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateCityCode(InCityCode cityCode) {
		cityCodeDao.update(cityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeCityCode(InCityCode cityCode) {
		cityCodeDao.remove(cityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// SUBJECT CODE
	// ====================================================================================================

	@Override
	public InSubjectCode findSubjectCodeById(Long id) {
		return subjectCodeDao.findById(id);
	}

	@Override
	public InSubjectCode findSubjectCodeByCode(String code) {
		return subjectCodeDao.findByCode(code);
	}

	@Override
	public List<InSubjectCode> findSubjectCodes() {
		return subjectCodeDao.find();
	}

	@Override
	public List<InSubjectCode> findSubjectCodes(Integer offset, Integer limit) {
		return subjectCodeDao.find(offset, limit);
	}

	@Override
	public List<InSubjectCode> findSubjectCodes(String filter, Integer offset, Integer limit) {
		return subjectCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countSubjectCode() {
		return subjectCodeDao.count();
	}

	@Override
	public Integer countSubjectCode(String filter) {
		return subjectCodeDao.count(filter);
	}

	@Override
	public void saveSubjectCode(InSubjectCode subjectCode) {
		subjectCodeDao.save(subjectCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSubjectCode(InSubjectCode subjectCode) {
		subjectCodeDao.update(subjectCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSubjectCode(InSubjectCode subjectCode) {
		subjectCodeDao.remove(subjectCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// GRADE CODE
	// ====================================================================================================

	@Override
	public InGradeCode findGradeCodeById(Long id) {
		return gradeCodeDao.findById(id);
	}

	@Override
	public InGradeCode findGradeCodeByCode(String code) {
		return gradeCodeDao.findByCode(code);
	}

	@Override
	public List<InGradeCode> findGradeCodes() {
		return gradeCodeDao.find();
	}

	@Override
	public List<InGradeCode> findGradeCodes(Integer offset, Integer limit) {
		return gradeCodeDao.find(offset, limit);
	}

	@Override
	public List<InGradeCode> findGradeCodes(String filter, Integer offset, Integer limit) {
		return gradeCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countGradeCode() {
		return gradeCodeDao.count();
	}

	@Override
	public Integer countGradeCode(String filter) {
		return gradeCodeDao.count(filter);
	}

	@Override
	public void saveGradeCode(InGradeCode gradeCode) {
		gradeCodeDao.save(gradeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateGradeCode(InGradeCode gradeCode) {
		gradeCodeDao.update(gradeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeGradeCode(InGradeCode gradeCode) {
		gradeCodeDao.remove(gradeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// PROGRAM CODE
	// ====================================================================================================

	@Override
	public InProgramCode findProgramCodeById(Long id) {
		return programCodeDao.findById(id);
	}

	@Override
	public InProgramCode findProgramCodeByCode(String code) {
		return programCodeDao.findByCode(code);
	}

	@Override
	public List<InProgramCode> findProgramCodes() {
		return programCodeDao.find();
	}

	@Override
	public List<InProgramCode> findProgramCodes(InGraduateCenter graduateCenter) {
		return programCodeDao.find(graduateCenter);
	}

	@Override
	public List<InProgramCode> findProgramCodes(InFacultyCode facultyCode, InProgramLevel programLevel) {
		return null;
	}

	@Override
	public List<InProgramCode> findProgramCodes(InGraduateCenter graduateCenter, InProgramLevel programLevel) {
		return null;
	}

	@Override
	public List<InProgramCode> findProgramCodes(InFacultyCode facultyCode) {
		return programCodeDao.find(facultyCode);
	}

	@Override
	public List<InProgramCode> findProgramCodes(String filter, Integer offset, Integer limit) {
		return programCodeDao.find(filter, offset, limit);
	}

	@Override
	public List<InProgramCode> findProgramCodesByProgramLevel(InProgramLevel inProgramLevel, String filter,
			Integer offset, Integer limit) {
		return programCodeDao.find(inProgramLevel, filter, offset, limit);
	}

	@Override
	public Integer countProgramCode() {
		return programCodeDao.count();
	}

	@Override
	public Integer countProgramCode(String filter) {
		return programCodeDao.count(filter);
	}

	@Override
	public Integer countProgramCode(InFacultyCode facultyCode) {
		return programCodeDao.count(facultyCode);
	}

	@Override
	public Integer countProgramCode(InGraduateCenter graduateCenter) {
		return programCodeDao.count(graduateCenter);
	}

	@Override
	public Integer countProgramCode(InFacultyCode facultyCode, InProgramLevel programLevel) {
		return null;
	}

	@Override
	public Integer countProgramCode(InGraduateCenter graduateCenter, InProgramLevel programLevel) {
		return null;
	}

	@Override
	public void saveProgramCode(InProgramCode programCode) {
		programCodeDao.save(programCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateProgramCode(InProgramCode programCode) {
		programCodeDao.update(programCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeProgramCode(InProgramCode programCode) {
		programCodeDao.remove(programCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// FIELD CODE
	// ====================================================================================================

	@Override
	public InFieldCode findFieldCodeById(Long id) {
		return fieldCodeDao.findById(id);
	}

	@Override
	public InFieldCode findFieldCodeByCode(String code) {
		return fieldCodeDao.findByCode(code);
	}

	@Override
	public List<InFieldCode> findFieldCodes() {
		return fieldCodeDao.find();
	}

	@Override
	public List<InFieldCode> findFieldCodes(Integer offset, Integer limit) {
		return fieldCodeDao.find(offset, limit);
	}

	@Override
	public List<InFieldCode> findFieldCodes(String filter, Integer offset, Integer limit) {
		System.out.println("size :" + fieldCodeDao.find(filter, offset, limit).size());
		return fieldCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countFieldCode() {
		return fieldCodeDao.count();
	}

	@Override
	public Integer countFieldCode(String filter) {
		return fieldCodeDao.count(filter);
	}

	@Override
	public void saveFieldCode(InFieldCode fieldCode) {
		fieldCodeDao.save(fieldCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateFieldCode(InFieldCode fieldCode) {
		fieldCodeDao.update(fieldCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeFieldCode(InFieldCode fieldCode) {
		fieldCodeDao.remove(fieldCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// GENDER CODE
	// ====================================================================================================
	@Override
	public InGenderCode findGenderCodeById(Long id) {
		return genderCodeDao.findById(id);
	}

	@Override
	public InGenderCode findGenderCodeByCode(String code) {
		return genderCodeDao.findByCode(code);
	}

	@Override
	public List<InGenderCode> findGenderCodes() {
		return genderCodeDao.find();
	}

	@Override
	public List<InGenderCode> findGenderCodes(String filter, Integer offset, Integer limit) {
		return genderCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countGenderCode() {
		return genderCodeDao.count();
	}

	@Override
	public Integer countGenderCode(String filter) {
		return genderCodeDao.count(filter);
	}

	@Override
	public void saveGenderCode(InGenderCode genderCode) {
		genderCodeDao.save(genderCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateGenderCode(InGenderCode genderCode) {
		genderCodeDao.update(genderCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeGenderCode(InGenderCode genderCode) {
		genderCodeDao.remove(genderCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// BUMI CODE
	// ====================================================================================================

	@Override
	public InBumiCode findBumiCodeById(Long id) {
		return bumiCodeDao.findById(id);
	}

	@Override
	public InBumiCode findBumiCodeByCode(String code) {
		return bumiCodeDao.findByCode(code);
	}

	@Override
	public List<InBumiCode> findBumiCodes() {
		return bumiCodeDao.find();
	}

	@Override
	public List<InBumiCode> findBumiCodes(String filter, Integer offset, Integer limit) {
		return bumiCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countBumiCode() {
		return bumiCodeDao.count();
	}

	@Override
	public Integer countBumiCode(String filter) {
		return bumiCodeDao.count(filter);
	}

	@Override
	public void saveBumiCode(InBumiCode bumiCode) {
		bumiCodeDao.save(bumiCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateBumiCode(InBumiCode bumiCode) {
		bumiCodeDao.update(bumiCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeBumiCode(InBumiCode bumiCode) {
		bumiCodeDao.remove(bumiCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// RACE CODE
	// ====================================================================================================

	@Override
	public InRaceCode findRaceCodeById(Long id) {
		return raceCodeDao.findById(id);
	}

	@Override
	public InRaceCode findRaceCodeByCode(String code) {
		return raceCodeDao.findByCode(code);
	}

	@Override
	public List<InRaceCode> findRaceCodes() {
		return raceCodeDao.find();
	}

	@Override
	public List<InRaceCode> findRaceCodes(String filter, Integer offset, Integer limit) {
		return raceCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countRaceCode() {
		return raceCodeDao.count();
	}

	@Override
	public Integer countRaceCode(String filter) {
		return raceCodeDao.count(filter);
	}

	@Override
	public void saveRaceCode(InRaceCode raceCode) {
		raceCodeDao.save(raceCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateRaceCode(InRaceCode raceCode) {
		raceCodeDao.update(raceCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeRaceCode(InRaceCode raceCode) {
		raceCodeDao.remove(raceCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// ETHNICITY CODE
	// ====================================================================================================

	@Override
	public InEthnicityCode findEthnicityCodeById(Long id) {
		return ethnicityCodeDao.findById(id);
	}

	@Override
	public InEthnicityCode findEthnicityCodeByCode(String code) {
		return ethnicityCodeDao.findByCode(code);
	}

	@Override
	public List<InEthnicityCode> findEthnicityCodes() {
		return ethnicityCodeDao.find();
	}

	@Override
	public List<InEthnicityCode> findEthnicityCodes(String filter, Integer offset, Integer limit) {
		return ethnicityCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countEthnicityCode() {
		return ethnicityCodeDao.count();
	}

	@Override
	public Integer countEthnicityCode(String filter) {
		return ethnicityCodeDao.count(filter);
	}

	@Override
	public void saveEthnicityCode(InEthnicityCode ethnicityCode) {
		ethnicityCodeDao.save(ethnicityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateEthnicityCode(InEthnicityCode ethnicityCode) {
		ethnicityCodeDao.update(ethnicityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeEthnicityCode(InEthnicityCode ethnicityCode) {
		ethnicityCodeDao.remove(ethnicityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// RELIGION CODE
	// ====================================================================================================

	@Override
	public InReligionCode findReligionCodeById(Long id) {
		return religionCodeDao.findById(id);
	}

	@Override
	public InReligionCode findReligionCodeByCode(String code) {
		return religionCodeDao.findByCode(code);
	}

	@Override
	public List<InReligionCode> findReligionCodes() {
		return religionCodeDao.find();
	}

	@Override
	public List<InReligionCode> findReligionCodes(String filter, Integer offset, Integer limit) {
		return religionCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countReligionCode() {
		return religionCodeDao.count();
	}

	@Override
	public Integer countReligionCode(String filter) {
		return religionCodeDao.count(filter);
	}

	@Override
	public void saveReligionCode(InReligionCode religionCode) {
		religionCodeDao.save(religionCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateReligionCode(InReligionCode religionCode) {
		religionCodeDao.update(religionCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeReligionCode(InReligionCode religionCode) {
		religionCodeDao.remove(religionCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// MARITAL CODE
	// ====================================================================================================

	// ====================================================================================================
	// DISABILITY CODE
	// ====================================================================================================

	@Override
	public InDisabilityCode findDisabilityCodeById(Long id) {
		return disabilityCodeDao.findById(id);
	}

	@Override
	public InDisabilityCode findDisabilityCodeByCode(String code) {
		return disabilityCodeDao.findByCode(code);
	}

	@Override
	public List<InDisabilityCode> findDisabilityCodes() {
		return disabilityCodeDao.find();
	}

	@Override
	public List<InDisabilityCode> findDisabilityCodes(String filter, Integer offset, Integer limit) {
		return disabilityCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countDisabilityCode() {
		return disabilityCodeDao.count();
	}

	@Override
	public Integer countDisabilityCode(String filter) {
		return disabilityCodeDao.count(filter);
	}

	@Override
	public void saveDisabilityCode(InDisabilityCode disabilityCode) {
		disabilityCodeDao.save(disabilityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateDisabilityCode(InDisabilityCode disabilityCode) {
		disabilityCodeDao.update(disabilityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeDisabilityCode(InDisabilityCode disabilityCode) {
		disabilityCodeDao.remove(disabilityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// NATIONALITY CODE
	// ====================================================================================================

	@Override
	public InNationalityCode findNationalityCodeById(Long id) {
		return nationalityCodeDao.findById(id);
	}

	@Override
	public InNationalityCode findNationalityCodeByCode(String code) {
		return nationalityCodeDao.findByCode(code);
	}

	@Override
	public List<InNationalityCode> findNationalityCodes() {
		return nationalityCodeDao.find();
	}

	@Override
	public List<InNationalityCode> findNationalityCodes(String filter, Integer offset, Integer limit) {
		return nationalityCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countNationalityCode() {
		return nationalityCodeDao.count();
	}

	@Override
	public Integer countNationalityCode(String filter) {
		return nationalityCodeDao.count(filter);
	}

	@Override
	public void saveNationalityCode(InNationalityCode nationalityCode) {
		nationalityCodeDao.save(nationalityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateNationalityCode(InNationalityCode nationalityCode) {
		nationalityCodeDao.update(nationalityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeNationalityCode(InNationalityCode nationalityCode) {
		nationalityCodeDao.remove(nationalityCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// RESIDENCY CODE
	// ====================================================================================================

	@Override
	public InResidencyCode findResidencyCodeById(Long id) {
		return residencyCodeDao.findById(id);
	}

	@Override
	public InResidencyCode findResidencyCodeByCode(String code) {
		return residencyCodeDao.findByCode(code);
	}

	@Override
	public List<InResidencyCode> findResidencyCodes() {
		return residencyCodeDao.find();
	}

	@Override
	public List<InResidencyCode> findResidencyCodes(String filter, Integer offset, Integer limit) {
		return residencyCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countResidencyCode() {
		return residencyCodeDao.count();
	}

	@Override
	public Integer countResidencyCode(String filter) {
		return residencyCodeDao.count(filter);
	}

	@Override
	public void saveResidencyCode(InResidencyCode residencyCode) {
		residencyCodeDao.save(residencyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateResidencyCode(InResidencyCode residencyCode) {
		residencyCodeDao.update(residencyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeResidencyCode(InResidencyCode residencyCode) {
		residencyCodeDao.remove(residencyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// SCHOOL CODE
	// ====================================================================================================

	@Override
	public InSchoolCode findSchoolCodeById(Long id) {
		return schoolCodeDao.findById(id);
	}

	@Override
	public InSchoolCode findSchoolCodeByCode(String code) {
		return schoolCodeDao.findByCode(code);
	}

	@Override
	public List<InSchoolCode> findSchoolCodes(String filter, Integer offset, Integer limit) {
		return schoolCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countSchoolCode(String filter) {
		return schoolCodeDao.count(filter);
	}

	@Override
	public void saveSchoolCode(InSchoolCode schoolCode) {
		schoolCodeDao.save(schoolCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSchoolCode(InSchoolCode schoolCode) {
		schoolCodeDao.update(schoolCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSchoolCode(InSchoolCode schoolCode) {
		schoolCodeDao.remove(schoolCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// FACULTY CODE
	// ====================================================================================================
	@Override
	public InFacultyCode findFacultyCodeById(Long id) {
		return facultyCodeDao.findById(id);
	}

	@Override
	public InFacultyCode findFacultyCodeByCode(String code) {
		return facultyCodeDao.findByCode(code);
	}

	@Override
	public boolean isFacultyCodeExists(String code) {
		return facultyCodeDao.isExists(code);
	}

	@Override
	public List<InFacultyCode> findFacultyCodes() {
		return facultyCodeDao.find();
	}

	@Override
	public List<InFacultyCode> findFacultyCodes(String filter, Integer offset, Integer limit) {
		System.out.println("size " + facultyCodeDao.find(filter, offset, limit).size());
		return facultyCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countFacultyCode() {
		return facultyCodeDao.count();
	}

	@Override
	public Integer countFacultyCode(String filter) {
		return facultyCodeDao.count(filter);
	}

	@Override
	public void saveFacultyCode(InFacultyCode facultyCode) {
		facultyCodeDao.save(facultyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateFacultyCode(InFacultyCode facultyCode) {
		facultyCodeDao.update(facultyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeFacultyCode(InFacultyCode facultyCode) {
		facultyCodeDao.remove(facultyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// STUDY CENTER CODE
	// ====================================================================================================

	@Override
	public InStudyCenterCode findStudyCenterCodeById(Long id) {
		return studyCenterCodeDao.findById(id);
	}

	@Override
	public InStudyCenterCode findStudyCenterCodeByCode(String code) {
		return studyCenterCodeDao.findByCode(code);
	}

	@Override
	public List<InStudyCenterCode> findStudyCenterCodes() {
		return studyCenterCodeDao.find();
	}

	@Override
	public List<InStudyCenterCode> findStudyCenterCodes(String filter, Integer offset, Integer limit) {
		return studyCenterCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countStudyCenterCode() {
		return studyCenterCodeDao.count();
	}

	@Override
	public Integer countStudyCenterCode(String filter) {
		return studyCenterCodeDao.count(filter);
	}

	@Override
	public void saveStudyCenterCode(InStudyCenterCode studyCenterCode) {
		studyCenterCodeDao.save(studyCenterCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateStudyCenterCode(InStudyCenterCode studyCenterCode) {
		studyCenterCodeDao.update(studyCenterCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeStudyCenterCode(InStudyCenterCode studyCenterCode) {
		studyCenterCodeDao.remove(studyCenterCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// VENUE CODE
	// ====================================================================================================

	@Override
	public InVenueCode findVenueCodeById(Long id) {
		return venueCodeDao.findById(id);
	}

	@Override
	public InVenueCode findVenueCodeByCode(String code) {
		return venueCodeDao.findByCode(code);
	}

	@Override
	public List<InVenueCode> findVenueCodes() {
		return venueCodeDao.find();
	}

	@Override
	public List<InVenueCode> findVenueCodes(String filter, Integer offset, Integer limit) {
		return venueCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countVenueCode() {
		return venueCodeDao.count();
	}

	@Override
	public Integer countVenueCode(String filter) {
		return venueCodeDao.count(filter);
	}

	@Override
	public void saveVenueCode(InVenueCode venueCode) {
		venueCodeDao.save(venueCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateVenueCode(InVenueCode venueCode) {
		venueCodeDao.update(venueCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeVenueCode(InVenueCode venueCode) {
		venueCodeDao.remove(venueCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// DEPENDENCY CODE
	// ====================================================================================================
	@Override
	public InDependencyCode findDependencyCodeById(Long id) {
		return dependencyCodeDao.findById(id);
	}

	@Override
	public InDependencyCode findDependencyCodeByCode(String code) {
		return dependencyCodeDao.findByCode(code);
	}

	@Override
	public List<InDependencyCode> findDependencyCodes() {
		return dependencyCodeDao.find();
	}

	@Override
	public List<InDependencyCode> findDependencyCodes(String filter, Integer offset, Integer limit) {
		return dependencyCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countDependencyCode() {
		return dependencyCodeDao.count();
	}

	@Override
	public Integer countDependencyCode(String filter) {
		return dependencyCodeDao.count(filter);
	}

	@Override
	public void saveDependencyCode(InDependencyCode dependencyCode) {
		dependencyCodeDao.save(dependencyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateDependencyCode(InDependencyCode dependencyCode) {
		dependencyCodeDao.update(dependencyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeDependencyCode(InDependencyCode dependencyCode) {
		dependencyCodeDao.remove(dependencyCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// BANK CODE
	// ====================================================================================================

	@Override
	public InBankCode findBankCodeById(Long id) {
		return bankCodeDao.findById(id);
	}

	@Override
	public InBankCode findBankCodeByCode(String code) {
		return bankCodeDao.findByCode(code);
	}

	@Override
	public List<InBankCode> findBankCodes() {
		return bankCodeDao.find();
	}

	@Override
	public List<InBankCode> findBankCodes(String filter, Integer offset, Integer limit) {
		return bankCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countBankCode() {
		return bankCodeDao.count();
	}

	@Override
	public Integer countBankCode(String filter) {
		return bankCodeDao.count(filter);
	}

	@Override
	public void saveBankCode(InBankCode bankCode) {
		bankCodeDao.save(bankCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateBankCode(InBankCode bankCode) {
		bankCodeDao.update(bankCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeBankCode(InBankCode bankCode) {
		bankCodeDao.remove(bankCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// MARITAL CODE
	// ====================================================================================================

	@Override
	public InMaritalCode findMaritalCodeById(Long id) {
		return maritalCodeDao.findById(id);
	}

	@Override
	public InMaritalCode findMaritalCodeByCode(String code) {
		return maritalCodeDao.findByCode(code);
	}

	@Override
	public List<InMaritalCode> findMaritalCodes() {
		return maritalCodeDao.find();
	}

	@Override
	public List<InMaritalCode> findMaritalCodes(String filter, Integer offset, Integer limit) {
		return maritalCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countMaritalCode() {
		return maritalCodeDao.count();
	}

	@Override
	public Integer countMaritalCode(String filter) {
		return maritalCodeDao.count(filter);
	}

	@Override
	public void saveMaritalCode(InMaritalCode MaritalCode) {
		maritalCodeDao.save(MaritalCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateMaritalCode(InMaritalCode MaritalCode) {
		maritalCodeDao.update(MaritalCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeMaritalCode(InMaritalCode MaritalCode) {
		maritalCodeDao.remove(MaritalCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// LANGUAGE CODE
	// ====================================================================================================

	@Override
	public InLanguageCode findLanguageCodeById(Long id) {
		return languageCodeDao.findById(id);
	}

	@Override
	public InLanguageCode findLanguageCodeByCode(String code) {
		return languageCodeDao.findByCode(code);
	}

	@Override
	public List<InLanguageCode> findLanguageCodes() {
		return languageCodeDao.find();
	}

	@Override
	public List<InLanguageCode> findLanguageCodes(String filter, Integer offset, Integer limit) {
		return languageCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countLanguageCode() {
		return languageCodeDao.count();
	}

	@Override
	public Integer countLanguageCode(String filter) {
		return languageCodeDao.count(filter);
	}

	@Override
	public void saveLanguageCode(InLanguageCode LanguageCode) {
		languageCodeDao.save(LanguageCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateLanguageCode(InLanguageCode LanguageCode) {
		languageCodeDao.update(LanguageCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeLanguageCode(InLanguageCode LanguageCode) {
		languageCodeDao.remove(LanguageCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// INVOLVEMENT TYPE CODE
	// ====================================================================================================

	@Override
	public InInvolvementTypeCode findInvolvementTypeCodeById(Long id) {
		return involvementTypeCodeDao.findById(id);
	}

	@Override
	public InInvolvementTypeCode findInvolvementTypeCodeByCode(String code) {
		return involvementTypeCodeDao.findByCode(code);
	}

	@Override
	public List<InInvolvementTypeCode> findInvolvementTypeCodes() {
		return involvementTypeCodeDao.find();
	}

	@Override
	public List<InInvolvementTypeCode> findInvolvementTypeCodes(Integer offset, Integer limit) {
		return involvementTypeCodeDao.find();
	}

	@Override
	public List<InInvolvementTypeCode> findInvolvementTypeCodes(String filter, Integer offset, Integer limit) {
		return involvementTypeCodeDao.find();
	}

	@Override
	public Integer countInvolvementTypeCode() {
		return involvementTypeCodeDao.count();
	}

	@Override
	public Integer countInvolvementTypeCode(String filter) {
		return involvementTypeCodeDao.count();
	}

	@Override
	public void saveInvolvementTypeCode(InInvolvementTypeCode involvementTypeCode) {
		involvementTypeCodeDao.save(involvementTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateInvolvementTypeCode(InInvolvementTypeCode involvementTypeCode) {
		involvementTypeCodeDao.update(involvementTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeInvolvementTypeCode(InInvolvementTypeCode involvementTypeCode) {
		involvementTypeCodeDao.remove(involvementTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// INVOLVEMENT LEVEL CODE
	// ====================================================================================================

	@Override
	public InInvolvementLevelCode findInvolvementLevelCodeById(Long id) {
		return involvementLevelCodeDao.findById(id);
	}

	@Override
	public InInvolvementLevelCode findInvolvementLevelCodeByCode(String code) {
		return involvementLevelCodeDao.findByCode(code);
	}

	@Override
	public List<InInvolvementLevelCode> findInvolvementLevelCodes() {
		return involvementLevelCodeDao.find();
	}

	@Override
	public List<InInvolvementLevelCode> findInvolvementLevelCodes(Integer offset, Integer limit) {
		return involvementLevelCodeDao.find();
	}

	@Override
	public List<InInvolvementLevelCode> findInvolvementLevelCodes(String filter, Integer offset, Integer limit) {
		return involvementLevelCodeDao.find();
	}

	@Override
	public Integer countInvolvementLevelCode() {
		return involvementLevelCodeDao.count();
	}

	@Override
	public Integer countInvolvementLevelCode(String filter) {
		return involvementLevelCodeDao.count();
	}

	@Override
	public void saveInvolvementLevelCode(InInvolvementLevelCode involvementLevelCode) {
		involvementLevelCodeDao.save(involvementLevelCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateInvolvementLevelCode(InInvolvementLevelCode involvementLevelCode) {
		involvementLevelCodeDao.update(involvementLevelCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeInvolvementLevelCode(InInvolvementLevelCode involvementLevelCode) {
		involvementLevelCodeDao.remove(involvementLevelCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// INVOLVEMENT TITLE CODE
	// ====================================================================================================

	@Override
	public InInvolvementTitleCode findInvolvementTitleCodeById(Long id) {
		return involvementTitleCodeDao.findById(id);
	}

	@Override
	public InInvolvementTitleCode findInvolvementTitleCodeByCode(String code) {
		return involvementTitleCodeDao.findByCode(code);
	}

	@Override
	public List<InInvolvementTitleCode> findInvolvementTitleCodes() {
		return involvementTitleCodeDao.find();
	}

	@Override
	public List<InInvolvementTitleCode> findInvolvementTitleCodes(Integer offset, Integer limit) {
		return involvementTitleCodeDao.find();
	}

	@Override
	public List<InInvolvementTitleCode> findInvolvementTitleCodes(String filter, Integer offset, Integer limit) {
		return involvementTitleCodeDao.find();
	}

	@Override
	public Integer countInvolvementTitleCode() {
		return involvementTitleCodeDao.count();
	}

	@Override
	public Integer countInvolvementTitleCode(String filter) {
		return involvementTitleCodeDao.count();
	}

	@Override
	public void saveInvolvementTitleCode(InInvolvementTitleCode involvementTitleCode) {
		involvementTitleCodeDao.save(involvementTitleCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateInvolvementTitleCode(InInvolvementTitleCode involvementTitleCode) {
		involvementTitleCodeDao.update(involvementTitleCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeInvolvementTitleCode(InInvolvementTitleCode involvementTitleCode) {
		involvementTitleCodeDao.remove(involvementTitleCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// EMPLOYMENT FIELD CODE
	// ====================================================================================================

	@Override
	public InEmploymentFieldCode findEmploymentFieldCodeById(Long id) {
		return employmentFieldCodeDao.findById(id);
	}

	@Override
	public InEmploymentFieldCode findEmploymentFieldCodeByCode(String code) {
		return employmentFieldCodeDao.findByCode(code);
	}

	@Override
	public List<InEmploymentFieldCode> findEmploymentFieldCodes() {
		return employmentFieldCodeDao.find();
	}

	@Override
	public List<InEmploymentFieldCode> findEmploymentFieldCodes(Integer offset, Integer limit) {
		return employmentFieldCodeDao.find();
	}

	@Override
	public List<InEmploymentFieldCode> findEmploymentFieldCodes(String filter, Integer offset, Integer limit) {
		return employmentFieldCodeDao.find();
	}

	@Override
	public Integer countEmploymentFieldCode() {
		return employmentLevelCodeDao.count();
	}

	@Override
	public Integer countEmploymentFieldCode(String filter) {
		return employmentLevelCodeDao.count();
	}

	@Override
	public void saveEmploymentFieldCode(InEmploymentFieldCode employmentFieldCode) {
		employmentFieldCodeDao.save(employmentFieldCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateEmploymentFieldCode(InEmploymentFieldCode employmentFieldCode) {
		employmentFieldCodeDao.update(employmentFieldCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeEmploymentFieldCode(InEmploymentFieldCode employmentFieldCode) {
		employmentFieldCodeDao.remove(employmentFieldCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// EMPLOYMENT LEVEL CODE
	// ====================================================================================================

	@Override
	public InEmploymentLevelCode findEmploymentLevelCodeById(Long id) {
		return employmentLevelCodeDao.findById(id);
	}

	@Override
	public InEmploymentLevelCode findEmploymentLevelCodeByCode(String code) {
		return employmentLevelCodeDao.findByCode(code);
	}

	@Override
	public List<InEmploymentLevelCode> findEmploymentLevelCodes() {
		return employmentLevelCodeDao.find();
	}

	@Override
	public List<InEmploymentLevelCode> findEmploymentLevelCodes(Integer offset, Integer limit) {
		return employmentLevelCodeDao.find();
	}

	@Override
	public List<InEmploymentLevelCode> findEmploymentLevelCodes(String filter, Integer offset, Integer limit) {
		return employmentLevelCodeDao.find();
	}

	@Override
	public Integer countEmploymentLevelCode() {
		return employmentLevelCodeDao.count();
	}

	@Override
	public Integer countEmploymentLevelCode(String filter) {
		return employmentLevelCodeDao.count();
	}

	@Override
	public void saveEmploymentLevelCode(InEmploymentLevelCode employmentLevelCode) {
		employmentLevelCodeDao.save(employmentLevelCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateEmploymentLevelCode(InEmploymentLevelCode employmentLevelCode) {
		employmentLevelCodeDao.update(employmentLevelCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeEmploymentLevelCode(InEmploymentLevelCode employmentLevelCode) {
		employmentLevelCodeDao.remove(employmentLevelCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// DUN CODE
	// ====================================================================================================

	@Override
	public InDunCode findDunCodeById(Long id) {
		return dunCodeDao.findById(id);
	}

	@Override
	public InDunCode findDunCodeByCode(String code) {
		return dunCodeDao.findByCode(code);
	}

	@Override
	public List<InDunCode> findDunCodes() {
		return dunCodeDao.find();
	}

	@Override
	public List<InDunCode> findDunCodes(String filter, Integer offset, Integer limit) {
		return dunCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countDunCode() {
		return dunCodeDao.count();
	}

	@Override
	public Integer countDunCode(String filter) {
		return dunCodeDao.count(filter);
	}

	@Override
	public void saveDunCode(InDunCode DunCode) {
		dunCodeDao.save(DunCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateDunCode(InDunCode DunCode) {
		dunCodeDao.update(DunCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeDunCode(InDunCode DunCode) {
		dunCodeDao.remove(DunCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// PARLIAMENT CODE
	// ====================================================================================================

	@Override
	public InParliamentCode findParliamentCodeById(Long id) {
		return parliamentCodeDao.findById(id);
	}

	@Override
	public InParliamentCode findParliamentCodeByCode(String code) {
		return parliamentCodeDao.findByCode(code);
	}

	@Override
	public List<InParliamentCode> findParliamentCodes() {
		return parliamentCodeDao.find();
	}

	@Override
	public List<InParliamentCode> findParliamentCodes(String filter, Integer offset, Integer limit) {
		return parliamentCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countParliamentCode() {
		return parliamentCodeDao.count();
	}

	@Override
	public Integer countParliamentCode(String filter) {
		return parliamentCodeDao.count(filter);
	}

	@Override
	public void saveParliamentCode(InParliamentCode ParliamentCode) {
		parliamentCodeDao.save(ParliamentCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateParliamentCode(InParliamentCode ParliamentCode) {
		parliamentCodeDao.update(ParliamentCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeParliamentCode(InParliamentCode ParliamentCode) {
		parliamentCodeDao.remove(ParliamentCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// STUDY MODE
	// ====================================================================================================

	@Override
	public InStudyMode findStudyModeById(Long id) {
		return studyModeDao.findById(id);
	}

	@Override
	public InStudyMode findStudyModeByCode(String code) {
		return studyModeDao.findByCode(code);
	}

	@Override
	public List<InStudyMode> findStudyModes() {
		return studyModeDao.find();
	}

	@Override
	public List<InStudyMode> findStudyModes(String filter, Integer offset, Integer limit) {
		return studyModeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countStudyMode() {
		return studyModeDao.count();
	}

	@Override
	public Integer countStudyMode(String filter) {
		return studyModeDao.count(filter);
	}

	@Override
	public void saveStudyMode(InStudyMode studyMode) {
		studyModeDao.save(studyMode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateStudyMode(InStudyMode studyMode) {
		studyModeDao.update(studyMode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeStudyMode(InStudyMode studyMode) {
		studyModeDao.remove(studyMode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// SUPERVISOR CODE
	// ====================================================================================================

	@Override
	public InSupervisorCode findSupervisorCodeById(Long id) {
		return supervisorCodeDao.findById(id);
	}

	@Override
	public InSupervisorCode findSupervisorCodeByCode(String code) {
		return supervisorCodeDao.findByCode(code);
	}

	@Override
	public InSupervisorCode findSupervisorCodeByName(String name) {
		return supervisorCodeDao.findByName(name);
	}

	@Override
	public List<InSupervisorCode> findSupervisorCodes() {
		return supervisorCodeDao.find();
	}

	@Override
	public List<InSupervisorCode> findSupervisorCodes(InFacultyCode facultyCode) {
		throw new UnsupportedOperationException(); // todo:
	}

	@Override
	public List<InSupervisorCode> findSupervisorCodes(String filter, Integer offset, Integer limit) {
		return supervisorCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countSupervisorCode() {
		return supervisorCodeDao.count();
	}

	@Override
	public Integer countSupervisorCode(InFacultyCode facultyCode) {
		throw new UnsupportedOperationException(); // todo:
	}

	@Override
	public Integer countSupervisorCode(String filter) {
		return supervisorCodeDao.count(filter);
	}

	@Override
	public boolean isSupervisorCodeExists(String code) {
		return supervisorCodeDao.isExists(code);
	}

	@Override
	public void saveSupervisorCode(InSupervisorCode supervisorCode) {
		supervisorCodeDao.save(supervisorCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSupervisorCode(InSupervisorCode supervisorCode) {
		supervisorCodeDao.update(supervisorCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSupervisorCode(InSupervisorCode supervisorCode) {
		supervisorCodeDao.remove(supervisorCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// SUPERVISOR OFFERING
	// ====================================================================================================

	@Override
	public InSupervisorOffering findSupervisorOfferingById(Long id) {
		return supervisorOfferingDao.findById(id);
	}

	@Override
	public InSupervisorOffering findSupervisorOfferingByCode(String offering) {
		return supervisorOfferingDao.findByCode(offering);
	}

	// @Override
	// public InSupervisorOffering findSupervisorOfferingByName(String name) {
	// return supervisorOfferingDao.findByName(name);
	// }
	//

	@Override
	public List<InSupervisorOffering> findSupervisorOfferings() {
		return supervisorOfferingDao.find();
	}

	@Override
	public List<InSupervisorOffering> findSupervisorOfferings(InFacultyCode facultyCode) {
		throw new UnsupportedOperationException(); // todo:
	}

	@Override
	public List<InSupervisorOffering> findSupervisorOfferings(String filter, Integer offset, Integer limit) {
		return supervisorOfferingDao.find(filter, offset, limit);
	}

	@Override
	public List<InSupervisorOffering> findSupervisorOfferingsByProgramLevel(InProgramLevel inProgramLevel,
			String filter, Integer offset, Integer limit) {
		return supervisorOfferingDao.find(inProgramLevel, filter, offset, limit);
	}

	@Override
	public Integer countSupervisorOffering() {
		return supervisorOfferingDao.count();
	}

	// @Override
	// public Integer countSupervisorOffering(InFacultyOffering facultyOffering)
	// {
	// throw new UnsupportedOperationException(); // todo:
	// }

	@Override
	public Integer countSupervisorOffering(String filter) {
		return supervisorOfferingDao.count(filter);
	}

	// @Override
	// public boolean isSupervisorOfferingExists(String offering) {
	// return supervisorOfferingDao.isExists(offering);
	// }

	@Override
	public void saveSupervisorOffering(InSupervisorOffering supervisorOffering) {
		supervisorOfferingDao.save(supervisorOffering, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSupervisorOfferings(InSupervisorOffering supervisorOffering) {
		supervisorOfferingDao.update(supervisorOffering, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSupervisorOfferings(InSupervisorOffering supervisorOffering) {
		supervisorOfferingDao.delete(supervisorOffering, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// PROGRAM FIELD CODE
	// ====================================================================================================

	@Override
	public InProgramFieldCode findProgramFieldCodeById(Long id) {
		return programFieldCodeDao.findById(id);
	}

	@Override
	public InProgramFieldCode findProgramFieldCodeByCode(String code) {
		return programFieldCodeDao.findByCode(code);
	}

	@Override
	public List<InProgramFieldCode> findProgramFieldCodes() {
		return programFieldCodeDao.find();
	}

	@Override
	public List<InProgramFieldCode> findProgramFieldCodes(InGraduateCenter graduateCenter) {
		return programFieldCodeDao.find(graduateCenter);
	}

	@Override
	public List<InProgramFieldCode> findProgramFieldCodes(InFacultyCode facultyCode, InProgramLevel programLevel) {
		return null;
	}

	@Override
	public List<InProgramFieldCode> findProgramFieldCodes(InGraduateCenter graduateCenter,
			InProgramLevel programLevel) {
		return null;
	}

	@Override
	public List<InProgramFieldCode> findProgramFieldCodes(InFacultyCode facultyCode) {
		return programFieldCodeDao.find(facultyCode);
	}

	@Override
	public List<InProgramFieldCode> findProgramFieldCodes(String filter, Integer offset, Integer limit) {
		return programFieldCodeDao.find(filter, offset, limit);
	}

	@Override
	public List<InProgramFieldCode> findProgramFieldCodesByProgramLevel(InProgramLevel inProgramLevel) {
		return programFieldCodeDao.find(inProgramLevel);
	}

	@Override
	public Integer countProgramFieldCode() {
		return programFieldCodeDao.count();
	}

	@Override
	public Integer countProgramFieldCode(String filter) {
		return programFieldCodeDao.count(filter);
	}

	@Override
	public Integer countProgramFieldCode(InFacultyCode facultyCode) {
		return programFieldCodeDao.count(facultyCode);
	}

	@Override
	public Integer countProgramFieldCode(InGraduateCenter graduateCenter) {
		return programFieldCodeDao.count(graduateCenter);
	}

	@Override
	public Integer countProgramFieldCode(InFacultyCode facultyCode, InProgramLevel programLevel) {
		return null;
	}

	@Override
	public Integer countProgramFieldCode(InGraduateCenter graduateCenter, InProgramLevel programLevel) {
		return null;
	}

	@Override
	public void saveProgramFieldCode(InProgramFieldCode programCode) {
		programFieldCodeDao.save(programCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();

		InFacultyCode faculty = facultyCodeDao.findByCode(programCode.getFacultyCode().getCode());

		LOG.debug("Start Broadcast Program Field Code");

		// Prepare Faculty Payload
		LOG.debug("prepare facultyCode payload");
		FacultyCodePayload f = new FacultyCodePayload();
		f.setCode(faculty.getCode());
		f.setDescription(faculty.getDescriptionEn());
		f.setPrefix(faculty.getPrefix());

		// Prepare Program Level Payload
		ProgramLevelPayload l = new ProgramLevelPayload();
		l.setCode(programCode.getProgramCode().getProgramLevel().getCode());
		l.setDescription(programCode.getProgramCode().getProgramLevel().getDescription());

		// Prepare Program Field Code Payload

		ProgramCodePayload p = new ProgramCodePayload();
		p.setCode(programCode.getCode());
		p.setDescriptionMs(programCode.getProgramCode().getDescriptionMs());
		p.setDescriptionEn(programCode.getProgramCode().getDescriptionEn());
		p.setFacultyCode(f);
		p.setProgramLevel(l);

		ProgramFieldCodeAddedEvent event = new ProgramFieldCodeAddedEvent(p);
		applicationContext.publishEvent(event);
		LOG.debug("Finsh Broadcast Program Field Code");
	}

	@Override
	public void updateProgramFieldCode(InProgramFieldCode programCode) {
		programFieldCodeDao.update(programCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeProgramFieldCode(InProgramFieldCode programCode) {
		programFieldCodeDao.delete(programCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// EMPLOYMENT TYPE CODE
	// ====================================================================================================

	@Override
	public InEmploymentTypeCode findEmploymentTypeCodeById(Long id) {
		return employmentTypeCodeDao.findById(id);
	}

	@Override
	public InEmploymentTypeCode findEmploymentTypeCodeByCode(String code) {
		return employmentTypeCodeDao.findByCode(code);
	}

	@Override
	public List<InEmploymentTypeCode> findEmploymentTypeCodes() {
		return employmentTypeCodeDao.find();
	}

	@Override
	public List<InEmploymentTypeCode> findEmploymentTypeCodes(String filter, Integer offset, Integer limit) {
		return employmentTypeCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countEmploymentTypeCode() {
		return employmentTypeCodeDao.count();
	}

	@Override
	public Integer countEmploymentTypeCode(String filter) {
		return employmentTypeCodeDao.count(filter);
	}

	@Override
	public void saveEmploymentTypeCode(InEmploymentTypeCode employmentTypeCode) {
		employmentTypeCodeDao.save(employmentTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateEmploymentTypeCode(InEmploymentTypeCode employmentTypeCode) {
		employmentTypeCodeDao.update(employmentTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeEmploymentTypeCode(InEmploymentTypeCode employmentTypeCode) {
		employmentTypeCodeDao.delete(employmentTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// EMPLOYMENT SECTOR CODE
	// ====================================================================================================

	@Override
	public InEmploymentSectorCode findEmploymentSectorCodeById(Long id) {
		return employmentSectorCodeDao.findById(id);
	}

	@Override
	public InEmploymentSectorCode findEmploymentSectorCodeByCode(String code) {
		return employmentSectorCodeDao.findByCode(code);
	}

	@Override
	public List<InEmploymentSectorCode> findEmploymentSectorCodes() {
		return employmentSectorCodeDao.find();
	}

	@Override
	public List<InEmploymentSectorCode> findEmploymentSectorCodes(Integer offset, Integer limit) {
		return employmentSectorCodeDao.find();
	}

	@Override
	public List<InEmploymentSectorCode> findEmploymentSectorCodes(String filter, Integer offset, Integer limit) {
		return employmentSectorCodeDao.find();
	}

	@Override
	public Integer countEmploymentSectorCode() {
		return employmentSectorCodeDao.count();
	}

	@Override
	public Integer countEmploymentSectorCode(String filter) {
		return employmentSectorCodeDao.count();
	}

	@Override
	public void saveEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode) {
		employmentSectorCodeDao.save(employmentSectorCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode) {
		employmentSectorCodeDao.update(employmentSectorCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeEmploymentSectorCode(InEmploymentSectorCode employmentSectorCode) {
		employmentSectorCodeDao.delete(employmentSectorCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// GUARDIAN TYPE CODE
	// ====================================================================================================

	@Override
	public InGuardianTypeCode findGuardianTypeCodeById(Long id) {
		return guardianTypeCodeDao.findById(id);
	}

	@Override
	public InGuardianTypeCode findGuardianTypeCodeByCode(String code) {
		return guardianTypeCodeDao.findByCode(code);
	}

	@Override
	public List<InGuardianTypeCode> findGuardianTypeCodes() {
		return guardianTypeCodeDao.find();
	}

	@Override
	public List<InGuardianTypeCode> findGuardianTypeCodes(String filter, Integer offset, Integer limit) {
		return guardianTypeCodeDao.find(filter, offset, limit);

	}

	@Override
	public Integer countGuardianTypeCode() {
		return guardianTypeCodeDao.count();
	}

	@Override
	public Integer countGuardianTypeCode(String filter) {
		return guardianTypeCodeDao.count();
	}

	@Override
	public void saveGuardianTypeCode(InGuardianTypeCode guardianTypeCode) {
		guardianTypeCodeDao.save(guardianTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateGuardianTypeCode(InGuardianTypeCode guardianTypeCode) {
		guardianTypeCodeDao.update(guardianTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeGuardianTypeCode(InGuardianTypeCode guardianTypeCode) {
		guardianTypeCodeDao.delete(guardianTypeCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// SPM RESULT
	// ====================================================================================================

	@Override
	public InSpmResult findSpmResultById(Long id) {
		return spmResultDao.findById(id);
	}

	@Override
	public InSpmResult findSpmResultByCode(String code) {
		return spmResultDao.findByCode(code);
	}

	@Override
	public List<InSpmResult> findSpmResults() {
		return spmResultDao.find();
	}

	@Override
	public List<InSpmResult> findSpmResults(String filter, Integer offset, Integer limit) {
		return spmResultDao.find(filter, offset, limit);

	}

	@Override
	public Integer countSpmResult() {
		return spmResultDao.count();
	}

	@Override
	public void saveSpmResult(InSpmResult spmResult) {
		spmResultDao.save(spmResult, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSpmResult(InSpmResult spmResult) {
		spmResultDao.update(spmResult, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSpmResult(InSpmResult spmResult) {
		spmResultDao.delete(spmResult, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// SPM SUBJECT CODE
	// ====================================================================================================

	@Override
	public InSpmSubjectCode findSpmSubjectCodeById(Long id) {
		return spmSubjectCodeDao.findById(id);
	}

	@Override
	public InSpmSubjectCode findSpmSubjectCodeByCode(String code) {
		return spmSubjectCodeDao.findByCode(code);
	}

	@Override
	public List<InSpmSubjectCode> findSpmSubjectCodes() {
		return spmSubjectCodeDao.find();
	}

	@Override
	public List<InSpmSubjectCode> findSpmSubjectCodes(Integer offset, Integer limit) {
		return spmSubjectCodeDao.find(offset, limit);
	}

	@Override
	public List<InSpmSubjectCode> findSpmSubjectCodes(String filter, Integer offset, Integer limit) {
		return spmSubjectCodeDao.find(filter, offset, limit);
	}

	@Override
	public Integer countSpmSubjectCode() {
		return spmSubjectCodeDao.count();
	}

	@Override
	public Integer countSpmSubjectCode(String filter) {
		return spmSubjectCodeDao.count(filter);
	}

	@Override
	public void saveSpmSubjectCode(InSpmSubjectCode spmSubjectCode) {
		spmSubjectCodeDao.save(spmSubjectCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateSpmSubjectCode(InSpmSubjectCode spmSubjectCode) {
		spmSubjectCodeDao.update(spmSubjectCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeSpmSubjectCode(InSpmSubjectCode spmSubjectCode) {
		spmSubjectCodeDao.remove(spmSubjectCode, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
}
