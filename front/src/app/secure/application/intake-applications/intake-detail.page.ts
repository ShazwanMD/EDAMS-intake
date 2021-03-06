import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ApplicationModuleState} from '../index';
import {Intake} from '../../../shared/model/policy/intake.interface';
import {IntakeApplicationActions} from './intake-application.action';
import {ProgramOffering} from '../../../shared/model/policy/program-offering.interface';

@Component({
  selector: 'pams-intake-detail',
  templateUrl: './intake-detail.page.html',
})

export class IntakeDetailPage implements OnInit {

  private INTAKE: string[] = 'applicationModuleState.intake'.split('.');
  private PROGRAM_OFFERINGS: string[] = 'applicationModuleState.programOfferings'.split('.');
  private STUDY_MODE_OFFERINGS: string[] = 'applicationModuleState.studyModeOfferings'.split('.');
  private SUPERVISOR_OFFERINGS: string[] = 'applicationModuleState.supervisorOfferings'.split('.');
  private intake$: Observable<Intake>;
  private programOfferings$: Observable<ProgramOffering[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: IntakeApplicationActions,
              private store: Store<ApplicationModuleState>) {
    this.intake$ = this.store.select(...this.INTAKE);
    this.programOfferings$ = this.store.select(...this.PROGRAM_OFFERINGS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      if (null != referenceNo)
        this.store.dispatch(this.actions.findIntakeByReferenceNo(referenceNo));
    });
  }

  apply(intake: Intake) {
    this.intake$.subscribe((intake) => this.store.dispatch(this.actions.applyIntake(intake)));
  }

}
