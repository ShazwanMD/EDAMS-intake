import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';
import {FieldCode} from '../../shared/model/common/field-code.interface';

export type FieldCodeListState = FieldCode[];

const initialState: FieldCodeListState = <FieldCode[]>[];

export function fieldCodeListReducer(state = initialState, action: Action): FieldCodeListState {
  switch (action.type) {
    case CommonActions.FIND_FIELD_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
