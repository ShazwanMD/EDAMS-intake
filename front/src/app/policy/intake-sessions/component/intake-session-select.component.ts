import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {IntakeSession} from "../intake-session.interface";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {IntakeSessionActions} from "../intake-session.action";
import {PolicyModuleState} from "../../index";


@Component({
  selector: 'pams-intake-session-select',
  templateUrl: './intake-session-select.component.html',
  styleUrls: ['./intake-session-select.scss'],
})
export class IntakeSessionSelectComponent implements OnInit {

  private INTAKE_SESSIONS = "policyModuleState.intakeSessions".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  intakeSessions$: Observable<IntakeSession[]>;

  constructor(private store: Store<PolicyModuleState>,
              private actions: IntakeSessionActions) {
    this.intakeSessions$ = this.store.select(...this.INTAKE_SESSIONS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findIntakeSessions());
  }

  selectChangeEvent(event: IntakeSession) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
