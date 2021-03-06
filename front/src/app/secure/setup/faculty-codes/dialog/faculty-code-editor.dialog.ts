import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef, MdSnackBar } from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {FacultyCode} from '../../../../shared/model/common/faculty-code.interface';

@Component({
  selector: 'pams-faculty-code-creator',
  templateUrl: './faculty-code-editor.dialog.html',
})

export class FacultyCodeEditorDialog implements OnInit {

  private creatorForm: FormGroup;
  private edit: boolean = false;
  private _facultyCode: FacultyCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<FacultyCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar) {
  }

  set facultyCode(value: FacultyCode) {
    this._facultyCode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.creatorForm = this.formBuilder.group(<FacultyCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
      prefix: '',
    });

    if (this.edit) this.creatorForm.patchValue(this._facultyCode);
  }

  submit(code: FacultyCode, isValid: boolean) {
    if (confirm('Confirm to update Faculty code?')){
    if (!code.id) this.store.dispatch(this.actions.saveFacultyCode(code));
    else  this.store.dispatch(this.actions.updateFacultyCode(code));
    this.dialog.close();
    };
  }
}
