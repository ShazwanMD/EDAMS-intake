import { EmploymentTypeCode } from './../../../shared/model/common/employment-type-code.interface';
import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import { GuardianTypeCode } from '../../../shared/model/common/guardian-type-code.interface';


export type GuardianTypeCodeListState = GuardianTypeCode[];

const initialState: GuardianTypeCodeListState = <GuardianTypeCode[]>[];

export function guardianTypeCodeListReducer(state = initialState, action: Action): GuardianTypeCodeListState {
  switch (action.type) {
    case SetupActions.FIND_GUARDIAN_TYPE_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
