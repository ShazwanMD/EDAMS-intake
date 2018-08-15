import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {SubjectCode} from '../../../shared/model/common/subject-code.interface';
import { SpmSubjectCode } from '../../../shared/model/common/spm-subject-code.interface';

@Component({
  selector: 'pams-spm-subject-code-select',
  templateUrl: './spm-subject-code-select.component.html',
  styleUrls: ['./spm-subject-code-select.scss'],
})
export class SpmSubjectCodeSelectComponent implements OnInit {

  private SPM_SUBJECT_CODE: string[] = 'commonModuleState.spmSubjectCodes'.split('.');
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  spmSubjectCodes$: Observable<SpmSubjectCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.spmSubjectCodes$ = this.store.select(...this.SPM_SUBJECT_CODE);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findSpmSubjectCodes());
  }

  selectChangeEvent(event: SpmSubjectCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

