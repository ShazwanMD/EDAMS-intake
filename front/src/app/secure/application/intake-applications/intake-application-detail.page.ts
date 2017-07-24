import { MgsebIntakeApplicationPanel } from './mgseb/intake-application.panel';
import { CpsIntakeApplicationPanel } from './cps/intake-application.panel';
import { IntakeApplication } from './../../../shared/model/application/intake-application.interface';

import {
  Component, OnInit, OnDestroy, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentFactory, ComponentRef,
} from '@angular/core';
import {Router} from '@angular/router';



@Component({
  selector: 'pams-intake-application-detail',
  templateUrl: './intake-application-detail.page.html',
})
export class IntakeApplicationDetailPage implements OnInit {

  private componentRef: ComponentRef<any>;
  private intakeApplication: IntakeApplication;
  @ViewChild('intakeApplicationFormPanel',{read: ViewContainerRef}) intakeApplicationFormPanel: ViewContainerRef;
  
  constructor(private viewContainerRef: ViewContainerRef,
             // private intakeApplication: IntakeApplication,
              private cfr: ComponentFactoryResolver,
              private router: Router) {
  }
  ngOnInit(): void {
    let componentFactory: ComponentFactory<any>;
    console.log('test IA detail 1');
    if (this.intakeApplication.intake.graduateCenter.code === 'CPS') {
      componentFactory = this.cfr.resolveComponentFactory(CpsIntakeApplicationPanel);
      console.log('test IA detail 2');
    } else if (this.intakeApplication.intake.graduateCenter.code === 'MGSEB') {
      componentFactory = this.cfr.resolveComponentFactory(MgsebIntakeApplicationPanel);
      console.log('test IA detail 3');
    }
    // handle null factory
    if (componentFactory) {
      this.componentRef = this.intakeApplicationFormPanel.createComponent(componentFactory);
      console.log('test IA detail 4');
    } else {
      this.router.navigate(['/intakes']);
      console.log('test IA detail 5');
    }
  }
}