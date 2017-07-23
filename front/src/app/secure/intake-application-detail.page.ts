import { MgsebIntakeApplicationPanel } from './application/intake-applications/mgseb/intake-application.panel';
import { CpsIntakeApplicationPanel} from './application/intake-applications/cps/intake-application.panel';
import { IntakeApplication } from './../shared/model/application/intake-application.interface';
import {
  Component, OnInit, OnDestroy, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentFactory, ComponentRef,
} from '@angular/core';
import {Router} from '@angular/router';
import {AuthorizationService} from '../../services/authorization.service';
import {AdministratorDashboardPanel} from './administrator-dashboard.panel';
import {ApplicantDashboardPanel} from './applicant-dashboard.panel';


@Component({
  selector: 'pams-intake-application-detail',
  templateUrl: './intake-application-detail.page.html',
})
export class IntakeApplicationDetailPage implements OnInit {

  private componentRef: ComponentRef<any>;
  @ViewChild('intakeApplicationFormPanel',{read: ViewContainerRef}) intakeApplicationFormPanel: ViewContainerRef;
  
  constructor(private viewContainerRef: ViewContainerRef,
              private intakeApplication: IntakeApplication,
              private cfr: ComponentFactoryResolver,
              private router: Router) {
  }
  ngOnInit(): void {
    let componentFactory: ComponentFactory<any>;
    if (this.intakeApplication.intake.graduateCenter.code === 'CPS') {
      componentFactory = this.cfr.resolveComponentFactory(CpsIntakeApplicationPanel);
    } else if (this.intakeApplication.intake.graduateCenter.code === 'MGSEB') {
      componentFactory = this.cfr.resolveComponentFactory(MgsebIntakeApplicationPanel);
    }
    // handle null factory
    if (componentFactory) {
      this.componentRef = this.intakeApplicationFormPanel.createComponent(componentFactory);
    } else {
      this.router.navigate(['/intakes']);
    }
  }
}