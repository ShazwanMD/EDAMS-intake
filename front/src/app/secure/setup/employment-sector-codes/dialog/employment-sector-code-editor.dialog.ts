import { EmploymentSectorCode } from './../../../../shared/model/common/employment-sector-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-employment-sector-code-creator',
  templateUrl: './employment-sector-code-editor.dialog.html',
})

export class EmploymentSectorCodeEditorDialog implements OnInit {

  private creatorForm: FormGroup;
  private edit: boolean = false;
  private _employmentSectorCode: EmploymentSectorCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<EmploymentSectorCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar) {
  }

set employmentSectorCode(value: EmploymentSectorCode) {
    this._employmentSectorCode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.creatorForm = this.formBuilder.group(<EmploymentSectorCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
    });

    if (this.edit) this.creatorForm.patchValue(this._employmentSectorCode);
  }

  submit(code: EmploymentSectorCode, isValid: boolean) {
    if (confirm('Update employment sector code?')) {
    if (!code.id) this.store.dispatch(this.actions.saveEmploymentSectorCode(code));
    else  this.store.dispatch(this.actions.updateEmploymentSectorCode(code));
    this.dialog.close();
    };
  }
}
