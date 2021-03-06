import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Store} from '@ngrx/store';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import {IntakeActions} from '../intake.action';
import {PolicyModuleState} from '../../index';
import {SupervisorCode} from '../../../../shared/model/common/supervisor-code.interface';
import {Intake} from '../../../../shared/model/policy/intake.interface';
import {SupervisorOffering} from '../../../../shared/model/policy/supervisor-offering.interface';

@Component({
  selector: 'pams-supervisor-offering-editor',
  templateUrl: './supervisor-offering-editor.dialog.html',
})

export class SupervisorOfferingEditorDialog implements OnInit {

  private editorForm: FormGroup;
  @Input() intake: Intake;

  constructor(private formBuilder: FormBuilder,
              private store: Store<PolicyModuleState>,
              private actions: IntakeActions,
              private dialog: MdDialogRef<SupervisorOfferingEditorDialog>,
              private snackBar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<SupervisorOffering>{
      id: null,
      supervisorCode: <SupervisorCode>{},
    });
  }

  add(supervisor: SupervisorOffering, isValid: boolean) {
    console.log('add supervisor offering');
    this.store.dispatch(this.actions.addSupervisorOffering(this.intake, supervisor));
    this.dialog.close();
  }

}
