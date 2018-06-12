import { EmploymentSectorCode } from './../../shared/model/common/employment-sector-code.interface';
import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';

export type EmploymentSectorCodeListState = EmploymentSectorCode[];

const initialState: EmploymentSectorCodeListState = <EmploymentSectorCode[]>[];

export function employmentSectorCodeListReducer(state = initialState, action: Action): EmploymentSectorCodeListState {
  switch (action.type) {
    case CommonActions.FIND_EMPLOYMENT_SECTOR_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
