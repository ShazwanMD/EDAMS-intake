import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {ApplicationModuleState} from '../../index';
import {IntakeApplicationActions} from '../intake-application.action';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';
import {Intake} from '../../../../shared/model/policy/intake.interface';
import {ProgramOffering} from '../../../../shared/model/policy/program-offering.interface';
import {Observable} from 'rxjs/Observable';
import {MdDialogRef} from '@angular/material';

@Component({
  selector: 'pams-program-offering-selector',
  templateUrl: './program-offering-selector.dialog.html',
})

export class ProgramOfferingSelectorDialog implements OnInit {

  private PROGRAM_OFFERINGS: string[] = 'applicationModuleState.programOfferings'.split('.');
  private _intake: Intake;
  private _intakeApplication: IntakeApplication;
  private programOfferings$: Observable<ProgramOffering[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private store: Store<ApplicationModuleState>,
              private dialog: MdDialogRef<ProgramOfferingSelectorDialog>) {
    this.programOfferings$ = this.store.select(...this.PROGRAM_OFFERINGS);
  }

  set intake(value: Intake) {
    this._intake = value;
  }

  set intakeApplication(value: IntakeApplication) {
    this._intakeApplication = value;
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findProgramOfferingsByIntake(this._intake));
  }

  select(offering: ProgramOffering) {
    console.log('selecting ' + offering.programCode.code);
    this.store.dispatch(this.actions.selectProgramOffering(this._intakeApplication, offering));
    this.dialog.close();
  }
}
