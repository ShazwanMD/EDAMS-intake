import { SpmSubjectCode } from './../../shared/model/common/spm-subject-code.interface';
import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';

export type SpmSubjectCodeListState = SpmSubjectCode[];

const initialState: SpmSubjectCodeListState = <SpmSubjectCode[]>[];

export function spmSubjectCodeListReducer(state = initialState, action: Action): SpmSubjectCodeListState {
  switch (action.type) {
    case CommonActions.FIND_SPM_SUBJECT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
