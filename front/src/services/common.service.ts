import { SpmResultCode } from './../app/shared/model/common/spm-result-code.interface';
import { Staff } from './../app/secure/identity/staff.interface';
import { EmploymentSectorCode } from './../app/shared/model/common/employment-sector-code.interface';
import { EmploymentTypeCode } from './../app/shared/model/common/employment-type-code.interface';
import { VenueCode } from './../app/shared/model/common/venue-code.interface';
import {ParliamentCode} from '../app/shared/model/common/parliament-code.interface';
import {StudyCenterCode} from '../app/shared/model/common/study-center-code.interface';
import {StudyMode} from '../app/shared/model/common/study-mode.interface';
import {NationalityCode} from '../app/shared/model/common/nationality-code.interface';
import {EthnicityCode} from '../app/shared/model/common/ethnicity-code.interface';
import {RaceCode} from '../app/shared/model/common/race-code.interface';
import {StateCode} from '../app/shared/model/common/state-code.interface';
import {SupervisorCode} from '../app/shared/model/common/supervisor-code.interface';
import {CountryCode} from '../app/shared/model/common/country-code.interface';
import {ReligionCode} from '../app/shared/model/common/religion-code.interface';
import {GenderCode} from '../app/shared/model/common/gender-code.interface';
import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {FacultyCode} from '../app/shared/model/common/faculty-code.interface';
import {environment} from '../environments/environment';
import {ProgramCode} from '../app/shared/model/common/program-code.interface';
import {GraduateCenter} from '../app/shared/model/common/graduate-center.interface';
import {MaritalCode} from '../app/shared/model/common/marital-code.interface';
import {DunCode} from '../app/shared/model/common/dun-code.interface';
import {BankCode} from '../app/shared/model/common/bank-code.interface';
import {DisabilityCode} from '../app/shared/model/common/disability-code.interface';
import {SchoolCode} from '../app/shared/model/common/school-code.interface';
import {DistrictCode} from '../app/shared/model/common/district-code.interface';
import {ResidencyCode} from '../app/shared/model/common/residency-code.interface';
import {LanguageCode} from '../app/shared/model/common/language-code.interface';
import {SubjectCode} from '../app/shared/model/common/subject-code.interface';
import {GradeCode} from '../app/shared/model/common/grade-code.interface';
import {ProgramLevel} from '../app/shared/model/policy/program-level.interface';
import { SupervisorOffering } from '../app/shared/model/common/supervisor-offering.interface';
import { ProgramFieldCode } from "../app/shared/model/common/program-field-code.interface";
import { FieldCode } from "../app/shared/model/common/field-code.interface";
import { GuardianTypeCode } from '../app/shared/model/common/guardian-type-code.interface';
import { SpmSubjectCode } from '../app/shared/model/common/spm-subject-code.interface';

@Injectable()
export class CommonService {

  private COMMON_API: string = environment.endpoint + '/api/common';

  constructor(private _http: HttpInterceptorService) {
  }

  //
  // UMKCEE STAFF
  //
  findUMKCEEStaffs(): Observable<Staff[]> {
    console.log('findUMKCEEStaffs');
    return this._http.get(this.COMMON_API + '/umkceeStaffs')
      .map((res: Response) => <Staff[]>res.json());
  }

