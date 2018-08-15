import { Result } from '../app/shared/model/application/result.interface';
import { Attachment } from '../app/shared/model/application/attachment.interface';
import { Referee } from '../app/shared/model/application/referee.interface';
import { Injectable } from '@angular/core';
import { Response, ResponseContentType, RequestOptions, Headers } from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import { IntakeApplication } from '../app/shared/model/application/intake-application.interface';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';
import { Education } from '../app/shared/model/application/education.interface';
import { Employment } from '../app/shared/model/application/employment.interface';
import { Intake } from '../app/shared/model/policy/intake.interface';
import { ProgramOffering } from '../app/shared/model/policy/program-offering.interface';
import { StudyModeOffering } from '../app/shared/model/policy/study-mode-offering.interface';
import { Language } from '../app/shared/model/application/language.interface';
import { SupervisorOffering } from '../app/shared/model/policy/supervisor-offering.interface';
import { AttachmentType } from '../app/shared/model/application/attachment-type.enum';
import { ProgramLevel } from '../app/shared/model/policy/program-level.interface';
import { Candidate } from "../app/shared/model/admission/candidate.interface";
import { SpmResultCode } from '../app/shared/model/common/spm-result-code.interface';

@Injectable()
export class ApplicationService {

  private APPLICATION_API: string = environment.endpoint + '/api/application';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  findPublishedIntakes(): Observable<Intake[]> {
    console.log('findPublishedIntakes');
    return this._http.get(this.APPLICATION_API + '/intakes/state/PUBLISHED')
      .map((res: Response) => <Intake[]>res.json());
  }

  findIntakes(): Observable<Intake[]> {
    console.log('findIntakes');
    return this._http.get(this.APPLICATION_API + '/intakes')
      .map((res: Response) => <Intake[]>res.json());
  }

  findIntakeByReferenceNo(referenceNo: string): Observable<Intake> {
    console.log('findIntakeByReferenceNo : ' + referenceNo);
    return this._http.get(this.APPLICATION_API + '/intakes/' + referenceNo)
      .map((res: Response) => <Intake>res.json());
  }

  findIntakeByCandidateReferenceNo(referenceNo: string): Observable<Intake> {
    console.log('findIntakeByCandidateReferenceNo : ' + referenceNo);
    return this._http.get(this.APPLICATION_API + '/intakes/candidates/' + referenceNo)
      .map((res: Response) => <Intake>res.json());
  }

  findIntakeApplicationByCandidate(candidate: Candidate): Observable<IntakeApplication> {
    return this._http.get(this.APPLICATION_API + '/intakeApplication/candidate/' + candidate.referenceNo)
      .map((res: Response) => <IntakeApplication>res.json());
  }

