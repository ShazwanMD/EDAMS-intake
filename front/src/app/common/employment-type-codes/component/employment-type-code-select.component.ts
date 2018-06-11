import { EmploymentTypeCode } from './../../../shared/model/common/employment-type-code.interface';
import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';

@Component({
  selector: 'pams-employment-type-code-select',
  templateUrl: './employment-type-code-select.component.html',
  styleUrls: ['./employment-type-code-select.scss'],
})
export class EmploymentTypeCodeSelectComponent implements OnInit {

  private EMPLOYMENT_TYPE_CODE: string[] = 'commonModuleState.employmentTypeCodes'.split('.');
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  employmentTypeCodes$: Observable<EmploymentTypeCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.employmentTypeCodes$ = this.store.select(...this.EMPLOYMENT_TYPE_CODE);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findEmploymentTypeCodes());
  }

  selectChangeEvent(event: EmploymentTypeCode): void {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

