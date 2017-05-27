import { Referee } from './../../referee.interface';
import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {ApplicationModuleState} from "../../../index";
import {MdDialogRef} from "@angular/material";
import {IntakeApplicationActions} from "../../intake-application.action";
import {IntakeApplication} from "../../intake-application.interface";
import {Intake} from "../../../../policy/intakes/intake.interface";
import {ProgramOffering} from "../../../../policy/intakes/program-offering.interface";
import {Observable} from "rxjs/Observable";



@Component({
  selector: 'pams-program-offering-selector',
  templateUrl: './program-offering-selector.dialog.html',
})

export class ProgramOfferingSelectorDialog implements OnInit {

  private PROGRAM_OFFERINGS: string[] = "applicationModuleState.programOfferings".split(".");
  private _intake: Intake;
  private _intakeApplication: IntakeApplication;
  private programOfferings$: Observable<ProgramOffering[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private store: Store<ApplicationModuleState>) {
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
    console.log("selecting " + offering.programCode.code);
  }
}
