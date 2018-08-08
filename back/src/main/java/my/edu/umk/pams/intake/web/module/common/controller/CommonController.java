package my.edu.umk.pams.intake.web.module.common.controller;

import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InProgramLevelImpl;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;
import my.edu.umk.pams.intake.policy.model.InSupervisorOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.integration.InAutoLoginToken;
import my.edu.umk.pams.intake.web.module.application.vo.FieldCode;
import my.edu.umk.pams.intake.web.module.common.vo.*;
import my.edu.umk.pams.intake.web.module.policy.vo.ProgramLevel;
import my.edu.umk.pams.intake.web.module.policy.vo.SupervisorOffering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/common")
public class CommonController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private CommonTransformer commonTransformer;

	@Autowired
	private AuthenticationManager authenticationManager;

	// ====================================================================================================
	// BANK CODES
	// ====================================================================================================

	@RequestMapping(value = "/bankCodes", method = RequestMethod.GET)
	public ResponseEntity<List<BankCode>> findBankCodes() {
		return new ResponseEntity<List<BankCode>>(commonTransformer.toBankCodeVos(commonService.findBankCodes()),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/bankCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<BankCode> findBankCode(@PathVariable String code) {
		return new ResponseEntity<BankCode>(commonTransformer.toBankCodeVo(commonService.findBankCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/bankCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveBankCode(@RequestBody BankCode vo) {
		dummyLogin();

		InBankCode bankCode = new InBankCodeImpl();
		bankCode.setCode(vo.getCode());
		bankCode.setName(vo.getName());
		bankCode.setIbgCode("TODO");
		bankCode.setSwiftCode("TODO");
		commonService.saveBankCode(bankCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/bankCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBankCode(@PathVariable String code, @RequestBody BankCode vo) {
		dummyLogin();

		InBankCode bankCode = commonService.findBankCodeById(vo.getId());
		bankCode.setCode(vo.getCode());
		bankCode.setName(vo.getName());
		bankCode.setIbgCode("TODO");
		bankCode.setSwiftCode("TODO");
		commonService.updateBankCode(bankCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/bankCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeBankCode(@PathVariable String code) {
		dummyLogin();

		InBankCode bankCode = commonService.findBankCodeByCode(code);
		commonService.removeBankCode(bankCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// DUN_CODE
	// ====================================================================================================

	@RequestMapping(value = "/dunCodes", method = RequestMethod.GET)
	public ResponseEntity<List<DunCode>> findDunCodes() {
		return new ResponseEntity<List<DunCode>>(
				commonTransformer.toDunCodeVos(commonService.findDunCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
	}

	@RequestMapping(value = "/dunCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<DunCode> findDunCodeByCode(@PathVariable String code) {
		return new ResponseEntity<DunCode>(commonTransformer.toDunCodeVo(commonService.findDunCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/dunCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveDunCode(@RequestBody DunCode vo) {
		dummyLogin();

		InDunCode dunCode = new InDunCodeImpl();
		dunCode.setCode(vo.getCode());
		dunCode.setDescription(vo.getDescription());
		commonService.saveDunCode(dunCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/dunCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDunCode(@PathVariable String code, @RequestBody DunCode vo) {
		dummyLogin();

		InDunCode dunCode = commonService.findDunCodeById(vo.getId());
		dunCode.setCode(vo.getCode());
		dunCode.setDescription(vo.getDescription());
		commonService.updateDunCode(dunCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/dunCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeDunCode(@PathVariable String code) {
		dummyLogin();

		InDunCode dunCode = commonService.findDunCodeByCode(code);
		commonService.removeDunCode(dunCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// DISTRICT_CODE
	// ====================================================================================================

	@RequestMapping(value = "/districtCodes", method = RequestMethod.GET)
	public ResponseEntity<List<DistrictCode>> findDistrictCodes() {
		return new ResponseEntity<List<DistrictCode>>(
				commonTransformer.toDistrictCodeVos(commonService.findDistrictCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/districtCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<DistrictCode> findDistrictCodeByCode(@PathVariable String code) {
		return new ResponseEntity<DistrictCode>(
				commonTransformer.toDistrictCodeVo(commonService.findDistrictCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/districtCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveDistrictCode(@RequestBody DistrictCode vo) {
		dummyLogin();

		InDistrictCode districtCode = new InDistrictCodeImpl();
		districtCode.setCode(vo.getCode());
		districtCode.setDescription(vo.getDescription());
		commonService.saveDistrictCode(districtCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/districtCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDistrictCode(@PathVariable String code, @RequestBody DistrictCode vo) {
		dummyLogin();

		InDistrictCode districtCode = commonService.findDistrictCodeById(vo.getId());
		districtCode.setCode(vo.getCode());
		districtCode.setDescription(vo.getDescription());
		commonService.updateDistrictCode(districtCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/districtCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeDistrictCode(@PathVariable String code) {
		dummyLogin();

		InDistrictCode districtCode = commonService.findDistrictCodeByCode(code);
		commonService.removeDistrictCode(districtCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// GRADUATE CENTER
	// ====================================================================================================

	@RequestMapping(value = "/graduateCenters", method = RequestMethod.GET)
	public ResponseEntity<List<GraduateCenter>> findGraduateCenters() {
		return new ResponseEntity<List<GraduateCenter>>(
				commonTransformer.toGraduateCenterVos(commonService.findGraduateCenters("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/graduateCenters/{code}", method = RequestMethod.GET)
	public ResponseEntity<GraduateCenter> findGraduateCenterByCode(@PathVariable String code) {
		return new ResponseEntity<GraduateCenter>(
				commonTransformer.toGraduateCenterVo(commonService.findGraduateCenterByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/graduateCenters/{code}/programCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramCode>> findProgramCodesByGraduateCenterCode(@PathVariable String code) {
		InGraduateCenter graduateCenter = commonService.findGraduateCenterByCode(code);
		return new ResponseEntity<List<ProgramCode>>(
				commonTransformer.toProgramCodeVos(commonService.findProgramCodes(graduateCenter)), HttpStatus.OK);
	}

	// localhost:8080/api/common/graduateCenters/MGSEB/programCodes/PHD
	@RequestMapping(value = "/graduateCenters/{code}/programCodes/{levelCode}", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramCode>> findProgramCodesByGraduateCenterCode(@PathVariable String code,
			@PathVariable String levelCode) {
		InGraduateCenter graduateCenter = commonService.findGraduateCenterByCode(code);
		InProgramLevel programLevel = policyService.findProgramLevelByCode(levelCode);
		return new ResponseEntity<List<ProgramCode>>(
				commonTransformer.toProgramCodeVos(commonService.findProgramCodes(graduateCenter, programLevel)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/graduateCenters", method = RequestMethod.POST)
	public ResponseEntity<String> saveGraduateCenter(@RequestBody GraduateCenter vo) {
		dummyLogin();

		InGraduateCenter graduateCenter = new InGraduateCenterImpl();
		graduateCenter.setCode(vo.getCode());
		graduateCenter.setDescriptionEn(vo.getDescriptionEn());
		graduateCenter.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveGraduateCenter(graduateCenter);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/graduateCenters/{center}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGraduateCenter(@PathVariable String center, @RequestBody GraduateCenter vo) {
		dummyLogin();

		InGraduateCenter graduateCenter = commonService.findGraduateCenterById(vo.getId());
		graduateCenter.setCode(vo.getCode());
		graduateCenter.setDescriptionEn(vo.getDescriptionEn());
		graduateCenter.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateGraduateCenter(graduateCenter);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/graduateCenters/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeGraduateCenter(@PathVariable String code) {
		dummyLogin();

		InGraduateCenter graduateCenter = commonService.findGraduateCenterByCode(code);
		commonService.removeGraduateCenter(graduateCenter);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// FACULTY CODES
	// ====================================================================================================

	@RequestMapping(value = "/facultyCodes", method = RequestMethod.GET)
	public ResponseEntity<List<FacultyCode>> findFacultyCodes() {
		return new ResponseEntity<List<FacultyCode>>(
				commonTransformer.toFacultyCodeVos(commonService.findFacultyCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<FacultyCode> findFacultyCode(@PathVariable String code) {
		return new ResponseEntity<FacultyCode>(
				commonTransformer.toFacultyCodeVo(commonService.findFacultyCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes/{code}/programCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramCode>> findProgramCodesByFacultyCode(@PathVariable String code) {
		InFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
		return new ResponseEntity<List<ProgramCode>>(
				commonTransformer.toProgramCodeVos(commonService.findProgramCodes(facultyCode)), HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes/{code}/programCodes/{levelCode}", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramCode>> findProgramCodesByFacultyCode(@PathVariable String code,
			@PathVariable String levelCode) {
		InFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
		InProgramLevel programLevel = policyService.findProgramLevelByCode(levelCode);
		return new ResponseEntity<List<ProgramCode>>(
				commonTransformer.toProgramCodeVos(commonService.findProgramCodes(facultyCode, programLevel)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveFacultyCode(@RequestBody FacultyCode vo) {
		dummyLogin();

		InFacultyCode facultyCode = new InFacultyCodeImpl();
		facultyCode.setCode(vo.getCode());
		facultyCode.setDescriptionMs(vo.getDescriptionMs());
		facultyCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.saveFacultyCode(facultyCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateFacultyCode(@PathVariable String code, @RequestBody FacultyCode vo) {
		dummyLogin();

		InFacultyCode facultyCode = commonService.findFacultyCodeById(vo.getId());
		facultyCode.setCode(vo.getCode());
		facultyCode.setDescriptionMs(vo.getDescriptionMs());
		facultyCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.updateFacultyCode(facultyCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/facultyCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeFacultyCode(@PathVariable String code) {
		dummyLogin();

		InFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
		commonService.removeFacultyCode(facultyCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// FIELD CODES
	// ====================================================================================================

	@RequestMapping(value = "/fieldCodes", method = RequestMethod.GET)
	public ResponseEntity<List<FieldCode>> findFieldCodes() {

		System.out.println("size field code cntrlr :" + commonService.findFieldCodes("%", 0, Integer.MAX_VALUE).size());
		return new ResponseEntity<List<FieldCode>>(
				commonTransformer.toFieldCodeVos(commonService.findFieldCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/fieldCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<FieldCode> findFieldCode(@PathVariable String code) {
		return new ResponseEntity<FieldCode>(commonTransformer.toFieldCodeVo(commonService.findFieldCodeByCode(code)),
				HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/fieldCodes/{code}/programCodes", method =
	 * RequestMethod.GET) public ResponseEntity<List<ProgramCode>>
	 * findProgramCodesByFieldCode(@PathVariable String code) { InFieldCode
	 * fieldCode = commonService.findFieldCodeByCode(code); return new
	 * ResponseEntity<List<ProgramCode>>(commonTransformer.toProgramCodeVos(
	 * commonService.findProgramCodes(fieldCode)), HttpStatus.OK); }
	 */

	@RequestMapping(value = "/fieldCodes/{code}/programCodes/{levelCode}", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramCode>> findFieldCodesByFacultyCode(@PathVariable String code,
			@PathVariable String levelCode) {
		InFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
		InProgramLevel programLevel = policyService.findProgramLevelByCode(levelCode);
		return new ResponseEntity<List<ProgramCode>>(
				commonTransformer.toProgramCodeVos(commonService.findProgramCodes(facultyCode, programLevel)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/saveFieldCode", method = RequestMethod.POST)
	public ResponseEntity<String> saveFieldCode(@RequestBody FieldCode vo) {
		dummyLogin();

		InFieldCode fieldCode = new InFieldCodeImpl();
		fieldCode.setCode(vo.getCode());
		fieldCode.setDescriptionMs(vo.getDescriptionMs());
		fieldCode.setDescriptionEn(vo.getDescriptionEn());

		commonService.saveFieldCode(fieldCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/fieldCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateFieldCode(@PathVariable String code, @RequestBody FieldCode vo) {
		dummyLogin();

		InFieldCode fieldCode = commonService.findFieldCodeById(vo.getId());
		fieldCode.setCode(vo.getCode());
		fieldCode.setDescriptionMs(vo.getDescriptionMs());
		fieldCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.updateFieldCode(fieldCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/fieldCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeFieldCode(@PathVariable String code) {
		// dummyLogin();

		InFieldCode fieldCode = commonService.findFieldCodeByCode(code);
		commonService.removeFieldCode(fieldCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// PROGRAM CODES
	// ====================================================================================================

	@RequestMapping(value = "/programCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramCode>> findProgramCodes() {
		return new ResponseEntity<List<ProgramCode>>(
				commonTransformer.toProgramCodeVos(commonService.findProgramCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/programCodes/programLevel/{levelCode}", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramCode>> findProgramCodesByProgramLevel(@PathVariable String levelCode) {
		InProgramLevel inProgramLevel = policyService.findProgramLevelByCode(levelCode);
		return new ResponseEntity<List<ProgramCode>>(
				commonTransformer.toProgramCodeVos(
						commonService.findProgramCodesByProgramLevel(inProgramLevel, "%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/programCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<ProgramCode> findProgramCode(@PathVariable String code) {
		return new ResponseEntity<ProgramCode>(
				commonTransformer.toProgramCodeVo(commonService.findProgramCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/programCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveProgramCode(@RequestBody ProgramCode vo) {
		dummyLogin();

		InProgramCode programCode = new InProgramCodeImpl();
		programCode.setCode(vo.getCode());
		programCode.setDescriptionEn(vo.getDescriptionEn());
		programCode.setDescriptionMs(vo.getDescriptionMs());
		programCode.setGraduateCenter(commonService.findGraduateCenterById(vo.getGraduateCenter().getId()));
		programCode.setProgramLevel(policyService.findProgramLevelById(vo.getProgramLevel().getId()));
		programCode.setFacultyCode(commonService.findFacultyCodeById(vo.getFacultyCode().getId()));
		commonService.saveProgramCode(programCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/programCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProgramCode(@PathVariable String code, @RequestBody ProgramCode vo) {
		dummyLogin();

		InProgramCode programCode = commonService.findProgramCodeById(vo.getId());
		programCode.setCode(vo.getCode());
		programCode.setDescriptionEn(vo.getDescriptionEn());
		programCode.setDescriptionMs(vo.getDescriptionMs());
		programCode.setGraduateCenter(commonService.findGraduateCenterById(vo.getGraduateCenter().getId()));
		programCode.setProgramLevel(policyService.findProgramLevelById(vo.getProgramLevel().getId()));
		programCode.setFacultyCode(commonService.findFacultyCodeById(vo.getFacultyCode().getId()));
		commonService.updateProgramCode(programCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/programCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeProgramCode(@PathVariable String code) {
		dummyLogin();

		InProgramCode programCode = commonService.findProgramCodeByCode(code);
		commonService.removeProgramCode(programCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// PROGRAM FIELD CODES
	// ====================================================================================================

	@RequestMapping(value = "/programFieldCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramFieldCode>> findProgramFieldCodes() {
		return new ResponseEntity<List<ProgramFieldCode>>(
				commonTransformer.toProgramFieldCodeVos(commonService.findProgramFieldCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/programFieldCodes/programLevel/{levelCode}", method = RequestMethod.GET)
	public ResponseEntity<List<ProgramFieldCode>> findProgramFieldCodesByProgramLevel(@PathVariable String levelCode) {
		InProgramLevel inProgramLevel = policyService.findProgramLevelByCode(levelCode);
		return new ResponseEntity<List<ProgramFieldCode>>(commonTransformer.toProgramFieldCodeVos(
				commonService.findProgramFieldCodesByProgramLevel(inProgramLevel)), HttpStatus.OK);
	}

	@RequestMapping(value = "/programFieldCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<ProgramFieldCode> findProgramFieldCode(@PathVariable String code) {
		return new ResponseEntity<ProgramFieldCode>(
				commonTransformer.toProgramFieldCodeVo(commonService.findProgramFieldCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/saveProgramFieldCode", method = RequestMethod.POST)
	public ResponseEntity<String> saveProgramFieldCode(@RequestBody ProgramFieldCode vo) {
		dummyLogin();

		String programCode = commonService.findProgramCodeById(vo.getProgramCode().getId()).getCode().substring(0, 1);
		String fieldCode = commonService.findFieldCodeById(vo.getFieldCode().getId()).getCode();

		System.out.println("vo :" + commonService.findProgramCodeById(vo.getProgramCode().getId()).getCode());

		InProgramFieldCode programFieldCode = new InProgramFieldCodeImpl();

		programFieldCode.setCode(programCode + fieldCode);
		programFieldCode.setFacultyCode(commonService.findFacultyCodeById(vo.getFacultyCode().getId()));
		programFieldCode.setProgramCode(commonService.findProgramCodeById(vo.getProgramCode().getId()));
		programFieldCode.setFieldCode(commonService.findFieldCodeById(vo.getFieldCode().getId()));
		commonService.saveProgramFieldCode(programFieldCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/updateProgramFieldCode/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProgramFieldCode(@PathVariable String code, @RequestBody ProgramFieldCode vo) {
		dummyLogin();

		InProgramFieldCode programFieldCode = commonService.findProgramFieldCodeById(vo.getId());
		programFieldCode.setFacultyCode(commonService.findFacultyCodeById(vo.getFacultyCode().getId()));
		programFieldCode.setProgramCode(commonService.findProgramCodeById(vo.getProgramCode().getId()));
		programFieldCode.setFieldCode(commonService.findFieldCodeById(vo.getFieldCode().getId()));
		commonService.updateProgramFieldCode(programFieldCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/programFieldCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeProgramFieldCode(@PathVariable String code) {
		dummyLogin();

		InProgramFieldCode programFieldCode = commonService.findProgramFieldCodeByCode(code);
		commonService.removeProgramFieldCode(programFieldCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// RESIDENCY_CODE
	// ====================================================================================================

	@RequestMapping(value = "/residencyCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ResidencyCode>> findResidencyCodes() {
		return new ResponseEntity<List<ResidencyCode>>(
				commonTransformer.toResidencyCodeVos(commonService.findResidencyCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/residencyCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<ResidencyCode> findResidencyCodeByCode(@PathVariable String code) {
		return new ResponseEntity<ResidencyCode>(
				commonTransformer.toResidencyCodeVo(commonService.findResidencyCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/residencyCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveResidencyCode(@RequestBody ResidencyCode vo) {
		dummyLogin();

		InResidencyCode residencyCode = new InResidencyCodeImpl();
		residencyCode.setCode(vo.getCode());
		residencyCode.setDescriptionEn(vo.getDescriptionEn());
		residencyCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveResidencyCode(residencyCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/residencyCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateResidencyCode(@PathVariable String code, @RequestBody ResidencyCode vo) {
		dummyLogin();

		InResidencyCode residencyCode = commonService.findResidencyCodeById(vo.getId());
		residencyCode.setCode(vo.getCode());
		residencyCode.setDescriptionEn(vo.getDescriptionEn());
		residencyCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateResidencyCode(residencyCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/residencyCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeResidencyCode(@PathVariable String code) {
		dummyLogin();

		InResidencyCode residencyCode = commonService.findResidencyCodeByCode(code);
		commonService.removeResidencyCode(residencyCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// SUPERVISOR CODES
	// ====================================================================================================

	@RequestMapping(value = "/supervisorCodes", method = RequestMethod.GET)
	public ResponseEntity<List<SupervisorCode>> findSupervisorCodes() {
		return new ResponseEntity<List<SupervisorCode>>(
				commonTransformer.toSupervisorCodeVos(commonService.findSupervisorCodes()), HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorCodes/byFilter/{filter}", method = RequestMethod.GET)
	public ResponseEntity<List<SupervisorCode>> findSupervisorCodes(@PathVariable String filter) {
		List<InSupervisorCode> supervisorCodes = commonService.findSupervisorCodes(filter, 0, Integer.MAX_VALUE);
		return new ResponseEntity<List<SupervisorCode>>(commonTransformer.toSupervisorCodeVos(supervisorCodes),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<SupervisorCode> findSupervisorCode(@PathVariable String code) {
		return new ResponseEntity<SupervisorCode>(
				commonTransformer.toSupervisorCodeVo(commonService.findSupervisorCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveSupervisorCode(@RequestBody SupervisorCode vo) {
		dummyLogin();

		InSupervisorCode supervisorCode = new InSupervisorCodeImpl();
		supervisorCode.setCode(vo.getCode());
		supervisorCode.setName(vo.getName());
		supervisorCode.setTitle(vo.getTitle());
		supervisorCode.setDescriptionEn(vo.getDescriptionEn());
		supervisorCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveSupervisorCode(supervisorCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSupervisorCode(@PathVariable String code, @RequestBody SupervisorCode vo) {
		dummyLogin();

		InSupervisorCode supervisorCode = commonService.findSupervisorCodeById(vo.getId());
		supervisorCode.setCode(vo.getCode());
		supervisorCode.setDescriptionEn(vo.getDescriptionEn());
		supervisorCode.setDescriptionMs(vo.getDescriptionMs());
		supervisorCode.setTitle(vo.getTitle());
		supervisorCode.setName(vo.getName());
		commonService.updateSupervisorCode(supervisorCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeSupervisorCode(@PathVariable String code) {
		dummyLogin();

		InSupervisorCode supervisorCode = commonService.findSupervisorCodeByCode(code);
		commonService.removeSupervisorCode(supervisorCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// SUPERVISOR OFFERINGS
	// ====================================================================================================

	@RequestMapping(value = "/supervisorOfferings", method = RequestMethod.GET)
	public ResponseEntity<List<SupervisorOffering>> findSupervisorOfferings() {
		return new ResponseEntity<List<SupervisorOffering>>(
				commonTransformer.toSupervisorOfferingVos(commonService.findSupervisorOfferings()), HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorOfferings/programLevel/{levelCode}", method = RequestMethod.GET)
	public ResponseEntity<List<SupervisorOffering>> findSupervisorOfferingsByProgramLevel(
			@PathVariable String levelCode) {
		InProgramLevel inProgramLevel = policyService.findProgramLevelByCode(levelCode);
		return new ResponseEntity<List<SupervisorOffering>>(
				commonTransformer.toSupervisorOfferingVos(
						commonService.findSupervisorOfferingsByProgramLevel(inProgramLevel, "%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorOfferings/byFilter/{filter}", method = RequestMethod.GET)
	public ResponseEntity<List<SupervisorOffering>> findSupervisorOfferings(@PathVariable String filter) {
		List<InSupervisorOffering> supervisorOfferings = commonService.findSupervisorOfferings(filter, 0,
				Integer.MAX_VALUE);
		return new ResponseEntity<List<SupervisorOffering>>(
				commonTransformer.toSupervisorOfferingVos(supervisorOfferings), HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorOfferings/{offering}", method = RequestMethod.GET)
	public ResponseEntity<SupervisorOffering> findSupervisorOffering(@PathVariable String offering) {
		return new ResponseEntity<SupervisorOffering>(
				commonTransformer.toSupervisorOfferingVo(commonService.findSupervisorOfferingByCode(offering)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorOfferings", method = RequestMethod.POST)
	public ResponseEntity<String> saveSupervisorOfferings(@RequestBody SupervisorOffering vo) {

		InSupervisorOffering supervisorOffering = new InSupervisorOfferingImpl();
		supervisorOffering.setSupervisorCode(commonService.findSupervisorCodeById(vo.getSupervisorCode().getId()));
		supervisorOffering.setProgramLevel(policyService.findProgramLevelById(vo.getProgramLevel().getId()));
		commonService.saveSupervisorOffering(supervisorOffering);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorOfferings/{offering}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSupervisorOfferings(@PathVariable String offering,
			@RequestBody SupervisorOffering vo) {
		dummyLogin();

		InSupervisorOffering supervisorOffering = commonService.findSupervisorOfferingById(vo.getId());
		supervisorOffering.setSupervisorCode(commonService.findSupervisorCodeById(vo.getSupervisorCode().getId()));
		supervisorOffering.setProgramLevel(policyService.findProgramLevelById(vo.getProgramLevel().getId()));
		commonService.updateSupervisorOfferings(supervisorOffering);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/supervisorOfferings/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeSupervisorOfferings(@PathVariable long id) {
		InSupervisorOffering supervisorOffering = commonService.findSupervisorOfferingById(id);
		commonService.removeSupervisorOfferings(supervisorOffering);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	// ====================================================================================================
	// STUDY MODES
	// ====================================================================================================

	@RequestMapping(value = "/studyModes", method = RequestMethod.GET)
	public ResponseEntity<List<StudyMode>> findStudyModes() {
		return new ResponseEntity<List<StudyMode>>(
				commonTransformer.toStudyModeVos(commonService.findStudyModes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/studyModes/{code}", method = RequestMethod.GET)
	public ResponseEntity<StudyMode> findStudyMode(@PathVariable String code) {
		return new ResponseEntity<StudyMode>(commonTransformer.toStudyModeVo(commonService.findStudyModeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/studyModes", method = RequestMethod.POST)
	public ResponseEntity<String> saveStudyMode(@RequestBody StudyMode vo) {
		dummyLogin();

		InStudyMode studyMode = new InStudyModeImpl();
		studyMode.setCode(vo.getCode());
		studyMode.setDescriptionEn(vo.getDescriptionEn());
		studyMode.setDescriptionMs(vo.getDescriptionMs());
		studyMode.setPrefix(vo.getPrefix());
		commonService.saveStudyMode(studyMode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/studyModes/{mode}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStudyMode(@PathVariable String mode, @RequestBody StudyMode vo) {
		dummyLogin();

		InStudyMode studyMode = commonService.findStudyModeById(vo.getId());
		studyMode.setCode(vo.getCode());
		studyMode.setDescriptionEn(vo.getDescriptionEn());
		studyMode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateStudyMode(studyMode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/studyModes/{mode}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeStudyMode(@PathVariable String mode) {
		dummyLogin();

		InStudyMode studyMode = commonService.findStudyModeByCode(mode);
		commonService.removeStudyMode(studyMode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// GENDER CODES
	// ====================================================================================================

	@RequestMapping(value = "/genderCodes", method = RequestMethod.GET)
	public ResponseEntity<List<GenderCode>> findGenderCodes() {
		return new ResponseEntity<List<GenderCode>>(
				commonTransformer.toGenderCodeVos(commonService.findGenderCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/genderCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<GenderCode> findGenderCode(@PathVariable String code) {
		return new ResponseEntity<GenderCode>(
				commonTransformer.toGenderCodeVo(commonService.findGenderCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/genderCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveGenderCode(@RequestBody GenderCode vo) {
		dummyLogin();

		InGenderCode genderCode = new InGenderCodeImpl();
		genderCode.setCode(vo.getCode());
		genderCode.setDescriptionEn(vo.getDescriptionEn());
		genderCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveGenderCode(genderCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/genderCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGenderCode(@PathVariable String code, @RequestBody GenderCode vo) {
		dummyLogin();

		InGenderCode genderCode = commonService.findGenderCodeById(vo.getId());
		genderCode.setCode(vo.getCode());
		genderCode.setDescriptionEn(vo.getDescriptionEn());
		genderCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateGenderCode(genderCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/genderCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeGenderCode(@PathVariable String code) {
		dummyLogin();

		InGenderCode genderCode = commonService.findGenderCodeByCode(code);
		commonService.removeGenderCode(genderCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// VENUE CODES
	// ====================================================================================================

	@RequestMapping(value = "/venueCodes", method = RequestMethod.GET)
	public ResponseEntity<List<VenueCode>> findVenueCodes() {
		return new ResponseEntity<List<VenueCode>>(commonTransformer.toVenueCodeVos(commonService.findVenueCodes()),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/VenueCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<VenueCode> findVenueCode(@PathVariable String code) {
		return new ResponseEntity<VenueCode>(commonTransformer.toVenueCodeVo(commonService.findVenueCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/venueCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveVenueCode(@RequestBody VenueCode vo) {
		dummyLogin();

		InVenueCode venueCode = new InVenueCodeImpl();
		venueCode.setCode(vo.getCode());
		venueCode.setRegistrationDate(vo.getRegistrationDate());
		venueCode.setRegistrationLocation(vo.getRegistrationLocation());
		venueCode.setStartTimeEn(vo.getStartTimeEn());
		venueCode.setStartTimeMs(vo.getStartTimeMs());
		venueCode.setEndTimeEn(vo.getEndTimeEn());
		venueCode.setEndTimeMs(vo.getEndTimeMs());
		commonService.saveVenueCode(venueCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/venueCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateVenueCode(@PathVariable String code, @RequestBody VenueCode vo) {
		dummyLogin();

		InVenueCode venueCode = commonService.findVenueCodeById(vo.getId());
		venueCode.setCode(vo.getCode());
		venueCode.setRegistrationDate(vo.getRegistrationDate());
		venueCode.setRegistrationLocation(vo.getRegistrationLocation());
		commonService.updateVenueCode(venueCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/venueCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeVenueCode(@PathVariable String code) {
		dummyLogin();

		InVenueCode venueCode = commonService.findVenueCodeByCode(code);
		commonService.removeVenueCode(venueCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	// ====================================================================================================
	// MARITAL_CODE
	// ====================================================================================================

	@RequestMapping(value = "/maritalCodes", method = RequestMethod.GET)
	public ResponseEntity<List<MaritalCode>> findMaritalCodes() {
		return new ResponseEntity<List<MaritalCode>>(
				commonTransformer.toMaritalCodeVos(commonService.findMaritalCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/maritalCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<MaritalCode> findMaritalCodeByCode(@PathVariable String code) {
		return new ResponseEntity<MaritalCode>(
				commonTransformer.toMaritalCodeVo(commonService.findMaritalCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/maritalCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveMaritalCode(@RequestBody MaritalCode vo) {
		dummyLogin();

		InMaritalCode maritalCode = new InMaritalCodeImpl();
		maritalCode.setCode(vo.getCode());
		maritalCode.setDescriptionEn(vo.getDescriptionEn());
		maritalCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveMaritalCode(maritalCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/maritalCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateMaritalCode(@PathVariable String code, @RequestBody MaritalCode vo) {
		dummyLogin();

		InMaritalCode maritalCode = commonService.findMaritalCodeById(vo.getId());
		maritalCode.setCode(vo.getCode());
		maritalCode.setDescriptionEn(vo.getDescriptionEn());
		maritalCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateMaritalCode(maritalCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/maritalCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeMaritalCode(@PathVariable String code) {
		dummyLogin();

		InMaritalCode maritalCode = commonService.findMaritalCodeByCode(code);
		commonService.removeMaritalCode(maritalCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// PRIVATE METHODS
	// ====================================================================================================

	private void dummyLogin() {
		// Noop
		// InAutoLoginToken token = new InAutoLoginToken("root");
		// Authentication authed = authenticationManager.authenticate(token);
		// SecurityContextHolder.getContext().setAuthentication(authed);
	}

	// ====================================================================================================
	// IN_RELIGION_CODE
	// ====================================================================================================

	@RequestMapping(value = "/religionCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ReligionCode>> findReligionCodes() {
		return new ResponseEntity<List<ReligionCode>>(
				commonTransformer.toReligionCodeVos(commonService.findReligionCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/religionCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<ReligionCode> findReligionCodeByCode(@PathVariable String code) {
		return new ResponseEntity<ReligionCode>(
				commonTransformer.toReligionCodeVo(commonService.findReligionCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/religionCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveReligionCode(@RequestBody ReligionCode vo) {
		dummyLogin();

		InReligionCode religionCode = new InReligionCodeImpl();
		religionCode.setCode(vo.getCode());
		religionCode.setDescriptionEn(vo.getDescriptionEn());
		religionCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveReligionCode(religionCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/religionCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateReligionCode(@PathVariable String code, @RequestBody ReligionCode vo) {
		dummyLogin();

		InReligionCode religionCode = commonService.findReligionCodeById(vo.getId());
		religionCode.setCode(vo.getCode());
		religionCode.setDescriptionEn(vo.getDescriptionEn());
		religionCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateReligionCode(religionCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/religionCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeReligionCode(@PathVariable String code) {
		dummyLogin();

		InReligionCode religionCode = commonService.findReligionCodeByCode(code);
		commonService.removeReligionCode(religionCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// COUNTRY_CODE
	// ====================================================================================================

	@RequestMapping(value = "/countryCodes", method = RequestMethod.GET)
	public ResponseEntity<List<CountryCode>> findCountryCodes() {
		return new ResponseEntity<List<CountryCode>>(
				commonTransformer.toCountryCodeVos(commonService.findCountryCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/countryCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<CountryCode> findCountryCodeByCode(@PathVariable String code) {
		return new ResponseEntity<CountryCode>(
				commonTransformer.toCountryCodeVo(commonService.findCountryCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/countryCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveCountryCode(@RequestBody CountryCode vo) {
		dummyLogin();

		InCountryCode countryCode = new InCountryCodeImpl();
		countryCode.setCode(vo.getCode());
		countryCode.setDescriptionEn(vo.getDescriptionEn());
		countryCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveCountryCode(countryCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/countryCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCountryCode(@PathVariable String code, @RequestBody CountryCode vo) {
		dummyLogin();

		InCountryCode countryCode = commonService.findCountryCodeById(vo.getId());
		countryCode.setCode(vo.getCode());
		countryCode.setDescriptionEn(vo.getDescriptionEn());
		countryCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateCountryCode(countryCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/countryCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeCountryCode(@PathVariable String code) {
		dummyLogin();

		InCountryCode countryCode = commonService.findCountryCodeByCode(code);
		commonService.removeCountryCode(countryCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// STATE_CODE
	// ====================================================================================================

	@RequestMapping(value = "/stateCodes", method = RequestMethod.GET)
	public ResponseEntity<List<StateCode>> findStateCodes() {
		return new ResponseEntity<List<StateCode>>(
				commonTransformer.toStateCodeVos(commonService.findStateCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/stateCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<StateCode> findStateCodeByCode(@PathVariable String code) {
		return new ResponseEntity<StateCode>(commonTransformer.toStateCodeVo(commonService.findStateCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/stateCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveStateCode(@RequestBody StateCode vo) {
		dummyLogin();

		InStateCode stateCode = new InStateCodeImpl();
		stateCode.setCode(vo.getCode());
		stateCode.setDescriptionEn(vo.getDescriptionEn());
		stateCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveStateCode(stateCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/stateCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStateCode(@PathVariable String code, @RequestBody StateCode vo) {
		dummyLogin();

		InStateCode stateCode = commonService.findStateCodeById(vo.getId());
		stateCode.setCode(vo.getCode());
		stateCode.setDescriptionEn(vo.getDescriptionEn());
		stateCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateStateCode(stateCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/stateCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeStateCode(@PathVariable String code) {
		dummyLogin();

		InStateCode stateCode = commonService.findStateCodeByCode(code);
		commonService.removeStateCode(stateCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// RACE CODES
	// ====================================================================================================

	@RequestMapping(value = "/raceCodes", method = RequestMethod.GET)
	public ResponseEntity<List<RaceCode>> findRaceCodes() {
		return new ResponseEntity<List<RaceCode>>(
				commonTransformer.toRaceCodeVos(commonService.findRaceCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
	}

	@RequestMapping(value = "/raceCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<RaceCode> findRaceCode(@PathVariable String code) {
		return new ResponseEntity<RaceCode>(commonTransformer.toRaceCodeVo(commonService.findRaceCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/raceCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveRaceCode(@RequestBody RaceCode vo) {
		dummyLogin();

		InRaceCode raceCode = new InRaceCodeImpl();
		raceCode.setCode(vo.getCode());
		raceCode.setDescriptionEn(vo.getDescriptionEn());
		raceCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveRaceCode(raceCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/raceCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateRaceCode(@PathVariable String code, @RequestBody RaceCode vo) {
		dummyLogin();

		InRaceCode raceCode = commonService.findRaceCodeById(vo.getId());
		raceCode.setCode(vo.getCode());
		raceCode.setDescriptionEn(vo.getDescriptionEn());
		raceCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateRaceCode(raceCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/raceCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeRaceCode(@PathVariable String code) {
		dummyLogin();

		InRaceCode raceCode = commonService.findRaceCodeByCode(code);
		commonService.removeRaceCode(raceCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// ETHNICITY_CODE
	// ====================================================================================================

	@RequestMapping(value = "/ethnicityCodes", method = RequestMethod.GET)
	public ResponseEntity<List<EthnicityCode>> findEthnicityCodes() {
		return new ResponseEntity<List<EthnicityCode>>(
				commonTransformer.toEthnicityCodeVos(commonService.findEthnicityCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/ethnicityCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<EthnicityCode> findEthnicityCodeByCode(@PathVariable String code) {
		return new ResponseEntity<EthnicityCode>(
				commonTransformer.toEthnicityCodeVo(commonService.findEthnicityCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/ethnicityCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveEthnicityCode(@RequestBody EthnicityCode vo) {
		dummyLogin();

		InEthnicityCode ethnicityCode = new InEthnicityCodeImpl();
		ethnicityCode.setCode(vo.getCode());
		ethnicityCode.setDescriptionEn(vo.getDescriptionEn());
		ethnicityCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveEthnicityCode(ethnicityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/ethnicityCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEthnicityCode(@PathVariable String code, @RequestBody EthnicityCode vo) {
		dummyLogin();

		InEthnicityCode ethnicityCode = commonService.findEthnicityCodeById(vo.getId());
		ethnicityCode.setCode(vo.getCode());
		ethnicityCode.setDescriptionEn(vo.getDescriptionEn());
		ethnicityCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateEthnicityCode(ethnicityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/ethnicityCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeEthnicityCode(@PathVariable String code) {
		dummyLogin();

		InEthnicityCode ethnicityCode = commonService.findEthnicityCodeByCode(code);
		commonService.removeEthnicityCode(ethnicityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// NATIONALITY_CODE
	// ====================================================================================================

	@RequestMapping(value = "/nationalityCodes", method = RequestMethod.GET)
	public ResponseEntity<List<NationalityCode>> findNationalityCodes() {
		return new ResponseEntity<List<NationalityCode>>(
				commonTransformer.toNationalityCodeVos(commonService.findNationalityCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/nationalityCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<NationalityCode> findNationalityCodeByCode(@PathVariable String code) {
		return new ResponseEntity<NationalityCode>(
				commonTransformer.toNationalityCodeVo(commonService.findNationalityCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/nationalityCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveNationalityCode(@RequestBody NationalityCode vo) {
		dummyLogin();

		InNationalityCode nationalityCode = new InNationalityCodeImpl();
		nationalityCode.setCode(vo.getCode());
		nationalityCode.setDescriptionEn(vo.getDescriptionEn());
		nationalityCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveNationalityCode(nationalityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/nationalityCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateNationalityCode(@PathVariable String code, @RequestBody NationalityCode vo) {
		dummyLogin();

		InNationalityCode nationalityCode = commonService.findNationalityCodeById(vo.getId());
		nationalityCode.setCode(vo.getCode());
		nationalityCode.setDescriptionEn(vo.getDescriptionEn());
		nationalityCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateNationalityCode(nationalityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/nationalityCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeNationalityCode(@PathVariable String code) {
		dummyLogin();

		InNationalityCode nationalityCode = commonService.findNationalityCodeByCode(code);
		commonService.removeNationalityCode(nationalityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// DISABILITY_CODE
	// ====================================================================================================

	@RequestMapping(value = "/disabilityCodes", method = RequestMethod.GET)
	public ResponseEntity<List<DisabilityCode>> findDisabilityCodes() {
		return new ResponseEntity<List<DisabilityCode>>(
				commonTransformer.toDisabilityCodeVos(commonService.findDisabilityCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/disabilityCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<DisabilityCode> findDisabilityCodeByCode(@PathVariable String code) {
		return new ResponseEntity<DisabilityCode>(
				commonTransformer.toDisabilityCodeVo(commonService.findDisabilityCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/disabilityCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveDisabilityCode(@RequestBody DisabilityCode vo) {
		dummyLogin();

		InDisabilityCode disabilityCode = new InDisabilityCodeImpl();
		disabilityCode.setCode(vo.getCode());
		disabilityCode.setDescriptionEn(vo.getDescriptionEn());
		disabilityCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveDisabilityCode(disabilityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/disabilityCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDisabilityCode(@PathVariable String code, @RequestBody DisabilityCode vo) {
		dummyLogin();

		InDisabilityCode disabilityCode = commonService.findDisabilityCodeById(vo.getId());
		disabilityCode.setCode(vo.getCode());
		disabilityCode.setDescriptionEn(vo.getDescriptionEn());
		disabilityCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateDisabilityCode(disabilityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/disabilityCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeDisabilityCode(@PathVariable String code) {
		dummyLogin();

		InDisabilityCode disabilityCode = commonService.findDisabilityCodeByCode(code);
		commonService.removeDisabilityCode(disabilityCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// STUDY_CENTER_CODE
	// ====================================================================================================

	@RequestMapping(value = "/studyCenterCodes", method = RequestMethod.GET)
	public ResponseEntity<List<StudyCenterCode>> findStudyCenterCodes() {
		return new ResponseEntity<List<StudyCenterCode>>(
				commonTransformer.toStudyCenterCodeVos(commonService.findStudyCenterCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/studyCenterCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<StudyCenterCode> findStudyCenterCodeByCode(@PathVariable String code) {
		return new ResponseEntity<StudyCenterCode>(
				commonTransformer.toStudyCenterCodeVo(commonService.findStudyCenterCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/studyCenterCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveStudyCenterCode(@RequestBody StudyCenterCode vo) {
		dummyLogin();

		InStudyCenterCode studyCenterCode = new InStudyCenterCodeImpl();
		studyCenterCode.setCode(vo.getCode());
		studyCenterCode.setDescriptionEn(vo.getDescriptionEn());
		studyCenterCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveStudyCenterCode(studyCenterCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/studyCenterCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateStudyCenterCode(@PathVariable String code, @RequestBody StudyCenterCode vo) {
		dummyLogin();

		InStudyCenterCode studyCenterCode = commonService.findStudyCenterCodeById(vo.getId());
		studyCenterCode.setCode(vo.getCode());
		studyCenterCode.setDescriptionEn(vo.getDescriptionEn());
		studyCenterCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateStudyCenterCode(studyCenterCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/studyCenterCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeStudyCenterCode(@PathVariable String code) {
		dummyLogin();

		InStudyCenterCode studyCenterCode = commonService.findStudyCenterCodeByCode(code);
		commonService.removeStudyCenterCode(studyCenterCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// SCHOOL_CODE
	// ====================================================================================================

	@RequestMapping(value = "/schoolCodes", method = RequestMethod.GET)
	public ResponseEntity<List<SchoolCode>> findSchoolCodes() {
		return new ResponseEntity<List<SchoolCode>>(
				commonTransformer.toSchoolCodeVos(commonService.findSchoolCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/schoolCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<SchoolCode> findSchoolCodeByCode(@PathVariable String code) {
		return new ResponseEntity<SchoolCode>(
				commonTransformer.toSchoolCodeVo(commonService.findSchoolCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/schoolCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveSchoolCode(@RequestBody SchoolCode vo) {
		dummyLogin();

		InSchoolCode schoolCode = new InSchoolCodeImpl();
		schoolCode.setCode(vo.getCode());
		schoolCode.setDescription(vo.getDescription());
		commonService.saveSchoolCode(schoolCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/schoolCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSchoolCode(@PathVariable String code, @RequestBody SchoolCode vo) {
		dummyLogin();

		InSchoolCode schoolCode = commonService.findSchoolCodeById(vo.getId());
		schoolCode.setCode(vo.getCode());
		schoolCode.setDescription(vo.getDescription());
		commonService.updateSchoolCode(schoolCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/schoolCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeSchoolCode(@PathVariable String code) {
		dummyLogin();

		InSchoolCode schoolCode = commonService.findSchoolCodeByCode(code);
		commonService.removeSchoolCode(schoolCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// PARLIAMENT_CODE
	// ====================================================================================================

	@RequestMapping(value = "/parliamentCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ParliamentCode>> findParliamentCodes() {
		return new ResponseEntity<List<ParliamentCode>>(
				commonTransformer.toParliamentCodeVos(commonService.findParliamentCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/parliamentCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<ParliamentCode> findParliamentCodeByCode(@PathVariable String code) {
		return new ResponseEntity<ParliamentCode>(
				commonTransformer.toParliamentCodeVo(commonService.findParliamentCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/parliamentCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveParliamentCode(@RequestBody ParliamentCode vo) {
		dummyLogin();

		InParliamentCode parliamentCode = new InParliamentCodeImpl();
		parliamentCode.setCode(vo.getCode());
		parliamentCode.setDescription(vo.getDescription());
		commonService.saveParliamentCode(parliamentCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/parliamentCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateParliamentCode(@PathVariable String code, @RequestBody ParliamentCode vo) {
		dummyLogin();

		InParliamentCode parliamentCode = commonService.findParliamentCodeById(vo.getId());
		parliamentCode.setCode(vo.getCode());
		parliamentCode.setDescription(vo.getDescription());
		commonService.updateParliamentCode(parliamentCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/parliamentCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeParliamentCode(@PathVariable String code) {
		dummyLogin();

		InParliamentCode parliamentCode = commonService.findParliamentCodeByCode(code);
		commonService.removeParliamentCode(parliamentCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// LANGUAGE_CODE
	// ====================================================================================================

	@RequestMapping(value = "/languageCodes", method = RequestMethod.GET)
	public ResponseEntity<List<LanguageCode>> findLanguageCodes() {
		return new ResponseEntity<List<LanguageCode>>(
				commonTransformer.toLanguageCodeVos(commonService.findLanguageCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/languageCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<LanguageCode> findLanguageCodeByCode(@PathVariable String code) {
		return new ResponseEntity<LanguageCode>(
				commonTransformer.toLanguageCodeVo(commonService.findLanguageCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/languageCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveLanguageCode(@RequestBody LanguageCode vo) {
		dummyLogin();

		InLanguageCode languageCode = new InLanguageCodeImpl();
		languageCode.setCode(vo.getCode());
		languageCode.setDescriptionEn(vo.getDescriptionEn());
		languageCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveLanguageCode(languageCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/languageCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateLanguageCode(@PathVariable String code, @RequestBody LanguageCode vo) {
		dummyLogin();

		InLanguageCode languageCode = commonService.findLanguageCodeById(vo.getId());
		languageCode.setCode(vo.getCode());
		languageCode.setDescriptionEn(vo.getDescriptionEn());
		languageCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateLanguageCode(languageCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/languageCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeLanguageCode(@PathVariable String code) {
		dummyLogin();

		InLanguageCode languageCode = commonService.findLanguageCodeByCode(code);
		commonService.removeLanguageCode(languageCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// Grade_CODE
	// ====================================================================================================

	@RequestMapping(value = "/gradeCodes", method = RequestMethod.GET)
	public ResponseEntity<List<GradeCode>> findGradeCodes() {
		return new ResponseEntity<List<GradeCode>>(
				commonTransformer.toGradeCodeVos(commonService.findGradeCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/gradeCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<GradeCode> findGradeCodeByCode(@PathVariable String code) {
		return new ResponseEntity<GradeCode>(commonTransformer.toGradeCodeVo(commonService.findGradeCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/gradeCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveGradeCode(@RequestBody GradeCode vo) {
		dummyLogin();

		InGradeCode gradeCode = new InGradeCodeImpl();
		gradeCode.setCode(vo.getCode());
		gradeCode.setDescription(vo.getDescription());
		gradeCode.setOrdinal(vo.getOrdinal());
		commonService.saveGradeCode(gradeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/gradeCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGradeCode(@PathVariable String code, @RequestBody GradeCode vo) {
		dummyLogin();

		InGradeCode gradeCode = commonService.findGradeCodeById(vo.getId());
		gradeCode.setCode(vo.getCode());
		gradeCode.setDescription(vo.getDescription());
		gradeCode.setOrdinal(vo.getOrdinal());
		commonService.updateGradeCode(gradeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/gradeCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeGradeCode(@PathVariable String code) {
		dummyLogin();

		InGradeCode gradeCode = commonService.findGradeCodeByCode(code);
		commonService.removeGradeCode(gradeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// Subject_CODE
	// ====================================================================================================

	@RequestMapping(value = "/subjectCodes", method = RequestMethod.GET)
	public ResponseEntity<List<SubjectCode>> findSubjectCodes() {
		return new ResponseEntity<List<SubjectCode>>(
				commonTransformer.toSubjectCodeVos(commonService.findSubjectCodes("%", 0, Integer.MAX_VALUE)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/subjectCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<SubjectCode> findSubjectCodeByCode(@PathVariable String code) {
		return new ResponseEntity<SubjectCode>(
				commonTransformer.toSubjectCodeVo(commonService.findSubjectCodeByCode(code)), HttpStatus.OK);
	}

	@RequestMapping(value = "/subjectCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveSubjectCode(@RequestBody SubjectCode vo) {
		dummyLogin();

		InSubjectCode subjectCode = new InSubjectCodeImpl();
		subjectCode.setCode(vo.getCode());
		subjectCode.setDescriptionEn(vo.getDescriptionEn());
		subjectCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.saveSubjectCode(subjectCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/subjectCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSubjectCode(@PathVariable String code, @RequestBody SubjectCode vo) {
		dummyLogin();

		InSubjectCode subjectCode = commonService.findSubjectCodeById(vo.getId());
		subjectCode.setCode(vo.getCode());
		subjectCode.setDescriptionEn(vo.getDescriptionEn());
		subjectCode.setDescriptionMs(vo.getDescriptionMs());
		commonService.updateSubjectCode(subjectCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/subjectCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeSubjectCode(@PathVariable String code) {
		dummyLogin();

		InSubjectCode subjectCode = commonService.findSubjectCodeByCode(code);
		commonService.removeSubjectCode(subjectCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// EMPLOYMENT_TYPE_CODE
	// ====================================================================================================

	@RequestMapping(value = "/employmentTypeCodes", method = RequestMethod.GET)
	public ResponseEntity<List<EmploymentTypeCode>> findEmploymentTypeCodes() {
		return new ResponseEntity<List<EmploymentTypeCode>>(commonTransformer.toEmploymentTypeCodeVos(
				commonService.findEmploymentTypeCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentTypeCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<EmploymentTypeCode> findEmploymentTypeCodeByCode(@PathVariable String code) {
		return new ResponseEntity<EmploymentTypeCode>(
				commonTransformer.toEmploymentTypeCodeVo(commonService.findEmploymentTypeCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentTypeCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveEmploymentTypeCode(@RequestBody EmploymentTypeCode vo) {
		dummyLogin();

		InEmploymentTypeCode employmentTypeCode = new InEmploymentTypeCodeImpl();
		employmentTypeCode.setCode(vo.getCode());
		employmentTypeCode.setDescriptionMs(vo.getDescriptionMs());
		employmentTypeCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.saveEmploymentTypeCode(employmentTypeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentTypeCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEmploymentTypeCode(@PathVariable String code,
			@RequestBody EmploymentTypeCode vo) {
		dummyLogin();

		InEmploymentTypeCode employmentTypeCode = commonService.findEmploymentTypeCodeById(vo.getId());
		employmentTypeCode.setCode(vo.getCode());
		employmentTypeCode.setDescriptionMs(vo.getDescriptionMs());
		employmentTypeCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.updateEmploymentTypeCode(employmentTypeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentTypeCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeEmploymentTypeCode(@PathVariable String code) {
		dummyLogin();

		InEmploymentTypeCode employmentTypeCode = commonService.findEmploymentTypeCodeByCode(code);
		commonService.removeEmploymentTypeCode(employmentTypeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// EMPLOYMENT_SECTOR_CODE
	// ====================================================================================================

	@RequestMapping(value = "/employmentSectorCodes", method = RequestMethod.GET)
	public ResponseEntity<List<EmploymentSectorCode>> findEmploymentSectorCodes() {
		return new ResponseEntity<List<EmploymentSectorCode>>(commonTransformer.toEmploymentSectorCodeVos(
				commonService.findEmploymentSectorCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentSectorCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<EmploymentSectorCode> findEmploymentSectorCodeByCode(@PathVariable String code) {
		return new ResponseEntity<EmploymentSectorCode>(
				commonTransformer.toEmploymentSectorCodeVo(commonService.findEmploymentSectorCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentSectorCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveEmploymentSectorCode(@RequestBody EmploymentSectorCode vo) {
		dummyLogin();

		InEmploymentSectorCode employmentSectorCode = new InEmploymentSectorCodeImpl();
		employmentSectorCode.setCode(vo.getCode());
		employmentSectorCode.setDescriptionMs(vo.getDescriptionMs());
		employmentSectorCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.saveEmploymentSectorCode(employmentSectorCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentSectorCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEmploymentSectorCode(@PathVariable String code,
			@RequestBody EmploymentSectorCode vo) {
		dummyLogin();

		InEmploymentSectorCode employmentSectorCode = commonService.findEmploymentSectorCodeById(vo.getId());
		employmentSectorCode.setCode(vo.getCode());
		employmentSectorCode.setDescriptionMs(vo.getDescriptionMs());
		employmentSectorCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.updateEmploymentSectorCode(employmentSectorCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/employmentSectorCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeEmploymentSectorCode(@PathVariable String code) {
		dummyLogin();

		InEmploymentSectorCode employmentSectorCode = commonService.findEmploymentSectorCodeByCode(code);
		commonService.removeEmploymentSectorCode(employmentSectorCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// GUARDIAN_TYPE_CODE
	// ====================================================================================================

	@RequestMapping(value = "/guardianTypeCodes", method = RequestMethod.GET)
	public ResponseEntity<List<GuardianTypeCode>> findGuardianTypeCodes() {
		return new ResponseEntity<List<GuardianTypeCode>>(commonTransformer.toGuardianTypeCodeVos(
				commonService.findGuardianTypeCodes("%", 0, Integer.MAX_VALUE)), HttpStatus.OK);
	}

	@RequestMapping(value = "/guardianTypeCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<GuardianTypeCode> findGuardianTypeCodeByCode(@PathVariable String code) {
		return new ResponseEntity<GuardianTypeCode>(
				commonTransformer.toGuardianTypeCodeVo(commonService.findGuardianTypeCodeByCode(code)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/guardianTypeCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveGuardianTypeCode(@RequestBody GuardianTypeCode vo) {
		dummyLogin();

		InGuardianTypeCode guardianTypeCode = new InGuardianTypeCodeImpl();
		guardianTypeCode.setCode(vo.getCode());
		guardianTypeCode.setDescriptionMs(vo.getDescriptionMs());
		guardianTypeCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.saveGuardianTypeCode(guardianTypeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/guardianTypeCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGuardianTypeCode(@PathVariable String code,
			@RequestBody GuardianTypeCode vo) {
		dummyLogin();

		InGuardianTypeCode guardianTypeCode = commonService.findGuardianTypeCodeById(vo.getId());
		guardianTypeCode.setCode(vo.getCode());
		guardianTypeCode.setDescriptionMs(vo.getDescriptionMs());
		guardianTypeCode.setDescriptionEn(vo.getDescriptionEn());
		commonService.updateGuardianTypeCode(guardianTypeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/guardianTypeCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeGuardianTypeCode(@PathVariable String code) {
		dummyLogin();

		InGuardianTypeCode guardianTypeCode = commonService.findGuardianTypeCodeByCode(code);
		commonService.removeGuardianTypeCode(guardianTypeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

}
