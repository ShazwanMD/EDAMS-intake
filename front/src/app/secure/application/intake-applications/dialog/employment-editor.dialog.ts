import { EmploymentWorkingDuration } from './../../../../shared/model/application/employment-working-duration.enum';
import { EmploymentSectorCode } from './../../../../shared/model/common/employment-sector-code.interface';
import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {ApplicationModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {IntakeApplicationActions} from '../intake-application.action';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';
import {Employment} from '../../../../shared/model/application/employment.interface';
import {EmploymentType} from '../../../../shared/model/application/employment-type.enum';

@Component({
  selector: 'pams-employment-editor',
  templateUrl: './employment-editor.dialog.html',
})

export class EmploymentEditorDialog implements OnInit {

  private _intakeApplication: IntakeApplication;
  private _employment: Employment;
  private editForm: FormGroup;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<ApplicationModuleState>,
              private actions: IntakeApplicationActions,
              private dialog: MdDialogRef<EmploymentEditorDialog>) {
  }

  set employment(value: Employment) {
    this._employment = value;
    this.edit = true;
  }

  set intakeApplication(value: IntakeApplication) {
    this._intakeApplication = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: null,
      startDate: null,
      endDate: null,
      employer: '',
      designation: '',
      current: false,
      employmentType: EmploymentType.CURRENTLY_EMPLOYED,
      workingDuration: EmploymentWorkingDuration.DUA_TAHUN,
      employmentSectorCode: [<EmploymentSectorCode>{}],
    });
    if (this.edit) this.editForm.patchValue(this._employment);
  }

  submit(employment: Employment, isValid: boolean) {
    if (this.edit) this.store.dispatch(this.actions.updateEmployment(this._intakeApplication, employment));
    else  this.store.dispatch(this.actions.addEmployment(this._intakeApplication, employment));
    this.dialog.close();
  }
}

