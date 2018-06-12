import { SetupActions } from './../../secure/setup/setup.action';
import { EmploymentTypeCode } from './../../shared/model/common/employment-type-code.interface';
import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';

export type EmploymentTypeCodeListState = EmploymentTypeCode[];

const initialState: EmploymentTypeCodeListState = <EmploymentTypeCode[]>[];

export function employmentTypeCodeListReducer(state = initialState, action: Action): EmploymentTypeCodeListState {
  switch (action.type) {
    case SetupActions.FIND_EMPLOYMENT_TYPE_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
