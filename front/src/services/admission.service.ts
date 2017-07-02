import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs/Observable';
import {environment} from '../environments/environment';
import {IntakeTask} from '../app/policy/intakes/intake-task.interface';
import {Candidate} from '../app/admission/candidate.interface';
import {Intake} from '../app/policy/intakes/intake.interface';

@Injectable()
export class AdmissionService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  findAssignedIntakeTasks(): Observable<IntakeTask[]> {
    console.log('findAssignedIntakeTasks');
    return this.http.get(environment.endpoint + '/api/admission/intakes/assignedTasks')
      .map((res: Response) => <IntakeTask[]>res.json());
  }

  findPooledIntakeTasks(): Observable<IntakeTask[]> {
    console.log('findPooledIntakeTasks');
    return this.http.get(environment.endpoint + '/api/admission/intakes/pooledTasks')
      .map((res: Response) => <IntakeTask[]>res.json());
  }

  findIntakeTaskByTaskId(taskId: string): Observable<IntakeTask> {
    console.log('findIntakeTaskByTaskId');
    return this.http.get(environment.endpoint + '/api/admission/intakes/viewTask/' + taskId)
      .map((res: Response) => <IntakeTask>res.json());
  }

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  findCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findCandidates');
    console.log('findCandidates intake.referenceNo: ' + intake.referenceNo );
    return this.http.get(environment.endpoint + '/api/admission/intakes/' + intake.referenceNo + '/candidates' )
      .map((res: Response) => <Candidate[]>res.json());
  }

  findSelectedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findSelectedCandidates');
    return this.http.get(environment.endpoint + '/api/admission/intakes/' + intake.referenceNo + '/candidates/candidateStatus/SELECTED' )
      .map((res: Response) => <Candidate[]>res.json());
  }

  findRejectedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findRejectedCandidates');
    return this.http.get(environment.endpoint + '/api/admission/intakes/' + intake.referenceNo + '/candidates/candidateStatus/REJECTED' )
      .map((res: Response) => <Candidate[]>res.json());
  }

  findAcceptedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findAcceptedCandidates');
    return this.http.get(environment.endpoint + '/api/admission/intakes/' + intake.referenceNo + '/candidates/candidateStatus/ACCEPTED' )
      .map((res: Response) => <Candidate[]>res.json());
  }
}
