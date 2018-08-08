import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {IntakeTask} from '../../../../shared/model/policy/intake-task.interface';
import {IntakeActions} from '../intake.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {PolicyModuleState} from '../../index';
import {ProgramOffering} from '../../../../shared/model/policy/program-offering.interface';
import {StudyModeOffering} from '../../../../shared/model/policy/study-mode-offering.interface';
import {SupervisorOffering} from '../../../../shared/model/policy/supervisor-offering.interface';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';

@Component({
  selector: 'pams-intake-evaluate-task',
  templateUrl: './intake-evaluate-task.panel.html',
})

export class IntakeEvaluateTaskPanel implements OnInit {

  private PROGRAM_OFFERINGS: string[] = 'policyModuleState.programOfferings'.split('.');
  private SUPERVISOR_OFFERINGS: string[] = 'policyModuleState.supervisorOfferings'.split('.');
  private STUDY_MODE_OFFERINGS: string[] = 'policyModuleState.studyModeOfferings'.split('.');
  private INTAKE_APPLICATIONS: string[] = 'policyModuleState.intakeApplications'.split('.');
  private INTAKE_SUBMITTED_APPLICATIONS: string[] = 'policyModuleState.intakeSubmittedApplications'.split('.');
  private INTAKE_SELECTED_APPLICATIONS: string[] = 'policyModuleState.intakeSelectedApplications'.split('.');
  private INTAKE_REJECTED_APPLICATIONS: string[] = 'policyModuleState.intakeRejectedApplications'.split('.');
  private INTAKE_VERIFIED_APPLICATIONS: string[] = 'policyModuleState.intakeVerifiedApplications'.split('.');
  private programOfferings$: Observable<ProgramOffering[]>;
  private supervisorOfferings$: Observable<SupervisorOffering[]>;
  private studyModeOfferings$: Observable<StudyModeOffering[]>;
  private intakeSubmittedApplications$: Observable<IntakeApplication[]>;
  private intakeSelectedApplications$: Observable<IntakeApplication[]>;
  private intakeRejectedApplications$: Observable<IntakeApplication[]>;
  private intakeVerifiedApplications$: Observable<IntakeApplication[]>;

  @Input() intakeTask: IntakeTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: IntakeActions,
              private store: Store<PolicyModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.programOfferings$ = this.store.select(...this.PROGRAM_OFFERINGS);
    this.supervisorOfferings$ = this.store.select(...this.SUPERVISOR_OFFERINGS);
    this.studyModeOfferings$ = this.store.select(...this.STUDY_MODE_OFFERINGS);
    this.intakeSubmittedApplications$ = this.store.select(...this.INTAKE_SUBMITTED_APPLICATIONS);
    this.intakeSelectedApplications$ = this.store.select(...this.INTAKE_SELECTED_APPLICATIONS);
    this.intakeRejectedApplications$ = this.store.select(...this.INTAKE_REJECTED_APPLICATIONS);
    this.intakeVerifiedApplications$ = this.store.select(...this.INTAKE_VERIFIED_APPLICATIONS);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findIntakeByReferenceNoAndBidStatus(this.intakeTask.referenceNo));
  }

  copy() {
      if(confirm('Salin Sesi Pengambilan?')){
      this.store.dispatch(this.actions.copyIntakeTask(this.intakeTask));
      this.goBack();
    }else {
    }
  }

  remove(): void {
    if(confirm('Padam Sesi Pengambilan?')){
    this.store.dispatch(this.actions.removeIntakeTask(this.intakeTask));
    this.goBack();
    } else {
    }
}

  process(): void {
    if (confirm('Proses Sesi Pengambilan?')) {
      this.store.dispatch(this.actions.completeIntakeTask(this.intakeTask));
         this.goBack();
    } else {
    }
  }

  goBack(): void {
    this.router.navigate(['/secure/policy/intakes']);
  }
}
