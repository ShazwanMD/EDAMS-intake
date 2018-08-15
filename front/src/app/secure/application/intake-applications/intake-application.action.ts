import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class IntakeApplicationActions {

  // ====================================================================================================
  // INTAKE
  // ====================================================================================================

  // todo: published intakes
  static FIND_INTAKES = '[Intake Application] Find Intakes';

  findIntakes(): Action {
    return {
      type: IntakeApplicationActions.FIND_INTAKES,
    };
  }

  static FIND_INTAKES_SUCCESS = '[Intake Application] Find Intakes Success';

  findIntakesSuccess(intakes): Action {
    console.log('findIntakesSuccess');
    return {
      type: IntakeApplicationActions.FIND_INTAKES_SUCCESS,
      payload: intakes,
    };
  }

  static FIND_PUBLISHED_INTAKES = '[Intake Application] Find Published Intakes';

  findPublishedIntakes(): Action {
    return {
      type: IntakeApplicationActions.FIND_PUBLISHED_INTAKES,
    };
  }

  static FIND_PUBLISHED_INTAKES_SUCCESS = '[Intake Application] Find Published Intakes Success';

  findPublishedIntakesSuccess(intakes): Action {
    console.log('findPublishedIntakesSuccess');
    return {
      type: IntakeApplicationActions.FIND_PUBLISHED_INTAKES_SUCCESS,
      payload: intakes,
    };
  }

  static FIND_INTAKE_BY_REFERENCE_NO = '[Intake] Find Intake By Reference No';

  findIntakeByReferenceNo(referenceNo): Action {
    console.log('findIntakeByReferenceNo');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_BY_REFERENCE_NO,
      payload: referenceNo,
    };
  }

  static FIND_INTAKE_BY_REFERENCE_NO_SUCCESS = '[Intake] Find Intake By Reference No Success';

  findIntakeByReferenceNoSuccess(intake): Action {
    console.log('findIntakeByReferenceNoSuccess');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_BY_REFERENCE_NO_SUCCESS,
      payload: intake,
    };
  }

  static FIND_INTAKE_APPLICATION_BY_CANDIDATE = '[Intake] Find Intake Application By Candidate';

  findIntakeApplicationByCandidate(candidate): Action {
    console.log('findIntakeByReferenceNo');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_APPLICATION_BY_CANDIDATE,
      payload: candidate,
    };
  }

  static FIND_INTAKE_APPLICATION_BY_CANDIDATE_SUCCESS = '[Intake] Find Intake Application By Candidate Success';

  findIntakeApplicationByCandidateSuccess(candidate): Action {
    console.log('findIntakeByReferenceNoSuccess');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_APPLICATION_BY_CANDIDATE_SUCCESS,
      payload: candidate,
    };
  }
  
  static FIND_INTAKE_BY_CANDIDATE_REF_NO = '[Intake] Find Intake Application By Candidate reference No';

  findIntakeByCandidateReferenceNo(referenceNo): Action {
    console.log('findIntakeByCandidateReferenceNo');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_BY_CANDIDATE_REF_NO,
      payload: referenceNo,
    };
  }

  static FIND_INTAKE_BY_CANDIDATE_REF_NO_SUCCESS = '[Intake] Find Intake Application By Candidate reference No Success';

  findIntakeByCandidateReferenceNoSuccess(message): Action {
    console.log('findIntakeByCandidateReferenceNoSuccess');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_BY_CANDIDATE_REF_NO_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // PROGRAM_OFFERING
  // ====================================================================================================

  static FIND_PROGRAM_OFFERINGS_BY_INTAKE = '[Intake] Find Program Offerings By Intake';

  findProgramOfferingsByIntake(intake): Action {
    console.log('findProgramOfferingsByIntake');
    return {
      type: IntakeApplicationActions.FIND_PROGRAM_OFFERINGS_BY_INTAKE,
      payload: intake,
    };
  }

  static FIND_PROGRAM_OFFERINGS_BY_INTAKE_SUCCESS = '[Intake] Find Program Offerings By Intake Success';

  findProgramOfferingsByIntakeSuccess(offerings): Action {
    console.log('findProgramOfferingsByIntakeSuccess');
    return {
      type: IntakeApplicationActions.FIND_PROGRAM_OFFERINGS_BY_INTAKE_SUCCESS,
      payload: offerings,
    };
  }

  // ====================================================================================================
  // SUPERVISOR_OFFERING
  // ====================================================================================================

  static FIND_SUPERVISOR_OFFERINGS_BY_INTAKE = '[Intake] Find Supervisor Offerings By Intake';

  findSupervisorOfferingsByIntake(intake): Action {
    console.log('findSupervisorOfferingsByIntake');
    return {
      type: IntakeApplicationActions.FIND_SUPERVISOR_OFFERINGS_BY_INTAKE,
      payload: intake,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS_BY_INTAKE_SUCCESS = '[Intake] Find Supervisor Offerings By Intake Success';

  findSupervisorOfferingsByIntakeSuccess(offerings): Action {
    console.log('findSupervisorOfferingsByIntakeSuccess');
    return {
      type: IntakeApplicationActions.FIND_SUPERVISOR_OFFERINGS_BY_INTAKE_SUCCESS,
      payload: offerings,
    };
  }

   // ====================================================================================================
  // SUPERVISOR_OFFERING_BY_PROGRAM_LEVEL
  // ====================================================================================================

  static FIND_SUPERVISOR_OFFERINGS_BY_PROGRAM_LEVEL = '[Intake] Find Supervisor Offerings By Program Level';

  findSupervisorOfferingsByProgramLevel(programLevel): Action {
    console.log('findSupervisorOfferingsByProgramLevel');
    return {
      type: IntakeApplicationActions.FIND_SUPERVISOR_OFFERINGS_BY_PROGRAM_LEVEL,
      payload: programLevel,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS_BY_PROGRAM_LEVEL_SUCCESS = '[Intake] Find Supervisor Offerings By Program Level Success';

  findSupervisorOfferingsByProgramLevelSuccess(offerings): Action {
    console.log('findSupervisorOfferingsByProgramLevelSuccess');
    return {
      type: IntakeApplicationActions.FIND_SUPERVISOR_OFFERINGS_BY_PROGRAM_LEVEL_SUCCESS,
      payload: offerings,
    };
  }

  // ====================================================================================================
  // STUDY_MODE_OFFERING
  // ====================================================================================================

  static FIND_STUDY_MODE_OFFERINGS_BY_INTAKE = '[Intake] Find StudyMode Offerings By Intake';

  findStudyModeOfferingsByIntake(intake): Action {
    console.log('findStudyModeOfferingsByIntake');
    return {
      type: IntakeApplicationActions.FIND_STUDY_MODE_OFFERINGS_BY_INTAKE,
      payload: intake,
    };
  }

  static FIND_STUDY_MODE_OFFERINGS_BY_INTAKE_SUCCESS = '[Intake] Find StudyMode Offerings By Intake Success';

  findStudyModeOfferingsByIntakeSuccess(offerings): Action {
    console.log('findStudyModeOfferingsByIntakeSuccess');
    return {
      type: IntakeApplicationActions.FIND_STUDY_MODE_OFFERINGS_BY_INTAKE_SUCCESS,
      payload: offerings,
    };
  }

  // ====================================================================================================
  // INTAKE APPLICATION
  // ====================================================================================================

  static APPLY_INTAKE = '[Intake Application] Apply Intake';

  applyIntake(intake): Action {
    console.log('applyIntake');
    return {
      type: IntakeApplicationActions.APPLY_INTAKE,
      payload: intake,
    };
  }

  static APPLY_INTAKE_SUCCESS = '[Intake Application] Apply Intake Success ';

  applyIntakeSuccess(referenceNo): Action {
    console.log('applyIntakeSuccess');
    return {
      type: IntakeApplicationActions.APPLY_INTAKE_SUCCESS,
      payload: referenceNo,
    };
  }

  static FIND_INTAKE_APPLICATIONS = '[Intake Application] Find Intake Applications';

  findIntakeApplications(): Action {
    return {
      type: IntakeApplicationActions.FIND_INTAKE_APPLICATIONS,
    };
  }

  static FIND_INTAKE_APPLICATIONS_SUCCESS = '[Intake Application] Find Intake Applications Success';

  findIntakeApplicationsSuccess(applcations): Action {
    console.log('findIntakeApplicationsSuccess');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_APPLICATIONS_SUCCESS,
      payload: applcations,
    };
  }

  static FIND_DRAFTED_INTAKE_APPLICATIONS = '[Intake Application] Find Drafted Intake Applications';

  findDraftedIntakeApplications(): Action {
    return {
      type: IntakeApplicationActions.FIND_DRAFTED_INTAKE_APPLICATIONS,
    };
  }

  static FIND_DRAFTED_INTAKE_APPLICATIONS_SUCCESS = '[Intake Application] Find Drafted Intake Applications Success';

  findDraftedIntakeApplicationsSuccess(applcations): Action {
    console.log('findIntakeApplicationsSuccess');
    return {
      type: IntakeApplicationActions.FIND_DRAFTED_INTAKE_APPLICATIONS_SUCCESS,
      payload: applcations,
    };
  }

  static FIND_SUBMITTED_INTAKE_APPLICATIONS = '[Intake Application] Find Submitted Intake Applications';

  findSubmittedIntakeApplications(): Action {
    return {
      type: IntakeApplicationActions.FIND_SUBMITTED_INTAKE_APPLICATIONS,
    };
  }

  static FIND_SUBMITTED_INTAKE_APPLICATIONS_SUCCESS = '[Intake Application] Find Submitted Intake Applications Success';

  findSubmittedIntakeApplicationsSuccess(applcations): Action {
    console.log('findIntakeApplicationsSuccess');
    return {
      type: IntakeApplicationActions.FIND_SUBMITTED_INTAKE_APPLICATIONS_SUCCESS,
      payload: applcations,
    };
  }

  static SUBMIT_INTAKE_APPLICATION = '[Intake Application] Submit Intake Application';

  submitIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.SUBMIT_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static SUBMIT_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Submit Intake Application Success';

  submitIntakeApplicationSuccess(message): Action {
    return {
      type: IntakeApplicationActions.SUBMIT_INTAKE_APPLICATION_SUCCESS,
      payload: message,
    };
  }

  static COPY_ADDRESS_APPLICATION = '[Intake Application] Copy Address Intake Application';

  copyAddressApplication(application): Action {
    return {
      type: IntakeApplicationActions.COPY_ADDRESS_APPLICATION,
      payload: application,
    };
  }

  static COPY_ADDRESS_APPLICATION_SUCCESS = '[Intake Application] Copy Address Application Success';

  copyAddressApplicationSuccess(message): Action {
    return {
      type: IntakeApplicationActions.COPY_ADDRESS_APPLICATION_SUCCESS,
      payload: message,
    };
  }

  static SELECT_INTAKE_APPLICATION = '[Intake Application] Select Intake Application';

  selectIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.SELECT_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static SELECT_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Select Intake Application Success';

  selectIntakeApplicationSuccess(message): Action {
    return {
      type: IntakeApplicationActions.SELECT_INTAKE_APPLICATION_SUCCESS,
      payload: message,
    };
  }

  static VERIFY_INTAKE_APPLICATION = '[Intake Application] Verify Intake Application';

  verifyIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.VERIFY_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static VERIFY_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Verify Intake Application Success';

  verifyIntakeApplicationSuccess(message): Action {
    return {
      type: IntakeApplicationActions.VERIFY_INTAKE_APPLICATION_SUCCESS,
      payload: message,
    };
  }

  static REJECT_INTAKE_APPLICATION = '[Intake Application] Reject Intake Application';

  rejectIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.REJECT_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static REJECT_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Reject Intake Application Success';

  rejectIntakeApplicationSuccess(message): Action {
    return {
      type: IntakeApplicationActions.REJECT_INTAKE_APPLICATION_SUCCESS,
      payload: message,
    };
  }

  static PROMO_CODE_INTAKE_APPLICATION = '[Intake Application] Promo Code Intake Application';
  
  promoCodeIntakeApplication(application): Action {
      return {
        type: IntakeApplicationActions.PROMO_CODE_INTAKE_APPLICATION,
        payload: application,
      };
    }
  
    static PROMO_CODE_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Promo Code Intake Application Success';
  
    promoCodeIntakeApplicationSuccess(message): Action {
      return {
        type: IntakeApplicationActions.PROMO_CODE_INTAKE_APPLICATION_SUCCESS,
        payload: message,
      };
    }

  static FIND_INTAKE_APPLICATION_BY_REFERENCE_NO = '[Intake Application] Find Intake By Reference No';

  findIntakeApplicationByReferenceNo(referenceNo): Action {
    console.log('findIntakeApplicationByReferenceNo: ' + referenceNo);
    return {
      type: IntakeApplicationActions.FIND_INTAKE_APPLICATION_BY_REFERENCE_NO,
      payload: referenceNo,
    };
  }

  static FIND_INTAKE_APPLICATION_BY_REFERENCE_NO_SUCCESS = '[Intake Application] Find Intake Application By Reference No Success';

  findIntakeApplicationByReferenceNoSuccess(application): Action {
    console.log('findIntakeApplicationByReferenceNoSuccess');
    return {
      type: IntakeApplicationActions.FIND_INTAKE_APPLICATION_BY_REFERENCE_NO_SUCCESS,
      payload: application,
    };
  }

  static UPDATE_INTAKE_APPLICATION = '[Intake Application] Update Intake Application';

  updateIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.UPDATE_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static UPDATE_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Update Intake Success';

  updateIntakeSuccess(message): Action {
    return {
      type: IntakeApplicationActions.UPDATE_INTAKE_APPLICATION_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // EDUCATION
  // ====================================================================================================

  static FIND_EDUCATIONS_BY_INTAKE_APPLICATION = '[Intake Application] Find Educations';

  findEducationsByIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.FIND_EDUCATIONS_BY_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static FIND_EDUCATIONS_BY_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Find Educations Success';

  findEducationsByIntakeApplicationSuccess(educations): Action {
    console.log('findEducationsByIntakeApplicationSuccess');
    return {
      type: IntakeApplicationActions.FIND_EDUCATIONS_BY_INTAKE_APPLICATION_SUCCESS,
      payload: educations,
    };
  }
  
  // ====================================================================================================
  // PROMO CODE
  // ====================================================================================================

  static ENTER_PROMO_CODE = '[Intake Application] Enter Promo Code';

  enterPromoCodeIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.ENTER_PROMO_CODE,
      payload: application,
    };
  }

  static ENTER_PROMO_CODE_SUCCESS = '[Intake Application] Enter Promo Code Success';

  enterPromoCodeIntakeApplicationSuccess(message): Action {
    console.log('enterPromoCodeIntakeApplicationSuccess');
    return {
      type: IntakeApplicationActions.ENTER_PROMO_CODE_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // EMPLOYMENT
  // ====================================================================================================

  static FIND_EMPLOYMENTS_BY_INTAKE_APPLICATION = '[Intake Application] Find Employments';

  findEmploymentsByIntakeApplication(application): Action {
    console.log('findEmploymentsByIntakeApplication');
    return {
      type: IntakeApplicationActions.FIND_EMPLOYMENTS_BY_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static FIND_EMPLOYMENTS_BY_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Find Employments Success';

  findEmploymentsByIntakeApplicationSuccess(employments): Action {
    console.log('findEmploymentsByIntakeApplicationSuccess');
    return {
      type: IntakeApplicationActions.FIND_EMPLOYMENTS_BY_INTAKE_APPLICATION_SUCCESS,
      payload: employments,
    };
  }

  static ADD_EMPLOYMENT = '[Intake Application] Save Employment';

  addEmployment(application, employment): Action {
    console.log('addEmployment');
    return {
      type: IntakeApplicationActions.ADD_EMPLOYMENT,
      payload: {application: application, employment: employment},
    };
  }

  static ADD_EMPLOYMENT_SUCCESS = '[Intake Application] Add Employment Success';

  addEmploymentSuccess(message): Action {
    console.log('addEmploymentSuccess');
    return {
      type: IntakeApplicationActions.ADD_EMPLOYMENT_SUCCESS,
      payload: message,
    };
  }

  static DELETE_EMPLOYMENT = '[Intake Application] Delete Employment';

  deleteEmployment(application, employment) {
    return {
      type: IntakeApplicationActions.DELETE_EMPLOYMENT,
      payload: {application: application, employment: employment},
    };
  }

  static DELETE_EMPLOYMENT_SUCCESS = '[Intake Application] Delete Employment Success';

  deleteEmploymentSucces(message) {
    return {
      type: IntakeApplicationActions.DELETE_EMPLOYMENT_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_EMPLOYMENT = '[Intake Application] Update Employment Application';

  updateEmployment(application, employment): Action {
    return {
      type: IntakeApplicationActions.UPDATE_EMPLOYMENT,
      payload: {application: application, employment: employment},
    };
  }

  static UPDATE_EMPLOYMENT_SUCCESS = '[Intake Application] Update Employment Success';

  updateEmploymentSuccess(message): Action {
    return {
      type: IntakeApplicationActions.UPDATE_EMPLOYMENT_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // LANGUAGE
  // ====================================================================================================

  static FIND_LANGUAGES_BY_INTAKE_APPLICATION = '[Intake Application] Find Languages';

  findLanguagesByIntakeApplication(application): Action {
    console.log('findLanguagesByIntakeApplication');
    return {
      type: IntakeApplicationActions.FIND_LANGUAGES_BY_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static FIND_LANGUAGES_BY_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Find Languages Success';

  findLanguagesByIntakeApplicationSuccess(languages): Action {
    console.log('findLanguagesByIntakeApplicationSuccess');
    return {
      type: IntakeApplicationActions.FIND_LANGUAGES_BY_INTAKE_APPLICATION_SUCCESS,
      payload: languages,
    };
  }

  static ADD_LANGUAGE = '[Intake Application] Save Language';

  addLanguage(application, language): Action {
    console.log('addLanguage');
    return {
      type: IntakeApplicationActions.ADD_LANGUAGE,
      payload: {application: application, language: language},
    };
  }

  static ADD_LANGUAGE_SUCCESS = '[Intake Application] Add Language Success';

  addLanguageSuccess(message): Action {
    console.log('addLanguageSuccess');
    return {
      type: IntakeApplicationActions.ADD_LANGUAGE_SUCCESS,
      payload: message,
    };
  }

  static DELETE_LANGUAGE = '[Intake Application] Delete Language';

  deleteLanguage(application, language) {
    return {
      type: IntakeApplicationActions.DELETE_LANGUAGE,
      payload: {application: application, language: language},
    };
  }

  static DELETE_LANGUAGE_SUCCESS = '[Intake Application] Delete Language Success';

  deleteLanguageSucces(message) {
    return {
      type: IntakeApplicationActions.DELETE_LANGUAGE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_LANGUAGE = '[Intake Application] Update Language Application';

  updateLanguage(application, language): Action {
    return {
      type: IntakeApplicationActions.UPDATE_LANGUAGE,
      payload: {application: application, language: language},
    };
  }

  static UPDATE_LANGUAGE_SUCCESS = '[Intake Application] Update Language Success';

  updateLanguageSuccess(message): Action {
    return {
      type: IntakeApplicationActions.UPDATE_LANGUAGE_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // REFEREE
  // ====================================================================================================

  static FIND_REFEREES_BY_INTAKE_APPLICATION = '[Intake Application] Find Referees';

  findRefereesByIntakeApplication(application): Action {
    console.log('findRefereesByIntakeApplication');
    return {
      type: IntakeApplicationActions.FIND_REFEREES_BY_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static FIND_REFEREES_BY_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Find Referees Success';

  findRefereesByIntakeApplicationSuccess(referees): Action {
    console.log('findRefereesByIntakeApplicationSuccess');
    return {
      type: IntakeApplicationActions.FIND_REFEREES_BY_INTAKE_APPLICATION_SUCCESS,
      payload: referees,
    };
  }

  static ADD_REFEREE = '[Intake Application] Save Referee';

  addReferee(application, referee): Action {
    console.log('addReferee');
    return {
      type: IntakeApplicationActions.ADD_REFEREE,
      payload: {application: application, referee: referee},
    };
  }

  static ADD_REFEREE_SUCCESS = '[Intake Application] Add Referee Success';

  addRefereeSuccess(message): Action {
    console.log('addRefereeSuccess');
    return {
      type: IntakeApplicationActions.ADD_REFEREE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_REFEREE = '[Intake Application] Update Referee Application';

  updateReferee(application, referee): Action {
    return {
      type: IntakeApplicationActions.UPDATE_REFEREE,
      payload: {application: application, referee: referee},
    };
  }

  static UPDATE_REFEREE_SUCCESS = '[Intake Application] Update Referee Success';

  updateRefereeSuccess(message): Action {
    return {
      type: IntakeApplicationActions.UPDATE_REFEREE_SUCCESS,
      payload: message,
    };
  }

  static DELETE_REFEREE = '[Intake Application] Delete Referee';

  deleteReferee(application, referee) {
    return {
      type: IntakeApplicationActions.DELETE_REFEREE,
      payload: {application: application, referee: referee},
    };
  }

  static DELETE_REFEREE_SUCCESS = '[Intake Application] Delete Referee Success';

  deleteRefereeSucces(message) {
    return {
      type: IntakeApplicationActions.DELETE_REFEREE_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // ATTACHMENT
  // ====================================================================================================

  static FIND_ATTACHMENTS_BY_INTAKE_APPLICATION = '[Intake Application] Find Attachments';

  findAttachmentsByIntakeApplication(application): Action {
    console.log('findAttachmentsByIntakeApplication');
    return {
      type: IntakeApplicationActions.FIND_ATTACHMENTS_BY_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static FIND_ATTACHMENTS_BY_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Find Attachments Success';

  findAttachmentsByIntakeApplicationSuccess(attachments): Action {
    console.log('findAttachmentsByIntakeApplicationSuccess');
    return {
      type: IntakeApplicationActions.FIND_ATTACHMENTS_BY_INTAKE_APPLICATION_SUCCESS,
      payload: attachments,
    };
  }

  static ADD_ATTACHMENT = '[Intake Application] Add Attachment';

  addAttachment(application, file, attachmentType): Action {
    console.log('addAttachment');
    return {
      type: IntakeApplicationActions.ADD_ATTACHMENT,
      payload: {application: application, file: file, attachmentType: attachmentType},
    };
  }

  static ADD_ATTACHMENT_SUCCESS = '[Intake Application] Add Attachment Success';

  addAttachmentSuccess(message): Action {
    console.log('addAttachmentSuccess');
    return {
      type: IntakeApplicationActions.ADD_ATTACHMENT_SUCCESS,
      payload: message,
    };
  }

  static ADD_AND_CHECK_ATTACHMENT = '[Intake Application] Add And Check Attachment';

  addAndCheckAttachment(application, file, attachmentType): Action {
    console.log('addAndCheckAttachment');
    return {
      type: IntakeApplicationActions.ADD_AND_CHECK_ATTACHMENT,
      payload: {application: application, file: file, attachmentType: attachmentType},
    };
  }

  static ADD_AND_CHECK_ATTACHMENT_SUCCESS = '[Intake Application] Add And Check Attachment Success';

  addAndCheckAttachmentSuccess(message): Action {
    console.log('addAndCheckAttachmentSuccess');
    return {
      type: IntakeApplicationActions.ADD_AND_CHECK_ATTACHMENT_SUCCESS,
      payload: message,
    };
  }  

  static DELETE_ATTACHMENT = '[Intake Application] Delete Attachment';

  deleteAttachment(application, attachment) {
    console.log('deleteAttachment');
    return {
      type: IntakeApplicationActions.DELETE_ATTACHMENT,
      payload: {application: application, attachment: attachment},
    };
  }

  static DELETE_ATTACHMENT_SUCCESS = '[Intake Application] Delete Attachment Success';

  deleteAttachmentSucces(message) {
    console.log('deleteAttachmentSuccess');
    return {
      type: IntakeApplicationActions.DELETE_ATTACHMENT_SUCCESS,
      payload: message,
    };
  }

  static DOWNLOAD_ATTACHMENT = '[Intake Application] Download Attachment';

  downloadAttachment(attachment) {
    console.log('downloadAttachment');
    return {
      type: IntakeApplicationActions.DOWNLOAD_ATTACHMENT,
      payload: {attachment: attachment},
    };
  }

  static DOWNLOAD_ATTACHMENT_SUCCESS = '[Intake Application] Download Attachment Success';

  downloadAttachmentSucces(message) {
    console.log('downloadAttachmentSucces');
    return {
      type: IntakeApplicationActions.DOWNLOAD_ATTACHMENT_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // RESULT
  // ====================================================================================================

  static FIND_RESULTS_BY_INTAKE_APPLICATION = '[Intake Application] Find Results';

  findResultsByIntakeApplication(application): Action {
    return {
      type: IntakeApplicationActions.FIND_RESULTS_BY_INTAKE_APPLICATION,
      payload: application,
    };
  }

  static FIND_RESULTS_BY_INTAKE_APPLICATION_SUCCESS = '[Intake Application] Find Results Success';

  findResultsByIntakeApplicationSuccess(result): Action {
    console.log('findResultsSuccess');
    return {
      type: IntakeApplicationActions.FIND_RESULTS_BY_INTAKE_APPLICATION_SUCCESS,
      payload: result,
    };
  }

  static ADD_RESULT = '[Intake Application] Add Result';

  addResult(application, result) {
    return {
      type: IntakeApplicationActions.ADD_RESULT,
      payload: {application: application, result: result},
    };
  }

  static ADD_RESULT_SUCCESS = '[Intake Application] Add Result Success';

  addResultSuccess(message) {
    return {
      type: IntakeApplicationActions.ADD_RESULT_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_RESULT = '[Intake Application] Update Result Application';

  updateResult(application, result): Action {
    return {
      type: IntakeApplicationActions.UPDATE_RESULT,
      payload: {application: application, result: result},
    };
  }

  static UPDATE_RESULT_SUCCESS = '[Intake Application] Update Result Success';

  updateResultSuccess(message): Action {
    return {
      type: IntakeApplicationActions.UPDATE_RESULT_SUCCESS,
      payload: message,
    };
  }

  static DELETE_RESULT = '[Intake Application] Delete Result';

  deleteResult(application, result) {
    return {
      type: IntakeApplicationActions.DELETE_RESULT,
      payload: {application: application, result: result},
    };
  }

  static DELETE_RESULT_SUCCESS = '[Intake Application] Delete Result Success';

  deleteResultSuccess(message) {
    return {
      type: IntakeApplicationActions.DELETE_RESULT_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // EDUCATION
  // ====================================================================================================

  static ADD_EDUCATION = '[Intake Application] Add Education';

  addEducation(intake, education) {
    return {
      type: IntakeApplicationActions.ADD_EDUCATION,
      payload: {intake: intake, education: education},
    };
  }

  static ADD_EDUCATION_SUCCESS = '[Intake Application] Add Education Success';

  addEducationSuccess(message) {
    return {
      type: IntakeApplicationActions.ADD_EDUCATION_SUCCESS,
      payload: message,
    };
  }

  static DELETE_EDUCATION = '[Intake Application] Delete Education';

  deleteEducation(intake, education) {
    return {
      type: IntakeApplicationActions.DELETE_EDUCATION,
      payload: {intake: intake, education: education},
    };
  }

  static DELETE_EDUCATION_SUCCESS = '[Intake Application] Delete Education Success';

  deleteEducationSucces(message) {
    return {
      type: IntakeApplicationActions.DELETE_EDUCATION_SUCCESS,
      payload: message,
    };
  }

  // ====================================================================================================
  // SELECT
  // ====================================================================================================

  static SELECT_PROGRAM_OFFERING = '[Intake Application] Select Program Offering';

  selectProgramOffering(application, offering) {
    return {
      type: IntakeApplicationActions.SELECT_PROGRAM_OFFERING,
      payload: {application: application, offering: offering},
    };
  }

  static SELECT_PROGRAM_OFFERING_SUCCESS = '[Intake Application] Select Program Offering Success';

  selectProgramOfferingSuccess(message) {
    return {
      type: IntakeApplicationActions.SELECT_PROGRAM_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static SELECT_SUPERVISOR_OFFERING = '[Intake Application] Select Supervisor Offering';

  selectSupervisorOffering(application, offering) {
    return {
      type: IntakeApplicationActions.SELECT_SUPERVISOR_OFFERING,
      payload: {application: application, offering: offering},
    };
  }

  static SELECT_SUPERVISOR_OFFERING_SUCCESS = '[Intake Application] Select Supervisor Offering Success';

  selectSupervisorOfferingSuccess(message) {
    return {
      type: IntakeApplicationActions.SELECT_SUPERVISOR_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static SELECT_STUDY_MODE_OFFERING = '[Intake Application] Select Study Mode Offering';

  selectStudyModeOffering(application, offering) {
    return {
      type: IntakeApplicationActions.SELECT_STUDY_MODE_OFFERING,
      payload: {application: application, offering: offering},
    };
  }

  static SELECT_STUDY_MODE_OFFERING_SUCCESS = '[Intake Application] Select Study Mode Offering Success';

  selectStudyModeOfferingSuccess(message) {
    return {
      type: IntakeApplicationActions.SELECT_STUDY_MODE_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static SELECT_TAB_INDEX = '[Intake Application] Select Tab Index';

  selectTabIndex(index): Action {
    console.log('selectTabIndex');
    return {
      type: IntakeApplicationActions.SELECT_TAB_INDEX,
      payload: index,
    };
  }

  static SELECT_TAB_INDEX_SUCCESS = '[Intake Application] Select Tab Index Success';

  selectTabIndexSuccess(tab): Action {
    console.log('selectTabIndexSuccess');
    return {
      type: IntakeApplicationActions.SELECT_TAB_INDEX_SUCCESS,
      payload: tab,
    };
  }

  static FIND_SPM_RESULT_CODES = '[Intake Application] Find Application SpmResultCodes';

  findSpmResultCodes(application): Action {
    console.log("findSpmResultCodes");
    return {
      type: IntakeApplicationActions.FIND_SPM_RESULT_CODES,
      payload: application,
    };
  }

  static FIND_SPM_RESULT_CODES_SUCCESS = '[Intake Application] Find Application SpmResultCodes Success';

  findSpmResultCodesSuccess(codes): Action {
    console.log("findSpmResultCodesSuccess");
    return {
      type: IntakeApplicationActions.FIND_SPM_RESULT_CODES_SUCCESS,
      payload: codes
    };
  }

  static ADD_SPM_RESULT = '[Intake Application] Save SPM RESULT CODE';

  addSpmResultCode(application, spmResultCode): Action {
    console.log('addSpmResultCode');
    return {
      type: IntakeApplicationActions.ADD_SPM_RESULT,
      payload: {application: application, spmResultCode: spmResultCode},
    };
  }

  static ADD_SPM_RESULT_SUCCESS = '[Intake Application] Add SPM RESULT CODE Success';

  addSpmResultCodeSuccess(message): Action {
    console.log('addSpmResultCodeSuccess');
    return {
      type: IntakeApplicationActions.ADD_SPM_RESULT_SUCCESS,
      payload: message,
    };
  }
  static UPDATE_SPM_RESULT = '[Intake Application] Update SPM RESULT CODE Application';

  updateSpmResultCode(application, spmResultCode): Action {
    return {
      type: IntakeApplicationActions.UPDATE_SPM_RESULT,
      payload: {application: application, spmResultCode: spmResultCode},
    };
  }

  static UPDATE_SPM_RESULT_SUCCESS = '[Intake Application] Update SPM RESULT CODE Success';

  updateSpmResultCodeSuccess(message): Action {
    return {
      type: IntakeApplicationActions.UPDATE_SPM_RESULT_SUCCESS,
      payload: message,
    };
  }
  static DELETE_SPM_RESULT_CODE = '[Intake Application] Delete SPM RESULT CODE';

  deleteSpmResultCode(application, spmResultCode) {
    return {
      type: IntakeApplicationActions.DELETE_SPM_RESULT_CODE,
      payload: {application: application, spmResultCode: spmResultCode},
    };
  }

  static DELETE_SPM_RESULT_CODE_SUCCESS = '[Intake Application] Delete SPM RESULT CODE Success';

  deleteSpmResultCodeSucces(message) {
    return {
      type: IntakeApplicationActions.DELETE_SPM_RESULT_CODE_SUCCESS,
      payload: message,
    };
  }

}
