import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {CommonActions} from "../../common.action";
import { CommonModuleState } from "../../index";
import { ResidencyCode } from "../residency-code.interface";

@Component({
  selector: 'pams-residency-code-select',
  templateUrl: './residency-code-select.component.html',
})
export class ResidencyCodeSelectComponent implements OnInit {

  private RESIDENCY_CODE = "commonModuleState.residencyCodes".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  residencyCodes$: Observable<ResidencyCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.residencyCodes$ = this.store.select(...this.RESIDENCY_CODE);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findResidencyCodes());
  }

  selectChangeEvent(event: ResidencyCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

