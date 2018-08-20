import { NotificationService } from '../../../../services/notification.service';
import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';
import { from } from 'rxjs/observable/from';
import { IntakeApplicationActions } from './intake-application.action';
import { ApplicationService } from '../../../../services/application.service';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { ApplicationModuleState } from '../index';
import { Observable } from 'rxjs';
import { Intake } from '../../../shared/model/policy/intake.interface';
import { IntakeApplication } from '../../../shared/model/application/intake-application.interface';
import { IntakeActions } from '../../policy/intakes/intake.action';
import { ApplicationContextActions } from '../../../application-context.action';
import { CommonService } from '../../../../services/common.service';

@Injectable()
export class IntakeApplicationEffects {

  private INTAKE: string[] = 'applicationModuleState.intake'.split('.');
  private INTAKE_APPLICATION: string[] = 'applicationModuleState.intakeApplication'.split('.');
  private intake$: Observable<Intake>;

  constructor(private actions$: Actions,
    private intakeApplicationActions: IntakeApplicationActions,
    private intakeActions: IntakeActions,
    private applicationService: ApplicationService,
    private notificationService: NotificationService,
    private commonService: CommonService,
    private router: Router,
    private store$: Store<ApplicationModuleState>,
    private ctxActions: ApplicationContextActions) {
    this.intake$ = this.store$.select(...this.INTAKE);
  }

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  @Effect() findIntake$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_INTAKES)
    .switchMap(() => this.applicationService.findIntakes())
    .map((intakes) => this.intakeApplicationActions.findIntakesSuccess(intakes));

  @Effect() findPublishedIntake$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_PUBLISHED_INTAKES)
    .switchMap(() => this.applicationService.findPublishedIntakes())
    .map((intakes) => this.intakeApplicationActions.findPublishedIntakesSuccess(intakes));

  @Effect() findIntakeByReferenceNo$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_INTAKE_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.applicationService.findIntakeByReferenceNo(referenceNo))
    .map((intake) => this.intakeApplicationActions.findIntakeByReferenceNoSuccess(intake))
    .mergeMap((action) => from([action,
      this.intakeApplicationActions.findProgramOfferingsByIntake(action.payload),
      this.intakeApplicationActions.findStudyModeOfferingsByIntake(action.payload),
      this.intakeApplicationActions.findSupervisorOfferingsByIntake(action.payload),
    ]));

  @Effect() findIntakeApplicationByCandidate$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_INTAKE_APPLICATION_BY_CANDIDATE)
    .map((action) => action.payload)
    .switchMap((candidate) => this.applicationService.findIntakeApplicationByCandidate(candidate))
    .map((intake) => this.intakeApplicationActions.findIntakeApplicationByCandidateSuccess(intake))
    .mergeMap((action) => from([action,
      //this.intakeApplicationActions.findIntakeApplicationByReferenceNo(action.payload),
    ]));

  @Effect() findIntakeByCandidateReferenceNo$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_INTAKE_BY_CANDIDATE_REF_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.applicationService.findIntakeByCandidateReferenceNo(referenceNo))
    .map((message) => this.intakeApplicationActions.findIntakeByCandidateReferenceNoSuccess(message));
  // .mergeMap((action) => from([action,
  //this.intakeApplicationActions.findIntakeApplicationByReferenceNo(action.payload),
  //]));

  @Effect() applyIntake$ = this.actions$
    .ofType(IntakeApplicationActions.APPLY_INTAKE)
    .map((action) => action.payload)
    .switchMap((intake) => this.applicationService.applyIntake(intake))
    .map((referenceNo) => this.intakeApplicationActions.applyIntakeSuccess(referenceNo))
    .catch((error) => this.notificationService.showError(error))
    .do(action => this.router.navigate(['secure/application/intake-application-detail/', action.payload])).ignoreElements();

  // ====================================================================================================
  // PROGRAM_OFFERING
  // ====================================================================================================

  @Effect() findProgramOfferingsByIntake$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_PROGRAM_OFFERINGS_BY_INTAKE)
    .map((action) => action.payload)
    .switchMap((intake) => this.applicationService.findProgramOfferingsByIntake(intake))
    .map((offerings) => this.intakeApplicationActions.findProgramOfferingsByIntakeSuccess(offerings));

  // ====================================================================================================
  // SUPERVISOR_OFFERING
  // ====================================================================================================

  @Effect() findSupervisorOfferingsByIntake$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_SUPERVISOR_OFFERINGS_BY_INTAKE)
    .map((action) => action.payload)
    .switchMap((intake) => this.applicationService.findSupervisorOfferingsByIntake(intake))
    .map((offerings) => this.intakeApplicationActions.findSupervisorOfferingsByIntakeSuccess(offerings));

  @Effect() findSupervisorOfferingsByProgramLevel$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_SUPERVISOR_OFFERINGS_BY_PROGRAM_LEVEL)
    .map(action => action.payload)
    .switchMap((code) => this.applicationService.findSupervisorOfferingsByProgramLevel(code))
    .map(codes => this.intakeApplicationActions.findSupervisorOfferingsByProgramLevelSuccess(codes));

  // ====================================================================================================
  // STUDY_MODE_OFFERING
  // ====================================================================================================

  @Effect() findStudyModeOfferingsByIntake$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_STUDY_MODE_OFFERINGS_BY_INTAKE)
    .map((action) => action.payload)
    .switchMap((intake) => this.applicationService.findStudyModeOfferingsByIntake(intake))
    .map((offerings) => this.intakeApplicationActions.findStudyModeOfferingsByIntakeSuccess(offerings));

  // ====================================================================================================
  // INTAKE_APPLICATION
  // ====================================================================================================

  @Effect() findIntakeApplicationByReferenceNo$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_INTAKE_APPLICATION_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.applicationService.findIntakeApplicationByReferenceNo(referenceNo))
    .map((application) => this.intakeApplicationActions.findIntakeApplicationByReferenceNoSuccess(application))
    .mergeMap((action) => from([action,
      this.intakeApplicationActions.findEmploymentsByIntakeApplication(action.payload),
      this.intakeApplicationActions.findEducationsByIntakeApplication(action.payload),
      this.intakeApplicationActions.findRefereesByIntakeApplication(action.payload),
      this.intakeApplicationActions.findLanguagesByIntakeApplication(action.payload),
      this.intakeApplicationActions.findAttachmentsByIntakeApplication(action.payload),
      this.intakeApplicationActions.findSpmResultCodes(action.payload),
      this.intakeApplicationActions.findStpmResultCodes(action.payload),
      this.intakeApplicationActions.findDiplomaResultCodes(action.payload),
    ]));

  @Effect() updateIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.updateIntakeApplication(application))
    .map((message) => this.intakeApplicationActions.updateIntakeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() submitIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.SUBMIT_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.submitIntakeApplication(application))
    .map((message) => this.intakeApplicationActions.submitIntakeApplicationSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((applications) => this.intakeApplicationActions.findIntakeApplications());

  @Effect() selectIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.SELECT_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.selectIntakeApplication(application))
    .map((message) => this.intakeApplicationActions.selectIntakeApplicationSuccess(message))

  @Effect() verifyIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.VERIFY_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.verifyIntakeApplication(application))
    .map((message) => this.intakeApplicationActions.verifyIntakeApplicationSuccess(message))

  @Effect() rejectIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.REJECT_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.rejectIntakeApplication(application))
    .map((message) => this.intakeApplicationActions.rejectIntakeApplicationSuccess(message))

  @Effect() promoCodeIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.PROMO_CODE_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.promoCodeIntakeApplication(application))
    .map((message) => this.intakeApplicationActions.promoCodeIntakeApplicationSuccess(message))

  @Effect() findSubmittedIntakeApplications$ = this.actions$
    .ofType(IntakeActions.FIND_INTAKE_APPLICATIONS_BY_INTAKE)
    .map((action) => action.payload)
    .switchMap((intake) => this.applicationService.findSubmittedIntakeApplications(intake))
    .map((applications) => this.intakeActions.findIntakeApplicationsByIntakeSuccess(applications));

  // ====================================================================================================
  // PROMO CODE
  // ====================================================================================================

  @Effect() enterPromoCodeIntakeApplications = this.actions$
    .ofType(IntakeApplicationActions.ENTER_PROMO_CODE)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.enterPromoCodeIntakeApplication(application))
    .map((message) => this.intakeApplicationActions.enterPromoCodeIntakeApplicationSuccess(message))
    .catch((error) => this.notificationService.showError(error));

  // ====================================================================================================
  // EDUCATION
  // ====================================================================================================

  @Effect() findEducationsByIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_EDUCATIONS_BY_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.findEducationsByIntakeApplication(application))
    .map((educations) => this.intakeApplicationActions.findEducationsByIntakeApplicationSuccess(educations));

  @Effect() addEducation = this.actions$
    .ofType(IntakeApplicationActions.ADD_EDUCATION)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addEducation(payload.intake, payload.education))
    .map((message) => this.intakeApplicationActions.addEducationSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  // ====================================================================================================
  // REFEREE
  // ====================================================================================================

  @Effect() findRefereesByIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_REFEREES_BY_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.findRefereesByIntakeApplication(application))
    .map((referees) => this.intakeApplicationActions.findRefereesByIntakeApplicationSuccess(referees));

  @Effect() addReferee = this.actions$
    .ofType(IntakeApplicationActions.ADD_REFEREE)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addReferee(payload.application, payload.referee))
    .map((message) => this.intakeApplicationActions.addRefereeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() deleteReferee$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_REFEREE)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.deleteReferee(payload.application, payload.referee))
    .map((message) => this.intakeApplicationActions.deleteRefereeSucces(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() updateReferee$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_REFEREE)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.updateReferee(payload.application, payload.referee))
    .map((message) => this.intakeApplicationActions.updateRefereeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  // ====================================================================================================
  // EMPLOYMENT
  // ====================================================================================================

  @Effect() findEmploymentsByIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_EMPLOYMENTS_BY_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.findEmploymentsByIntakeApplication(application))
    .map((employments) => this.intakeApplicationActions.findEmploymentsByIntakeApplicationSuccess(employments));

  @Effect() addEmployment = this.actions$
    .ofType(IntakeApplicationActions.ADD_EMPLOYMENT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addEmployment(payload.application, payload.employment))
    .map((message) => this.intakeApplicationActions.addEmploymentSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() deleteEmployment$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_EMPLOYMENT)
    .map((action) => action.payload)
    .flatMap((payload) => this.applicationService.deleteEmployment(payload.application, payload.employment))
    .map((message) => this.intakeApplicationActions.deleteEmploymentSucces(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() updateEmployment$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_EMPLOYMENT)
    .map((action) => action.payload)
    .flatMap((payload) => this.applicationService.updateEmployment(payload.application, payload.employment))
    .map((message) => this.intakeApplicationActions.updateEmploymentSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  // ====================================================================================================
  // LANGUAGE
  // ====================================================================================================

  @Effect() findLanguagesByIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_LANGUAGES_BY_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.findLanguagesByIntakeApplication(application))
    .map((languages) => this.intakeApplicationActions.findLanguagesByIntakeApplicationSuccess(languages));

  @Effect() addLanguage = this.actions$
    .ofType(IntakeApplicationActions.ADD_LANGUAGE)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addLanguage(payload.application, payload.language))
    .map((message) => this.intakeApplicationActions.addLanguageSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() deleteLanguage$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_LANGUAGE)
    .map((action) => action.payload)
    .flatMap((payload) => this.applicationService.deleteLanguage(payload.application, payload.language))
    .map((message) => this.intakeApplicationActions.deleteLanguageSucces(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() updateLanguage$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_LANGUAGE)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.updateLanguage(payload.application, payload.language))
    .map((message) => this.intakeApplicationActions.updateLanguageSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  // ====================================================================================================
  // ATTACHMENT
  // ====================================================================================================

  @Effect() findAttachmentsByIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_ATTACHMENTS_BY_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.findAttachmentsByIntakeApplication(application))
    .map((attachments) => this.intakeApplicationActions.findAttachmentsByIntakeApplicationSuccess(attachments));

  @Effect() addAttachment = this.actions$
    .ofType(IntakeApplicationActions.ADD_ATTACHMENT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addAttachment(payload.application, payload.file, payload.attachmentType))
    .map((message) => this.intakeApplicationActions.addAttachmentSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() addAndCheckAttachment = this.actions$
    .ofType(IntakeApplicationActions.ADD_AND_CHECK_ATTACHMENT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addAndCheckAttachment(payload.application, payload.file, payload.attachmentType))
    .map((message) => this.intakeApplicationActions.addAndCheckAttachmentSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() downloadAttachment = this.actions$
    .ofType(IntakeApplicationActions.DOWNLOAD_ATTACHMENT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.downloadAttachment(payload.attachment))
    .map((file) => {
      let url = URL.createObjectURL(file);
      let a = document.createElement('a');
      a.href = url;
      a.download = file.name;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      URL.revokeObjectURL(url);
    }).ignoreElements();

  @Effect() deleteAttachment$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_ATTACHMENT)
    .map((action) => action.payload)
    .flatMap((payload) => this.applicationService.deleteAttachment(payload.application, payload.attachment))
    .map((message) => this.intakeApplicationActions.deleteAttachmentSucces(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  // ====================================================================================================
  // RESULT
  // ====================================================================================================

  @Effect() findResultsByIntakeApplication$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_RESULTS_BY_INTAKE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.findResultsByIntakeApplication(application))
    .map((results) => this.intakeApplicationActions.findResultsByIntakeApplicationSuccess(results));

  @Effect() addResult = this.actions$
    .ofType(IntakeApplicationActions.ADD_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addResult(payload.application, payload.result))
    .map((message) => this.intakeApplicationActions.addResultSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() deleteResult$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.deleteResult(payload.application, payload.result))
    .map((message) => this.intakeApplicationActions.deleteResultSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() updateResult$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.updateResult(payload.application, payload.result))
    .map((message) => this.intakeApplicationActions.updateResultSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  // ====================================================================================================
  // SELECT
  // ====================================================================================================

  @Effect() selectProgramOffering = this.actions$
    .ofType(IntakeApplicationActions.SELECT_PROGRAM_OFFERING)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.selectProgramOffering(payload.application, payload.offering))
    .map((message) => this.intakeApplicationActions.selectProgramOfferingSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() selectSupervisorOffering = this.actions$
    .ofType(IntakeApplicationActions.SELECT_SUPERVISOR_OFFERING)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.selectSupervisorOffering(payload.application, payload.offering))
    .map((message) => this.intakeApplicationActions.selectSupervisorOfferingSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() selectStudyModeOffering = this.actions$
    .ofType(IntakeApplicationActions.SELECT_STUDY_MODE_OFFERING)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.selectStudyModeOffering(payload.application, payload.offering))
    .map((message) => this.intakeApplicationActions.selectStudyModeOfferingSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() copyAddressApplication$ = this.actions$
    .ofType(IntakeApplicationActions.COPY_ADDRESS_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.applicationService.copyAddressApplication(application))
    .map((message) => this.intakeApplicationActions.copyAddressApplicationSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));


  @Effect() selectTabIndex$ = this.actions$
    .ofType(IntakeApplicationActions.SELECT_TAB_INDEX)
    .map((action) => action.payload)
    .map((index) => this.intakeApplicationActions.selectTabIndexSuccess(index));

  //================================================================================================================================
  // SPM RESULT
  //================================================================================================================================

  @Effect() findSpmResultCodes$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_SPM_RESULT_CODES)
    .map(action => action.payload)
    .switchMap((application) => this.applicationService.findSpmResultCodes(application))
    .map(codes => this.intakeApplicationActions.findSpmResultCodesSuccess(codes));

  @Effect() addSpmResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.ADD_SPM_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addSpmResultCodes(payload.application, payload.spmResultCode))
    .map((message) => this.intakeApplicationActions.addSpmResultCodeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() updateSpmResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_SPM_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.updateSpmResultCodes(payload.application, payload.spmResultCode))
    .map((message) => this.intakeApplicationActions.updateSpmResultCodeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() deleteSpmResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_SPM_RESULT_CODE)
    .map((action) => action.payload)
    .flatMap((payload) => this.applicationService.deleteSpmResultCodes(payload.application, payload.spmResultCode))
    .map((message) => this.intakeApplicationActions.deleteSpmResultCodeSucces(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));


  //================================================================================================================================
  // STPM RESULT
  //================================================================================================================================

  @Effect() findStpmResultCodes$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_STPM_RESULT_CODES)
    .map(action => action.payload)
    .switchMap((application) => this.applicationService.findStpmResultCodes(application))
    .map(codes => this.intakeApplicationActions.findStpmResultCodesSuccess(codes));

  @Effect() addStpmResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.ADD_STPM_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addStpmResultCodes(payload.application, payload.stpmResultCode))
    .map((message) => this.intakeApplicationActions.addStpmResultCodeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() updateStpmResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_STPM_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.updateStpmResultCodes(payload.application, payload.stpmResultCode))
    .map((message) => this.intakeApplicationActions.updateStpmResultCodeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() deleteStpmResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_STPM_RESULT_CODE)
    .map((action) => action.payload)
    .flatMap((payload) => this.applicationService.deleteStpmResultCodes(payload.application, payload.stpmResultCode))
    .map((message) => this.intakeApplicationActions.deleteStpmResultCodeSucces(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));


  //================================================================================================================================
  // DIPLOMA RESULT
  //================================================================================================================================

  @Effect() findDiplomaResultCodes$ = this.actions$
    .ofType(IntakeApplicationActions.FIND_DIPLOMA_RESULT_CODES)
    .map(action => action.payload)
    .switchMap((application) => this.applicationService.findDiplomaResultCodes(application))
    .map(codes => this.intakeApplicationActions.findDiplomaResultCodesSuccess(codes));

  @Effect() addDiplomaResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.ADD_DIPLOMA_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.addDiplomaResultCodes(payload.application, payload.diplomaResultCode))
    .map((message) => this.intakeApplicationActions.addDiplomaResultCodeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() updateDiplomaResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.UPDATE_DIPLOMA_RESULT)
    .map((action) => action.payload)
    .switchMap((payload) => this.applicationService.updateDiplomaResultCodes(payload.application, payload.diplomaResultCode))
    .map((message) => this.intakeApplicationActions.updateDiplomaResultCodeSuccess(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));

  @Effect() deleteDiplomaResultCode$ = this.actions$
    .ofType(IntakeApplicationActions.DELETE_DIPLOMA_RESULT_CODE)
    .map((action) => action.payload)
    .flatMap((payload) => this.applicationService.deleteDiplomaResultCodes(payload.application, payload.diplomaResultCode))
    .map((message) => this.intakeApplicationActions.deleteDiplomaResultCodeSucces(message))
    .withLatestFrom(this.store$.select(...this.INTAKE_APPLICATION))
    .map((state) => state[1])
    .map((application: IntakeApplication) => this.intakeApplicationActions.findIntakeApplicationByReferenceNo(application.referenceNo));





}

