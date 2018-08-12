import { SpmResultCode } from './../../shared/model/common/spm-result-code.interface';
import {Action} from '@ngrx/store';
import {ProgramFieldCode} from '../../shared/model/common/program-field-code.interface';
import {CommonActions} from '../common.action';

export type SpmResultCodeListState = SpmResultCode[];

const initialState: SpmResultCodeListState = <SpmResultCode[]>[];

export function spmResultCodeListReducer(state = initialState, action: Action): SpmResultCodeListState {
  switch (action.type) {
    case CommonActions.FIND_SPM_RESULT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
