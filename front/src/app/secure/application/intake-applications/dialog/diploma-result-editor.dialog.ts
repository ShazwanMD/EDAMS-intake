import { DiplomaResultCode } from './../../../../shared/model/application/diploma-result.interface';
import {Result} from '../../../../shared/model/application/result.interface';
import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {ApplicationModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {IntakeApplicationActions} from '../intake-application.action';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';
import {ResultType} from '../../../../shared/model/application/result-type.enum';

@Component({
  selector: 'pams-diploma-result-editor',
  templateUrl: './diploma-result-editor.dialog.html',
})

export class DiplomaResultEditorDialog implements OnInit {

  private _intakeApplication: IntakeApplication;
  private editForm: FormGroup;
  private edit: boolean = false;
  private _diplomaResultCode: DiplomaResultCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<ApplicationModuleState>,
              private actions: IntakeApplicationActions,
              private dialog: MdDialogRef<DiplomaResultEditorDialog>) {
  }

  set diplomaResultCode(value: DiplomaResultCode) {
    this._diplomaResultCode = value;
    this.edit = true;
  }

  set intakeApplication(value: IntakeApplication) {
    this._intakeApplication = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: undefined,
      graduationYear: 0,
      cgpa:'',
      institution:'',
      program:'',
      resultType: ResultType.DIPLOMA,

    });
    if (this.edit) this.editForm.patchValue(this._diplomaResultCode);
  }

  submit(diplomaResultCode: DiplomaResultCode, isValid: boolean) {
    this.store.dispatch(this.actions.updateDiplomaResultCode(this._intakeApplication, diplomaResultCode));
    this.dialog.close();
  }
}
