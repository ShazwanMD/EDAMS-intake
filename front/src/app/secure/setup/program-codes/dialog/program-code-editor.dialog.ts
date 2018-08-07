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
  selector: 'pams-program-code-editor',
  templateUrl: './program-code-editor.dialog.html',
})

export class ProgramCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _programCode: ProgramCode;
  private _facultyCode: FacultyCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<ProgramCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,
              private snackBar: MdSnackBar, ) {
  }

  set programCode(value: ProgramCode) {
    this._programCode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.editorForm = this.formBuilder.group(<ProgramCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
      programLevel: <ProgramLevel>{},
      graduateCenter: <GraduateCenter>{},
      facultyCode: <FacultyCode>{},


    });

    if (this.edit) this.editorForm.patchValue(this._programCode);
  }

  submit(code: ProgramCode, isValid: boolean) {
    if (confirm('Confirm to update program code?')) {
    if (!code.id) this.store.dispatch(this.actions.saveProgramCode(code));
    else  this.store.dispatch(this.actions.updateProgramCode(code));
    this.dialog.close();
    };
  }
}
