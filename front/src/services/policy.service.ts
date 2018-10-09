import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Intake} from '../app/shared/model/policy/intake.interface';
import {Observable} from 'rxjs';
import {IntakeTask} from '../app/shared/model/policy/intake-task.interface';
import {ProgramOffering} from '../app/shared/model/policy/program-offering.interface';
import {SupervisorOffering} from '../app/shared/model/policy/supervisor-offering.interface';
import {StudyModeOffering} from '../app/shared/model/policy/study-mode-offering.interface';
import {IntakeSession} from '../app/shared/model/policy/intake-session.interface';
import {ProgramLevel} from '../app/shared/model/policy/program-level.interface';
import {IntakeApplication} from '../app/shared/model/application/intake-application.interface';
import { environment } from '../environments/environment.prod';

@Injectable()
export class PolicyService {

  private POLICY_API: string = environment.endpoint + '/api/policy';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INTAKE SESSION
  // ====================================================================================================

  findIntakeSessions(): Observable<IntakeSession[]> {
    console.log('findIntakeSessions');
    return this._http.get(this.POLICY_API + '/intakeSessions')
      .map((res: Response) => <IntakeSession[]>res.json());
  }

    findIntakeSessionsByCurrent(): Observable<IntakeSession[]> {
    console.log('findIntakeSessionsCurrent');
    return this._http.get(this.POLICY_API + '/intakeSessionsCurrent')
      .map((res: Response) => <IntakeSession[]>res.json());
  }

