import {ParliamentCode} from './../../../common/parliament-codes/parliament-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {SetupModuleState} from "../../index";
import {SetupActions} from "../../setup.action";


@Component({
  selector: 'pams-parliament-code-editor',
  templateUrl: './parliament-code-editor.dialog.html',
})

export class ParliamentCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _parliamentCode: ParliamentCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<ParliamentCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set parliamentCode(value: ParliamentCode) {
    this._parliamentCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<ParliamentCode>{
      id: null,
      code: '',
      description: '',
     });

    if (this.edit) this.editorForm.patchValue(this._parliamentCode);
  }

  save(code: ParliamentCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveParliamentCode(code));
    else  this.store.dispatch(this.actions.updateParliamentCode(code));
    this.dialog.close();
  }
}