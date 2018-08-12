import { SpmSubjectCodesComponent } from './spm-subject-codes/component/spm-subject-code';
import { SpmSubjectCodeEditorDialog } from './spm-subject-codes/dialog/spm-subject-code-editor.dialog';
import { SpmSubjectCodeListPage } from './spm-subject-codes/spm-subject-code-list-page';
import { SpmSubjectCodeListState } from './spm-subject-codes/spm-subject-code-list.reducer';
import { SpmResultCodesComponent } from './spm-result-codes/component/spm-result-code';
import { SpmResultCodeEditorDialog } from './spm-result-codes/dialog/spm-result-code-editor.dialog';
import { SpmResultCodeListState, spmResultCodeListReducer } from './../../common/spm-result-codes/spm-result-code-list.reducer';
import { UMKCEEStaffEditorDialog } from './umkcee-staffs/dialog/umkcee-staff-editor.dialog';
import { StaffTypeSelectComponent } from './umkcee-staffs/component/staff-type-select.component';
import { UMKCEEStaffListComponent } from './umkcee-staffs/component/umkcee-staff.component';
import { UMKCEEStaffListPage } from './umkcee-staffs/umkcee-staff-list.page';
import { Staff } from './../identity/staff.interface';
import { UmkceeStaffListState, umkceeStaffListReducer } from './umkcee-staffs/umkcee-staff-list.reducer';
import { GuardianTypeCodesComponent } from './guardian-type-codes/component/guardian-type-code';
import { GuardianTypeCodeEditorDialog } from './guardian-type-codes/dialog/guardian-type-code-editor.dialog';
import { GuardianTypeCodeListPage } from './guardian-type-codes/guardian-type-code-list.page';
import { GuardianTypeCodeListState} from './../../common/guardian-type-codes/guardian-type-code-list.reducer';
import { EmploymentSectorCodesComponent } from './employment-sector-codes/component/employment-sector-code';
import { EmploymentSectorCodeEditorDialog } from './employment-sector-codes/dialog/employment-sector-code-editor.dialog';
import { EmploymentSectorCodeListPage } from './employment-sector-codes/employment-sector-code-list.page';
import { EmploymentSectorCode } from './../../shared/model/common/employment-sector-code.interface';
import { EmploymentSectorCodeListState, employmentSectorCodeListReducer } from './employment-sector-codes/employment-sector-code-list.reducer';
import { EmploymentTypeCodesComponent } from './employment-type-codes/component/employment-type-code';
import { EmploymentTypeCodeEditorDialog } from './employment-type-codes/dialog/employment-type-code-editor.dialog';
import { EmploymentTypeCodeListPage } from './employment-type-codes/employment-type-code-list.page';
import { EmploymentTypeCode } from './../../shared/model/common/employment-type-code.interface';
import { EmploymentTypeCodeListState, employmentTypeCodeListReducer } from './../../common/employment-type-codes/employment-type-code-list.reducer';
import { GradeCodesComponent } from './grade-codes/component/grade-code';
import { GenderCodesComponent } from './gender-codes/component/gender-code';
import { DistrictCodesComponent } from './district-codes/component/district-code';
import { MaritalCodesComponent } from './marital-codes/component/marital-code';
import { ParliamentCodesComponent } from './parliament-codes/component/parliament-code';
import { StudyModesComponent } from './study-modes/component/study-mode';
import { SubjectCodesComponent } from './subject-codes/component/subject-code';
import { SupervisorCodesComponent } from './supervisor-codes/component/supervisor-code';
import { SupervisorOfferingsComponent } from './supervisor-offerings/component/supervisor-offering';
import { VenueCodesComponent } from './venue-codes/component/venue-code';
import { StudyCenterCodesComponent } from './study-center-codes/component/study-center-code';
import { StateCodesComponent } from './state-codes/component/state-code';
import { SchoolCodesComponent } from './school-codes/component/school-code';
import { ResidencyCodesComponent } from './residency-codes/component/residency-code';
import { ReligionCodesComponent } from './religion-codes/component/religion-code';
import { ProgramCodesComponent } from './program-codes/component/program-code';
import { ProgramFieldCodesComponent } from './program-field-codes/component/program-field-code';
import { NationalityCodesComponent } from './nationality-codes/component/nationality-code';
import { LanguageCodesComponent } from './language-codes/component/language-code';
import { FacultyCodesComponent } from './faculty-codes/component/faculty-code';
import { FieldCodesComponent } from './field-codes/component/field-code';
import { RaceCodesComponent } from './race-codes/component/race-code';
import { EthnicityCodesComponent } from './ethnicity-codes/component/ethnicity-code';
import { DunCodesComponent } from './dun-codes/component/dun-code';
import { DisabilityCodesComponent } from './disability-codes/component/disability-code';

