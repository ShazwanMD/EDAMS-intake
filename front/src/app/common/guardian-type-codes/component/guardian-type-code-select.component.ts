import { EmploymentTypeCode } from './../../../shared/model/common/employment-type-code.interface';
import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import { GuardianTypeCode } from '../../../shared/model/common/guardian-type-code.interface';

@Component({
  selector: 'pams-guardian-type-code-select',
  templateUrl: './guardian-type-code-select.component.html',
  styleUrls: ['./guardian-type-code-select.scss'],
})
export class GuardianTypeCodeSelectComponent implements OnInit {

  private GUARDIAN_TYPE_CODE: string[] = 'commonModuleState.guardianTypeCodes'.split('.');
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  guardianTypeCodes$: Observable<GuardianTypeCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.guardianTypeCodes$ = this.store.select(...this.GUARDIAN_TYPE_CODE);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findGuardianTypeCodes());
  }

  selectChangeEvent(event: GuardianTypeCode): void {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

