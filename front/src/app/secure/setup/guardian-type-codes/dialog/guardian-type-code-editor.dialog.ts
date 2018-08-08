import { EmploymentSectorCode } from './../../../../shared/model/common/employment-sector-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import { GuardianTypeCode } from '../../../../shared/model/common/guardian-type-code.interface';

@Component({
  selector: 'pams-guardian-type-code-creator',
  templateUrl: './guardian-type-code-editor.dialog.html',
})

export class GuardianTypeCodeEditorDialog implements OnInit {

  private creatorForm: FormGroup;
  private edit: boolean = false;
  private _guardianTypeCode: GuardianTypeCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<GuardianTypeCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar) {
  }

set guardianTypeCode(value: GuardianTypeCode) {
    this._guardianTypeCode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.creatorForm = this.formBuilder.group(<GuardianTypeCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
    });

    if (this.edit) this.creatorForm.patchValue(this._guardianTypeCode);
  }

  submit(code: GuardianTypeCode, isValid: boolean) {
    if (confirm('Update guardian type code?')) {
    if (!code.id) this.store.dispatch(this.actions.saveGuardianTypeCode(code));
    else  this.store.dispatch(this.actions.updateGuardianTypeCode(code));
    this.dialog.close();
    };
  }
}