import { VenueCodeListPage } from './venue-codes/venue-code-list.page';
import { VenueCodeEditorDialog } from './venue-codes/dialog/venue-code-editor.dialog';
import { VenueCode } from './../../shared/model/common/venue-code.interface';
import {GradeCodeListPage} from './grade-codes/grade-code-list-page';
import {SubjectCodeEditorDialog} from './subject-codes/dialog/subject-code-editor.dialog';
import {SubjectCodeListPage} from './subject-codes/subject-code-list-page';
import {LanguageCode} from '../../shared/model/common/language-code.interface';
import {ResidencyCodeEditorDialog} from './residency-codes/dialog/residency-code-editor.dialog';
import {ResidencyCodeListPage} from './residency-codes/residency-code-list-page';
import {StateCodeEditorDialog} from './state-codes/dialog/state-code-editor.dialog';
import {DistrictCodeEditorDialog} from './district-codes/dialog/district-code-editor.dialog';
import {DistrictCodeListPage} from './district-codes/district-code-list-page';
import {DunCodeEditorDialog} from './dun-codes/dialog/dun-code-editor.dialog';
import {DunCodeListPage} from './dun-codes/dun-code-list-page';
import {GradeCodeEditorDialog} from './grade-codes/dialog/grade-code-editor.dialog';
import {ParliamentCodeEditorDialog} from './parliament-codes/dialog/parliament-code-editor.dialog';
import {ParliamentCodeListPage} from './parliament-codes/parliament-code-list-page';
import {StudyCenterCodeEditorDialog} from './study-center-codes/dialog/study-center-code-editor.dialog';
import {StudyCenterCode} from '../../shared/model/common/study-center-code.interface';
import {StudyCenterCodeListPage} from './study-center-codes/study-center-code-list.page';
import {SchoolCodeEditorDialog} from './school-codes/dialog/school-code-editor.dialog';
import {SchoolCodeListPage} from './school-codes/school-code-list-page';
import {DisabilityCode} from '../../shared/model/common/disability-code.interface';
import {DisabilityCodeEditorDialog} from './disability-codes/dialog/disability-code-editor.dialog';
import {ProgramLevelSubModule} from '../policy/program-levels/index';
import {SupervisorCodeEditorDialog} from './supervisor-codes/dialog/supervisor-code-editor.dialog';
import {StudyMode} from '../../shared/model/common/study-mode.interface';
import {StudyModeListPage} from './study-modes/study-mode-list.page';
import {StudyModeEditorDialog} from './study-modes/dialog/study-mode-editor.dialog';
import {FacultyCodeEditorDialog} from './faculty-codes/dialog/faculty-code-editor.dialog';
import {FacultyCodeListPage} from './faculty-codes/faculty-code-list.page';
import {FieldCodeEditorDialog} from './field-codes/dialog/field-code-editor.dialog';
import {FieldCodeListPage} from './field-codes/field-code-list.page';
import {NationalityCodeEditorDialog} from './nationality-codes/dialog/nationality-code-editor.dialog';
import {NationalityCodeListPage} from './nationality-codes/nationality-code-list.page';
import {EthnicityCodeEditorDialog} from './ethnicity-codes/dialog/ethnicity-code-editor.dialog';
import {EthnicityCodeListPage} from './ethnicity-codes/ethnicity-code-list.page';
import {GenderCodeEditorDialog} from './gender-codes/dialog/gender-code-editor.dialog';
import {GenderCodeListPage} from './gender-codes/gender-code-list.page';
import {RaceCodeEditorDialog} from './race-codes/dialog/race-code-editor.dialog';
import {RaceCodeListPage} from './race-codes/race-code-list.page';
import {SupervisorCodeListPage} from './supervisor-codes/supervisor-code-list.page';
import {ReligionCodeEditorDialog} from './religion-codes/dialog/religion-code-editor.dialog';
import {ProgramCodeEditorDialog} from './program-codes/dialog/program-code-editor.dialog';
import {ProgramCodeListPage} from './program-codes/program-code-list.page';
import {ProgramFieldCodeListPage} from './program-field-codes/program-field-code-list.page';
import {StateCodeListPage} from './state-codes/state-code-list.page';
import {CountryCodeEditorDialog} from './country-codes/dialog/country-code-editor.dialog';
import {CountryCodeListPage} from './country-codes/country-code-list.page';
import {MaritalCodeListPage} from './marital-codes/marital-code-list-page';
import {SetupPage} from './setup.page';
import {DisabilityCodeListPage} from './disability-codes/disability-code-list-page';
import {CountryCodesComponent} from './country-codes/component/country-code';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {NgModule, ModuleWithProviders} from '@angular/core';
import {CovalentCoreModule} from '@covalent/core';
import {bankCodeListReducer, BankCodeListState} from './bank-codes/bank-code-list.reducer';
import {maritalCodeListReducer, MaritalCodeListState} from './marital-codes/marital-code-list.reducer';
import {religionCodeListReducer, ReligionCodeListState} from './religion-codes/religion-code-list.reducer';
import {SetupEffects} from './setup.effect';
import {EffectsModule} from '@ngrx/effects';
import {BankCodeListPage} from './bank-codes/bank-code-list.page';
import {SetupActions} from './setup.action';
import {graduateCenterListReducer, GraduateCenterListState} from './graduate-centers/graduate-center-list.reducer';
import {GraduateCenterListPage} from './graduate-centers/graduate-center-list.page';
import {GraduateCenterEditorDialog} from './graduate-centers/dialog/graduate-center-editor.dialog';
import {BankCodeCreatorDialog} from './bank-codes/dialog/bank-code-creator.dialog';
import {countryCodeListReducer, CountryCodeListState} from './country-codes/country-code-list.reducer';
import {stateCodeListReducer, StateCodeListState} from './state-codes/state-code-list.reducer';
import {programCodeListReducer, ProgramCodeListState} from './program-codes/program-code-list.reducer';
import {programFieldCodeListReducer, ProgramFieldCodeListState} from './program-field-codes/program-field-code-list.reducer';
import {supervisorCodeListReducer, SupervisorCodeListState} from './supervisor-codes/supervisor-code-list.reducer';
import {raceCodeListReducer, RaceCodeListState} from './race-codes/race-code-list.reducer';
import {genderCodeListReducer, GenderCodeListState} from './gender-codes/gender-code-list.reducer';
import {ethnicityCodeListReducer, EthnicityCodeListState} from './ethnicity-codes/ethnicity-code-list.reducer';
import {nationalityCodeListReducer, NationalityCodeListState} from './nationality-codes/nationality-code-list.reducer';
import {facultyCodeListReducer, FacultyCodeListState} from './faculty-codes/faculty-code-list.reducer';
import {studyModeListReducer, StudyModeListState} from './study-modes/study-mode-list.reducer';
import {titleReducer, TitleState} from './title.reducer';
import {MaritalCodeEditorDialog} from './marital-codes/dialog/marital-code-editor.dialog';
import {disabilityCodeListReducer, DisabilityCodeListState} from './disability-codes/disability-code-list.reducer';
import {schoolCodeListReducer, SchoolCodeListState} from './school-codes/school-code-list.reducer';
import {studyCenterCodeListReducer, StudyCenterCodeListState} from './study-center-codes/study-center-code-list.reducer';
import {districtCodeListReducer, DistrictCodeListState} from './district-codes/district-code-list.reducer';
import {dunCodeListReducer, DunCodeListState} from './dun-codes/dun-code-list.reducer';
import {parliamentCodeListReducer, ParliamentCodeListState} from './parliament-codes/parliament-code-list.reducer';
import {residencyCodeListReducer, ResidencyCodeListState} from './residency-codes/residency-code-list.reducer';
import {LanguageCodeListPage} from './language-codes/language-code-list-page';
import {LanguageCodeEditorDialog} from './language-codes/dialog/language-code-editor.dialog';
import {languageCodeListReducer, LanguageCodeListState} from './language-codes/language-code-list.reducer';
import {subjectCodeListReducer, SubjectCodeListState} from './subject-codes/subject-code-list.reducer';
import {gradeCodeListReducer, GradeCodeListState} from './grade-codes/grade-code-list.reducer';
import {ResidencyCode} from '../../shared/model/common/residency-code.interface';
import {ParliamentCode} from '../../shared/model/common/parliament-code.interface';
import {DunCode} from '../../shared/model/common/dun-code.interface';
import {SubjectCode} from '../../shared/model/common/subject-code.interface';
import {DistrictCode} from '../../shared/model/common/district-code.interface';
import {GradeCode} from '../../shared/model/common/grade-code.interface';
import {SchoolCode} from '../../shared/model/common/school-code.interface';
import {FacultyCode} from '../../shared/model/common/faculty-code.interface';
import {NationalityCode} from '../../shared/model/common/nationality-code.interface';
import {EthnicityCode} from '../../shared/model/common/ethnicity-code.interface';
import {MaritalCode} from '../../shared/model/common/marital-code.interface';
import {GenderCode} from '../../shared/model/common/gender-code.interface';
import {RaceCode} from '../../shared/model/common/race-code.interface';
import {CountryCode} from '../../shared/model/common/country-code.interface';
import {BankCode} from '../../shared/model/common/bank-code.interface';
import {GraduateCenter} from '../../shared/model/common/graduate-center.interface';
import {ReligionCode} from '../../shared/model/common/religion-code.interface';
import {SupervisorCode} from '../../shared/model/common/supervisor-code.interface';
import {ProgramCode} from '../../shared/model/common/program-code.interface';
import {StateCode} from '../../shared/model/common/state-code.interface';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {ReligionCodeListPage} from './religion-codes/religion-code-list.page';
import {CommonModule} from '../../common/index';
import {IntakeSessionSubModule} from '../policy/intake-sessions/index';
import {venueCodeListReducer, VenueCodeListState} from './venue-codes/venue-code-list.reducer';
import { SupervisorOfferingListState, supervisorOfferingListReducer } from './supervisor-offerings/supervisor-offering-list.reducer';
import { SupervisorOffering } from '../../shared/model/common/supervisor-offering.interface';
import { SupervisorOfferingListPage } from './supervisor-offerings/supervisor-offering-list.page';
import { SupervisorOfferingEditorDialog } from './supervisor-offerings/dialog/supervisor-offering-editor.dialog';
//import { FieldCodeListState, fieldCodeListReducer } from "../../common/field-codes/field-code-list.reducer";
import { FieldCode } from "../../shared/model/common/field-code.interface";
import { FieldCodeListState, fieldCodeListReducer } from './field-codes/field-code-list.reducer';
import { ProgramFieldCode } from "../../shared/model/common/program-field-code.interface";
import { ProgramFieldCodeEditorDialog } from "./program-field-codes/dialog/program-field-code-editor.dialog";
import { GuardianTypeCode } from '../../shared/model/common/guardian-type-code.interface';
import { guardianTypeCodeListReducer } from './guardian-type-codes/guardian-type-code-list.reducer';
import { SpmResultCode } from '../../shared/model/common/spm-result-code.interface';
import { SpmResultCodeListPage } from './spm-result-codes/spm-result-code-list.page';
import { ActorTypeSelectComponent } from './umkcee-staffs/component/actor-type-select.component';
import { SpmSubjectCode } from '../../shared/model/common/spm-subject-code.interface';
import { spmSubjectCodeListReducer } from '../../common/spm-subject-codes/spm-subject-code-list.reducer';
import { SpmSubjectCodeSelectComponent } from '../../common/spm-subject-codes/component/spm-subject-code-select.component';


