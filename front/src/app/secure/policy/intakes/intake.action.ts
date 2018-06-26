import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';
import {Intake} from '../../../shared/model/policy/intake.interface';
import {ProgramOffering} from '../../../shared/model/policy/program-offering.interface';
import {SupervisorOffering} from '../../../shared/model/policy/supervisor-offering.interface';
import {StudyModeOffering} from '../../../shared/model/policy/study-mode-offering.interface';

@Injectable()
export class IntakeActions {

  static FIND_ARCHIVED_INTAKES = '[Intake] Find Archived Intakes';

  findArchivedIntakes(): Action {
    return {
      type: IntakeActions.FIND_ARCHIVED_INTAKES,
    };
  }

  static FIND_ARCHIVED_INTAKES_SUCCESS = '[Intake] Find Archived Intakes Success';

  findArchivedIntakesSuccess(intakes): Action {
    console.log('findArchivedIntakesSuccess');
    return {
      type: IntakeActions.FIND_ARCHIVED_INTAKES_SUCCESS,
      payload: intakes,
    };
  }


  static FIND_ASSIGNED_INTAKE_TASKS = '[Intake] Find Assigned Intake Tasks';

  findAssignedIntakeTasks(): Action {
    return {
      type: IntakeActions.FIND_ASSIGNED_INTAKE_TASKS,
    };
  }

  static FIND_ASSIGNED_INTAKE_TASKS_SUCCESS = '[Intake] Find Assigned Intake Tasks Success';

  findAssignedIntakeTasksSuccess(tasks): Action {
    console.log('findAssignedIntakeTasksSuccess');
    return {
      type: IntakeActions.FIND_ASSIGNED_INTAKE_TASKS_SUCCESS,
      payload: tasks,
    };
  }

  static FIND_POOLED_INTAKE_TASKS = '[Intake] Find Pooled Intake Tasks';

  findPooledIntakeTasks(): Action {
    return {
      type: IntakeActions.FIND_POOLED_INTAKE_TASKS,
    };
  }

  static FIND_POOLED_INTAKE_TASKS_SUCCESS = '[Intake] Find Pooled Intake Tasks Success';

  findPooledIntakeTasksSuccess(tasks): Action {
    console.log('findAssignedIntakeTasksSuccess');
    return {
      type: IntakeActions.FIND_POOLED_INTAKE_TASKS_SUCCESS,
      payload: tasks,
    };
  }

  static FIND_INTAKE_TASK_BY_TASK_ID = '[Intake] Find Intake Task By Task Id';

  findIntakeTaskByTaskId(taskId): Action {
    console.log('findIntakeTaskByTaskId');
    return {
      type: IntakeActions.FIND_INTAKE_TASK_BY_TASK_ID,
      payload: taskId,
    };
  }

  static FIND_INTAKE_TASK_BY_TASK_ID_SUCCESS = '[Intake] Find Intake Task By Task Id Success';

  findIntakeTaskByTaskIdSuccess(task): Action {
    console.log('findIntakeTaskByTaskIdSuccess');
    return {
      type: IntakeActions.FIND_INTAKE_TASK_BY_TASK_ID_SUCCESS,
      payload: task,
    };
  }

  static START_INTAKE_TASK = '[Intake] Start Intake Task';

  startIntakeTask(intake): Action {
    return {
      type: IntakeActions.START_INTAKE_TASK,
      payload: intake,
    };
  }

  static START_INTAKE_TASK_SUCCESS = '[Intake] Start Intake Task Success';

  startIntakeTaskSuccess(task): Action {
    return {
      type: IntakeActions.START_INTAKE_TASK_SUCCESS,
      payload: task,
    };
  }

  static PROCESS_INTAKE_CANDIDATE = '[Intake] Process Intake Candidate';

  processIntakeCandidate(intake): Action {
    return {
      type: IntakeActions.PROCESS_INTAKE_CANDIDATE,
      payload: intake,
    };
  }

  static PROCESS_INTAKE_CANDIDATE_SUCCESS = '[Intake] Process Intake Candidate Success';

  processIntakeCandidateSuccess(task): Action {
    return {
      type: IntakeActions.PROCESS_INTAKE_CANDIDATE_SUCCESS,
      payload: task,
    };
  }

