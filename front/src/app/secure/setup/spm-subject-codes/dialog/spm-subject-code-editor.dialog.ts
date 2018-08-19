import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {SubjectCode} from '../../../../shared/model/common/subject-code.interface';
import { SpmSubjectCode } from '../../../../shared/model/common/spm-subject-code.interface';

@Component({
  selector: 'pams-spm-subject-code-editor',
  templateUrl: './spm-subject-code-editor.dialog.html',
})

export class SpmSubjectCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _spmSubjectCode: SpmSubjectCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<SpmSubjectCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set spmSubjectCode(value: SpmSubjectCode) {
    this._spmSubjectCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group({
      id: null,
      code: '',
      description: '',
    });

    if (this.edit) this.editorForm.patchValue(this._spmSubjectCode);
  }

  submit(code: SpmSubjectCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveSpmSubjectCode(code));
    else  this.store.dispatch(this.actions.updateSpmSubjectCode(code));
    this.dialog.close();
    
  }
}
