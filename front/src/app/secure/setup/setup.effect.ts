import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {SetupActions} from './setup.action';
import {from} from 'rxjs/observable/from';
import {SetupModuleState} from './index';
import {Store} from '@ngrx/store';
import {CommonService} from '../../../services/common.service';

@Injectable()
export class SetupEffects {
  constructor(private actions$: Actions,
              private setupActions: SetupActions,
              private commonService: CommonService,
              private store: Store<SetupModuleState>) {
  }

  // ====================================================================================================
  // TITLE
  // ====================================================================================================

  @Effect() changeTitle$ = this.actions$
    .ofType(SetupActions.CHANGE_TITLE)
    .map((action) => action.payload)
    .map((payload) => this.setupActions.changeTitleSuccess(payload));

  @Effect() findBankCode$ = this.actions$
    .ofType(SetupActions.FIND_BANK_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findBankCodes())
    .map((codes) => this.setupActions.findBankCodesSuccess(codes));

  // ====================================================================================================
  // GRADUATE CENTER
  // ====================================================================================================

  @Effect() findGraduateCenters$ = this.actions$
    .ofType(SetupActions.FIND_GRADUATE_CENTERS)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findGraduateCenters())
    .map((codes) => this.setupActions.findGraduateCentersSuccess(codes));

  @Effect() saveGraduateCenter$ = this.actions$
    .ofType(SetupActions.SAVE_GRADUATE_CENTER)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveGraduateCenter(payload))
    .map((message) => this.setupActions.saveGraduateCenterSuccess(message));

    @Effect() updateGraduateCenter$ = this.actions$
    .ofType(SetupActions.UPDATE_GRADUATE_CENTER)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateGraduateCenter(payload))
    .map((message) => this.setupActions.updateGraduateCenterSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGraduateCenters()]));

  @Effect() removeGraduateCenters$ = this.actions$
    .ofType(SetupActions.REMOVE_GRADUATE_CENTER)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeGraduateCenter(payload))
    .map((message) => this.setupActions.removeGraduateCenterSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGraduateCenters()]));

  // ====================================================================================================
  // RELIGION CODE
  // ====================================================================================================

   @Effect() findReligionCode$ = this.actions$
    .ofType(SetupActions.FIND_RELIGION_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findReligionCodes())
    .map((codes) => this.setupActions.findReligionCodesSuccess(codes));

  @Effect() saveReligionCodes$ = this.actions$
    .ofType(SetupActions.SAVE_RELIGION_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveReligionCode(payload))
    .map((message) => this.setupActions.saveReligionCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findReligionCodes()]));

  @Effect() updateReligionCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_RELIGION_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateReligionCode(payload))
    .map((message) => this.setupActions.updateReligionCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findReligionCodes()]));

  @Effect() removeReligionCode$ = this.actions$
    .ofType(SetupActions.REMOVE_RELIGION_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeReligionCode(payload))
    .map((message) => this.setupActions.removeReligionCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findReligionCodes()]));

  // ====================================================================================================
  // MARITAL CODE
  // ====================================================================================================

  @Effect() findMaritalCode$ = this.actions$
    .ofType(SetupActions.FIND_MARITAL_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findMaritalCodes())
    .map((codes) => this.setupActions.findMaritalCodesSuccess(codes));

  @Effect() saveMaritalCodes$ = this.actions$
    .ofType(SetupActions.SAVE_MARITAL_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveMaritalCode(payload))
    .map((message) => this.setupActions.saveMaritalCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findMaritalCodes()]));

  @Effect() updateMaritalCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_MARITAL_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateMaritalCode(payload))
    .map((message) => this.setupActions.updateMaritalCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findMaritalCodes()]));

  @Effect() removeMaritalCode$ = this.actions$
    .ofType(SetupActions.REMOVE_MARITAL_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeMaritalCode(payload))
    .map((message) => this.setupActions.removeMaritalCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findMaritalCodes()]));


  // ====================================================================================================
  // VENUE CODE
  // ====================================================================================================

  @Effect() findVenueCode$ = this.actions$
    .ofType(SetupActions.FIND_VENUE_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findVenueCodes())
    .map((codes) => this.setupActions.findVenueCodesSuccess(codes));

  @Effect() saveVenueCodes$ = this.actions$
    .ofType(SetupActions.SAVE_VENUE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveVenueCode(payload))
    .map((message) => this.setupActions.saveVenueCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findVenueCodes()]));

  @Effect() updateVenueCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_VENUE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateVenueCode(payload))
    .map((message) => this.setupActions.updateVenueCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findVenueCodes()]));

  @Effect() removeVenueCode$ = this.actions$
    .ofType(SetupActions.REMOVE_VENUE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeVenueCode(payload))
    .map((message) => this.setupActions.removeVenueCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findVenueCodes()]));    

  // ====================================================================================================
  // COUNTRY CODE
  // ====================================================================================================

  @Effect() findCountryCode$ = this.actions$
    .ofType(SetupActions.FIND_COUNTRY_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findCountryCodes())
    .map((codes) => this.setupActions.findCountryCodesSuccess(codes));

  @Effect() saveCountryCodes$ = this.actions$
    .ofType(SetupActions.SAVE_COUNTRY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveCountryCode(payload))
    .map((message) => this.setupActions.saveCountryCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findCountryCodes()]));

  @Effect() updateCountryCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_COUNTRY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateCountryCode(payload))
    .map((message) => this.setupActions.updateCountryCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findCountryCodes()]));

  @Effect() removeCountryCode$ = this.actions$
    .ofType(SetupActions.REMOVE_COUNTRY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeCountryCode(payload))
    .map((message) => this.setupActions.removeCountryCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findCountryCodes()]));

  // ====================================================================================================
  // STATE CODE
  // ====================================================================================================

  @Effect() findStateCodes$ = this.actions$
    .ofType(SetupActions.FIND_STATE_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findStateCodes())
    .map((codes) => this.setupActions.findStateCodesSuccess(codes));

  @Effect() saveStateCodes$ = this.actions$
    .ofType(SetupActions.SAVE_STATE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveStateCode(payload))
    .map((message) => this.setupActions.saveStateCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStateCodes()]));

  @Effect() updateStateCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_STATE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateStateCode(payload))
    .map((message) => this.setupActions.updateStateCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStateCodes()]));

  @Effect() removeStateCode$ = this.actions$
    .ofType(SetupActions.REMOVE_STATE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeStateCode(payload))
    .map((message) => this.setupActions.removeStateCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStateCodes()]));

  // ====================================================================================================
  // PROGRAM CODE
  // ====================================================================================================

  @Effect() findProgramCodes$ = this.actions$
    .ofType(SetupActions.FIND_PROGRAM_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findProgramCodes())
    .map((codes) => this.setupActions.findProgramCodesSuccess(codes));

  @Effect() saveProgramCodes$ = this.actions$
    .ofType(SetupActions.SAVE_PROGRAM_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveProgramCode(payload))
    .map((message) => this.setupActions.saveProgramCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findProgramCodes()]));

  @Effect() removeProgramCode$ = this.actions$
    .ofType(SetupActions.REMOVE_PROGRAM_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeProgramCode(payload))
    .map((message) => this.setupActions.removeProgramCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findProgramCodes()]));

  @Effect() updateProgramCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_PROGRAM_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateProgramCode(payload))
    .map((message) => this.setupActions.updateProgramCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findProgramCodes()]));
  
  //====================================================================================================
  // PROGRAM FIELD CODE
  // ====================================================================================================

  @Effect() findProgramFieldCodes$ = this.actions$
    .ofType(SetupActions.FIND_PROGRAM_FIELD_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findProgramFieldCodes())
    .map((codes) => this.setupActions.findProgramFieldCodesSuccess(codes));

  @Effect() saveProgramFieldCode$ = this.actions$
    .ofType(SetupActions.SAVE_PROGRAM_FIELD_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveProgramFieldCode(payload))
    .map((message) => this.setupActions.saveProgramFieldCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findProgramFieldCodes()]));

  @Effect() removeProgramFieldCode$ = this.actions$
    .ofType(SetupActions.REMOVE_PROGRAM_FIELD_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeProgramFieldCode(payload))
    .map((message) => this.setupActions.removeProgramFieldCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findProgramFieldCodes()]));

  @Effect() updateProgramFieldCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_PROGRAM_FIELD_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateProgramFieldCode(payload))
    .map((message) => this.setupActions.updateProgramFieldCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findProgramFieldCodes()]));

  // ====================================================================================================
  // SUPERVISOR CODE
  // ====================================================================================================

  @Effect() findSupervisorCodes$ = this.actions$
    .ofType(SetupActions.FIND_SUPERVISOR_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findSupervisorCodes())
    .map((codes) => this.setupActions.findSupervisorCodesSuccess(codes));

  @Effect() findSupervisorCodesByFilter$ = this.actions$
    .ofType(SetupActions.FIND_SUPERVISOR_CODES_BY_FILTER)
    .map((action) => action.payload)
    .switchMap((filter) => this.commonService.findSupervisorCodesByFilter(filter))
    .map((codes) => this.setupActions.findSupervisorCodesByFilterSuccess(codes));

  @Effect() saveSupervisorCodes$ = this.actions$
    .ofType(SetupActions.SAVE_SUPERVISOR_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveSupervisorCode(payload))
    .map((message) => this.setupActions.saveSupervisorCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSupervisorCodes()]));

  @Effect() updateSupervisorCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_SUPERVISOR_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateSupervisorCode(payload))
    .map((message) => this.setupActions.updateSupervisorCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSupervisorCodes()]));

  @Effect() removeSupervisorCode$ = this.actions$
    .ofType(SetupActions.REMOVE_SUPERVISOR_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeSupervisorCode(payload))
    .map((message) => this.setupActions.removeSupervisorCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSupervisorCodes()]));

