import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {IntakeTask} from '../intake-task.interface';
import {IntakeActions} from '../intake.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {PolicyModuleState} from '../../index';
import {ProgramOffering} from '../program-offering.interface';
import {StudyModeOffering} from '../study-mode-offering.interface';
import {SupervisorOffering} from '../supervisor-offering.interface';
import {IntakeUpdaterDialog} from '../dialog/intake-updater.dialog';

@Component({
  selector: 'pams-intake-verify-task',
  templateUrl: './intake-verify-task.panel.html',
})

export class IntakeVerifyTaskPanel implements OnInit {
  editorDialogRef: MdDialogRef<IntakeUpdaterDialog>;

  private PROGRAM_OFFERINGS = 'policyModuleState.programOfferings'.split('.');
  private SUPERVISOR_OFFERINGS = 'policyModuleState.supervisorOfferings'.split('.');
  private STUDY_MODE_OFFERINGS = 'policyModuleState.studyModeOfferings'.split('.');

  @Input() intakeTask: IntakeTask;
  programOfferings$: Observable<ProgramOffering[]>;
  supervisorOfferings$: Observable<SupervisorOffering[]>;
  studyModeOfferings$: Observable<StudyModeOffering[]>;

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
    let snackBarRef = this.snackBar.open('Publish this intake?', 'Yes');
    snackBarRef.afterDismissed().subscribe(() => {
      this.store.dispatch(this.actions.completeIntakeTask(this.intakeTask));
      this.goBack();
    });
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

  goBack(): void {
    this.router.navigate(['/policy/intakes']);
  }
}