  static COMPLETE_INTAKE_TASK = '[Intake] Complete Intake Task';

  completeIntakeTask(intake): Action {
    return {
      type: IntakeActions.COMPLETE_INTAKE_TASK,
      payload: intake,
    };
  }

  static COMPLETE_INTAKE_TASK_SUCCESS = '[Intake] Complete Intake Task Success';

  completeIntakeTaskSuccess(task): Action {
    return {
      type: IntakeActions.COMPLETE_INTAKE_TASK_SUCCESS,
      payload: task,
    };
  }

  static REMOVE_INTAKE_TASK = '[Intake] Remove Intake Task';

  removeIntakeTask(intake): Action {
    return {
      type: IntakeActions.REMOVE_INTAKE_TASK,
      payload: intake,
    };
  }

  static REMOVE_INTAKE_TASK_SUCCESS = '[Intake] Remove Intake Task Success';

  removeIntakeTaskSuccess(task): Action {
    return {
      type: IntakeActions.REMOVE_INTAKE_TASK_SUCCESS,
      payload: task,
    };
  }

  static COPY_INTAKE_TASK = '[Intake] Copy Intake Task';

  copyIntakeTask(intake): Action {
    return {
      type: IntakeActions.COPY_INTAKE_TASK,
      payload: intake,
    };
  }

  static COPY_INTAKE_TASK_SUCCESS = '[Intake] Copy Intake Task Success';

  copyIntakeTaskSuccess(task): Action {
    return {
      type: IntakeActions.COPY_INTAKE_TASK_SUCCESS,
      payload: task,
    };
  }

  static CLAIM_INTAKE_TASK = '[Intake] Assign Intake Task';

  claimIntakeTask(intake): Action {
    return {
      type: IntakeActions.CLAIM_INTAKE_TASK,
      payload: intake,
    };
  }

  static CLAIM_INTAKE_TASK_SUCCESS = '[Intake] Assign Intake Task Success';

  claimIntakeTaskSuccess(task): Action {
    return {
      type: IntakeActions.CLAIM_INTAKE_TASK_SUCCESS,
      payload: task,
    };
  }

  static RELEASE_INTAKE_TASK = '[Intake] Release Intake Task';

  releaseIntakeTask(intake): Action {
    return {
      type: IntakeActions.RELEASE_INTAKE_TASK,
      payload: intake,
    };
  }

  static RELEASE_INTAKE_TASK_SUCCESS = '[Intake] Release Intake Task Success';

  releaseIntakeTaskSuccess(task): Action {
    return {
      type: IntakeActions.RELEASE_INTAKE_TASK_SUCCESS,
      payload: task,
    };
  }

  static FIND_INTAKE_BY_ID = '[Intake] Find Intake By Id';

  findIntakeById(id): Action {
    return {
      type: IntakeActions.FIND_INTAKE_BY_ID,
      payload: id,
    };
  }

  static FIND_INTAKE_BY_ID_SUCCESS = '[Intake] Find Intake By Id Success';

  findIntakeByIdSuccess(intake): Action {
    return {
      type: IntakeActions.FIND_INTAKE_BY_ID_SUCCESS,
      payload: intake,
    };
  }

  static FIND_INTAKE_BY_REFERENCE_NO = '[Intake] Find Intake By Reference No';

  findIntakeByReferenceNo(referenceNo): Action {
    return {
      type: IntakeActions.FIND_INTAKE_BY_REFERENCE_NO,
      payload: referenceNo,
    };
  }

  static FIND_INTAKE_BY_REFERENCE_NO_SUCCESS = '[Intake] Find Intake By Reference No Success';

  findIntakeByReferenceNoSuccess(intake): Action {
    return {
      type: IntakeActions.FIND_INTAKE_BY_REFERENCE_NO_SUCCESS,
      payload: intake,
    };
  }

  static FIND_INTAKE_BY_REFERENCE_NO_AND_BID_STATUS = '[Intake] Find Intake By Reference No And Bid Status';

  findIntakeByReferenceNoAndBidStatus(referenceNo): Action {
      console.log ('findIntakeByReferenceNoAndBidStatus :' + referenceNo);
      return {
      type: IntakeActions.FIND_INTAKE_BY_REFERENCE_NO_AND_BID_STATUS,
      payload: referenceNo,
    };
  }