  findProgramOfferingsByIntake(intake: Intake): Observable<ProgramOffering[]> {
    return this._http.get(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/programOfferings')
      .map((res: Response) => <ProgramOffering[]>res.json());
  }

  findSupervisorOfferingsByIntake(intake: Intake): Observable<SupervisorOffering[]> {
    return this._http.get(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/supervisorOfferings')
      .map((res: Response) => <SupervisorOffering[]>res.json());
  }

  findSupervisorOfferingsByProgramLevel(programLevel: ProgramLevel): Observable<SupervisorOffering> {
    console.log('findSupervisorOfferingsByProgramLevel :' + programLevel.code);
    return this._http.get(this.APPLICATION_API + '/programLevel/' + programLevel.code + '/supervisorOfferings')
      .map((res: Response) => <SupervisorOffering>res.json());
  }

  findStudyModeOfferingsByIntake(intake: Intake): Observable<StudyModeOffering[]> {
    return this._http.get(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/studyModeOfferings')
      .map((res: Response) => <StudyModeOffering[]>res.json());
  }

  findIntakeApplicationsByIntake(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findIntakeApplicationsByIntake');
    return this._http.get(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/intakeApplications')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  applyIntake(intake: Intake): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/apply', JSON.stringify(intake))
      .flatMap((res: Response) => Observable.of(res.text()))
      .catch(this.handleError);
  }

  // ====================================================================================================
  // INTAKE APPLICATION
  // ====================================================================================================

  findIntakeApplicationByReferenceNo(referenceNo: string): Observable<IntakeApplication> {
    return this._http.get(this.APPLICATION_API + '/intakeApplication/' + referenceNo)
      .map((res: Response) => <IntakeApplication>res.json());
  }

  submitIntakeApplication(application: IntakeApplication): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/submit', JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  selectIntakeApplication(application: IntakeApplication): Observable<String> {
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/select', JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  verifyIntakeApplication(application: IntakeApplication): Observable<String> {
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/verify', JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  rejectIntakeApplication(application: IntakeApplication): Observable<String> {
    console.log('application {}', application);
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/reject', JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  promoCodeIntakeApplication(application: IntakeApplication): Observable<String> {
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/promoCode', JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateIntakeApplication(application: IntakeApplication): Observable<String> {
    console.log('updateIntakeApplication ');
    console.log('email: ' + application.email);
    console.log('researchTitle: ' + application.researchTitle);
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo, JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  findSubmittedIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findSubmittedIntakeApplications ' + intake.referenceNo);
    return this._http.get(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/intakeApplications/bidStatus/SUBMITTED')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  findRejectedIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findIntakeApplications');
    return this._http.get(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/intakeApplications/bidStatus/REJECTED')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  findSelectedIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findIntakeApplications');
    return this._http.get(this.APPLICATION_API + '/intakes/' + intake.referenceNo + '/intakeApplications/bidStatus/SELECTED')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  findVerifiedInternationalIntakeApplications(intake: Intake): Observable<IntakeApplication[]> {
    console.log('findVerifiedInternationalIntakeApplications');
    return this._http.get(this.APPLICATION_API + '/intakes/'
      + intake.referenceNo + '/intakeApplications/bidStatus/SELECTED/verify/false')
      .map((res: Response) => <IntakeApplication[]>res.json());
  }

  //====================================================================================================
  // PROMO CODE
  // ====================================================================================================

  enterPromoCodeIntakeApplication(application: IntakeApplication): Observable<String> {
    console.log('enterPromoCodeIntakeApplication');
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/promoCode/' + application.promoCode, JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()))
      .catch(this.handleError);
  }

  // ====================================================================================================
  // EDUCATION
  // ====================================================================================================

  findEducationsByIntakeApplication(application: IntakeApplication): Observable<Education[]> {
    console.log('findEducations');
    return this._http.get(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/educations')
      .map((res: Response) => <Education[]>res.json());
  }

  addEducation(application: IntakeApplication, education: Education): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/educations', JSON.stringify(education))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteEducation(application: IntakeApplication, education: Education): Observable<String> {
    return this._http.delete(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/educations/' + education.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // EMPLOYMENT
  // ====================================================================================================

  findEmploymentsByIntakeApplication(application: IntakeApplication): Observable<Employment[]> {
    console.log('findEmployments');
    return this._http.get(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/employments')
      .map((res: Response) => <Employment[]>res.json());
  }

  addEmployment(application: IntakeApplication, employment: Employment): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/employments', JSON.stringify(employment))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateEmployment(application: IntakeApplication, employment: Employment): Observable<String> {
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/employments/' + employment.id, JSON.stringify(employment))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteEmployment(application: IntakeApplication, employment: Employment): Observable<String> {
    return this._http.delete(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/employments/' + employment.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // LANGUAGE
  // ====================================================================================================

  findLanguagesByIntakeApplication(application: IntakeApplication): Observable<Language[]> {
    console.log('findLanguages');
    return this._http.get(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/languages')
      .map((res: Response) => <Language[]>res.json());
  }

  addLanguage(application: IntakeApplication, language: Language): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/languages', JSON.stringify(language))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateLanguage(application: IntakeApplication, language: Language): Observable<String> {
    return this._http.put(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/languages/' + language.id, JSON.stringify(language))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteLanguage(application: IntakeApplication, language: Language): Observable<String> {
    return this._http.delete(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/languages/' + language.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // ATTACHMENT
  // ====================================================================================================

  findAttachmentsByIntakeApplication(application: IntakeApplication): Observable<Attachment[]> {
    console.log('findAttachments');
    return this._http.get(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/attachments')
      .map((res: Response) => <Attachment[]>res.json());
  }

  downloadAttachment(attachment: Attachment): Observable<File> {
    console.log('downloadAttachment');
    let options: RequestOptions = new RequestOptions({ responseType: ResponseContentType.ArrayBuffer });
    return this._http.get(this.APPLICATION_API + '/intakeApplications/download/attachment/' + attachment.id, options)
      .map((res: Response) => {
        let type: string = attachment.mimeType;
        let filename: string = attachment.name;
        return new File([res.arrayBuffer()], filename, { type: type });
      });
  }

  addAttachment(application: IntakeApplication, file: File, attachmentType: AttachmentType): Observable<String> {
    console.log('addAttachment');
    console.log('file: ' + file.name);
    console.log('attachmentType: ' + attachmentType);
    let headers: Headers = new Headers({ 'Content-Type': '' });
    let options: RequestOptions = new RequestOptions({ headers: headers });
    let formData: FormData = new FormData();
    formData.append('attachmentType', attachmentType.toString());
    formData.append('file', file);
    return this._http.post(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/attachments', formData, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addAndCheckAttachment(application: IntakeApplication, file: File, attachmentType: AttachmentType): Observable<String> {
    console.log('addAttachment');
    console.log('file: ' + file.name);
    console.log('attachmentType: ' + attachmentType);
    let headers: Headers = new Headers({ 'Content-Type': '' });
    let options: RequestOptions = new RequestOptions({ headers: headers });
    let formData: FormData = new FormData();
    formData.append('attachmentType', attachmentType.toString());
    formData.append('file', file);
    return this._http.post(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/addAndCheckAttachments', formData, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // deleteAttachment(application: IntakeApplication, attachment: Attachment): Observable<String> {
  //   return this._http.delete(this.APPLICATION_API + '/intakeApplications/'
  //     + application.referenceNo + '/attachments/' + attachment.id)
  //     .flatMap((res: Response) => Observable.of(res.text()));
  // }

  deleteAttachment(application: IntakeApplication, attachment: Attachment): Observable<String> {
    console.log('deleteAttachment');
    console.log('attachment :' + attachment.id);
    return this._http.delete(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/attachments/' + attachment.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  // ====================================================================================================
  // REFEREE
  // ====================================================================================================

  findRefereesByIntakeApplication(application: IntakeApplication): Observable<Referee[]> {
    console.log('findReferees');
    return this._http.get(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/referees')
      .map((res: Response) => <Referee[]>res.json());
  }

  addReferee(application: IntakeApplication, referee: Referee): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/referees', JSON.stringify(referee))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateReferee(application: IntakeApplication, referee: Referee): Observable<String> {
    return this._http.put(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/referees/' + referee.id, JSON.stringify(referee))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteReferee(application: IntakeApplication, referee: Referee): Observable<String> {
    return this._http.delete(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/referees/' + referee.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // RESULT
  // ====================================================================================================

  findResultsByIntakeApplication(application: IntakeApplication): Observable<Result[]> {
    console.log('findResultsByIntakeApplication');
    return this._http.get(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo + '/results')
      .map((res: Response) => <Result[]>res.json());
  }

  addResult(application: IntakeApplication, result: Result): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/results', JSON.stringify(result))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateResult(application: IntakeApplication, result: Result): Observable<String> {
    return this._http.put(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/results/' + result.id, JSON.stringify(result))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteResult(application: IntakeApplication, result: Result): Observable<String> {
    return this._http.delete(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/results/' + result.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // SELECT
  // ====================================================================================================

  selectProgramOffering(application: IntakeApplication, offering: ProgramOffering): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/programOfferingSelection', JSON.stringify(offering))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  selectSupervisorOffering(application, offering): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/supervisorOfferingSelection', JSON.stringify(offering))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  selectStudyModeOffering(application, offering): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/studyModeOfferingSelection', JSON.stringify(offering))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // PROCESS CANDIDATE
  // ====================================================================================================
  processIntakeCandidate(application: IntakeApplication): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/processCandidate', JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  private handleError(error: Response | any) {
    let body: any = error.json();
    return Observable.throw(body);
  }

  copyAddressApplication(application: IntakeApplication): Observable<String> {
    return this._http.post(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/copy', JSON.stringify(application))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  //  SPM RESULT CODES
  // ====================================================================================================

  findSpmResultCodes(application: IntakeApplication): Observable<SpmResultCode[]> {
    console.log('findSpmResultCodes');
    return this._http.get(this.APPLICATION_API + '/intakeApplications/'+ application.referenceNo+'/spmResults')
      .map((res: Response) => <SpmResultCode[]>res.json());
  }

  addSpmResultCodes(application: IntakeApplication, spmResultCode: SpmResultCode): Observable<String> {
    console.log('save spm result codes');
    return this._http.post(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/spmResults', JSON.stringify(spmResultCode))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateSpmResultCodes(application: IntakeApplication, spmResultCode: SpmResultCode): Observable<String> {
    console.log('save spm result codes');
    return this._http.put(this.APPLICATION_API + '/intakeApplications/' + application.referenceNo
      + '/spmResults/' + spmResultCode.id, JSON.stringify(spmResultCode))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteSpmResultCodes(application: IntakeApplication, spmResultCode: SpmResultCode): Observable<String> {
    return this._http.delete(this.APPLICATION_API + '/intakeApplications/'
      + application.referenceNo + '/spmResults/' + spmResultCode.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

}
