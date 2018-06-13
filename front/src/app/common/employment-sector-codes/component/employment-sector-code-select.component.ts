import { EmploymentSectorCode } from './../../../shared/model/common/employment-sector-code.interface';
import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';

@Component({
  selector: 'pams-employment-sector-code-select',
  templateUrl: './employment-sector-code-select.component.html',
  styleUrls: ['./employment-sector-code-select.scss'],
})
export class EmploymentSectorCodeSelectComponent implements OnInit {

  private EMPLOYMENT_SECTOR_CODE: string[] = 'commonModuleState.employmentSectorCodes'.split('.');
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  employmentSectorCodes$: Observable<EmploymentSectorCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.employmentSectorCodes$ = this.store.select(...this.EMPLOYMENT_SECTOR_CODE);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findEmploymentSectorCodes());
  }

  selectChangeEvent(event: EmploymentSectorCode): void {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

