import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {SubjectCode} from '../../../../shared/model/common/subject-code.interface';
import { StpmSubjectCode } from '../../../../shared/model/common/stpm-subject-code.interface';

@Component({
  selector: 'pams-stpm-subject-code-editor',
  templateUrl: './stpm-subject-code-editor.dialog.html',
})

export class StpmSubjectCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _stpmSubjectCode: StpmSubjectCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<StpmSubjectCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set stpmSubjectCode(value: StpmSubjectCode) {
    this._stpmSubjectCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group({
      id: null,
      code: '',
      description: '',
    });

    if (this.edit) this.editorForm.patchValue(this._stpmSubjectCode);
  }

  submit(code: StpmSubjectCode, isValid: boolean) {
    if (confirm('Update stpm subject?')) {
    if (!code.id) this.store.dispatch(this.actions.saveStpmSubjectCode(code));
    else  this.store.dispatch(this.actions.updateStpmSubjectCode(code));
    this.dialog.close();
    };
  }
}
