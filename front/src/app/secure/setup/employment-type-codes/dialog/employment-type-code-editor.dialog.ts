import { EmploymentTypeCode } from './../../../../shared/model/common/employment-type-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-employment-type-code-creator',
  templateUrl: './employment-type-code-editor.dialog.html',
})

export class EmploymentTypeCodeEditorDialog implements OnInit {

  private creatorForm: FormGroup;
  private edit: boolean = false;
  private _employmentTypeCode: EmploymentTypeCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<EmploymentTypeCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar) {
  }

set employmentTypeCode(value: EmploymentTypeCode) {
    this._employmentTypeCode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.creatorForm = this.formBuilder.group(<EmploymentTypeCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
    });

    if (this.edit) this.creatorForm.patchValue(this._employmentTypeCode);
  }

  submit(code: EmploymentTypeCode, isValid: boolean) {
    if (confirm('Update employment type code?')) {
    if (!code.id) this.store.dispatch(this.actions.saveEmploymentTypeCode(code));
    else  this.store.dispatch(this.actions.updateEmploymentTypeCode(code));
    this.dialog.close();
    };
  }
}