  saveUMKCEEStaff(staff: Staff): Observable<String> {
    return this._http.post(this.COMMON_API + '/saveUMKCEEStaff', JSON.stringify(staff))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateUMKCEEStaff(staff: Staff): Observable<String> {
    return this._http.put(this.COMMON_API + '/updateUMKCEEStaff/'+staff.identityNo, JSON.stringify(staff))
      .flatMap((res: Response) => Observable.of(res.text()));
  }



  // ====================================================================================================
  // BANK CODES
  // ====================================================================================================

  findBankCodes(): Observable<BankCode[]> {
    console.log('findBankCodes');
    return this._http.get(this.COMMON_API + '/bankCodes')
      .map((res: Response) => <BankCode[]>res.json());
  }

  findBankCodeByCode(code: string): Observable<BankCode> {
    console.log('findBankCodeByCode');
    return this._http.get(this.COMMON_API + '/bankCodes/' + code)
      .map((res: Response) => <BankCode>res.json());
  }

  // ====================================================================================================
  // DISTRICT CODES
  // ====================================================================================================

  findDistrictCodes(): Observable<DistrictCode[]> {
    console.log('findDistrictCodes');
    return this._http.get(this.COMMON_API + '/districtCodes')
      .map((res: Response) => <DistrictCode[]>res.json());
  }

  findDistrictCodeByCode(code: string): Observable<DistrictCode> {
    console.log('findDistrictCodeByCode');
    return this._http.get(this.COMMON_API + '/districtCodes/' + code)
      .map((res: Response) => <DistrictCode>res.json());
  }

  saveDistrictCode(code: DistrictCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/districtCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDistrictCode(code: DistrictCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/districtCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeDistrictCode(code: DistrictCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/districtCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // DUN CODES
  // ====================================================================================================

  findDunCodes(): Observable<DunCode[]> {
    console.log('findDunCodes');
    return this._http.get(this.COMMON_API + '/dunCodes')
      .map((res: Response) => <DunCode[]>res.json());
  }

  findDunCodeByCode(code: string): Observable<DunCode> {
    console.log('findDunCodeByCode');
    return this._http.get(this.COMMON_API + '/dunCodes/' + code)
      .map((res: Response) => <DunCode>res.json());
  }

  saveDunCode(code: DunCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/dunCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDunCode(code: DunCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/dunCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeDunCode(code: DunCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/dunCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // PARLIAMENT CODES
  // ====================================================================================================

  findParliamentCodes(): Observable<ParliamentCode[]> {
    console.log('findParliamentCodes');
    return this._http.get(this.COMMON_API + '/parliamentCodes')
      .map((res: Response) => <ParliamentCode[]>res.json());
  }

  findParliamentCodeByCode(code: string): Observable<ParliamentCode> {
    console.log('findParliamentCodeByCode');
    return this._http.get(this.COMMON_API + '/parliamentCodes/' + code)
      .map((res: Response) => <ParliamentCode>res.json());
  }

  saveParliamentCode(code: ParliamentCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/parliamentCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateParliamentCode(code: ParliamentCode): Observable<String> {

    return this._http.put(this.COMMON_API + '/parliamentCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeParliamentCode(code: ParliamentCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/parliamentCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // GENDER CODES
  // ====================================================================================================

  findGenderCodes(): Observable<GenderCode[]> {
    console.log('findGenderCodes');
    return this._http.get(this.COMMON_API + '/genderCodes')
      .map((res: Response) => <GenderCode[]>res.json());
  }

  findGenderCodeByCode(code: string): Observable<GenderCode> {
    console.log('findGenderCodeByCode');
    return this._http.get(this.COMMON_API + '/genderCodes/' + code)
      .map((res: Response) => <GenderCode>res.json());
  }

  saveGenderCode(code: GenderCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/genderCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateGenderCode(code: GenderCode): Observable<String> {

    return this._http.put(this.COMMON_API + '/genderCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeGenderCode(code: GenderCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/genderCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }


  // ====================================================================================================
  // MARITAL CODES
  // ====================================================================================================

  findMaritalCodes(): Observable<MaritalCode[]> {
    console.log('findMaritalCodes');
    return this._http.get(this.COMMON_API + '/maritalCodes')
      .map((res: Response) => <MaritalCode[]>res.json());
  }

  findMaritalCodeByCode(code: string): Observable<MaritalCode> {
    console.log('findMaritalCodeByCode');
    return this._http.get(this.COMMON_API + '/maritalCodes/' + code)
      .map((res: Response) => <MaritalCode>res.json());
  }

  saveMaritalCode(code: MaritalCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/maritalCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateMaritalCode(code: MaritalCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/maritalCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeMaritalCode(code: MaritalCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/maritalCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // VENUE CODES
  // ====================================================================================================

  findVenueCodes(): Observable<VenueCode[]> {
    console.log('findVenueCodes');
    return this._http.get(this.COMMON_API + '/venueCodes')
      .map((res: Response) => <VenueCode[]>res.json());
  }

  findVenueCodeByCode(code: string): Observable<VenueCode> {
    console.log('findVenueCodeByCode');
    return this._http.get(this.COMMON_API + '/venueCodes/' + code)
      .map((res: Response) => <VenueCode>res.json());
  }

  saveVenueCode(code: VenueCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/venueCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateVenueCode(code: VenueCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/venueCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeVenueCode(code: VenueCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/venueCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }


  // ====================================================================================================
  // LANGUAGE CODES
  // ====================================================================================================

  findLanguageCodes(): Observable<LanguageCode[]> {
    console.log('findLanguageCodes');
    return this._http.get(this.COMMON_API + '/languageCodes')
      .map((res: Response) => <LanguageCode[]>res.json());
  }

  findLanguageCodeByCode(code: string): Observable<LanguageCode> {
    console.log('findLanguageCodeByCode');
    return this._http.get(this.COMMON_API + '/languageCodes/' + code)
      .map((res: Response) => <LanguageCode>res.json());
  }

  saveLanguageCode(code: LanguageCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/languageCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateLanguageCode(code: LanguageCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/languageCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeLanguageCode(code: LanguageCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/languageCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // GRADUATE CENTER
  // ====================================================================================================

  findGraduateCenters(): Observable<GraduateCenter[]> {
    console.log('findGraduateCenters');
    return this._http.get(this.COMMON_API + '/graduateCenters')
      .map((res: Response) => <GraduateCenter[]>res.json());
  }

  findGraduateCenterByCode(code: string): Observable<GraduateCenter> {
    console.log('findGraduateCenterByCode');
    return this._http.get(this.COMMON_API + '/graduateCenters/' + code)
      .map((res: Response) => <GraduateCenter>res.json());
  }

  findProgramCodesByGraduateCenter(graduateCenter: GraduateCenter): Observable<ProgramCode[]> {
    console.log('findProgramCodesByGraduateCenter');
    return this._http.get(this.COMMON_API + '/graduateCenters/' + graduateCenter.code + '/programCodes')
      .map((res: Response) => <ProgramCode[]>res.json());
  }

  saveGraduateCenter(code: GraduateCenter): Observable<String> {
    return this._http.post(this.COMMON_API + '/graduateCenters', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeGraduateCenter(code: GraduateCenter): Observable<String> {
    return this._http.delete(this.COMMON_API + '/graduateCenters/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateGraduateCenter(code: GraduateCenter): Observable<String> {
    return this._http.put(this.COMMON_API + '/graduateCenters/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateProgramCode(code: ProgramCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/programCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // FACULTY CODES
  // ====================================================================================================

  findFacultyCodes(): Observable<FacultyCode[]> {
    console.log('findFacultyCodes');
    return this._http.get(this.COMMON_API + '/facultyCodes')
      .map((res: Response) => <FacultyCode[]>res.json());
  }

  findFacultyCodeByCode(code: string): Observable<FacultyCode> {
    console.log('findFacultyCodeByCode');
    return this._http.get(this.COMMON_API + '/facultyCodes/' + code)
      .map((res: Response) => <FacultyCode>res.json());
  }

  saveFacultyCode(code: FacultyCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/facultyCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateFacultyCode(code: FacultyCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/facultyCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeFacultyCode(code: FacultyCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/facultyCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  //====================================================================================================
  // FIELD CODES
  // ====================================================================================================

  findFieldCodes(): Observable<FieldCode[]> {
    console.log('findFieldCodes');
    return this._http.get(this.COMMON_API + '/fieldCodes')
      .map((res: Response) => <FieldCode[]>res.json());
  }

  findFieldCodeByCode(code: string): Observable<FieldCode> {
    console.log('findFieldCodeByCode');
    return this._http.get(this.COMMON_API + '/fieldCodes/' + code)
      .map((res: Response) => <FieldCode>res.json());
  }

  saveFieldCode(code: FieldCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/saveFieldCode', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateFieldCode(code: FieldCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/fieldCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeFieldCode(code: FieldCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/fieldCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // PROGRAM FIELD CODES
  // ====================================================================================================

  findProgramFieldCodes(): Observable<ProgramFieldCode[]> {
    console.log('findProgramFieldCodes');
    return this._http.get(this.COMMON_API + '/programFieldCodes')
      .map((res: Response) => <ProgramFieldCode[]>res.json());
  }

  findProgramFieldCodeByCode(code: string): Observable<ProgramFieldCode> {
    console.log('findProgramFieldCodeByCode');
    return this._http.get(this.COMMON_API + '/programFieldCodes/' + code)
      .map((res: Response) => <ProgramFieldCode>res.json());
  }

  findProgramFieldCodesByProgramLevel(programLevel: ProgramLevel): Observable<ProgramFieldCode> {
    console.log('findProgramFieldCodeByProgramLevel :' + programLevel.code);
    return this._http.get(this.COMMON_API + '/programFieldCodes/programLevel/' + programLevel.code)
      .map((res: Response) => <ProgramFieldCode>res.json());
  }

  saveProgramFieldCode(programFieldCode: ProgramFieldCode): Observable<String> {
    console.log("programFieldCode : "+programFieldCode.facultyCode.code);
    return this._http.post(this.COMMON_API + '/saveProgramFieldCode',  JSON.stringify(programFieldCode))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  
  updateProgramFieldCode(programFieldCode: ProgramFieldCode): Observable<String> {
    console.log("hok ni ko : " + programFieldCode.code);
    return this._http.put(this.COMMON_API + '/updateProgramFieldCode/' + programFieldCode.code, JSON.stringify(programFieldCode))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeProgramFieldCode(code: ProgramFieldCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/programFieldCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  // ====================================================================================================
  // PROGRAM CODES
  // ====================================================================================================

  findProgramCodes(): Observable<ProgramCode[]> {
    console.log('findProgramCodes');
    return this._http.get(this.COMMON_API + '/programCodes')
      .map((res: Response) => <ProgramCode[]>res.json());
  }

  findProgramCodeByCode(code: string): Observable<ProgramCode> {
    console.log('findProgramCodeByCode');
    return this._http.get(this.COMMON_API + '/programCodes/' + code)
      .map((res: Response) => <ProgramCode>res.json());
  }

  findProgramCodesByProgramLevel(programLevel: ProgramLevel): Observable<ProgramCode> {
    console.log('findProgramCodeByProgramLevel :' + programLevel.code);
    return this._http.get(this.COMMON_API + '/programCodes/programLevel/' + programLevel.code)
      .map((res: Response) => <ProgramCode>res.json());
  }

  saveProgramCode(code: ProgramCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/programCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeProgramCode(code: ProgramCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/programCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // STUDY MODE
  // ====================================================================================================

  findStudyModes(): Observable<StudyMode[]> {
    console.log('findStudyModes');
    return this._http.get(this.COMMON_API + '/studyModes')
      .map((res: Response) => <StudyMode[]>res.json());
  }

  findStudyModeByCode(code: string): Observable<StudyMode> {
    console.log('findStudyModeByCode');
    return this._http.get(this.COMMON_API + '/studyModes/' + code)
      .map((res: Response) => <StudyMode>res.json());
  }

  saveStudyMode(code: StudyMode): Observable<String> {
    return this._http.post(this.COMMON_API + '/studyModes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateStudyMode(code: StudyMode): Observable<String> {
    return this._http.put(this.COMMON_API + '/studyModes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeStudyMode(code: StudyMode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/studyModes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // RELIGION CODES
  // ====================================================================================================

  findReligionCodes(): Observable<ReligionCode[]> {
    console.log('findReligionCodes');
    return this._http.get(this.COMMON_API + '/religionCodes')
      .map((res: Response) => <ReligionCode[]>res.json());
  }

  findReligionCodeByCode(code: string): Observable<ReligionCode> {
    console.log('findReligionCodeByCode');
    return this._http.get(this.COMMON_API + '/religionCodes/' + code)
      .map((res: Response) => <ReligionCode>res.json());
  }

  saveReligionCode(code: ReligionCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/religionCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateReligionCode(code: ReligionCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/religionCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeReligionCode(code: ReligionCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/religionCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

// ====================================================================================================
// COUNTRY CODES
// ====================================================================================================

  findCountryCodes(): Observable<CountryCode[]> {
    return this._http.get(this.COMMON_API + '/countryCodes')
      .map((res: Response) => <CountryCode[]>res.json());
  }

  findCountryCodeByCode(code: string): Observable<CountryCode> {
    console.log('findCountryCodeByCode');
    return this._http.get(this.COMMON_API + '/countryCodes/' + code)
      .map((res: Response) => <CountryCode>res.json());
  }

  saveCountryCode(code: CountryCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/countryCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateCountryCode(code: CountryCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/countryCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeCountryCode(code: CountryCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/countryCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

// ====================================================================================================
// SUPERVISOR CODES
// ====================================================================================================

  findSupervisorCodes(): Observable<SupervisorCode[]> {
    console.log('findSupervisorCodes()');
    return this._http.get(this.COMMON_API + '/supervisorCodes')
      .map((res: Response) => <SupervisorCode[]>res.json());
  }

  findSupervisorCodesByFilter(filter: string): Observable<SupervisorCode[]> {
    console.log('findSupervisorCodesByFilter');
    return this._http.get(this.COMMON_API + '/supervisorCodes/byFilter/' + filter)
      .map((res: Response) => <SupervisorCode[]>res.json());
  }

  findSupervisorCodeByCode(code: string): Observable<SupervisorCode> {
    console.log('findSupervisorCodeByCode');
    return this._http.get(this.COMMON_API + '/supervisorCodes/' + code)
      .map((res: Response) => <SupervisorCode>res.json());
  }

  saveSupervisorCode(code: SupervisorCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/supervisorCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeSupervisorCode(code: SupervisorCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/supervisorCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateSupervisorCode(code: SupervisorCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/supervisorCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

// ====================================================================================================
// SUPERVISOR OFFERINGS
// ====================================================================================================

findSupervisorOfferings(): Observable<SupervisorOffering[]> {
  console.log('findSupervisorOfferings()');
  return this._http.get(this.COMMON_API + '/supervisorOfferings')
    .map((res: Response) => <SupervisorOffering[]>res.json());
}

findSupervisorOfferingsByFilter(filter: string): Observable<SupervisorOffering[]> {
  console.log('findSupervisorOfferingsByFilter');
  return this._http.get(this.COMMON_API + '/supervisorOfferings/byFilter/' + filter)
    .map((res: Response) => <SupervisorOffering[]>res.json());
}

findSupervisorOfferingByOffering(offering: string): Observable<SupervisorOffering> {
  console.log('findSupervisorOfferingByOffering');
  return this._http.get(this.COMMON_API + '/supervisorOfferings/' + offering)
    .map((res: Response) => <SupervisorOffering>res.json());
}

findSupervisorOfferingsByProgramLevel(levelCode: ProgramLevel): Observable<SupervisorOffering> {
  console.log('findSupervisorOfferingsByProgramLevel :' + levelCode.code);
  return this._http.get(this.COMMON_API + '/supervisorOfferings/programLevel/' + levelCode.code)
    .map((res: Response) => <SupervisorOffering>res.json());
}

saveSupervisorOfferings(offering: SupervisorOffering): Observable<String> {
  console.log('offering'+offering.supervisorCode.name);
  return this._http.post(this.COMMON_API + '/supervisorOfferings', JSON.stringify(offering))
    .flatMap((res: Response) => Observable.of(res.text()));
}

removeSupervisorOfferings(offering: SupervisorOffering): Observable<String> {
  console.log("SV_Code_Service:{}",offering.id);
  return this._http.delete(this.COMMON_API + '/supervisorOfferings/' + offering.id)
    .flatMap((res: Response) => Observable.of(res.text()));
}

updateSupervisorOfferings(offering: SupervisorOffering): Observable<String> {
  return this._http.put(this.COMMON_API + '/supervisorOfferings/' + offering.id, JSON.stringify(offering))
    .flatMap((res: Response) => Observable.of(res.text()));
}

// ====================================================================================================
// STATE CODES
// ====================================================================================================

  findStateCodes(): Observable<StateCode[]> {
    console.log('findStateCodes()');
    return this._http.get(this.COMMON_API + '/stateCodes')
      .map((res: Response) => <StateCode[]>res.json());
  }

  findStateCodeByCode(code: string): Observable<StateCode> {
    console.log('findStateCodeByCode');
    return this._http.get(this.COMMON_API + '/stateCodes/' + code)
      .map((res: Response) => <StateCode>res.json());
  }

  saveStateCode(code: StateCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/stateCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateStateCode(code: StateCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/stateCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeStateCode(code: StateCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/stateCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

// ====================================================================================================
// RACE CODES
// ====================================================================================================

  findRaceCodes(): Observable<RaceCode[]> {
    console.log('findRaceCodes');
    return this._http.get(this.COMMON_API + '/raceCodes')
      .map((res: Response) => <RaceCode[]>res.json());
  }

  findRaceCodeByCode(code: string): Observable<RaceCode> {
    console.log('findRaceCodeByCode');
    return this._http.get(this.COMMON_API + '/raceCodes/' + code)
      .map((res: Response) => <RaceCode>res.json());
  }

  saveRaceCode(code: RaceCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/raceCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateRaceCode(code: RaceCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/raceCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeRaceCode(code: RaceCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/raceCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

// ====================================================================================================
// ETHNICITY CODES
// ====================================================================================================

  findEthnicityCodes(): Observable<EthnicityCode[]> {
    console.log('findEthnicityCodes');
    return this._http.get(this.COMMON_API + '/ethnicityCodes')
      .map((res: Response) => <EthnicityCode[]>res.json());
  }

  findEthnicityCodeByCode(code: string): Observable<EthnicityCode> {
    console.log('findEthnicityCodeByCode');
    return this._http.get(this.COMMON_API + '/ethnicityCodes/' + code)
      .map((res: Response) => <EthnicityCode>res.json());
  }

  saveEthnicityCode(code: EthnicityCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/ethnicityCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateEthnicityCode(code: EthnicityCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/ethnicityCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeEthnicityCode(code: EthnicityCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/ethnicityCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // NATIONALITY CODES
  // ====================================================================================================

  findNationalityCodes(): Observable<NationalityCode[]> {
    console.log('findNationalityCodes');
    return this._http.get(this.COMMON_API + '/nationalityCodes')
      .map((res: Response) => <NationalityCode[]>res.json());
  }

  findNationalityCodeByCode(code: string): Observable<NationalityCode> {
    console.log('findNationalityCodeByCode');
    return this._http.get(this.COMMON_API + '/nationalityCodes/' + code)
      .map((res: Response) => <NationalityCode>res.json());
  }

  saveNationalityCode(code: NationalityCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/nationalityCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateNationalityCode(code: NationalityCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/nationalityCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeNationalityCode(code: NationalityCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/nationalityCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // RESIDENCY CODES
  // ====================================================================================================

  findResidencyCodes(): Observable<ResidencyCode[]> {
    console.log('findResidencyCodes');
    return this._http.get(this.COMMON_API + '/residencyCodes')
      .map((res: Response) => <ResidencyCode[]>res.json());
  }

  findResidencyCodeByCode(code: string): Observable<ResidencyCode> {
    console.log('findResidencyCodeByCode');
    return this._http.get(this.COMMON_API + '/residencyCodes/' + code)
      .map((res: Response) => <ResidencyCode>res.json());
  }

  saveResidencyCode(code: ResidencyCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/residencyCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateResidencyCode(code: ResidencyCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/residencyCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeResidencyCode(code: ResidencyCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/residencyCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // DISABILITY CODES
  // ====================================================================================================

  findDisabilityCodes(): Observable<DisabilityCode[]> {
    console.log('findDisabilityCodes');
    return this._http.get(this.COMMON_API + '/disabilityCodes')
      .map((res: Response) => <DisabilityCode[]>res.json());
  }

  findDisabilityCodeByCode(code: string): Observable<DisabilityCode> {
    console.log('findDisabilityCodeByCode');
    return this._http.get(this.COMMON_API + '/disabilityCodes/' + code)
      .map((res: Response) => <DisabilityCode>res.json());
  }

  saveDisabilityCode(code: DisabilityCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/disabilityCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateDisabilityCode(code: DisabilityCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/disabilityCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeDisabilityCode(code: DisabilityCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/disabilityCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // SCHOOL CODES
  // ====================================================================================================

  findSchoolCodes(): Observable<SchoolCode[]> {
    console.log('findSchoolCodes');
    return this._http.get(this.COMMON_API + '/schoolCodes')
      .map((res: Response) => <SchoolCode[]>res.json());
  }

  findSchoolCodeByCode(code: string): Observable<SchoolCode> {
    console.log('findSchoolCodeByCode');
    return this._http.get(this.COMMON_API + '/schoolCodes/' + code)
      .map((res: Response) => <SchoolCode>res.json());
  }

  saveSchoolCode(code: SchoolCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/schoolCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateSchoolCode(code: SchoolCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/schoolCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeSchoolCode(code: SchoolCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/schoolCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // STUDY CENTER CODES
  // ====================================================================================================

  findStudyCenterCodes(): Observable<StudyCenterCode[]> {
    console.log('findStudyCenterCodes');
    return this._http.get(this.COMMON_API + '/studyCenterCodes')
      .map((res: Response) => <StudyCenterCode[]>res.json());
  }

  findStudyCenterCodeByCode(code: string): Observable<StudyCenterCode> {
    console.log('findStudyCenterCodeByCode');
    return this._http.get(this.COMMON_API + '/studyCenterCodes/' + code)
      .map((res: Response) => <StudyCenterCode>res.json());
  }

  saveStudyCenterCode(code: StudyCenterCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/studyCenterCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateStudyCenterCode(code: StudyCenterCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/studyCenterCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeStudyCenterCode(code: StudyCenterCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/studyCenterCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // SUBJECT CODES
  // ====================================================================================================

  findSubjectCodes(): Observable<SubjectCode[]> {
    console.log('findSubjectCodes');
    return this._http.get(this.COMMON_API + '/subjectCodes')
      .map((res: Response) => <SubjectCode[]>res.json());
  }

  findSubjectCodeByCode(code: string): Observable<SubjectCode> {
    console.log('findSubjectCodeByCode');
    return this._http.get(this.COMMON_API + '/subjectCodes/' + code)
      .map((res: Response) => <SubjectCode>res.json());
  }

  saveSubjectCode(code: SubjectCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/subjectCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateSubjectCode(code: SubjectCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/subjectCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeSubjectCode(code: SubjectCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/subjectCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // GRADE CODES
  // ====================================================================================================

  findGradeCodes(): Observable<GradeCode[]> {
    console.log('findGradeCodes');
    return this._http.get(this.COMMON_API + '/gradeCodes')
      .map((res: Response) => <GradeCode[]>res.json());
  }

  findGradeCodeByCode(code: string): Observable<GradeCode> {
    console.log('findGradeCodeByCode');
    return this._http.get(this.COMMON_API + '/gradeCodes/' + code)
      .map((res: Response) => <GradeCode>res.json());
  }

  saveGradeCode(code: GradeCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/gradeCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateGradeCode(code: GradeCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/gradeCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeGradeCode(code: GradeCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/gradeCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // EMPLOYMENT TYPE CODES
  // ====================================================================================================

  findEmploymentTypeCodes(): Observable<EmploymentTypeCode[]> {
    console.log('findEmploymentTypeCodes');
    return this._http.get(this.COMMON_API + '/employmentTypeCodes')
      .map((res: Response) => <EmploymentTypeCode[]>res.json());
  }

  findEmploymentTypeCodeByCode(code: string): Observable<EmploymentTypeCode> {
    console.log('findEmploymentTypeCodeByCode');
    return this._http.get(this.COMMON_API + '/employmentTypeCodes/' + code)
      .map((res: Response) => <EmploymentTypeCode>res.json());
  }

  saveEmploymentTypeCode(code: EmploymentTypeCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/employmentTypeCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateEmploymentTypeCode(code: EmploymentTypeCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/employmentTypeCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeEmploymentTypeCode(code: EmploymentTypeCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/employmentTypeCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  // ====================================================================================================
  // EMPLOYMENT SECTOR CODES
  // ====================================================================================================

  findEmploymentSectorCodes(): Observable<EmploymentSectorCode[]> {
    console.log('findEmploymentSectorCodes');
    return this._http.get(this.COMMON_API + '/employmentSectorCodes')
      .map((res: Response) => <EmploymentSectorCode[]>res.json());
  }

  findEmploymentSectorCodeByCode(code: string): Observable<EmploymentSectorCode> {
    console.log('findEmploymentSectorCodeByCode');
    return this._http.get(this.COMMON_API + '/employmentSectorCodes/' + code)
      .map((res: Response) => <EmploymentSectorCode>res.json());
  }

  saveEmploymentSectorCode(code: EmploymentSectorCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/employmentSectorCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateEmploymentSectorCode(code: EmploymentSectorCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/employmentSectorCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeEmploymentSectorCode(code: EmploymentSectorCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/employmentSectorCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // GUARDIAN TYPE CODES
  // ====================================================================================================

  findGuardianTypeCodes(): Observable<GuardianTypeCode[]> {
    console.log('findGuardianTypeCodes');
    return this._http.get(this.COMMON_API + '/guardianTypeCodes')
      .map((res: Response) => <GuardianTypeCode[]>res.json());
  }

  findGuardianTypeCodeByCode(code: string): Observable<GuardianTypeCode> {
    console.log('findGuardianTypeCodeByCode');
    return this._http.get(this.COMMON_API + '/guardianTypeCodes/' + code)
      .map((res: Response) => <GuardianTypeCode>res.json());
  }

  saveGuardianTypeCode(code: GuardianTypeCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/guardianTypeCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateGuardianTypeCode(code: GuardianTypeCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/guardianTypeCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeGuardianTypeCode(code: GuardianTypeCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/guardianTypeCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  //  SPM RESULT CODES
  // ====================================================================================================

  findSpmResultCodes(): Observable<SpmResultCode[]> {
    console.log('findSpmResultCodes');
    return this._http.get(this.COMMON_API + '/spmResultCodes')
      .map((res: Response) => <SpmResultCode[]>res.json());
  }

  findSpmResultCodeByCode(code: string): Observable<SpmResultCode> {
    console.log('findSpmResultCodeByCode');
    return this._http.get(this.COMMON_API + '/spmResultCodes/' + code)
      .map((res: Response) => <SpmResultCode>res.json());
  }

  saveSpmResultCode(code: GuardianTypeCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/spmResultCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updatSpmResultCode(code: GuardianTypeCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/spmResultCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeSpmResultCode(code: GuardianTypeCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/spmResultCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  //  SPM SUBJECT CODES
  // ====================================================================================================

  findSpmSubjectCodes(): Observable<SpmSubjectCode[]> {
    console.log('findSpmSubjectCodes');
    return this._http.get(this.COMMON_API + '/spmSubjectCodes')
      .map((res: Response) => <SpmSubjectCode[]>res.json());
  }

  findSpmSubjectCodeByCode(code: string): Observable<SpmSubjectCode> {
    console.log('findSpmSubjectCodeByCode');
    return this._http.get(this.COMMON_API + '/spmSubjectCodes/' + code)
      .map((res: Response) => <SpmSubjectCode>res.json());
  }

  saveSpmSubjectCode(code: SpmSubjectCode): Observable<String> {
    return this._http.post(this.COMMON_API + '/spmSubjectCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateSpmSubjectCode(code: SpmSubjectCode): Observable<String> {
    return this._http.put(this.COMMON_API + '/spmSubjectCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeSpmSubjectCode(code: SpmSubjectCode): Observable<String> {
    return this._http.delete(this.COMMON_API + '/spmSubjectCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
}
