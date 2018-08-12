import { SetupActions } from './../setup.action';
import { Action } from '@ngrx/store';
import { Staff } from './../../identity/staff.interface';
import { expand } from 'rxjs/operator/expand';

export type UmkceeStaffListState = Staff[];

const initialState: UmkceeStaffListState = <Staff[]>[];

export function umkceeStaffListReducer(state = initialState, action: Action): UmkceeStaffListState {
    switch(action.type){
        case SetupActions.FIND_UMKCEE_STAFFS_SUCCESS: {
            return action.payload;
        }
        default: {
            return state;
        }
    }
}