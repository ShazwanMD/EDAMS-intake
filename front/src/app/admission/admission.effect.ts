import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {AdmissionActions} from './admission.action';
import {AdmissionService} from '../../services/admission.service';
import {from} from "rxjs/observable/from";

@Injectable()
export class AdmissionEffects {

  private INTAKE_TASKS: string[] = 'admissionModuleState.intakeTasks'.split('.');
  private CANDIDATES: string[] = 'admissionModuleState.candidates'.split('.');

  constructor(private actions$: Actions,
              private admissionActions: AdmissionActions,
              private admissionService: AdmissionService) {
  }

  @Effect() findAssignedIntakeTasks$ = this.actions$
    .ofType(AdmissionActions.FIND_ASSIGNED_INTAKE_TASKS)
    .switchMap(() => this.admissionService.findAssignedIntakeTasks())
    .map((intakeTasks) => this.admissionActions.findAssignedIntakeTasksSuccess(intakeTasks));

  @Effect() findPooledIntakeTasks$ = this.actions$
    .ofType(AdmissionActions.FIND_POOLED_INTAKE_TASKS)
    .switchMap(() => this.admissionService.findPooledIntakeTasks())
    .map((admissions) => this.admissionActions.findPooledIntakeTasksSuccess(admissions));

  @Effect() findIntakeTaskByTaskId = this.actions$
    .ofType(AdmissionActions.FIND_INTAKE_TASK_BY_TASK_ID)
    .map((action) => action.payload)
    .switchMap((taskId) => this.admissionService.findIntakeTaskByTaskId(taskId))
    .map((task) => this.admissionActions.findIntakeTaskByTaskIdSuccess(task))
    .mergeMap((action) => from([action,
                             this.admissionActions.findCandidates(action.payload.intake),
                             this.admissionActions.findSelectedCandidates(action.payload.intake),
                             this.admissionActions.findRejectedCandidates(action.payload.intake),
                           ]));

  @Effect() findCandidates = this.actions$
    .ofType(AdmissionActions.FIND_CANDIDATES)
    .map(action => action.payload)
    .switchMap((intake) => this.admissionService.findCandidates(intake))
    .map((candidates) => this.admissionActions.findCandidatesSuccess(candidates));

  @Effect() findSelectedCandidates = this.actions$
    .ofType(AdmissionActions.FIND_SELECTED_CANDIDATES)
    .map(action => action.payload)
    .switchMap(intake => this.admissionService.findSelectedCandidates(intake))
    .map(candidates => this.admissionActions.findSelectedCandidatesSuccess(candidates));

  @Effect() findRejectedCandidates = this.actions$
    .ofType(AdmissionActions.FIND_REJECTED_CANDIDATES)
    .map(action => action.payload)
    .switchMap(intake => this.admissionService.findRejectedCandidates(intake))
    .map(candidates => this.admissionActions.findRejectedCandidatesSuccess(candidates));
}
