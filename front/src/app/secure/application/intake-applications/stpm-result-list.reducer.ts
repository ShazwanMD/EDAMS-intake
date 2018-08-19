import { StpmResultCode } from './../../../shared/model/common/stpm-result-code.interface';
import {Action} from '@ngrx/store';
import { ApplicationActions } from '../application.action';
import { IntakeApplicationActions } from './intake-application.action';


export type StpmResultCodesListState = StpmResultCode[];

const initialState: StpmResultCodesListState = <StpmResultCode[]>[];

export function stpmResultCodesListReducer(state = initialState, action: Action): StpmResultCodesListState {
  switch (action.type) {
    case IntakeApplicationActions.FIND_STPM_RESULT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
