import { SpmSubjectCode } from './../../../../shared/model/common/spm-subject-code.interface';
import { GradeCode } from './../../../../shared/model/common/grade-code.interface';
import { Result } from '../../../../shared/model/application/result.interface';
import { Component, ViewContainerRef, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { ApplicationModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { IntakeApplicationActions } from '../intake-application.action';
import { IntakeApplication } from '../../../../shared/model/application/intake-application.interface';
import { ResultType } from '../../../../shared/model/application/result-type.enum';
import { SpmResultCode } from '../../../../shared/model/common/spm-result-code.interface';

@Component({
  selector: 'pams-spm-result-editor',
  templateUrl: './spm-result-editor.dialog.html',
})

export class SpmResultEditorDialog implements OnInit {

  private _intakeApplication: IntakeApplication;
  private editForm: FormGroup;
  private edit: boolean = false;
  private _spmResultCode: SpmResultCode;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private viewContainerRef: ViewContainerRef,
    private store: Store<ApplicationModuleState>,
    private actions: IntakeApplicationActions,
    private dialog: MdDialogRef<SpmResultEditorDialog>) {
  }

  set spmResultCode(value: SpmResultCode) {
    console.log('setting result');
    this._spmResultCode = value;
    console.log(value);
    this.edit = true;
  }

  set intakeApplication(value: IntakeApplication) {
    console.log('setting application');
    this._intakeApplication = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: undefined,
      graduationYear: 0,
      aggregate: '',
      gradeCode: <GradeCode>{},
      spmSubjectCode: <SpmSubjectCode>{},
      resultType: ResultType.SPM,

    });
    if (this.edit) this.editForm.patchValue(this._spmResultCode);
  }

  submit(spmResultCode: SpmResultCode, isValid: boolean): void {
    console.log("Update spmResultCode");
    this.store.dispatch(this.actions.updateSpmResultCode(this._intakeApplication, spmResultCode));
    this.dialog.close();
  }
}
