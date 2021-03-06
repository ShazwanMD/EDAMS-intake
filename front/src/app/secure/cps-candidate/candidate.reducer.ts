import {Action} from '@ngrx/store';
import { Candidate } from './../../shared/model/admission/candidate.interface';
import { AdmissionCandidateActions } from './admission-candidate.action';

export type CandidatesState = Candidate;

const initialState: CandidatesState = <CandidatesState>{};

export function candidateAdmissionReducer(state = initialState, action: Action): CandidatesState {
  switch (action.type) {
    case AdmissionCandidateActions.FIND_CANDIDATE_BY_ID_SUCCESS: {
        console.log("find candidate reducer" + action.payload)
      return action.payload;
    }
    case AdmissionCandidateActions.FIND_CANDIDATE_BY_REFERENCE_NO_SUCCESS: {
        console.log("find candidate" + action.payload)
      return action.payload;
    }
    case AdmissionCandidateActions.FIND_CANDIDATE_BY_INTAKE_REFERENCE_NO_SUCCESS: {
        console.log("find candidate" + action.payload)
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