export interface SetupModuleState {
  title: TitleState;
  maritalCodes: MaritalCodeListState;
  venueCodes: VenueCodeListState;
  bankCodes: BankCodeListState;
  graduateCenters: GraduateCenterListState;
  religionCodes: ReligionCodeListState;
  countryCodes: CountryCodeListState;
  stateCodes: StateCodeListState;
  programCodes: ProgramCodeListState;
  programFieldCodes: ProgramFieldCodeListState;
  supervisorCodes: SupervisorCodeListState;
  supervisorOfferings: SupervisorOfferingListState;
  raceCodes: RaceCodeListState;
  genderCodes: GenderCodeListState;
  ethnicityCodes: EthnicityCodeListState;
  nationalityCodes: NationalityCodeListState;
  facultyCodes: FacultyCodeListState;
  fieldCodes: FieldCodeListState;
  studyModes: StudyModeListState;
  disabilityCodes: DisabilityCodeListState;
  schoolCodes: SchoolCodeListState;
  studyCenterCodes: StudyCenterCodeListState;
  districtCodes: DistrictCodeListState;
  dunCodes: DunCodeListState;
  parliamentCodes: ParliamentCodeListState;
  residencyCodes: ResidencyCodeListState;
  languageCodes: LanguageCodeListState;
  subjectCodes: SubjectCodeListState;
  gradeCodes: GradeCodeListState;
  employmentTypeCodes: EmploymentTypeCodeListState;
  employmentSectorCodes: EmploymentSectorCodeListState;
  guardianTypeCodes: GuardianTypeCodeListState;
  spmResultCodes: SpmResultCodeListState;
  staffs: UmkceeStaffListState;
  spmSubjectCodes: SpmSubjectCodeListState;

};