  static FIND_INTAKE_BY_REFERENCE_NO_AND_BID_STATUS_SUCCESS = '[Intake] Find Intake By Reference No And Bid Status Success';

  findIntakeByReferenceNoAndBidStatusSuccess(intake): Action {
      console.log ('findIntakeByReferenceNoAndBidSuccess :' + intake);
      return {
      type: IntakeActions.FIND_INTAKE_BY_REFERENCE_NO_AND_BID_STATUS_SUCCESS,
      payload: intake,
    };
  }

  static FIND_PROGRAM_OFFERINGS = '[Intake] Find Program Offerings';

  findProgramOfferings(intake): Action {
    console.log('findProgramOfferings :' + intake);
    return {
      type: IntakeActions.FIND_PROGRAM_OFFERINGS,
      payload: intake,
    };
  }

  static FIND_PROGRAM_OFFERINGS_SUCCESS = '[Intake] Find Program Offerings Success';

  findProgramOfferingsSuccess(items): Action {
    console.log('findProgramOfferingsSuccess');
    return {
      type: IntakeActions.FIND_PROGRAM_OFFERINGS_SUCCESS,
      payload: items,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS = '[Intake] Find Supervisor Offerings';

  findSupervisorOfferings(intake): Action {
    return {
      type: IntakeActions.FIND_SUPERVISOR_OFFERINGS,
      payload: intake,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS_SUCCESS = '[Intake] Find Supervisor Offering Success';

  findSupervisorOfferingsSuccess(offerings): Action {
    console.log('findSupervisorOfferingsSuccess');
    return {
      type: IntakeActions.FIND_SUPERVISOR_OFFERINGS_SUCCESS,
      payload: offerings,
    };
  }

  static FIND_STUDY_MODE_OFFERINGS = '[Intake] Find Study Mode Offerings';

  findStudyModeOfferings(intake): Action {
    return {
      type: IntakeActions.FIND_STUDY_MODE_OFFERINGS,
      payload: intake,
    };
  }

  static FIND_STUDY_MODE_OFFERINGS_SUCCESS = '[Intake] Find Study Mode Offering Success';

  findStudyModeOfferingsSuccess(offerings): Action {
    console.log('findStudyModeOfferingsSuccess');
    return {
      type: IntakeActions.FIND_STUDY_MODE_OFFERINGS_SUCCESS,
      payload: offerings,
    };
  }

  static FIND_INTAKE_APPLICATIONS_BY_INTAKE = '[Intake] Find Intake Application By Intake ';

  findIntakeApplications(intake): Action {
    return {
      type: IntakeActions.FIND_INTAKE_APPLICATIONS_BY_INTAKE,
      payload: intake,
    };
  }

  static FIND_INTAKE_APPLICATIONS_INTAKE_SUCCESS = '[Intake] Find Intake Application By Intake Success';

  findIntakeApplicationsByIntakeSuccess(applications): Action {
    console.log('findIntakeApplicationsByIntakeSuccess');
    return {
      type: IntakeActions.FIND_INTAKE_APPLICATIONS_INTAKE_SUCCESS,
      payload: applications,
    };
  }

  static FIND_SUBMITTED_INTAKE_APPLICATIONS = '[Intake] Find SUBMITTED INTAKE APPLICATIONS ';

  findSubmittedIntakeApplications(intake): Action {
      console.log('findSubmittedIntakeApplications :' + intake);
      return {
      type: IntakeActions.FIND_SUBMITTED_INTAKE_APPLICATIONS,
      payload: intake,
    };
  }

  static FIND_SUBMITTED_INTAKE_APPLICATIONS_SUCCESS = '[Intake] Find SUBMITTED Intake Applications Success';

  findSubmittedIntakeApplicationsSuccess(applications): Action {
    console.log('findSubmittedIntakeApplicationsSuccess');
    return {
      type: IntakeActions.FIND_SUBMITTED_INTAKE_APPLICATIONS_SUCCESS,
      payload: applications,
    };
  }

  static FIND_SELECTED_INTAKE_APPLICATIONS = '[Intake] Find SELECTED INTAKE APPLICATION ';

  findSelectedIntakeApplications(intake): Action {
      console.log('findSelectedIntakeApplications :' + intake);
      return {
      type: IntakeActions.FIND_SELECTED_INTAKE_APPLICATIONS,
      payload: intake,
    };
  }

  static FIND_SELECTED_INTAKE_APPLICATIONS_SUCCESS = '[Intake] Find SELECTED Intake Application Success';

  findSelectedIntakeApplicationsSuccess(applications): Action {
    console.log('FIND_SELECTED_INTAKE_APPLICATIONS_SUCCESS');
    return {
      type: IntakeActions.FIND_SELECTED_INTAKE_APPLICATIONS_SUCCESS,
      payload: applications,
    };
  }

  static FIND_EVALUATED_INTAKE_APPLICATIONS = '[Intake] Find EVALUATED INTAKE APPLICATION ';

  findEvaluatedIntakeApplications(intake): Action {
      console.log('findEvaluatedIntakeApplications :' + intake);
      return {
      type: IntakeActions.FIND_EVALUATED_INTAKE_APPLICATIONS,
      payload: intake,
    };
  }

  static FIND_EVALUATED_INTAKE_APPLICATIONS_SUCCESS = '[Intake] Find EVALUATED Intake Application Success';

  findEvaluatedIntakeApplicationsSuccess(applications): Action {
    console.log('findEvaluatedIntakeApplicationsSuccess');
    return {
      type: IntakeActions.FIND_EVALUATED_INTAKE_APPLICATIONS_SUCCESS,
      payload: applications,
    };
  }

  static FIND_REJECTED_INTAKE_APPLICATIONS = '[Intake] Find REJECTED INTAKE APPLICATION ';

  findRejectedIntakeApplications(intake): Action {
      console.log('findRejectedIntakeApplications :' + intake);
      return {
      type: IntakeActions.FIND_REJECTED_INTAKE_APPLICATIONS,
      payload: intake,
    };
  }

  static FIND_REJECTED_INTAKE_APPLICATIONS_SUCCESS = '[Intake] Find REJECTED Intake Application Success';

  findRejectedIntakeApplicationsSuccess(applications): Action {
    console.log('findRejectedIntakeApplicationsSuccess');
    return {
      type: IntakeActions.FIND_REJECTED_INTAKE_APPLICATIONS_SUCCESS,
      payload: applications,
    };
  }

  static FIND_VERIFIED_INTAKE_APPLICATIONS = '[Intake] Find VERIFIED INTAKE APPLICATION ';

  findVerifiedIntakeApplications(intake): Action {
      console.log('findVerifieddIntakeApplications :' + intake);
      return {
      type: IntakeActions.FIND_VERIFIED_INTAKE_APPLICATIONS,
      payload: intake,
    };
  }

  static FIND_VERIFIED_INTAKE_APPLICATIONS_SUCCESS = '[Intake] Find VERIFIED Intake Application Success';

  findVerifiedIntakeApplicationsSuccess(applications): Action {
    console.log('findVerifieddIntakeApplications');
    return {
      type: IntakeActions.FIND_VERIFIED_INTAKE_APPLICATIONS_SUCCESS,
      payload: applications,
    };
  }

  static UPDATE_INTAKE = '[Intake] Update Intake';

  updateIntake(intake): Action {
    return {
      type: IntakeActions.UPDATE_INTAKE,
      payload: intake,
    };
  }

  static UPDATE_INTAKE_SUCCESS = '[Intake] Update Intake Success';

  updateIntakeSuccess(message): Action {
      console.log('updateIntakeSuccess');
      return {
      type: IntakeActions.UPDATE_INTAKE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_INTAKE = '[Intake] Remove Intake';

  removeIntake(intake): Action {
    return {
      type: IntakeActions.REMOVE_INTAKE,
      payload: intake,
    };
  }

  static REMOVE_INTAKE_SUCCESS = '[Intake] Remove Intake Success';

  removeIntakeSuccess(intake): Action {
    return {
      type: IntakeActions.REMOVE_INTAKE_SUCCESS,
      payload: intake,
    };
  }

  static ADD_PROGRAM_OFFERING = '[Intake] Add Program Offering';

  addProgramOffering(intake: Intake, programOffering: ProgramOffering) {
    return {
      type: IntakeActions.ADD_PROGRAM_OFFERING,
      payload: {intake: intake, programOffering: programOffering},
    };
  }

  static ADD_PROGRAM_OFFERING_SUCCESS = '[Intake] Add Program Offering Success';

  addProgramOfferingSuccess(message) {
    return {
      type: IntakeActions.ADD_PROGRAM_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_PROGRAM_OFFERING = '[Intake] Update Program Offering';

  updateProgramOffering(intake: Intake, programOffering: ProgramOffering) {
      console.log('updateProgramOffering');
      console.log('intake id :' + intake.id);
      console.log('programOffering programCode id :' + programOffering.programCode.code);
      return {
      type: IntakeActions.UPDATE_PROGRAM_OFFERING,
      payload: {intake: intake, programOffering: programOffering},
    };
  }

   static UPDATE_PROGRAM_OFFERING_SUCCESS = '[Intake] Update Program Offering Success';

  updateProgramOfferingSuccess(message) {
      console.log('updateProgramOfferingSuccess');
      return {
      type: IntakeActions.UPDATE_PROGRAM_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static DELETE_PROGRAM_OFFERING = '[Intake] DELETE Program Offering';

  deleteProgramOffering(intake: Intake, programOffering: ProgramOffering) {
    return {
      type: IntakeActions.DELETE_PROGRAM_OFFERING,
      payload: {intake: intake, programOffering: programOffering},
    };
  }

  static DELETE_PROGRAM_OFFERING_SUCCESS = '[Intake] Delete Program Offering Success';

  deleteProgramOfferingSuccess(message) {
    return {
      type: IntakeActions.DELETE_PROGRAM_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static ADD_SUPERVISOR_OFFERING = '[Intake] Add Supervisor Offering';

  addSupervisorOffering(intake: Intake, supervisorOffering: SupervisorOffering) {
    return {
      type: IntakeActions.ADD_SUPERVISOR_OFFERING,
      payload: {intake: intake, supervisorOffering: supervisorOffering},
    };
  }

  static ADD_SUPERVISOR_OFFERING_SUCCESS = '[Intake] Add Supervisor Offering Success';

  addSupervisorOfferingSuccess(message) {
    return {
      type: IntakeActions.ADD_SUPERVISOR_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static DELETE_SUPERVISOR_OFFERING = '[Intake] Delete Supervisor Offering';

  deleteSupervisorOffering(intake: Intake, supervisorOffering: SupervisorOffering) {
    return {
      type: IntakeActions.DELETE_SUPERVISOR_OFFERING,
      payload: {intake: intake, supervisorOffering: supervisorOffering},
    };
  }

  static DELETE_SUPERVISOR_OFFERING_SUCCESS = '[Intake] Delete Supervisor Offering Success';

  deleteSupervisorOfferingSuccess(message) {
    return {
      type: IntakeActions.DELETE_SUPERVISOR_OFFERING_SUCCESS,
      payload: message,
    };
  }
  static ADD_STUDY_MODE_OFFERING = '[Intake] Add StudyMode Offering';

  addStudyModeOffering(intake: Intake, studyModeOffering: StudyModeOffering) {
    return {
      type: IntakeActions.ADD_STUDY_MODE_OFFERING,
      payload: {intake: intake, studyModeOffering: studyModeOffering},
    };
  }

  static ADD_STUDY_MODE_OFFERING_SUCCESS = '[Intake] Add StudyMode Offering Success';

  addStudyModeOfferingSuccess(message) {
    return {
      type: IntakeActions.ADD_STUDY_MODE_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static DELETE_STUDY_MODE_OFFERING = '[Intake] Delete Study Mode Offering';

  deleteStudyModeOffering(intake: Intake, studyModeOffering: StudyModeOffering) {
    return {
      type: IntakeActions.DELETE_STUDY_MODE_OFFERING,
      payload: {intake: intake, studyModeOffering: studyModeOffering},
    };
  }

  static DELETE_STUDY_MODE_OFFERING_SUCCESS = '[Intake] Delete Study Mode Offering Success';

  deleteStudyModeOfferingSuccess(message) {
    return {
      type: IntakeActions.DELETE_STUDY_MODE_OFFERING_SUCCESS,
      payload: message,
    };
  }
}
