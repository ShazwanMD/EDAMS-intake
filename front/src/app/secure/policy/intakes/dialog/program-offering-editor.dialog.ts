import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef, MdSnackBar} from '@angular/material';
import {IntakeActions} from '../intake.action';
import {PolicyModuleState} from '../../index';
import {ProgramCode} from '../../../../shared/model/common/program-code.interface';
import {Intake} from '../../../../shared/model/policy/intake.interface';
import {ProgramOffering} from '../../../../shared/model/policy/program-offering.interface';
import { ProgramFieldCode } from "../../../../shared/model/common/program-field-code.interface";

@Component({
  selector: 'pams-program-offering-editor',
  templateUrl: './program-offering-editor.dialog.html',
})

export class ProgramOfferingEditorDialog implements OnInit {

  private editorForm: FormGroup;
  @Input() intake: Intake;

  constructor(private formBuilder: FormBuilder,
              private store: Store<PolicyModuleState>,
              private actions: IntakeActions,
              private dialog: MdDialogRef<ProgramOfferingEditorDialog>,
              private snackBar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<ProgramOffering>{
      id: null,
      projection: 0,
      interview: true,
      generalCriteria: '',
      specificCriteria: '',
      programCode: <ProgramCode>{},
    });
  }

  add(offering: ProgramOffering, isValid: boolean) {
    console.log('add program offering');
    this.store.dispatch(this.actions.addProgramOffering(this.intake, offering));
    this.dialog.close();
  }
}
