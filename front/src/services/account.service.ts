import { MyIntakeApplication } from './../app/shared/model/application/my-intake-application.interface';
import { Candidate } from './../app/shared/model/admission/candidate.interface';
import { AddressChange } from './../app/shared/model/identity/address-change.interface';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import { Observable } from 'rxjs/Observable';
import { IntakeApplication } from '../app/shared/model/application/intake-application.interface';
import { Applicant } from '../app/secure/identity/applicant.interface';
import { Intake } from '../app/shared/model/policy/intake.interface';
import { User } from '../app/secure/identity/user.interface';
import { PasswordChange } from "../app/shared/model/identity/password-change.interface";
import { EmailChange } from "../app/shared/model/identity/email-change.interface";
import { environment } from '../environments/environment.prod';

@Injectable()
export class AccountService {

  private ACCOUNT_API: string = environment.endpoint + '/api/account';

  constructor(private _http: HttpInterceptorService) {
  }
  findIntakes(): Observable<Intake[]> {
    console.log('FindIntakes');
    return this._http.get(this.ACCOUNT_API + '/intakes')
      .map((res: Response) => <Intake[]>res.json())
      .catch((error) => this.handleError(error));
  }

  findPublishedIntakes(): Observable<Intake[]> {
    console.log('findPublishedIntakes');
    return this._http.get(this.ACCOUNT_API + '/intakes/flowState/PUBLISHED  ')
      .map((res: Response) => <Intake[]>res.json())
      .catch((error) => this.handleError(error));
  }

  findIntakeApplications(): Observable<IntakeApplication[]> {
    console.log('findIntakeApplications');
    return this._http.get(this.ACCOUNT_API + '/intakeApplications')
      .map((res: Response) => <IntakeApplication[]>res.json())
      .catch((error) => this.handleError(error));
  }

  findMyIntakeApplications(): Observable<MyIntakeApplication[]> {
    console.log('findMyIntakeApplications');
    return this._http.get(this.ACCOUNT_API + '/myIntakeApplications')
      .map((res: Response) => <MyIntakeApplication[]>res.json())
      .catch((error) => this.handleError(error));
  }  

  findCandidates(): Observable<Candidate[]> {
    console.log('findCandidates');
    return this._http.get(this.ACCOUNT_API + '/candidates')
      .map((res: Response) => <Candidate[]>res.json())
      .catch((error) => this.handleError(error));
  }

  findDraftedIntakeApplications(): Observable<IntakeApplication[]> {
    console.log('findDraftedIntakeApplications');
    return this._http.get(this.ACCOUNT_API + '/intakeApplications/bidStatus/DRAFTED')
      .map((res: Response) => <IntakeApplication[]>res.json())
      .catch((error) => this.handleError(error));
  }

  findSubmittedIntakeApplications(): Observable<IntakeApplication[]> {
    console.log('findSubmittedIntakeApplications');
    return this._http.get(this.ACCOUNT_API + '/intakeApplications/bidStatus/SUBMITTED')
      .map((res: Response) => <IntakeApplication[]>res.json())
      .catch((error) => this.handleError(error));
  }

  updateApplicant(applicant: Applicant): Observable<String> {
    console.log('updateApplicant');
    return this._http.post(this.ACCOUNT_API + '/applicant', JSON.stringify(applicant))
      .flatMap((res: Response) => Observable.of(res.text()))
      .catch((error) => this.handleError(error));
  }

  findApplicant(): Observable<Applicant> {
    console.log('findApplicant');
    return this._http.get(this.ACCOUNT_API + '/applicant')
      .map((res: Response) => <Applicant>res.json())
      .catch((error) => this.handleError(error));
  }

  findUser(): Observable<User[]> {
    console.log('findUser');
    return this._http.get(this.ACCOUNT_API + '/user')
      .map((res: Response) => <User[]>res.json())
      .catch((error) => this.handleError(error));
  }

  saveUser(user: User): Observable<String> {
    return this._http.post(this.ACCOUNT_API + '/user', JSON.stringify(user))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateUser(user: User): Observable<String> {
    return this._http.put(this.ACCOUNT_API + '/updateUser', JSON.stringify(user))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  changeUserPassword(change: PasswordChange): Observable<String> {
    return this._http.post(this.ACCOUNT_API + '/passwordChange', JSON.stringify(change))
      .flatMap((res: Response) => Observable.of(res.text()))
      .catch((error) => this.handleError(error));
  }

  changeApplicantEmail(change: EmailChange): Observable<String> {
    console.log('EmailChange: ', change);
    return this._http.post(this.ACCOUNT_API + '/emailChange/' + change.currentEmail, JSON.stringify(change))
      .map((res: Response) => <String>res.text());
  }

  // changeApplicantAddress(change: AddressChange): Observable<String> {
  //   return this._http.post(this.ACCOUNT_API + '/addressChange/' + change.currentAddress, JSON.stringify(change))
  //     .map((res: Response) => <String>res.text());
  // }

  updateIntakeApplication(intakeApplication: IntakeApplication): Observable<String> {
    return this._http.put(this.ACCOUNT_API + '/updateIntakeApplication', JSON.stringify(intakeApplication))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  findOfferedCandidates(intake: Intake): Observable<Candidate[]> {
    console.log('findOfferedCandidates');
    return this._http.get(this.ACCOUNT_API + '/intakes/' + intake.referenceNo + '/candidates/candidateStatus/OFFERED')
      .map((res: Response) => <Candidate[]>res.json());
  }

  acceptCandidate(myIntakeApplication: MyIntakeApplication): Observable<String> {
    console.log('acceptCandidate');
    return this._http.put(this.ACCOUNT_API + '/acceptCandidate/application/'+myIntakeApplication.referenceNo, JSON.stringify(myIntakeApplication))
     .flatMap((res: Response) => Observable.of(res.text()));
  }

  declinedCandidate(myIntakeApplication: MyIntakeApplication): Observable<String> {
    console.log('declinedCandidate');
    return this._http.put(this.ACCOUNT_API + '/declinedCandidate/application/'+myIntakeApplication.referenceNo, JSON.stringify(myIntakeApplication))
     .flatMap((res: Response) => Observable.of(res.text()));
  }

  changeApplicantAddress(intakeApplication: IntakeApplication): Observable<String> {
    console.log('addressChange ');
    return this._http.post(this.ACCOUNT_API + '/addressChange', JSON.stringify(intakeApplication))
      .flatMap((res: Response) => Observable.of(res.text()))
      .catch((error) => this.handleError(error));
  }

  // ====================================================================================================
  // PRIVATE METHODS
  // ====================================================================================================

  private handleError(error: Response | any) {
    let body: any = error.json();
    return Observable.throw(body);
  }
}

