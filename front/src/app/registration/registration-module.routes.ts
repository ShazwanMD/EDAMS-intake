import {Routes} from '@angular/router';
import {RegistrationPage} from "./registration.page";
import {VerificationPage} from "./verification.page";
import {RegistrationInformationPage} from "./registration-information.page";
import {ForgetPasswordInformationPage} from "../login/forget-password-information.page";
import { ChangeEmailVerificationPage } from "./change-email-verification.page";

export const registrationModuleRoutes: Routes = [
  {path: 'registration', component: RegistrationPage},
  {path: 'registrationInfo', component: RegistrationInformationPage},
  {path: 'verification/:token', component: VerificationPage},
  {path: 'changeEmailVerification/:token', component: ChangeEmailVerificationPage},
  {path: 'forgetPasswordInfo', component: ForgetPasswordInformationPage},

];