// ====================================================================================================
  // SUPERVISOR OFFERING
  // ====================================================================================================

  @Effect() findSupervisorOfferings$ = this.actions$
  .ofType(SetupActions.FIND_SUPERVISOR_OFFERINGS)
  .map((action) => action.payload)
  .switchMap(() => this.commonService.findSupervisorOfferings())
  .map((offerings) => this.setupActions.findSupervisorOfferingsSuccess(offerings));

@Effect() findSupervisorOfferingsByFilter$ = this.actions$
  .ofType(SetupActions.FIND_SUPERVISOR_OFFERINGS_BY_FILTER)
  .map((action) => action.payload)
  .switchMap((filter) => this.commonService.findSupervisorOfferingsByFilter(filter))
  .map((offerings) => this.setupActions.findSupervisorOfferingsByFilterSuccess(offerings));

@Effect() saveSupervisorOfferings$ = this.actions$
  .ofType(SetupActions.SAVE_SUPERVISOR_OFFERING)
  .map((action) => action.payload)
  .switchMap((payload) => this.commonService.saveSupervisorOfferings(payload))
  .map((message) => this.setupActions.saveSupervisorOfferingsSuccess(message))
  .mergeMap((action) => from([action, this.setupActions.findSupervisorOfferings()]));

@Effect() updateSupervisorOfferings$ = this.actions$
  .ofType(SetupActions.UPDATE_SUPERVISOR_OFFERING)
  .map((action) => action.payload)
  .switchMap((payload) => this.commonService.updateSupervisorOfferings(payload))
  .map((message) => this.setupActions.updateSupervisorOfferingsSuccess(message))
  .mergeMap((action) => from([action, this.setupActions.findSupervisorOfferings()]));

