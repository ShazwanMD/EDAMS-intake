
import {Action} from '@ngrx/store';
import { DiplomaResultCode } from '../../../shared/model/application/diploma-result.interface';
import { IntakeApplicationActions } from './intake-application.action';


export type DiplomaResultCodesListState = DiplomaResultCode[];

const initialState: DiplomaResultCodesListState = <DiplomaResultCode[]>[];

export function diplomaResultCodesListReducer(state = initialState, action: Action): DiplomaResultCodesListState {
  switch (action.type) {
    case IntakeApplicationActions.FIND_DIPLOMA_RESULT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
