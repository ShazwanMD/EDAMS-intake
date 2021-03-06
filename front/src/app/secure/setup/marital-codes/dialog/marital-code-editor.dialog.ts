import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef, MdSnackBar} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {MaritalCode} from '../../../../shared/model/common/marital-code.interface';

@Component({
  selector: 'pams-marital-code-editor',
  templateUrl: './marital-code-editor.dialog.html',
})

export class MaritalCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _maritalCode: MaritalCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<MaritalCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar) {
  }

  set maritalCode(value: MaritalCode) {
    this._maritalCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<MaritalCode>{
      id: null,
      code: '',
      name: '',
      descriptionEn: '',
      descriptionMs: '',
    });

    if (this.edit) this.editorForm.patchValue(this._maritalCode);
  }

  submit(code: MaritalCode, isValid: boolean) {
    if (confirm('Confirm to update marital code?')) {
    if (!code.id) this.store.dispatch(this.actions.saveMaritalCode(code));
    else  this.store.dispatch(this.actions.updateMaritalCode(code));
    this.dialog.close();
    };
  }
}
