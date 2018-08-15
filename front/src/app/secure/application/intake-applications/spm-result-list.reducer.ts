import { SpmResultCode } from './../../../shared/model/common/spm-result-code.interface';

import {Action} from '@ngrx/store';
import { ApplicationActions } from '../application.action';
import { IntakeApplicationActions } from './intake-application.action';


export type SpmResultCodesListState = SpmResultCode[];

const initialState: SpmResultCodesListState = <SpmResultCode[]>[];

export function spmResultCodesListReducer(state = initialState, action: Action): SpmResultCodesListState {
  switch (action.type) {
    case IntakeApplicationActions.FIND_SPM_RESULT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
