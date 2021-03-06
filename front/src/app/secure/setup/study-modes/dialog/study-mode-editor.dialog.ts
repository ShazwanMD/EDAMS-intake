import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {StudyMode} from '../../../../shared/model/common/study-mode.interface';

@Component({
  selector: 'pams-study-mode-editor',
  templateUrl: './study-mode-editor.dialog.html',
})

export class StudyModeEditorDialog implements OnInit {

  private creatorForm: FormGroup;
  private edit: boolean = false;
  private _studyMode: StudyMode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<StudyModeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set studyMode(value: StudyMode) {
    this._studyMode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.creatorForm = this.formBuilder.group(<StudyMode>{
      id: undefined,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
      prefix: '',
     });

    if (this.edit) this.creatorForm.patchValue(this._studyMode);
  }

    save(code: StudyMode, isValid: boolean) {
      if (confirm('Update study mode?')) {
    if (!code.id) this.store.dispatch(this.actions.saveStudyMode(code));
    else  this.store.dispatch(this.actions.updateStudyMode(code));
    this.dialog.close();
    };
  }
}
