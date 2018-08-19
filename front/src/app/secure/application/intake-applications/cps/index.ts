import { SpmResultCreatorDialog } from './../dialog/spm-result-creator.dialog';
import { StpmResultCreatorDialog } from './../dialog/stpm-result-creator.dialog';
import { EmploymentWorkingDurationComponent } from './../component/employment-working-duration.component';
import { PhdResultEditorDialog } from './../dialog/phd-result-editor.dialog';
import { PhdResultComponent } from './../component/phd-result.component';
import { MasterResultComponent } from './../component/master-result.component';
import { MasterResultEditorDialog } from './../dialog/master-result-editor.dialog';
import { PromoCodeDialog } from './../dialog/promo-code.dialog';
import {DiplomaResultComponent} from '../component/diploma-result.component';
import {StpmResultComponent} from '../component/stpm-result.component';
import {BachelorResultComponent} from '../component/bachelor-result.component';
import {StpmResultEditorDialog} from '../dialog/stpm-result-editor.dialog';
import {ResultListComponent} from '../component/result-list.component';
import {EmploymentTypeSelectComponent} from '../component/employment-type-select.component';
import {DiplomaResultEditorDialog} from '../dialog/diploma-result-editor.dialog';
import {LanguageEditorDialog} from '../dialog/language-editor.dialog';
import {ResultTypeSelectComponent} from '../component/result-type-select.component';
import {BachelorResultEditorDialog} from '../dialog/bachelor-result-editor.dialog';
import {SpmResultEditorDialog} from '../dialog/spm-result-editor.dialog';
import {AttachmentTypeSelectComponent} from '../component/attachment-type-select.component';
import {RefereeTypeSelectComponent} from '../component/referee-type-select.component';
import {RefereeEditorDialog} from '../dialog/referee-editor.dialog';
import {RefereeListComponent} from '../component/referee-list.component';
import {EmploymentEditorDialog} from '../dialog/employment-editor.dialog';
import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {CovalentCoreModule, CovalentFileModule} from '@covalent/core';
import {appRoutes, appRoutingProviders} from '../../../../app.routes';
import {CpsIntakeApplicationPanel} from './intake-application.panel';
import {IdentityService} from '../../../../../services/identity.service';
import {CommonService} from '../../../../../services/common.service';
import {ProgramLevelSubModule} from '../../../policy/program-levels/index';
import {CommonModule} from '../../../../common/index';
import {PolicyService} from '../../../../../services/policy.service';
import {IntakeSessionActions} from '../../../policy/intake-sessions/intake-session.action';
import {IntakeProgramOfferingSelectComponent} from '../component/intake-program-offering-select.component';
import {EmploymentListComponent} from '../component/employment-list.component';
import {ProgramChoiceComponent} from '../component/program-choice.component';
import {LanguageListComponent} from '../component/language-list.component';
import {AttachmentCreatorDialog} from '../dialog/attachment-creator.dialog';
import {AttachmentListComponent} from '../component/attachment-list.component';
import {SpmResultComponent} from '../component/spm-result.component';
import { GuardiansTypeSelectComponent } from '../component/guardian-type-select.component';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    CovalentFileModule.forRoot(),
    ProgramLevelSubModule.forRoot(),
    CommonModule.forRoot(),
  ],
  declarations: [
    CpsIntakeApplicationPanel,
    IntakeProgramOfferingSelectComponent,

    // components
    EmploymentListComponent,
    LanguageListComponent,
    RefereeListComponent,
    AttachmentListComponent,
    ProgramChoiceComponent,
    EmploymentTypeSelectComponent,
    EmploymentWorkingDurationComponent,
    AttachmentTypeSelectComponent,
    RefereeTypeSelectComponent,
    ResultListComponent,
    SpmResultComponent,
    StpmResultComponent,
    DiplomaResultComponent,
    MasterResultComponent,
    PhdResultComponent,
    BachelorResultComponent,
    ResultTypeSelectComponent,
    GuardiansTypeSelectComponent,

    // dialogs
    EmploymentEditorDialog,
    RefereeEditorDialog,
    SpmResultEditorDialog,
    SpmResultCreatorDialog,
    StpmResultEditorDialog,
    StpmResultCreatorDialog,
    BachelorResultEditorDialog,
    MasterResultEditorDialog,
    DiplomaResultEditorDialog,
    PhdResultEditorDialog,
    LanguageEditorDialog,
    AttachmentCreatorDialog,
    PromoCodeDialog,

  ],
  exports: [
    EmploymentListComponent,
    LanguageListComponent,
    AttachmentCreatorDialog,
    RefereeListComponent,
    AttachmentListComponent,
    ProgramChoiceComponent,
    EmploymentTypeSelectComponent,
    EmploymentWorkingDurationComponent,
    RefereeTypeSelectComponent,
    ResultListComponent,
    AttachmentTypeSelectComponent,
    ResultTypeSelectComponent,
    SpmResultComponent,
    BachelorResultComponent,
    PhdResultComponent,
    StpmResultComponent,
    DiplomaResultComponent,
    MasterResultComponent,
    CpsIntakeApplicationPanel,
    GuardiansTypeSelectComponent,
  ],

  entryComponents: [
    EmploymentEditorDialog,
    RefereeEditorDialog,
    SpmResultEditorDialog,
    SpmResultCreatorDialog,
    StpmResultEditorDialog,
    StpmResultCreatorDialog,
    BachelorResultEditorDialog,
    MasterResultEditorDialog,
    PhdResultEditorDialog,
    DiplomaResultEditorDialog,
    LanguageEditorDialog,
    AttachmentCreatorDialog,
    PromoCodeDialog,
    CpsIntakeApplicationPanel,
  ],
})
export class CpsIntakeApplicationSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CpsIntakeApplicationSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        PolicyService,
        IntakeSessionActions,
      ],
    };
  }
}
