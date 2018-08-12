import { Staff } from './../../../identity/staff.interface';
import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef, MdSnackBar, } from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {ProgramCode} from '../../../../shared/model/common/program-code.interface';
import {ProgramLevel} from '../../../../shared/model/policy/program-level.interface';
import {FacultyCode} from '../../../../shared/model/common/faculty-code.interface';
import {GraduateCenter} from '../../../../shared/model/common/graduate-center.interface';

@Component({
  selector: 'pams-umkcee-staff-editor',
  templateUrl: './umkcee-staff-editor.dialog.html',
})

export class UMKCEEStaffEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _staff: Staff;
  private _facultyCode: FacultyCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<UMKCEEStaffEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar, ) {
  }

  set staff(value: Staff) {
    this._staff = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.editorForm = this.formBuilder.group({
      id: null,
      identityNo: '',
      name: '',
      email: '',
      mobile: '',
      staffCategory: '',
      facultyCode: <FacultyCode>{},
    });

    if (this.edit) this.editorForm.patchValue(this._staff);
  }

  submit(staff: Staff, isValid: boolean) {
    if (confirm('Daftar Staf?')) {
    if (!staff.id) this.store.dispatch(this.actions.saveUMKCEEStaffs(staff));
    else  this.store.dispatch(this.actions.updateUMKCEEStaffs(staff));
    this.dialog.close();
    };
  }
}
