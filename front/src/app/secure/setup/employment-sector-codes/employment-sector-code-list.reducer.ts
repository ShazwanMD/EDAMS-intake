import { EmploymentSectorCode } from './../../../shared/model/common/employment-sector-code.interface';
import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';

export type EmploymentSectorCodeListState = EmploymentSectorCode[];

const initialState: EmploymentSectorCodeListState = <EmploymentSectorCode[]>[];

export function employmentSectorCodeListReducer(state = initialState, action: Action): EmploymentSectorCodeListState {
  switch (action.type) {
    case SetupActions.FIND_EMPLOYMENT_SECTOR_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