@Effect() removeSupervisorOfferings$ = this.actions$
  .ofType(SetupActions.REMOVE_SUPERVISOR_OFFERING)
  .map((action) => action.payload)
  .switchMap((payload) => this.commonService.removeSupervisorOfferings(payload))
  .map((message) => this.setupActions.removeSupervisorOfferingsSuccess(message))
  .mergeMap((action) => from([action, this.setupActions.findSupervisorOfferings()]));
  // ====================================================================================================
  // RACE CODE
  // ====================================================================================================

  @Effect() findRaceCodes$ = this.actions$
    .ofType(SetupActions.FIND_RACE_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findRaceCodes())
    .map((codes) => this.setupActions.findRaceCodesSuccess(codes));

  @Effect() saveRaceCodes$ = this.actions$
    .ofType(SetupActions.SAVE_RACE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveRaceCode(payload))
    .map((message) => this.setupActions.saveRaceCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findRaceCodes()]));

    @Effect() removeRaceCode$ = this.actions$
    .ofType(SetupActions.REMOVE_RACE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeRaceCode(payload))
    .map((message) => this.setupActions.removeRaceCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findRaceCodes()]));

  @Effect() updateRaceCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_RACE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateRaceCode(payload))
    .map((message) => this.setupActions.updateRaceCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findRaceCodes()]));

  // ====================================================================================================
  // GENDER CODE
  // ====================================================================================================

  @Effect() findGenderCodes$ = this.actions$
    .ofType(SetupActions.FIND_GENDER_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findGenderCodes())
    .map((codes) => this.setupActions.findGenderCodesSuccess(codes));

  @Effect() saveGenderCodes$ = this.actions$
    .ofType(SetupActions.SAVE_GENDER_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveGenderCode(payload))
    .map((message) => this.setupActions.saveGenderCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGenderCodes()]));

     @Effect() removeGenderCode$ = this.actions$
    .ofType(SetupActions.REMOVE_GENDER_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeGenderCode(payload))
    .map((message) => this.setupActions.removeGenderCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGenderCodes()]));

  @Effect() updateGenderCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_GENDER_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateGenderCode(payload))
    .map((message) => this.setupActions.updateGenderCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGenderCodes()]));

  // ====================================================================================================
  // ETHNICITY CODE
  // ====================================================================================================

  @Effect() findEthnicityCode$ = this.actions$
    .ofType(SetupActions.FIND_ETHNICITY_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findEthnicityCodes())
    .map((codes) => this.setupActions.findEthnicityCodesSuccess(codes));

  @Effect() saveEthnicityCodes$ = this.actions$
    .ofType(SetupActions.SAVE_ETHNICITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveEthnicityCode(payload))
    .map((message) => this.setupActions.saveEthnicityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findEthnicityCodes()]));

  @Effect() updateEthnicityCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_ETHNICITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateEthnicityCode(payload))
    .map((message) => this.setupActions.updateEthnicityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findEthnicityCodes()]));

  @Effect() removeEthnicityCode$ = this.actions$
    .ofType(SetupActions.REMOVE_ETHNICITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeEthnicityCode(payload))
    .map((message) => this.setupActions.removeEthnicityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findEthnicityCodes()]));

  // ====================================================================================================
  // NATIONALITY CODE
  // ====================================================================================================

   @Effect() findNationalityCode$ = this.actions$
    .ofType(SetupActions.FIND_NATIONALITY_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findNationalityCodes())
    .map((codes) => this.setupActions.findNationalityCodesSuccess(codes));

  @Effect() saveNationalityCodes$ = this.actions$
    .ofType(SetupActions.SAVE_NATIONALITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveNationalityCode(payload))
    .map((message) => this.setupActions.saveNationalityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findNationalityCodes()]));

  @Effect() updateNationalityCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_NATIONALITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateNationalityCode(payload))
    .map((message) => this.setupActions.updateNationalityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findNationalityCodes()]));

  @Effect() removeNationalityCode$ = this.actions$
    .ofType(SetupActions.REMOVE_NATIONALITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeNationalityCode(payload))
    .map((message) => this.setupActions.removeNationalityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findNationalityCodes()]));

  // ====================================================================================================
  // FACULTY CODE
  // ====================================================================================================

  @Effect() findFacultyCodes$ = this.actions$
    .ofType(SetupActions.FIND_FACULTY_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findFacultyCodes())
    .map((codes) => this.setupActions.findFacultyCodesSuccess(codes));

  @Effect() saveFacultyCodes$ = this.actions$
    .ofType(SetupActions.SAVE_FACULTY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveFacultyCode(payload))
    .map((message) => this.setupActions.saveFacultyCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findFacultyCodes()]));

    @Effect() updateFacultyCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_FACULTY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateFacultyCode(payload))
    .map((message) => this.setupActions.updateFacultyCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findFacultyCodes()]));

  @Effect() removeFacultyCode$ = this.actions$
    .ofType(SetupActions.REMOVE_FACULTY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeFacultyCode(payload))
    .map((message) => this.setupActions.removeFacultyCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findFacultyCodes()]));
  
  
  // ====================================================================================================
  // FIELD CODE
  // ====================================================================================================

  @Effect() findFieldCodes$ = this.actions$
    .ofType(SetupActions.FIND_FIELD_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findFieldCodes())
    .map((codes) => this.setupActions.findFieldCodesSuccess(codes));

  @Effect() saveFieldCodes$ = this.actions$
    .ofType(SetupActions.SAVE_FIELD_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveFieldCode(payload))
    .map((message) => this.setupActions.saveFieldCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findFieldCodes()]));

    @Effect() updateFieldCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_FIELD_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateFieldCode(payload))
    .map((message) => this.setupActions.updateFieldCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findFieldCodes()]));

  @Effect() removeFieldCode$ = this.actions$
    .ofType(SetupActions.REMOVE_FIELD_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeFieldCode(payload))
    .map((message) => this.setupActions.removeFieldCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findFieldCodes()]));


  // ====================================================================================================
  // STUDY MODE
  // ====================================================================================================

  @Effect() findStudyMode$ = this.actions$
    .ofType(SetupActions.FIND_STUDY_MODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findStudyModes())
    .map((codes) => this.setupActions.findStudyModesSuccess(codes));

  @Effect() saveStudyModes$ = this.actions$
    .ofType(SetupActions.SAVE_STUDY_MODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveStudyMode(payload))
    .map((message) => this.setupActions.saveStudyModeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStudyModes()]));

    @Effect() updateStudyModes$ = this.actions$
    .ofType(SetupActions.UPDATE_STUDY_MODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateStudyMode(payload))
    .map((message) => this.setupActions.updateStudyModeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStudyModes()]));

  @Effect() removeStudyMode$ = this.actions$
    .ofType(SetupActions.REMOVE_STUDY_MODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeStudyMode(payload))
    .map((message) => this.setupActions.removeStudyModeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStudyModes()]));

  // ====================================================================================================
  // DISABILITY CODE
  // ====================================================================================================

  @Effect() findDisabilityCode$ = this.actions$
    .ofType(SetupActions.FIND_DISABILITY_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findDisabilityCodes())
    .map((codes) => this.setupActions.findDisabilityCodesSuccess(codes));

  @Effect() saveDisabilityCodes$ = this.actions$
    .ofType(SetupActions.SAVE_DISABILITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveDisabilityCode(payload))
    .map((message) => this.setupActions.saveDisabilityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDisabilityCodes()]));

  @Effect() updateDisabilityCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_DISABILITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateDisabilityCode(payload))
    .map((message) => this.setupActions.updateDisabilityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDisabilityCodes()]));

  @Effect() removeDisabilityCode$ = this.actions$
    .ofType(SetupActions.REMOVE_DISABILITY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeDisabilityCode(payload))
    .map((message) => this.setupActions.removeDisabilityCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDisabilityCodes()]));

  // ====================================================================================================
  // SCHOOL CODE
  // ====================================================================================================

  @Effect() findSchoolCode$ = this.actions$
    .ofType(SetupActions.FIND_SCHOOL_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findSchoolCodes())
    .map((codes) => this.setupActions.findSchoolCodesSuccess(codes));

  @Effect() saveSchoolCodes$ = this.actions$
    .ofType(SetupActions.SAVE_SCHOOL_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveSchoolCode(payload))
    .map((message) => this.setupActions.saveSchoolCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSchoolCodes()]));

  @Effect() updateSchoolCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_SCHOOL_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateSchoolCode(payload))
    .map((message) => this.setupActions.updateSchoolCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSchoolCodes()]));

  @Effect() removeSchoolCode$ = this.actions$
    .ofType(SetupActions.REMOVE_SCHOOL_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeSchoolCode(payload))
    .map((message) => this.setupActions.removeSchoolCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSchoolCodes()]));

  //=====================================================================================================
  // STUDY CENTER CODE
  // ====================================================================================================

  @Effect() findStudyCenterCodes$ = this.actions$
    .ofType(SetupActions.FIND_STUDY_CENTER_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findStudyCenterCodes())
    .map((codes) => this.setupActions.findStudyCenterCodesSuccess(codes));

  @Effect() saveStudyCenterCodes$ = this.actions$
    .ofType(SetupActions.SAVE_STUDY_CENTER_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveStudyCenterCode(payload))
    .map((message) => this.setupActions.saveStudyCenterCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStudyCenterCodes()]));

  @Effect() updateStudyCenterCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_STUDY_CENTER_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateStudyCenterCode(payload))
    .map((message) => this.setupActions.updateStudyCenterCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStudyCenterCodes()]));

  @Effect() removeStudyCenterCode$ = this.actions$
    .ofType(SetupActions.REMOVE_STUDY_CENTER_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeStudyCenterCode(payload))
    .map((message) => this.setupActions.removeStudyCenterCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findStudyCenterCodes()]));

  // ====================================================================================================
  // DISTRICT CODE
  // ====================================================================================================

  @Effect() findDistrictCode$ = this.actions$
    .ofType(SetupActions.FIND_DISTRICT_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findDistrictCodes())
    .map((codes) => this.setupActions.findDistrictCodesSuccess(codes));

  @Effect() saveDistrictCodes$ = this.actions$
    .ofType(SetupActions.SAVE_DISTRICT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveDistrictCode(payload))
    .map((message) => this.setupActions.saveDistrictCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDistrictCodes()]));

  @Effect() updateDistrictCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_DISTRICT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateDistrictCode(payload))
    .map((message) => this.setupActions.updateDistrictCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDistrictCodes()]));

  @Effect() removeDistrictCode$ = this.actions$
    .ofType(SetupActions.REMOVE_DISTRICT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeDistrictCode(payload))
    .map((message) => this.setupActions.removeDistrictCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDistrictCodes()]));

  // ====================================================================================================
  // DUN CODE
  // ====================================================================================================

  @Effect() findDunCode$ = this.actions$
    .ofType(SetupActions.FIND_DUN_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findDunCodes())
    .map((codes) => this.setupActions.findDunCodesSuccess(codes));

  @Effect() saveDunCodes$ = this.actions$
    .ofType(SetupActions.SAVE_DUN_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveDunCode(payload))
    .map((message) => this.setupActions.saveDunCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDunCodes()]));

  @Effect() updateDunCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_DUN_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateDunCode(payload))
    .map((message) => this.setupActions.updateDunCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDunCodes()]));

  @Effect() removeDunCode$ = this.actions$
    .ofType(SetupActions.REMOVE_DUN_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeDunCode(payload))
    .map((message) => this.setupActions.removeDunCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findDunCodes()]));

  // ====================================================================================================
  // PARLIAMENT CODE
  // ====================================================================================================

  @Effect() findParliamentCode$ = this.actions$
    .ofType(SetupActions.FIND_PARLIAMENT_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findParliamentCodes())
    .map((codes) => this.setupActions.findParliamentCodesSuccess(codes));

  @Effect() saveParliamentCodes$ = this.actions$
    .ofType(SetupActions.SAVE_PARLIAMENT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveParliamentCode(payload))
    .map((message) => this.setupActions.saveParliamentCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findParliamentCodes()]));

  @Effect() updateParliamentCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_PARLIAMENT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateParliamentCode(payload))
    .map((message) => this.setupActions.updateParliamentCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findParliamentCodes()]));

  @Effect() removeParliamentCode$ = this.actions$
    .ofType(SetupActions.REMOVE_PARLIAMENT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeParliamentCode(payload))
    .map((message) => this.setupActions.removeParliamentCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findParliamentCodes()]));

  // ====================================================================================================
  // RESIDENCY CODE
  // ====================================================================================================

  @Effect() findResidencyCode$ = this.actions$
    .ofType(SetupActions.FIND_RESIDENCY_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findResidencyCodes())
    .map((codes) => this.setupActions.findResidencyCodesSuccess(codes));

  @Effect() saveResidencyCodes$ = this.actions$
    .ofType(SetupActions.SAVE_RESIDENCY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveResidencyCode(payload))
    .map((message) => this.setupActions.saveResidencyCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findResidencyCodes()]));

  @Effect() updateResidencyCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_RESIDENCY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateResidencyCode(payload))
    .map((message) => this.setupActions.updateResidencyCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findResidencyCodes()]));

  @Effect() removeResidencyCode$ = this.actions$
    .ofType(SetupActions.REMOVE_RESIDENCY_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeResidencyCode(payload))
    .map((message) => this.setupActions.removeResidencyCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findResidencyCodes()]));

  // ====================================================================================================
  // LANGUAGE CODE
  // ====================================================================================================

  @Effect() findLanguageCode$ = this.actions$
    .ofType(SetupActions.FIND_LANGUAGE_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findLanguageCodes())
    .map((codes) => this.setupActions.findLanguageCodesSuccess(codes));

  @Effect() saveLanguageCodes$ = this.actions$
    .ofType(SetupActions.SAVE_LANGUAGE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveLanguageCode(payload))
    .map((message) => this.setupActions.saveLanguageCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findLanguageCodes()]));

  @Effect() updateLanguageCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_LANGUAGE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateLanguageCode(payload))
    .map((message) => this.setupActions.updateLanguageCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findLanguageCodes()]));

  @Effect() removeLanguageCode$ = this.actions$
    .ofType(SetupActions.REMOVE_LANGUAGE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeLanguageCode(payload))
    .map((message) => this.setupActions.removeLanguageCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findLanguageCodes()]));

 // ====================================================================================================
  // SUBJECT CODE
  // ====================================================================================================

  @Effect() findSubjectCode$ = this.actions$
    .ofType(SetupActions.FIND_SUBJECT_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findSubjectCodes())
    .map((codes) => this.setupActions.findSubjectCodesSuccess(codes));

  @Effect() saveSubjectCodes$ = this.actions$
    .ofType(SetupActions.SAVE_SUBJECT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveSubjectCode(payload))
    .map((message) => this.setupActions.saveSubjectCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSubjectCodes()]));

  @Effect() updateSubjectCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_SUBJECT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateSubjectCode(payload))
    .map((message) => this.setupActions.updateSubjectCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSubjectCodes()]));

  @Effect() removeSubjectCode$ = this.actions$
    .ofType(SetupActions.REMOVE_SUBJECT_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeSubjectCode(payload))
    .map((message) => this.setupActions.removeSubjectCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findSubjectCodes()]));

  // ====================================================================================================
  // GRADE CODE
  // ====================================================================================================

  @Effect() findGradeCode$ = this.actions$
    .ofType(SetupActions.FIND_GRADE_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findGradeCodes())
    .map((codes) => this.setupActions.findGradeCodesSuccess(codes));

  @Effect() saveGradeCodes$ = this.actions$
    .ofType(SetupActions.SAVE_GRADE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.saveGradeCode(payload))
    .map((message) => this.setupActions.saveGradeCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGradeCodes()]));

  @Effect() updateGradeCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_GRADE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.updateGradeCode(payload))
    .map((message) => this.setupActions.updateGradeCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGradeCodes()]));

  @Effect() removeGradeCode$ = this.actions$
    .ofType(SetupActions.REMOVE_GRADE_CODE)
    .map((action) => action.payload)
    .switchMap((payload) => this.commonService.removeGradeCode(payload))
    .map((message) => this.setupActions.removeGradeCodeSuccess(message))
    .mergeMap((action) => from([action, this.setupActions.findGradeCodes()]));

  // ====================================================================================================
  // EMPLOYMENT TYPE CODE
  // ====================================================================================================

  @Effect() findEmploymentTypeCode$ = this.actions$
  .ofType(SetupActions.FIND_EMPLOYMENT_TYPE_CODES)
  .map((action) => action.payload)
  .switchMap(() => this.commonService.findEmploymentTypeCodes())
  .map((codes) => this.setupActions.findEmploymentTypeCodesSuccess(codes));