export const INITIAL_SETUP_STATE: SetupModuleState =
  <SetupModuleState>{
    title: 'Setup Codes',
    bankCodes: <BankCode[]>[],
    venueCodes: <VenueCode[]>[],
    graduateCenters: <GraduateCenter[]>[],
    religionCodes: <ReligionCode[]>[],
    supervisorCodes: <SupervisorCode[]>[],
    supervisorOfferings: <SupervisorOffering[]>[],
    programCodes: <ProgramCode[]>[],
    programFieldCodes: <ProgramFieldCode[]>[],
    stateCodes: <StateCode[]>[],
    countryCodes: <CountryCode[]>[],
    raceCodes: <RaceCode[]>[],
    genderCodes: <GenderCode[]>[],
    maritalCodes: <MaritalCode[]>[],
    ethnicityCodes: <EthnicityCode[]>[],
    nationalityCodes: <NationalityCode[]>[],
    facultyCodes: <FacultyCode[]>[],
    fieldCodes: <FieldCode[]>[],
    studyModes: <StudyMode[]>[],
    disabilityCodes: <DisabilityCode[]>[],
    schoolCodes: <SchoolCode[]>[],
    studyCenterCodes: <StudyCenterCode[]>[],
    districtCodes: <DistrictCode[]>[],
    dunCodes: <DunCode[]>[],
    parliamentCodes: <ParliamentCode[]>[],
    residencyCodes: <ResidencyCode[]>[],
    languageCodes: <LanguageCode[]>[],
    subjectCodes: <SubjectCode[]>[],
    gradeCodes: <GradeCode[]>[],
    employmentTypeCodes: <EmploymentTypeCode[]>[],
    employmentSectorCodes: <EmploymentSectorCode[]>[],
    guardianTypeCodes: <GuardianTypeCode[]>[],
    spmResultCodes: <SpmResultCode[]>[],
    staffs: <Staff[]>[],
    spmSubjectCodes: <SpmSubjectCode[]>[],

  };

