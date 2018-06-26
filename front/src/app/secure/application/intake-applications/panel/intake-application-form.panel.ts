import { IntakeActions } from './../../../policy/intakes/intake.action';
import {Observable} from 'rxjs/Observable';
import {Store} from '@ngrx/store';
import {
  Component, OnInit, OnDestroy, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentFactory, ComponentRef, Input, Output,
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';
import {IntakeApplicationActions} from '../intake-application.action';
import {ApplicationModuleState} from '../../index';
import {CpsIntakeApplicationPanel} from '../cps/intake-application.panel';
import {MgsebIntakeApplicationPanel} from '../mgseb/intake-application.panel';
@Component({
  selector: 'pams-intake-application-form',
  templateUrl: './intake-application-form.panel.html',
})
export class IntakeApplicationFormPanel implements OnInit {
  private componentRef: ComponentRef<any>;


  @Input() intakeApplicationObservable: Observable<IntakeApplication>;
  @ViewChild('intakeApplicationFormPanel', {read: ViewContainerRef}) intakeApplicationFormPanel: ViewContainerRef;

  constructor(private viewContainerRef: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private cfr: ComponentFactoryResolver,
              private store: Store<ApplicationModuleState>,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    let componentFactory;

    this.intakeApplicationObservable.subscribe((intakeApplication: IntakeApplication) => {

      if (this.componentRef) {
        this.componentRef.destroy();
      }

      if (intakeApplication && intakeApplication.intake) {
        console.log('graduate center: ' + intakeApplication.intake.graduateCenter.code);
        if (intakeApplication.intake.graduateCenter.code === 'CPS') {
          componentFactory = this.cfr.resolveComponentFactory(CpsIntakeApplicationPanel);
          console.log('test IA detail 2');
        } else if (intakeApplication.intake.graduateCenter.code === 'UMKCEE') {
          componentFactory = this.cfr.resolveComponentFactory(MgsebIntakeApplicationPanel);
          console.log('test IA detail 3');
        }
        // handle null factory
        if (componentFactory) {
          this.componentRef = this.intakeApplicationFormPanel.createComponent(componentFactory);
          this.componentRef.instance.intakeApplication = intakeApplication;
          console.log('test IA detail 4');
        } else {
          this.router.navigate(['/intakes']);
          console.log('test IA detail 5');
        }
      }
    });
  }

  ngOnDestroy() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
  }
}
