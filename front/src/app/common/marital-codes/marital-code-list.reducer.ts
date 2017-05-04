import {Action} from '@ngrx/store';
import {MaritalCode} from "./marital-code.interface";
import {CommonActions} from "../common.action";

export type MaritalCodeListState = MaritalCode[];

const initialState: MaritalCodeListState = <MaritalCode[]>[];

export function maritalCodeListReducer(state = initialState, action: Action): MaritalCodeListState {
  switch (action.type) {
    case CommonActions.FIND_MARITAL_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