export const setupModuleReducers = {
  title: titleReducer,
  bankCodes: bankCodeListReducer,
  graduateCenters: graduateCenterListReducer,
  religionCodes: religionCodeListReducer,
  maritalCodes: maritalCodeListReducer,
  countryCodes: countryCodeListReducer,
  stateCodes: stateCodeListReducer,
  programCodes: programCodeListReducer,
  programFieldCodes: programFieldCodeListReducer,
  supervisorCodes: supervisorCodeListReducer,
  supervisorOfferings: supervisorOfferingListReducer,
  raceCodes: raceCodeListReducer,
  venueCodes: venueCodeListReducer,
  genderCodes: genderCodeListReducer,
  ethnicityCodes: ethnicityCodeListReducer,
  nationalityCodes: nationalityCodeListReducer,
  facultyCodes: facultyCodeListReducer,
  fieldCodes: fieldCodeListReducer,
  studyModes: studyModeListReducer,
  disabilityCodes: disabilityCodeListReducer,
  schoolCodes: schoolCodeListReducer,
  studyCenterCodes: studyCenterCodeListReducer,
  districtCodes: districtCodeListReducer,
  dunCodes: dunCodeListReducer,
  parliamentCodes: parliamentCodeListReducer,
  residencyCodes: residencyCodeListReducer,
  languageCodes: languageCodeListReducer,
  subjectCodes: subjectCodeListReducer,
  gradeCodes: gradeCodeListReducer,
  employmentTypeCodes: employmentTypeCodeListReducer,
  employmentSectorCodes: employmentSectorCodeListReducer,
  guardianTypeCodes: guardianTypeCodeListReducer,
  spmResultCodes: spmResultCodeListReducer,
  staffs: umkceeStaffListReducer,
  spmSubjectCodes: spmSubjectCodeListReducer,

};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    ProgramLevelSubModule.forRoot(),
    CovalentCoreModule.forRoot(),
    CommonModule.forRoot(),
    IntakeSessionSubModule.forRoot(),
    EffectsModule.run(SetupEffects),
  ],
  declarations: [
    // page
    SetupPage,
    BankCodeListPage,
    GraduateCenterListPage,
    ReligionCodeListPage,
    MaritalCodeListPage,
    VenueCodeListPage,
    LanguageCodeListPage,
    CountryCodeListPage,
    StateCodeListPage,
    ProgramCodeListPage,
    ProgramFieldCodeListPage,
    SupervisorCodeListPage,
    SupervisorOfferingListPage,
    RaceCodeListPage,
    GenderCodeListPage,
    EthnicityCodeListPage,
    NationalityCodeListPage,
    FacultyCodeListPage,
    FieldCodeListPage,
    StudyModeListPage,
    DisabilityCodeListPage,
    SchoolCodeListPage,
    StudyCenterCodeListPage,
    DistrictCodeListPage,
    DunCodeListPage,
    ParliamentCodeListPage,
    ResidencyCodeListPage,
    SubjectCodeListPage,
    GradeCodeListPage,
    EmploymentTypeCodeListPage,
    EmploymentSectorCodeListPage,
    GuardianTypeCodeListPage,
    SpmResultCodeListPage,
    UMKCEEStaffListPage,
    SpmSubjectCodeListPage,

    // dialog
    MaritalCodeEditorDialog,
    VenueCodeEditorDialog,
    LanguageCodeEditorDialog,
    BankCodeCreatorDialog,
    GraduateCenterEditorDialog,
    CountryCodeEditorDialog,
    StateCodeEditorDialog,
    ProgramCodeEditorDialog,
    ProgramFieldCodeEditorDialog,
    ReligionCodeEditorDialog,
    SupervisorCodeEditorDialog,
    SupervisorOfferingEditorDialog,
    RaceCodeEditorDialog,
    GenderCodeEditorDialog,
    EthnicityCodeEditorDialog,
    NationalityCodeEditorDialog,
    FacultyCodeEditorDialog,
    FieldCodeEditorDialog,
    StudyModeEditorDialog,
    DisabilityCodeEditorDialog,
    SchoolCodeEditorDialog,
    StudyCenterCodeEditorDialog,
    DistrictCodeEditorDialog,
    DunCodeEditorDialog,
    ParliamentCodeEditorDialog,
    ResidencyCodeEditorDialog,
    SubjectCodeEditorDialog,
    GradeCodeEditorDialog,
    EmploymentTypeCodeEditorDialog,
    EmploymentSectorCodeEditorDialog,
    GuardianTypeCodeEditorDialog,
    SpmResultCodeEditorDialog,
    UMKCEEStaffEditorDialog,
    SpmSubjectCodeEditorDialog,

    //component
    CountryCodesComponent,
    DisabilityCodesComponent,
    DistrictCodesComponent,
    DunCodesComponent,
    EthnicityCodesComponent,
    FacultyCodesComponent,
    FieldCodesComponent,
    GenderCodesComponent,
    GradeCodesComponent,
    LanguageCodesComponent,
    MaritalCodesComponent,
    NationalityCodesComponent,
    ParliamentCodesComponent,
    ProgramCodesComponent,
    ProgramFieldCodesComponent,
    RaceCodesComponent,
    ReligionCodesComponent,
    ResidencyCodesComponent,
    SchoolCodesComponent,
    StateCodesComponent,
    StudyCenterCodesComponent,
    StudyModesComponent,
    SubjectCodesComponent,
    SupervisorCodesComponent,
    SupervisorOfferingsComponent,
    VenueCodesComponent,
    EmploymentTypeCodesComponent,
    EmploymentSectorCodesComponent,
    GuardianTypeCodesComponent,
    SpmResultCodesComponent,
    UMKCEEStaffListComponent,
    StaffTypeSelectComponent,
    ActorTypeSelectComponent,
    SpmSubjectCodesComponent,
    

  ],
  exports: [
    StaffTypeSelectComponent,
    ActorTypeSelectComponent,

  ],
  entryComponents: [
    MaritalCodeEditorDialog,
    VenueCodeEditorDialog,
    LanguageCodeEditorDialog,
    BankCodeCreatorDialog,
    GraduateCenterEditorDialog,
    ReligionCodeEditorDialog,
    CountryCodeEditorDialog,
    StateCodeEditorDialog,
    ProgramCodeEditorDialog,
    ProgramFieldCodeEditorDialog,
    SupervisorCodeEditorDialog,
    SupervisorOfferingEditorDialog,
    GenderCodeEditorDialog,
    EthnicityCodeEditorDialog,
    NationalityCodeEditorDialog,
    FacultyCodeEditorDialog,
    FieldCodeEditorDialog,
    StudyModeEditorDialog,
    DisabilityCodeEditorDialog,
    SchoolCodeEditorDialog,
    StudyCenterCodeEditorDialog,
    DistrictCodeEditorDialog,
    DunCodeEditorDialog,
    ParliamentCodeEditorDialog,
    ResidencyCodeEditorDialog,
    SubjectCodeEditorDialog,
    GradeCodeEditorDialog,
    RaceCodeEditorDialog,
    EmploymentTypeCodeEditorDialog,
    EmploymentSectorCodeEditorDialog,
    GuardianTypeCodeEditorDialog,
    SpmResultCodeEditorDialog,
    UMKCEEStaffEditorDialog,
    SpmSubjectCodeEditorDialog,

  ],
})
export class SetupModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SetupModule,
      providers: [
        appRoutingProviders,
        SetupActions,
      ],
    };
  }
}
