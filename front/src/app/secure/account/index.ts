import { IntakeListsState, intakeListReducer } from './intake-lists.reducer';
import { ResultCandidateReasonDialog } from './dialog/result-candidate-reason.dialog';
import { MyIntakeApplication } from './../../shared/model/application/my-intake-application.interface';
import { Candidate } from './../../shared/model/admission/candidate.interface';
import { CpsIntakeApplicationSubModule } from './../application/intake-applications/cps/index';
import { AddressChangerDialog } from './dialog/address-changer.dialog';
import { IntakeApplication } from './../../shared/model/application/intake-application.interface';
import { EmailChangerDialog } from './dialog/email-changer.dialog';
import {UserComponent} from './component/user.component';
import {User} from './../identity/user.interface';
import {ApplicantComponent} from './component/applicant.component';
import {Applicant} from '../identity/applicant.interface';
import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService} from '../../../services';
import {IdentityService, ReportService} from '../../../services';
import {ApplicationService} from '../../../services/application.service';
import {PolicyService} from '../../../services/policy.service';
import {IntakeApplicationSubModule} from '../application/intake-applications/index';
import {CommonModule} from '../../common/index';
import {IntakeListState} from '../application/intake-applications/intake-list.reducer';
import {IntakeApplicationListState} from '../application/intake-applications/intake-application-list.reducer';
import {publishedIntakeListReducer} from './intake-list.reducer';
import {applicantReducer, ApplicantState} from './applicant.reducer';
import {userReducer, UserState} from './user.reducer';
import {
  draftedIntakeApplicationListReducer, intakeApplicationListReducer,
  submittedIntakeApplicationListReducer,
} from './intake-application-list.reducer';
import {Intake} from '../../shared/model/policy/intake.interface';
import {AccountActions} from './account.action';
import {AccountEffects} from './account.effect';
import {EffectsModule} from '@ngrx/effects';
import {PasswordChangerDialog} from './dialog/password-changer.dialog';
import { ResultCandidateDialog } from "./dialog/result-candidate.dialog";
import { candidateListReducer } from "../admission/candidate-list.reducer";
import { CandidateListState } from "./candidate-list.reducer";
import {
  MyIntakeApplicationListState, myIntakeApplicationListReducer} from './my-intake-application-list.reducer';
import { ReportActions } from "../../shared/report/report.action";
import { ReportModule } from "../../shared/report/index";

export interface AccountModuleState {
  user: UserState;
  applicant: ApplicantState;
  publishedIntakes: IntakeListState;
  intakeApplications: IntakeApplicationListState;
  myIntakeApplications: MyIntakeApplicationListState;
  candidates: CandidateListState;
  intakes: IntakeListsState;
}

export const INITIAL_ACCOUNT_STATE: AccountModuleState = <AccountModuleState>{
  user: <User>{},
  applicant: <Applicant>{},
  publishedIntakes: <Intake[]>[],
  intakeApplications: <IntakeApplication[]>[],
  myIntakeApplications: <MyIntakeApplication[]>[],
  candidates: <Candidate[]>[],
  intakes: <Intake[]>[],

};

export const accountModuleReducers = {
  user: userReducer,
  applicant: applicantReducer,
  publishedIntakes: publishedIntakeListReducer,
  intakeApplications: intakeApplicationListReducer,
  myIntakeApplications: myIntakeApplicationListReducer,
  candidates: candidateListReducer,
  intakes: intakeListReducer,
};

@NgModule({
  imports: [
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IntakeApplicationSubModule.forRoot(),
    CpsIntakeApplicationSubModule.forRoot(),
    CommonModule.forRoot(),
    ReportModule.forRoot(),
    EffectsModule.run(AccountEffects),
  ],
  declarations: [
    ApplicantComponent,
    UserComponent,

    PasswordChangerDialog,
    EmailChangerDialog,
    AddressChangerDialog,
    ResultCandidateDialog,
    ResultCandidateReasonDialog,

  ],
  exports: [
    ApplicantComponent,
    UserComponent,
  ],

  entryComponents: [
    PasswordChangerDialog,
    EmailChangerDialog,
    AddressChangerDialog,
    ResultCandidateDialog,
    ResultCandidateReasonDialog,
  ],
})
export class AccountModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AccountModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        PolicyService,
        ApplicationService,
        ReportService,
        AccountActions,
        ReportActions,
      ],
    };
  }
}