@Effect() saveEmploymentTypeCodes$ = this.actions$
  .ofType(SetupActions.SAVE_EMPLOYMENT_TYPE_CODE)
  .map((action) => action.payload)
  .switchMap((payload) => this.commonService.saveEmploymentTypeCode(payload))
  .map((message) => this.setupActions.saveEmploymentTypeCodeSuccess(message))
  .mergeMap((action) => from([action, this.setupActions.findEmploymentTypeCodes()]));

@Effect() updateEmploymentTypeCodes$ = this.actions$
  .ofType(SetupActions.UPDATE_EMPLOYMENT_TYPE_CODE)
  .map((action) => action.payload)
  .switchMap((payload) => this.commonService.updateEmploymentTypeCode(payload))
  .map((message) => this.setupActions.updateEmploymentTypeCodeSuccess(message))
  .mergeMap((action) => from([action, this.setupActions.findEmploymentTypeCodes()]));

@Effect() removeEmploymentTypeCode$ = this.actions$
  .ofType(SetupActions.REMOVE_EMPLOYMENT_TYPE_CODE)
  .map((action) => action.payload)
  .switchMap((payload) => this.commonService.removeEmploymentTypeCode(payload))
  .map((message) => this.setupActions.removeEmploymentTypeCodeSuccess(message))
  .mergeMap((action) => from([action, this.setupActions.findEmploymentTypeCodes()]));
}