  saveIntakeSession(sessions: IntakeSession): Observable<String> {
    return this._http.post(this.POLICY_API + '/intake-sessions', JSON.stringify(sessions))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeIntakeSession(session: IntakeSession): Observable<String> {
    return this._http.delete(this.POLICY_API + '/intake-sessions/' + session.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateIntakeSession(code: IntakeSession): Observable<String> {
    return this._http.put(this.POLICY_API + '/intake-sessions/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // PROGRAM LEVEL
  // ====================================================================================================

  findProgramLevels(): Observable<ProgramLevel[]> {
    console.log('findProgramLevels');
    return this._http.get(this.POLICY_API + '/programLevels')
      .map((res: Response) => <ProgramLevel[]>res.json());
  }

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  findArchivedIntakes(): Observable<Intake[]> {
    console.log('findArchivedIntakes');
    return this._http.get(this.POLICY_API + '/intakes/archived' )
      .map((res: Response) => <Intake[]>res.json());
  }

  findAssignedIntakeTasks(): Observable<IntakeTask[]> {
    console.log('findAssignedIntakeTasks');
    return this._http.get(this.POLICY_API + '/intakes/assignedTasks')
      .map((res: Response) => <IntakeTask[]>res.json());
  }

  findPooledIntakeTasks(): Observable<IntakeTask[]> {
    console.log('findPooledIntakeTasks');
    return this._http.get(this.POLICY_API + '/intakes/pooledTasks')
      .map((res: Response) => <IntakeTask[]>res.json());
  }

  findIntakeTaskByTaskId(taskId: string): Observable<IntakeTask> {
    console.log('findIntakeTaskByTaskId');
    return this._http.get(this.POLICY_API + '/intakes/viewTask/' + taskId)
      .map((res: Response) => <IntakeTask>res.json());
  }

  findIntakeByReferenceNo(referenceNo: string): Observable<Intake> {
    return this._http.get(this.POLICY_API + '/intakes/' + referenceNo)
      .map((res: Response) => <Intake>res.json());
  }

  findIntakeByTaskId(taskId: string): Observable<Intake> {
    return this._http.get(this.POLICY_API + '/intakes/' + taskId)
      .map((res: Response) => <Intake>res.json());
  }

  findProgramOfferings(intake: Intake): Observable<ProgramOffering[]> {
    console.log('findProgramOfferings: ' + intake.referenceNo);
    return this._http.get(this.POLICY_API + '/intakes/' + intake.referenceNo + '/programOfferings')
      .map((res: Response) => <ProgramOffering[]>res.json());
  }

  findSupervisorOfferings(intake: Intake): Observable<SupervisorOffering[]> {
    console.log('findSupervisorOfferings');
    return this._http.get(this.POLICY_API + '/intakes/' + intake.referenceNo + '/supervisorOfferings')
      .map((res: Response) => <SupervisorOffering[]>res.json());
  }

  findStudyModeOfferings(intake: Intake): Observable<StudyModeOffering[]> {
    console.log('findStudyModeOfferings');
    return this._http.get(this.POLICY_API + '/intakes/' + intake.referenceNo + '/studyModeOfferings')
      .map((res: Response) => <StudyModeOffering[]>res.json());
  }

  findIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findIntakeApplications :' + intake.referenceNo);
    return this._http.get(this.POLICY_API + '/intakes/' + intake.referenceNo + '/intakeApplications')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  findSubmittedIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findSubmittedIntakeApplications :' + intake);
    return this._http.get(this.POLICY_API + '/intakes/' + intake.referenceNo + '/intakeApplications/bidStatus/SUBMITTED')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  findRejectedIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findIntakeApplications');
    return this._http.get(this.POLICY_API + '/intakes/' + intake.referenceNo + '/intakeApplications/bidStatus/REJECTED')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  findSelectedIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findIntakeApplications');
    return this._http.get(this.POLICY_API + '/intakes/' + intake.referenceNo + '/intakeApplications/bidStatus/SELECTED')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  startIntakeTask(intake: Intake): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/startTask', JSON.stringify(intake))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  copyIntakeTask(intake: Intake): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/' + intake.referenceNo + '/copy', JSON.stringify(intake))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeIntakeTask(intakeTask: IntakeTask): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/completeTask', JSON.stringify(intakeTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeIntakeTask(intakeTask: IntakeTask): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/removeTask', JSON.stringify(intakeTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimIntakeTask(intakeTask: IntakeTask): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/claimTask', JSON.stringify(intakeTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseIntakeTask(intakeTask: IntakeTask): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/releaseTask', JSON.stringify(intakeTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateIntake(intake: Intake): Observable<String> {
    console.log('referenceNo :' + intake.referenceNo);
    console.log('description :' + intake.descriptionEn);
    console.log('description :' + intake.startDate);
    console.log('description :' + intake.endDate);
    return this._http.put(this.POLICY_API + '/intakes/' + intake.referenceNo, JSON.stringify(intake))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addProgramOffering(intake: Intake, offering: ProgramOffering): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/' + intake.referenceNo + '/programOfferings',
      JSON.stringify(offering))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateProgramOffering(intake: Intake, offering: ProgramOffering): Observable<String> {
    console.log('offering gen criteria :' + offering.generalCriteria);
    console.log('offering spes criteria :' + offering.specificCriteria);
    return this._http.put(this.POLICY_API + '/intakes/' + intake.referenceNo + '/programOfferings/'
      + offering.id, JSON.stringify(offering))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteProgramOffering(intake: Intake, offering: ProgramOffering): Observable<String> {
    return this._http.delete(this.POLICY_API + '/intakes/' + intake.referenceNo + '/programOfferings/' + offering.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addSupervisorOffering(intake: Intake, offering: SupervisorOffering): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/' + intake.referenceNo + '/supervisorOfferings',
      JSON.stringify(offering))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteSupervisorOffering(intake: Intake, offering: SupervisorOffering): Observable<String> {
    return this._http.delete(this.POLICY_API + '/intakes/' + intake.referenceNo + '/supervisorOfferings/' + offering.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addStudyModeOffering(intake: Intake, offering: StudyModeOffering): Observable<String> {
    return this._http.post(this.POLICY_API + '/intakes/' + intake.referenceNo + '/studyModeOfferings',
      JSON.stringify(offering))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteStudyModeOffering(intake: Intake, offering: StudyModeOffering): Observable<String> {
    return this._http.delete(this.POLICY_API + '/intakes/' + intake.referenceNo + '/studyModeOfferings/' + offering.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

}
