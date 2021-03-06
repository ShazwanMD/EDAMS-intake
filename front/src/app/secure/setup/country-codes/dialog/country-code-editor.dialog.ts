import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {CountryCode} from '../../../../shared/model/common/country-code.interface';

@Component({
  selector: 'pams-country-code-creator',
  templateUrl: './country-code-editor.dialog.html',
})

export class CountryCodeEditorDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _countryCode: CountryCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<CountryCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar) {
  }

  set countryCode(value: CountryCode) {
    this._countryCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<CountryCode>{
      id: null,
      code: '',
      name: '',
      descriptionMs: '',
      descriptionEn: '',
     });

    if (this.edit) this.createForm.patchValue(this._countryCode);
  }

  save(code: CountryCode, isValid: boolean) {
    if (confirm('Update country code?')) {
    if (!code.id) this.store.dispatch(this.actions.saveCountryCode(code));
    else  this.store.dispatch(this.actions.updateCountryCode(code));
    this.dialog.close();
    };
  }
}
