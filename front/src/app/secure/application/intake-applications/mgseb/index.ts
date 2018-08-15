import {SupervisorOfferingSelectorDialog} from '../dialog/supervisor-offering-selector.dialog';
import {CpsIntakeApplicationSubModule} from '../cps/index';
import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {CovalentCoreModule} from '@covalent/core';
import {appRoutes, appRoutingProviders} from '../../../../app.routes';
import {MgsebIntakeApplicationPanel} from './intake-application.panel';
import {IntakeApplicationEducationPage} from './intake-application-education.page';
import {IdentityService} from '../../../../../services/identity.service';
import {CommonService} from '../../../../../services/common.service';
import {CommonModule} from '../../../../common/index';
import {ProgramLevelSubModule} from '../../../policy/program-levels/index';
import {IntakeApplicationActions} from '../intake-application.action';
import {CommonActions} from '../../../../common/common.action';
import {ProgramOfferingSelectComponent} from '../../../policy/intakes/component/program-offering-select.component';
import {EmploymentListComponent} from '../component/employment-list.component';
import {StudyModeOfferingSelectorDialog} from '../dialog/study-mode-offering-selector.dialog';
import {StudyCenterChoiceComponent} from '../component/study-center-choice.component';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    CommonModule.forRoot(),
    ProgramLevelSubModule.forRoot(),
    CpsIntakeApplicationSubModule.forRoot(),
  ],
  declarations: [
    MgsebIntakeApplicationPanel,
    IntakeApplicationEducationPage,

    // components
    StudyCenterChoiceComponent,
    

    // dialog
    StudyModeOfferingSelectorDialog,
    SupervisorOfferingSelectorDialog,

  ],
  exports: [
    StudyCenterChoiceComponent,
    MgsebIntakeApplicationPanel,

  ],
  entryComponents: [
    EmploymentListComponent,
    ProgramOfferingSelectComponent,
    StudyModeOfferingSelectorDialog,
    SupervisorOfferingSelectorDialog,
    MgsebIntakeApplicationPanel,
  ],
})
export class MgsebIntakeApplicationSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: MgsebIntakeApplicationSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        IntakeApplicationActions,
        CommonActions,
      ],
    };
  }
}
