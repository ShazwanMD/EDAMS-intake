import { Action } from '@ngrx/store';
import { StpmSubjectCode } from "../../../shared/model/common/stpm-subject-code.interface";
import { SetupActions } from '../setup.action';



export type StpmSubjectCodeListState = StpmSubjectCode[];

const initialState: StpmSubjectCodeListState = <StpmSubjectCode[]>[];

export function stpmSubjectCodeListReducer(state = initialState, action: Action): StpmSubjectCodeListState {
  switch (action.type) {
    case SetupActions.FIND_STPM_SUBJECT_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
