import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import { IdentityService, NotificationService } from '../../services';

import {RegistrationPage} from "./registration.page";
import {RegistrationService} from "../../services/registration.service";
import {RegistrationState, registrationReducer} from "./registration.reducer";
import {RegistrationActions} from "./registration.action";
import {RegistrationEffects} from "./registration.effect";
import {VerificationPage} from "./verification.page";
import {RegistrationInformationPage} from "./registration-information.page";
import { ForgetPasswordInformationPage } from "../login/forget-password-information.page";
import { ChangeEmailVerificationPage } from "./change-email-verification.page";


export interface RegistrationModuleState {
  registration: RegistrationState
}
;

export const INITIAL_REGISTRATION_STATE: RegistrationModuleState =
  <RegistrationModuleState>{
    registration: '',
  };

export const registrationModuleReducers = {
  registration: registrationReducer
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(RegistrationEffects),
  ],
  declarations: [
    // page
    RegistrationPage,
    RegistrationInformationPage,
    VerificationPage,
    ChangeEmailVerificationPage,
    ForgetPasswordInformationPage,
  ],
  exports: [],
})
export class RegistrationModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: RegistrationModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        RegistrationService,
        RegistrationActions,
        NotificationService,
      ],
    };
  }
}
