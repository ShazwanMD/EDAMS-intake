import { IntakeApplication } from '../../../shared/model/application/intake-application.interface';
import { ApplicationModuleState } from '../../application';
import { IntakeApplicationActions } from '../../application/intake-applications/intake-application.action';
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdSnackBar, MdDialogRef, MdDialogConfig} from '@angular/material';

@Component({
  selector: 'pams-candidate-profile-reject',
  templateUrl: './candidate-profile-reject.dialog.html',
})

export class CandidateProfileRejectDialog implements OnInit {

  private rejectForm: FormGroup;
  private _intakeApplication: IntakeApplication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private vcf: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private dialog: MdDialogRef<CandidateProfileRejectDialog>,
              private snackBar: MdSnackBar,
              private store: Store<ApplicationModuleState>) {
  }

  set intakeApplication(intakeApplication: IntakeApplication) {
    console.log('intakeApplication.id :' + intakeApplication.id);
    this._intakeApplication = intakeApplication;
  }

  ngOnInit(): void {
    this.rejectForm = this.formBuilder.group(<IntakeApplication>{
      id: null,
      reason: '',
      referenceNo: '',
    });

    this.rejectForm.patchValue(this._intakeApplication);
  }

  submit(intakeApplication: IntakeApplication) {
    let snackBarRef = this.snackBar.open('Confirm to Reject This Applicant?', 'Ok');

    snackBarRef.afterDismissed().subscribe(() => {
      this.store.dispatch(this.actions.rejectIntakeApplication(intakeApplication));
      this.dialog.close();
    });
  }

}
