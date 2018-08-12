import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import { SpmResultCode } from '../../../shared/model/common/spm-result-code.interface';

@Component({
  selector: 'pams-spm-result-code-select',
  templateUrl: './spm-result-code-select.component.html',
  styleUrls: ['./spm-result-code-select.scss'],
})
export class SpmResultCodeSelectComponent implements OnInit {

  private SPM_RESULT_CODES: string[] = 'commonModuleState.spmResultCodes'.split('.');
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  spmResultCodes$: Observable<SpmResultCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.spmResultCodes$ = this.store.select(...this.SPM_RESULT_CODES);
  }

  ngOnInit() {
   
  }

  selectChangeEvent(event: SpmResultCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
