import {Injectable} from "@angular/core";
import {Action} from '@ngrx/store';
@Injectable()
export class IntakeSessionActions {

  static FIND_INTAKE_SESSIONS = '[IntakeSession] Find Intake Sessions';

  findIntakeSessions(): Action {
    console.log("findIntakeSessions");
    return {
      type: IntakeSessionActions.FIND_INTAKE_SESSIONS,
    };
  }

  static FIND_INTAKE_SESSIONS_SUCCESS = '[IntakeSession] Find Intake Sessions Success';

  findIntakeSessionsSuccess(sessions): Action {
    console.log("findIntakeSessionsSuccess");
    return {
      type: IntakeSessionActions.FIND_INTAKE_SESSIONS_SUCCESS,
      payload: sessions
    };
  }

  static SAVE_INTAKE_SESSIONS = '[IntakeSession] Save Intake Sessions';

  saveIntakeSession(sessions): Action {
    console.log("saveIntakeSession");
    return {
      type: IntakeSessionActions.SAVE_INTAKE_SESSIONS,
    };
  }  

  static SAVE_INTAKE_SESSION_SUCCESS = '[IntakeSession] Save Intake Sessions Success';

  saveIntakeSessionSuccess(sessions): Action {
    console.log("saveIntakeSessionSuccess");
    return {
      type: IntakeSessionActions.SAVE_INTAKE_SESSION_SUCCESS,
      payload: sessions
    };
  }

}
