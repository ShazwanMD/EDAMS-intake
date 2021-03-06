import {Routes, RouterModule} from '@angular/router';

import {LoginPage} from './login/login.page';
import {HomePage} from './home/home.page';
import {registrationModuleRoutes} from './registration/registration-module.routes';
import {policyModuleRoutes} from './secure/policy/policy-module.routes';
import {admissionModuleRoutes} from './secure/admission/admission-module.routes';
import {centerModuleRoutes} from './center/center-module.routes';
import {ForgetPasswordPage} from './login/forget-password.page';
import {applicationModuleRoutes} from './secure/application/application-module.routes';
import {SecurePage} from './secure/secure.page';
import {AuthenticationGuard} from './secure/identity/guard/authentication.guard';
import {DashboardPage} from './secure/dashboard.page';
import {setupModuleRoutes} from './secure/setup/setup-module.routes';
import { admissionCandidateModuleRoutes } from "./secure/cps-candidate/admission-candidate-module.routes";

const routes: Routes = [
   // {path: '', component: HomePage},
    {path: '', component: LoginPage},
    {path: 'login', component: LoginPage},
    {path: 'forget-password', component: ForgetPasswordPage},
    ...registrationModuleRoutes,
    ...centerModuleRoutes,
    {
      path: 'secure', component: SecurePage, canActivate: [AuthenticationGuard],
      children: [
        {
          path: '', component: DashboardPage,
        },
        ...admissionModuleRoutes,
        ...policyModuleRoutes,
        ...applicationModuleRoutes,
        ...setupModuleRoutes,
        ...admissionCandidateModuleRoutes,
      ],
    },
  ]
;

export const appRoutingProviders: any[] = [];

export const appRoutes: any = RouterModule.forRoot(routes, {useHash: false});
