import { CandidateTask } from './../app/shared/model/admission/candidate-task.interface';
import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs/Observable';
import {IntakeTask} from '../app/shared/model/policy/intake-task.interface';
import {Candidate} from '../app/shared/model/admission/candidate.interface';
import {Intake} from '../app/shared/model/policy/intake.interface';
import { environment } from '../environments/environment.prod';

@Injectable()
export class AdmissionService {

  private ADMISSION_API: string = environment.endpoint + '/api/admission';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  findAssignedIntakeTasks(): Observable<IntakeTask[]> {
    console.log('findAssignedIntakeTasks');
    return this._http.get(this.ADMISSION_API + '/intakes/assignedTasks')
      .map((res: Response) => <IntakeTask[]>res.json());
  }

  findPooledIntakeTasks(): Observable<IntakeTask[]> {
    console.log('findPooledIntakeTasks');
    return this._http.get(this.ADMISSION_API + '/intakes/pooledTasks')
      .map((res: Response) => <IntakeTask[]>res.json());
  }

  findIntakeTaskByTaskId(taskId: string): Observable<IntakeTask> {
    console.log('findIntakeTaskByTaskId');
    return this._http.get(this.ADMISSION_API + '/intakes/viewTask/' + taskId)
      .map((res: Response) => <IntakeTask>res.json());
  }

  // ====================================================================================================
  // CANDIDATE WORKFLOW
  // ====================================================================================================

  findAssignedCandidateTasks(): Observable<CandidateTask[]> {
    console.log('findAssignedCandidateTasks di front service');
    return this._http.get(this.ADMISSION_API + '/candidates/assignedCandidateTasks')
      .map((res: Response) => <CandidateTask[]>res.json());
  }

  findPooledCandidateTasks(): Observable<CandidateTask[]> {
    console.log('findPooledCandidateTasks');
    return this._http.get(this.ADMISSION_API + '/candidates/pooledCandidateTasks')
      .map((res: Response) => <CandidateTask[]>res.json());
  }
  
  findArchivedCandidates(): Observable<Candidate[]> {
      console.log('findArchivedCandidates');
      return this._http.get(this.ADMISSION_API + '/candidates/archived')
        .map((res: Response) => <Candidate[]>res.json());
    }
  
  findCandidateTaskByTaskId(taskId: string): Observable<CandidateTask> {
      console.log('findCandidateTaskkByTaskId');
      return this._http.get(this.ADMISSION_API + '/candidates/viewTask/' + taskId)
        .map((res: Response) => <CandidateTask>res.json());
    }
  
  findCandidateById(id: string): Observable<Candidate> {
      console.log('findCandidates candidate.id: ' + id);
      return this._http.get(this.ADMISSION_API + '/candidates/' + id)
      .map((res: Response) => <Candidate>res.json());
  }
  
  findCandidateByReferenceNo(referenceNo: string): Observable<Candidate> {
      console.log('findCandidates candidate.refNo: ' + referenceNo);
      return this._http.get(this.ADMISSION_API + '/candidates/application/' + referenceNo)
      .map((res: Response) => <Candidate>res.json());
  }

  completeCandidateTask(candidateTask: CandidateTask): Observable<String> {
      return this._http.post(this.ADMISSION_API + '/candidates/completeTask', JSON.stringify(candidateTask))
        .flatMap((res: Response) => Observable.of(res.text()));
    }
  
  removeCandidateTask(candidateTask: CandidateTask): Observable<String> {
      return this._http.post(this.ADMISSION_API + '/candidates/removeTask', JSON.stringify(candidateTask))
        .flatMap((res: Response) => Observable.of(res.text()));
    }
  
  claimCandidateTask(candidateTask: CandidateTask): Observable<String> {
      return this._http.post(this.ADMISSION_API + '/candidates/claimTask', JSON.stringify(candidateTask))
        .flatMap((res: Response) => Observable.of(res.text()));
    }

  findCandidateByIntakeApplicationReferenceNo(referenceNo: string): Observable<Candidate> {
      console.log('findCandidates candidate.refNo: ' + referenceNo);
      return this._http.get(this.ADMISSION_API + '/candidates/intakeApplication/' + referenceNo)
      .map((res: Response) => <Candidate>res.json());
  }

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  findCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findCandidates');
    console.log('findCandidates intake.referenceNo: ' + intake.referenceNo);
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates')
      .map((res: Response) => <Candidate[]>res.json());
  }

  findSelectedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findSelectedCandidates');
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/SELECTED')
      .map((res: Response) => <Candidate[]>res.json());
  }
  
  findApprovedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findApprovedCandidates');
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/APPROVED')
      .map((res: Response) => <Candidate[]>res.json());
  }
  
  findOfferedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findOfferedCandidates');
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/OFFERED')
      .map((res: Response) => <Candidate[]>res.json());
  }
  
  findAcceptOfferCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findAcceptOfferCandidates');
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/OFFERED/accepted')
      .map((res: Response) => <Candidate[]>res.json());
  }
  
  findPreSelectedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findPreSelectedCandidates');
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/PREAPPROVED')
      .map((res: Response) => <Candidate[]>res.json());
  }

  findRejectedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findRejectedCandidates');
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/REJECTED')
      .map((res: Response) => <Candidate[]>res.json());
  }
  
  findRejisteredCandidates(intake: Intake): Observable<Candidate[]> {
      console.log('findRejisteredCandidates');
      return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/REGISTERED')
        .map((res: Response) => <Candidate[]>res.json());
    }

  findAcceptedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findAcceptedCandidates');
    return this._http.get(this.ADMISSION_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/ACCEPTED')
      .map((res: Response) => <Candidate[]>res.json());
  }
  
  offerCandidates(intake: Intake): Observable<String> {
    return this._http.put(this.ADMISSION_API + '/intakes/'
      + intake.referenceNo + '/candidates/offer', JSON.stringify(intake))
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  registerCandidate(candidate: Candidate): Observable<String> {
      return this._http.put(this.ADMISSION_API + '/application/'
              + candidate.application.referenceNo + '/candidates/candidateStatus/register', JSON.stringify(candidate))
              .flatMap((res: Response) => Observable.of(res.text()));
    }
  
  preSelectCandidate(candidate: Candidate): Observable<String> {
    return this._http.put(this.ADMISSION_API + '/application/'
      + candidate.application.referenceNo + '/candidates/candidateStatus/preSelect', JSON.stringify(candidate))
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  selectCandidate(candidate: Candidate): Observable<String> {
    return this._http.put(this.ADMISSION_API + '/application/'
      + candidate.application.referenceNo + '/candidates/candidateStatus/select', JSON.stringify(candidate))
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  rejectCandidate(candidate: Candidate): Observable<String> {
    console.log("candidate reason :"+candidate.reason);
    console.log("candidate application :"+candidate.application.referenceNo);
    return this._http.put(this.ADMISSION_API + '/application/'
      + candidate.application.referenceNo + '/candidates/candidateStatus/reject', JSON.stringify(candidate))
      .flatMap((res: Response) => Observable.of(res.text()));
  }
}
