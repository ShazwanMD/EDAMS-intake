import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {IntakeActions} from '../intake.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {PolicyModuleState} from '../../index';
import {IntakeUpdaterDialog} from '../dialog/intake-updater.dialog';
import {IntakeTask} from '../../../../shared/model/policy/intake-task.interface';
import {ProgramOffering} from '../../../../shared/model/policy/program-offering.interface';
import {SupervisorOffering} from '../../../../shared/model/policy/supervisor-offering.interface';
import {StudyModeOffering} from '../../../../shared/model/policy/study-mode-offering.interface';

@Component({
  selector: 'pams-intake-verify-task',
  templateUrl: './intake-verify-task.panel.html',
})

export class IntakeVerifyTaskPanel implements OnInit {

  private PROGRAM_OFFERINGS: string[] = 'policyModuleState.programOfferings'.split('.');
  private SUPERVISOR_OFFERINGS: string[] = 'policyModuleState.supervisorOfferings'.split('.');
  private STUDY_MODE_OFFERINGS: string[] = 'policyModuleState.studyModeOfferings'.split('.');
  private editorDialogRef: MdDialogRef<IntakeUpdaterDialog>;
  private programOfferings$: Observable<ProgramOffering[]>;
  private supervisorOfferings$: Observable<SupervisorOffering[]>;
  private studyModeOfferings$: Observable<StudyModeOffering[]>;

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
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findIntakeByReferenceNo(this.intakeTask.referenceNo));
  }

  publish() {
    if (confirm('Publish this intake?')) {
      this.store.dispatch(this.actions.completeIntakeTask(this.intakeTask));
         this.goBack();
    } else {
    }
  }

  edit(): void {
    console.log('edit');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(IntakeUpdaterDialog, config);
    this.editorDialogRef.componentInstance.intake = this.intakeTask.intake;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }

  remove(): void {
    if(confirm('Confirm remove this intake?')){
    this.store.dispatch(this.actions.removeIntakeTask(this.intakeTask));
    this.goBack();
    }else {
   }
}

  goBack(): void {
    this.router.navigate(['/secure/policy/intakes']);
  }
}
