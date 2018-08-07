import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {EffectsModule} from '@ngrx/effects';
import {CommonService} from '../../../../services';
import {IdentityService} from '../../../../services';
import {PolicyService} from '../../../../services/policy.service';
import {CommonModule} from '../../../common/index';
import {IntakeSessionActions} from './intake-session.action';
import {IntakeSessionEffects} from './intake-session.effect';
import {IntakeSessionCenterPage} from './intake-session-center.page';
import {IntakeSessionSelectComponent} from './component/intake-session-select.component';
import {IntakeSessionListComponent} from './component/intake-session-list.component';
import {IntakeSessionCreatorDialog} from './component/intake-session-creator.dialog';
import {IntakeSessionEditorDialog } from './component/intake-session-editor.dialog';
import { PipeModule } from '../../../app.pipe.module';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    CommonModule.forRoot(),
    EffectsModule.run(IntakeSessionEffects),
    PipeModule,
  ],
  declarations: [
    // page
    IntakeSessionCenterPage,

    // components
    IntakeSessionSelectComponent,
    IntakeSessionListComponent,

    // panels

    // dialog
    IntakeSessionCreatorDialog,
    IntakeSessionEditorDialog,
  ],
  exports: [
    IntakeSessionSelectComponent,
  ],
  entryComponents: [
    IntakeSessionCreatorDialog,
    IntakeSessionEditorDialog,
  ],

})
export class IntakeSessionSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: IntakeSessionSubModule,
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
