import { UMKCEEStaffListPage } from './umkcee-staffs/umkcee-staff-list.page';
import { GuardianTypeCodeListPage } from './guardian-type-codes/guardian-type-code-list.page';
import { EmploymentSectorCodeListPage } from './employment-sector-codes/employment-sector-code-list.page';
import { EmploymentTypeCodeListPage } from './employment-type-codes/employment-type-code-list.page';
import {VenueCodeListPage} from './venue-codes/venue-code-list.page';
import {ResidencyCodeListPage} from './residency-codes/residency-code-list-page';
import {ParliamentCodeListPage} from './parliament-codes/parliament-code-list-page';
import {DistrictCodeListPage} from './district-codes/district-code-list-page';
import {SchoolCodeListPage} from './school-codes/school-code-list-page';
import {DisabilityCodeListPage} from './disability-codes/disability-code-list-page';
import {StudyModeListPage} from './study-modes/study-mode-list.page';
import {FacultyCodeListPage} from './faculty-codes/faculty-code-list.page';
import {FieldCodeListPage} from './field-codes/field-code-list.page';
import {NationalityCodeListPage} from './nationality-codes/nationality-code-list.page';
import {EthnicityCodeListPage} from './ethnicity-codes/ethnicity-code-list.page';
import {GenderCodeListPage} from './gender-codes/gender-code-list.page';
import {RaceCodeListPage} from './race-codes/race-code-list.page';
import {SupervisorCodeListPage} from './supervisor-codes/supervisor-code-list.page';
import {ProgramCodeListPage} from './program-codes/program-code-list.page';
import {StateCodeListPage} from './state-codes/state-code-list.page';
import {MaritalCodeListPage} from './marital-codes/marital-code-list-page';
import {ReligionCodeListPage} from './religion-codes/religion-code-list.page';
import {Routes, RouterModule} from '@angular/router';
import {SetupPage} from './setup.page';
import {BankCodeListPage} from './bank-codes/bank-code-list.page';
import {GraduateCenterListPage} from './graduate-centers/graduate-center-list.page';
import {CountryCodeListPage} from './country-codes/country-code-list.page';
import {StudyCenterCodeListPage} from './study-center-codes/study-center-code-list.page';
import {DunCodeListPage} from './dun-codes/dun-code-list-page';
import {LanguageCodeListPage} from './language-codes/language-code-list-page';
import {SubjectCodeListPage} from './subject-codes/subject-code-list-page';
import {GradeCodeListPage} from './grade-codes/grade-code-list-page';
import {AuthenticationGuard} from '../identity/guard/authentication.guard';
import { SupervisorOfferingListPage } from './supervisor-offerings/supervisor-offering-list.page';
import { ProgramFieldCodeListPage } from "./program-field-codes/program-field-code-list.page";
import { SpmSubjectCodeListPage } from './spm-subject-codes/spm-subject-code-list-page';


export const setupModuleRoutes: Routes = [
  {
    path: 'setup', component: SetupPage, canActivate: [AuthenticationGuard],
    children: [
      {path: 'bank-codes', component: BankCodeListPage},
      {path: 'graduate-centers', component: GraduateCenterListPage},
      {path: 'religion-codes', component: ReligionCodeListPage},
      {path: 'marital-codes', component: MaritalCodeListPage},
      {path: 'venue-codes', component: VenueCodeListPage},
      {path: 'language-codes', component: LanguageCodeListPage},
      {path: 'country-codes', component: CountryCodeListPage},
      {path: 'state-codes', component: StateCodeListPage},
      {path: 'program-codes', component: ProgramCodeListPage},
      {path: 'supervisor-codes', component: SupervisorCodeListPage},
      {path: 'supervisor-offerings', component: SupervisorOfferingListPage},
      {path: 'race-codes', component: RaceCodeListPage},
      {path: 'gender-codes', component: GenderCodeListPage},
      {path: 'ethnicity-codes', component: EthnicityCodeListPage},
      {path: 'nationality-codes', component: NationalityCodeListPage},
      {path: 'faculty-codes', component: FacultyCodeListPage},
      {path: 'study-modes', component: StudyModeListPage},
      {path: 'disability-codes', component: DisabilityCodeListPage},
      {path: 'school-codes', component: SchoolCodeListPage},
      {path: 'study-center-codes', component: StudyCenterCodeListPage},
      {path: 'district-codes', component: DistrictCodeListPage},
      {path: 'dun-codes', component: DunCodeListPage},
      {path: 'ethnicity-codes', component: EthnicityCodeListPage},
      {path: 'parliament-codes', component: ParliamentCodeListPage},
      {path: 'residency-codes', component: ResidencyCodeListPage},
      {path: 'subject-codes', component: SubjectCodeListPage},
      {path: 'grade-codes', component: GradeCodeListPage},
      {path: 'field-codes', component: FieldCodeListPage},
      {path: 'program-field-codes', component: ProgramFieldCodeListPage},
      {path: 'employment-type-codes', component: EmploymentTypeCodeListPage},
      {path: 'employment-sector-codes', component: EmploymentSectorCodeListPage},
      {path: 'guardian-type-codes', component: GuardianTypeCodeListPage},
      {path: 'umkcee-staffs', component: UMKCEEStaffListPage},
      {path: 'spm-subject-codes', component: SpmSubjectCodeListPage},

    ],
  },

];
