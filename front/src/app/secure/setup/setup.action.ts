import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class SetupActions {

  static CHANGE_TITLE = '[Setup] Change Title';

  changeTitle(title): Action {
    console.log('changeTitle');
    return {
      type: SetupActions.CHANGE_TITLE,
      payload: title,
    };
  }

  static CHANGE_TITLE_SUCCESS = '[Setup] Change Title Success';

  changeTitleSuccess(title): Action {
    console.log('changeTitleSuccess');
    return {
      type: SetupActions.CHANGE_TITLE_SUCCESS,
      payload: title,
    };
  }

  static FIND_BANK_CODES = '[Setup] Find Bank Codes';

  findBankCodes(): Action {
    console.log('findBankCodes');
    return {
      type: SetupActions.FIND_BANK_CODES,
    };
  }

  static FIND_BANK_CODES_SUCCESS = '[Setup] Find Bank Codes Success';

  findBankCodesSuccess(codes): Action {
    console.log('findBankCodesSuccess');
    return {
      type: SetupActions.FIND_BANK_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_BANK_CODE = '[Setup] Save Bank Code';

  saveBankCode(code): Action {
    console.log('saveBankCode');
    return {
      type: SetupActions.SAVE_BANK_CODE,
      payload: code,
    };
  }

  static SAVE_BANK_CODE_SUCCESS = '[Setup] Save Bank Code  Success';

  saveBankCodeSuccess(message): Action {
    console.log('saveBankCodeSuccess');
    return {
      type: SetupActions.SAVE_BANK_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_BANK_CODE = '[Setup] Update Bank Code';

  updateBankCode(code): Action {
    console.log('updateBankCode');
    return {
      type: SetupActions.UPDATE_BANK_CODE,
      payload: code,
    };
  }

  static UPDATE_BANK_CODE_SUCCESS = '[Setup] Update Bank Code  Success';

  updateBankCodeSuccess(message): Action {
    console.log('updateBankCodeSuccess');
    return {
      type: SetupActions.UPDATE_BANK_CODE_SUCCESS,
      payload: message,
    };
  }
//=================================================================================//
//  GRADUATE_CODES                                                                 //
//=================================================================================//
 //1-------------------------------------------------------------
  static FIND_GRADUATE_CENTERS = '[Setup] Find Graduate Centers';

  findGraduateCenters(): Action {
    console.log('findGraduateCenters');
    return {
      type: SetupActions.FIND_GRADUATE_CENTERS,
    };
  }
//2---------------------------------------------------------------------
  static FIND_GRADUATE_CENTERS_SUCCESS = '[Setup] Find Graduate Centers Success';

  findGraduateCentersSuccess(codes): Action {
    console.log('findGraduateCentersSuccess');
    return {
      type: SetupActions.FIND_GRADUATE_CENTERS_SUCCESS,
      payload: codes,
    };
  }
//3-------------------------------------------------------------------------
  static SAVE_GRADUATE_CENTER = '[Setup] Save Graduate Center';

  saveGraduateCenter(code): Action {
    console.log('saveGraduateCenter');
    return {
      type: SetupActions.SAVE_GRADUATE_CENTER,
      payload: code,
    };
  }
//4--------------------------------------------------------------------------
  static SAVE_GRADUATE_CENTER_SUCCESS = '[Setup] Save Graduate Center  Success';

  saveGraduateCenterSuccess(message): Action {
    console.log('saveGraduateCenterSuccess');
    return {
      type: SetupActions.SAVE_GRADUATE_CENTER_SUCCESS,
      payload: message,
    };
  }
//5---------------------------------------------------------------------------
  static UPDATE_GRADUATE_CENTER = '[Setup] Update Graduate Center';

  updateGraduateCenter(code): Action {
    console.log('updateGraduateCenter');
    return {
      type: SetupActions.UPDATE_GRADUATE_CENTER,
      payload: code,
    };
  }
//6-----------------------------------------------------------------------
  static UPDATE_GRADUATE_CENTER_SUCCESS = '[Setup] Update Graduate Center  Success';

  updateGraduateCenterSuccess(message): Action {
    console.log('updateGraduateCenterSuccess');
    return {
      type: SetupActions.UPDATE_GRADUATE_CENTER_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_GRADUATE_CENTER = '[Setup] Remove Graduate Center';

  removeGraduateCenter(code): Action {
    console.log('removeGraduateCenter');
    return {
      type: SetupActions.REMOVE_GRADUATE_CENTER,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_GRADUATE_CENTER_SUCCESS = '[Setup] Remove Graduate Center  Success';

  removeGraduateCenterSuccess(message): Action {
    console.log('removeGraduateCenterSuccess');
    return {
      type: SetupActions.REMOVE_GRADUATE_CENTER_SUCCESS,
      payload: message,
    };
  }


//=================================================================================//
//  RELIGION_CODES                                                                 //
//=================================================================================//
 //1-------------------------------------------------------------
  static FIND_RELIGION_CODES = '[Setup] Find Religion Codes';

  findReligionCodes(): Action {
    console.log('findReligionCodes');
    return {
      type: SetupActions.FIND_RELIGION_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_RELIGION_CODES_SUCCESS = '[Setup] Find Religion Codes Success';

  findReligionCodesSuccess(codes): Action {
    console.log('findReligionCodesSuccess');
    return {
      type: SetupActions.FIND_RELIGION_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_RELIGION_CODE = '[Setup] Save Religion Code';

  saveReligionCode(code): Action {
    console.log('saveReligionCode');
    return {
      type: SetupActions.SAVE_RELIGION_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_RELIGION_CODE_SUCCESS = '[Setup] Save Religion Code Success';

  saveReligionCodeSuccess(message): Action {
    console.log('saveReligionCodeSuccess');
    return {
      type: SetupActions.SAVE_RELIGION_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_RELIGION_CODE = '[Setup] Update Religion Code';

  updateReligionCode(code): Action {
    console.log('updateReligionCode');
    return {
      type: SetupActions.UPDATE_RELIGION_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_RELIGION_CODE_SUCCESS = '[Setup] Update Religion Code  Success';

  updateReligionCodeSuccess(message): Action {
    console.log('updateReligionCodeSuccess');
    return {
      type: SetupActions.UPDATE_RELIGION_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_RELIGION_CODE = '[Setup] Remove Religion Code';

  removeReligionCode(code): Action {
    console.log('removeReligionCode');
    return {
      type: SetupActions.REMOVE_RELIGION_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_RELIGION_CODE_SUCCESS = '[Setup] Remove Religion Code  Success';

  removeReligionCodeSuccess(message): Action {
    console.log('removeReligionCodeSuccess');
    return {
      type: SetupActions.REMOVE_RELIGION_CODE_SUCCESS,
      payload: message,
    };
  }


  //=================================================================================//
  //  VENUE_CODES                                                               //
  //=================================================================================//

  static FIND_VENUE_CODES = '[Setup] Find Venue Codes';

  findVenueCodes(): Action {
    console.log('findVenueCodes');
    return {
      type: SetupActions.FIND_VENUE_CODES,
    };
  }

  static FIND_VENUE_CODES_SUCCESS = '[Setup] Find Venue Codes Success';

  findVenueCodesSuccess(codes): Action {
    console.log('findVenueCodesSuccess');
    return {
      type: SetupActions.FIND_VENUE_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_VENUE_CODE = '[Setup] Save Venue Code';

  saveVenueCode(code): Action {
    console.log('saveVenueCode');
    return {
      type: SetupActions.SAVE_VENUE_CODE,
      payload: code,
    };
  }

  static SAVE_VENUE_CODE_SUCCESS = '[Setup] Save Venue Code Success';

  saveVenueCodeSuccess(message): Action {
    console.log('saveVenueCodeSuccess');
    return {
      type: SetupActions.SAVE_VENUE_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_VENUE_CODE = '[Setup] Update Venue Code';

  updateVenueCode(code): Action {
    console.log('updateVenueCode');
    return {
      type: SetupActions.UPDATE_VENUE_CODE,
      payload: code,
    };
  }

  static UPDATE_VENUE_CODE_SUCCESS = '[Setup] Update Venue Code Success';

  updateVenueCodeSuccess(message): Action {
    console.log('updateVenueCodeSuccess');
    return {
      type: SetupActions.UPDATE_VENUE_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_VENUE_CODE = '[Setup] Remove Venue Code';

  removeVenueCode(code): Action {
    console.log('removeVenueCode');
    return {
      type: SetupActions.REMOVE_VENUE_CODE,
      payload: code,
    };
  }

  static REMOVE_VENUE_CODE_SUCCESS = '[Setup] Remove Venue Code Success';

  removeVenueCodeSuccess(message): Action {
    console.log('removeVenueCodeSuccess');
    return {
      type: SetupActions.REMOVE_VENUE_CODE_SUCCESS,
      payload: message,
    };
  }

  //=================================================================================//
  //  MARITAL_CODES                                                               //
  //=================================================================================//

  static FIND_MARITAL_CODES = '[Setup] Find Marital Codes';

  findMaritalCodes(): Action {
    console.log('findMaritalCodes');
    return {
      type: SetupActions.FIND_MARITAL_CODES,
    };
  }

  static FIND_MARITAL_CODES_SUCCESS = '[Setup] Find Marital Codes Success';

  findMaritalCodesSuccess(codes): Action {
    console.log('findMaritalCodesSuccess');
    return {
      type: SetupActions.FIND_MARITAL_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_MARITAL_CODE = '[Setup] Save Marital Code';

  saveMaritalCode(code): Action {
    console.log('saveMaritalCode');
    return {
      type: SetupActions.SAVE_MARITAL_CODE,
      payload: code,
    };
  }

  static SAVE_MARITAL_CODE_SUCCESS = '[Setup] Save Marital Code Success';

  saveMaritalCodeSuccess(message): Action {
    console.log('saveMaritalCodeSuccess');
    return {
      type: SetupActions.SAVE_MARITAL_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_MARITAL_CODE = '[Setup] Update Marital Code';

  updateMaritalCode(code): Action {
    console.log('updateMaritalCode');
    return {
      type: SetupActions.UPDATE_MARITAL_CODE,
      payload: code,
    };
  }

  static UPDATE_MARITAL_CODE_SUCCESS = '[Setup] Update Marital Code  Success';

  updateMaritalCodeSuccess(message): Action {
    console.log('updateMaritalCodeSuccess');
    return {
      type: SetupActions.UPDATE_MARITAL_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_MARITAL_CODE = '[Setup] Remove Marital Code';

  removeMaritalCode(code): Action {
    console.log('removeMaritalCode');
    return {
      type: SetupActions.REMOVE_MARITAL_CODE,
      payload: code,
    };
  }

  static REMOVE_MARITAL_CODE_SUCCESS = '[Setup] Remove Marital Code  Success';

  removeMaritalCodeSuccess(message): Action {
    console.log('removeMaritalCodeSuccess');
    return {
      type: SetupActions.REMOVE_MARITAL_CODE_SUCCESS,
      payload: message,
    };
  }
  //=================================================================================//
  //  COUNTRY_CODES                                                               //
  //=================================================================================//
  //1---------------------------------------------------------------
  static FIND_COUNTRY_CODES = '[Setup] Find Country Codes';

  findCountryCodes(): Action {
    console.log('findCountryCodes');
    return {
      type: SetupActions.FIND_COUNTRY_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_COUNTRY_CODES_SUCCESS = '[Setup] Find Country Codes Success';

  findCountryCodesSuccess(codes): Action {
    console.log('findCountryCodesSuccess');
    return {
      type: SetupActions.FIND_COUNTRY_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_COUNTRY_CODE = '[Setup] Save Country Code';

  saveCountryCode(code): Action {
    console.log('saveCountryCode');
    return {
      type: SetupActions.SAVE_COUNTRY_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_COUNTRY_CODE_SUCCESS = '[Setup] Save Country Code Success';

  saveCountryCodeSuccess(message): Action {
    console.log('saveCountryCodeSuccess');
    return {
      type: SetupActions.SAVE_COUNTRY_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_COUNTRY_CODE = '[Setup] Update Country Code';

  updateCountryCode(code): Action {
    console.log('updateCountryCode');
    return {
      type: SetupActions.UPDATE_COUNTRY_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_COUNTRY_CODE_SUCCESS = '[Setup] Update Country Code  Success';

  updateCountryCodeSuccess(message): Action {
    console.log('updateCountryCodeSuccess');
    return {
      type: SetupActions.UPDATE_COUNTRY_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_COUNTRY_CODE = '[Setup] Remove Country Code';

  removeCountryCode(code): Action {
    console.log('removeCountryCode');
    return {
      type: SetupActions.REMOVE_COUNTRY_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_COUNTRY_CODE_SUCCESS = '[Setup] Remove Country Code  Success';

  removeCountryCodeSuccess(message): Action {
    console.log('removeCountryCodeSuccess');
    return {
      type: SetupActions.REMOVE_COUNTRY_CODE_SUCCESS,
      payload: message,
    };
  }

//-------------------------------------------------------------------------------------

  static FIND_STATE_CODES_SUCCESS = '[Common] Find StateCodes Success';

  findStateCodesSuccess(codes): Action {
    console.log('findStateCodesSuccess');
    return {
      type: SetupActions.FIND_STATE_CODES_SUCCESS,
      payload: codes,
    };
  }

  static FIND_STATE_CODES = '[Common] Find StateCodes';

  findStateCodes(): Action {
    console.log('findStateCodes');
    return {
      type: SetupActions.FIND_STATE_CODES,
    };
  }

   static SAVE_STATE_CODE = '[Setup] Save State Code';

  saveStateCode(code): Action {
    console.log('saveStateCode');
    return {
      type: SetupActions.SAVE_STATE_CODE,
      payload: code,
    };
  }

  static SAVE_STATE_CODE_SUCCESS = '[Setup] Save State Code Success';

  saveStateCodeSuccess(message): Action {
    console.log('saveStateCodeSuccess');
    return {
      type: SetupActions.SAVE_STATE_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_STATE_CODE = '[Setup] Update State Code';

  updateStateCode(code): Action {
    console.log('updateStateCode');
    return {
      type: SetupActions.UPDATE_STATE_CODE,
      payload: code,
    };
  }

  static UPDATE_STATE_CODE_SUCCESS = '[Setup] Update State Code  Success';

  updateStateCodeSuccess(message): Action {
    console.log('updateStateCodeSuccess');
    return {
      type: SetupActions.UPDATE_STATE_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_STATE_CODE = '[Setup] Remove State Code';

  removeStateCode(code): Action {
    console.log('removeStateCode');
    return {
      type: SetupActions.REMOVE_STATE_CODE,
      payload: code,
    };
  }

  static REMOVE_STATE_CODE_SUCCESS = '[Setup] Remove State Code  Success';

  removeStateCodeSuccess(message): Action {
    console.log('removeStateCodeSuccess');
    return {
      type: SetupActions.REMOVE_STATE_CODE_SUCCESS,
      payload: message,
    };
  }

  static FIND_PROGRAM_CODES = '[Common] Find ProgramCodes';

  findProgramCodes(): Action {
    console.log('findProgramCodes');
    return {
      type: SetupActions.FIND_PROGRAM_CODES,
    };
  }

  static FIND_PROGRAM_CODES_SUCCESS = '[Common] Find ProgramCodes Success';

  findProgramCodesSuccess(codes): Action {
    console.log('findProgramCodesSuccess');
    return {
      type: SetupActions.FIND_PROGRAM_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_PROGRAM_CODE = '[Setup] Save Program Code';

  saveProgramCode(code): Action {
    console.log('saveProgramCode');
    return {
      type: SetupActions.SAVE_PROGRAM_CODE,
      payload: code,
    };
  }

  static SAVE_PROGRAM_CODE_SUCCESS = '[Setup] Save Program Code Success';

  saveProgramCodeSuccess(message): Action {
    console.log('saveProgramCodeSuccess');
    return {
      type: SetupActions.SAVE_PROGRAM_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_PROGRAM_CODE = '[Setup] Remove Program Code';

  removeProgramCode(code): Action {
    console.log('removeProgramCode');
    return {
      type: SetupActions.REMOVE_PROGRAM_CODE,
      payload: code,
    };
  }

  static REMOVE_PROGRAM_CODE_SUCCESS = '[Setup] Remove Program Code Success';

  removeProgramCodeSuccess(message): Action {
    console.log('removeProgramCodeSuccess');
    return {
      type: SetupActions.REMOVE_PROGRAM_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_PROGRAM_CODE = '[Setup] Update Program Code';

  updateProgramCode(code): Action {
    console.log('updateProgramCode');
    return {
      type: SetupActions.UPDATE_PROGRAM_CODE,
      payload: code,
    };
  }

  static UPDATE_PROGRAM_CODE_SUCCESS = '[Setup] Update Program Code  Success';

  updateProgramCodeSuccess(message): Action {
    console.log('updateProgramCodeSuccess');
    return {
      type: SetupActions.UPDATE_PROGRAM_CODE_SUCCESS,
      payload: message,
    };
  }
  
  static FIND_PROGRAM_FIELD_CODES = '[Common] Find Program Field Codes';

  findProgramFieldCodes(): Action {
    console.log('findProgramFieldCodes');
    return {
      type: SetupActions.FIND_PROGRAM_FIELD_CODES,
    };
  }

  static FIND_PROGRAM_FIELD_CODES_SUCCESS = '[Common] Find Program Field Codes Success';

  findProgramFieldCodesSuccess(codes): Action {
    console.log('findProgramFieldCodesSuccess');
    return {
      type: SetupActions.FIND_PROGRAM_FIELD_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_PROGRAM_FIELD_CODE = '[Setup] Save Program Field Code';

  saveProgramFieldCode(code): Action {
    console.log('saveProgramFieldCode');
    return {
      type: SetupActions.SAVE_PROGRAM_FIELD_CODE,

      payload:code,
    };
  }

  static SAVE_PROGRAM_FIELD_CODE_SUCCESS = '[Setup] Save Program Field Code Success';

  saveProgramFieldCodeSuccess(message): Action {
    console.log('saveProgramFieldCodeSuccess');
    return {
      type: SetupActions.SAVE_PROGRAM_FIELD_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_PROGRAM_FIELD_CODE = '[Setup] Remove Program Field Code';

  removeProgramFieldCode(code): Action {
    console.log('removeProgramFieldCode');
    return {
      type: SetupActions.REMOVE_PROGRAM_FIELD_CODE,
      payload: code,
    };
  }

  static REMOVE_PROGRAM_FIELD_CODE_SUCCESS = '[Setup] Remove Program Field Code Success';

  removeProgramFieldCodeSuccess(message): Action {
    console.log('removeProgramFieldCodeSuccess');
    return {
      type: SetupActions.REMOVE_PROGRAM_FIELD_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_PROGRAM_FIELD_CODE = '[Setup] Update Program Field Code';

  updateProgramFieldCode(code): Action {
    console.log('updateProgramFieldCode');
    return {
      type: SetupActions.UPDATE_PROGRAM_FIELD_CODE,
      payload: code,
    };
  }

  static UPDATE_PROGRAM_FIELD_CODE_SUCCESS = '[Setup] Update Program Field Code  Success';

  updateProgramFieldCodeSuccess(message): Action {
    console.log('updateProgramFieldCodeSuccess');
    return {
      type: SetupActions.UPDATE_PROGRAM_FIELD_CODE_SUCCESS,
      payload: message,
    };
  }

  static SAVE_SUPERVISOR_CODE = '[Setup] Save Supervisor Code';

  saveSupervisorCode(code): Action {
    console.log('saveSupervisorCode');
    return {
      type: SetupActions.SAVE_SUPERVISOR_CODE,
      payload: code,
    };
  }

  static SAVE_SUPERVISOR_CODE_SUCCESS = '[Setup] Save Supervisor Code Success';

  saveSupervisorCodeSuccess(message): Action {
    console.log('saveSupervisorCodeSuccess');
    return {
      type: SetupActions.SAVE_SUPERVISOR_CODE_SUCCESS,
      payload: message,
    };
  }

  static FIND_SUPERVISOR_CODES = '[Common] Find SupervisorCodes';

  findSupervisorCodes(): Action {
    console.log('findSupervisorCodes');
    return {
      type: SetupActions.FIND_SUPERVISOR_CODES,
    };
  }

  static FIND_SUPERVISOR_CODES_SUCCESS = '[Common] Find SupervisorCodes Success';

  findSupervisorCodesSuccess(codes): Action {
    console.log('findSupervisorCodesSuccess');
    return {
      type: SetupActions.FIND_SUPERVISOR_CODES_SUCCESS,
      payload: codes,
    };
  }

  static FIND_SUPERVISOR_CODES_BY_FILTER = '[Common] Find SupervisorCodes By Filter';

  findSupervisorCodesByFilter(filter): Action {
    console.log('findSupervisorCodesByFilter');
    return {
      type: SetupActions.FIND_SUPERVISOR_CODES_BY_FILTER,
      payload: filter,
    };
  }

  static FIND_SUPERVISOR_CODES_BY_FILTER_SUCCESS = '[Common] Find SupervisorCodes By Filter Success';

  findSupervisorCodesByFilterSuccess(codes): Action {
    console.log('findSupervisorCodesByFilterSuccess');
    return {
      type: SetupActions.FIND_SUPERVISOR_CODES_BY_FILTER_SUCCESS,
      payload: codes,
    };
  }

  static REMOVE_SUPERVISOR_CODE = '[Setup] Remove Supervisor Code';

  removeSupervisorCode(code): Action {
    console.log('removeSupervisorCode');
    return {
      type: SetupActions.REMOVE_SUPERVISOR_CODE,
      payload: code,
    };
  }

  static REMOVE_SUPERVISOR_CODE_SUCCESS = '[Setup] Remove Supervisor Code Success';

  removeSupervisorCodeSuccess(message): Action {
    console.log('removeSupervisorCodeSuccess');
    return {
      type: SetupActions.REMOVE_SUPERVISOR_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_SUPERVISOR_CODE = '[Setup] Update Supervisor Code';

  updateSupervisorCode(code): Action {
    console.log('updateSupervisorCode');
    return {
      type: SetupActions.UPDATE_SUPERVISOR_CODE,
      payload: code,
    };
  }

  static UPDATE_SUPERVISOR_CODE_SUCCESS = '[Setup] Update Supervisor Code Success';

  updateSupervisorCodeSuccess(message): Action {
    console.log('updateSupervisorCodeSuccess');
    return {
      type: SetupActions.UPDATE_SUPERVISOR_CODE_SUCCESS,
      payload: message,
    };
  }
//=================================================================================//
//  SUPERVISOR OFFERINGS                                                                    //
//=================================================================================//

static SAVE_SUPERVISOR_OFFERING = '[Setup] Save Supervisor Offering';

  saveSupervisorOfferings(offering): Action {
    console.log('saveSupervisorOfferings');
    return {
      type: SetupActions.SAVE_SUPERVISOR_OFFERING,
      payload: offering,
    };
  }

  static SAVE_SUPERVISOR_OFFERING_SUCCESS = '[Setup] Save Supervisor Offering Success';

  saveSupervisorOfferingsSuccess(message): Action {
    console.log('saveSupervisorOfferingsSuccess');
    return {
      type: SetupActions.SAVE_SUPERVISOR_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS = '[Common] Find SupervisorOfferings';

  findSupervisorOfferings(): Action {
    console.log('findSupervisorOfferings');
    return {
      type: SetupActions.FIND_SUPERVISOR_OFFERINGS,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS_SUCCESS = '[Common] Find SupervisorOfferings Success';

  findSupervisorOfferingsSuccess(offerings): Action {
    console.log('findSupervisorOfferingsSuccess');
    return {
      type: SetupActions.FIND_SUPERVISOR_OFFERINGS_SUCCESS,
      payload: offerings,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS_BY_FILTER = '[Common] Find SupervisorOfferings By Filter';

  findSupervisorOfferingsByFilter(filter): Action {
    console.log('findSupervisorOfferingsByFilter');
    return {
      type: SetupActions.FIND_SUPERVISOR_OFFERINGS_BY_FILTER,
      payload: filter,
    };
  }

  static FIND_SUPERVISOR_OFFERINGS_BY_FILTER_SUCCESS = '[Common] Find SupervisorOfferings By Filter Success';

  findSupervisorOfferingsByFilterSuccess(offerings): Action {
    console.log('findSupervisorOfferingsByFilterSuccess');
    return {
      type: SetupActions.FIND_SUPERVISOR_OFFERINGS_BY_FILTER_SUCCESS,
      payload: offerings,
    };
  }

  static REMOVE_SUPERVISOR_OFFERING = '[Setup] Remove Supervisor Offerings';

  removeSupervisorOfferings(offering): Action {
    console.log('removeSupervisorOfferings');
    return {
      type: SetupActions.REMOVE_SUPERVISOR_OFFERING,
      payload: offering,
    };
  }

  static REMOVE_SUPERVISOR_OFFERING_SUCCESS = '[Setup] Remove Supervisor Offering Success';

  removeSupervisorOfferingsSuccess(message): Action {
    console.log('removeSupervisorOfferingsSuccess');
    return {
      type: SetupActions.REMOVE_SUPERVISOR_OFFERING_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_SUPERVISOR_OFFERING = '[Setup] Update Supervisor Offering';

  updateSupervisorOfferings(offering): Action {
    console.log('updateSupervisorOfferings');
    return {
      type: SetupActions.UPDATE_SUPERVISOR_OFFERING,
      payload: offering,
    };
  }

  static UPDATE_SUPERVISOR_OFFERING_SUCCESS = '[Setup] Update Supervisor Offering Success';

  updateSupervisorOfferingsSuccess(message): Action {
    console.log('updateSupervisorOfferingsSuccess');
    return {
      type: SetupActions.UPDATE_SUPERVISOR_OFFERING_SUCCESS,
      payload: message,
    };
  }
  
//=================================================================================//
//  RACE_CODES                                                                     //
//=================================================================================//
  //1----------------------------------------------------
  static FIND_RACE_CODES = '[Common] Find RaceCodes';

  findRaceCodes(): Action {
    console.log('findRaceCodes');
    return {
      type: SetupActions.FIND_RACE_CODES,
    };
  }
  //2-------------------------------------------------------------------
  static FIND_RACE_CODES_SUCCESS = '[Common] Find Race Codes Success';

  findRaceCodesSuccess(codes): Action {
    console.log('findRaceCodesSuccess');
    return {
      type: SetupActions.FIND_RACE_CODES_SUCCESS,
      payload: codes,
    };
  }
  //3------------------------------------------------------------------------
  static SAVE_RACE_CODE = '[Setup] Save Race Code';

  saveRaceCode(code): Action {
    console.log('saveRaceCode');
    return {
      type: SetupActions.SAVE_RACE_CODE,
      payload: code,
    };
  }
  //4--------------------------------------------------------------------------
  static SAVE_RACE_CODE_SUCCESS = '[Setup] Save Race Code Success';

  saveRaceCodeSuccess(message): Action {
    console.log('saveRaceCodeSuccess');
    return {
      type: SetupActions.SAVE_RACE_CODE_SUCCESS,
      payload: message,
    };
  }
  //5------------------------------------------------------------------
  static UPDATE_RACE_CODE = '[Setup] Update Race Code';

  updateRaceCode(code): Action {
    console.log('updateRaceCode');
    return {
      type: SetupActions.UPDATE_RACE_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_RACE_CODE_SUCCESS = '[Setup] Update Race Code  Success';

  updateRaceCodeSuccess(message): Action {
    console.log('updateRaceCodeSuccess');
    return {
      type: SetupActions.UPDATE_RACE_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_RACE_CODE = '[Setup] Remove Race Code';

  removeRaceCode(code): Action {
    console.log('removeRaceCode');
    return {
      type: SetupActions.REMOVE_RACE_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_RACE_CODE_SUCCESS = '[Setup] Remove Race Code  Success';

  removeRaceCodeSuccess(message): Action {
    console.log('removeRaceCodeSuccess');
    return {
      type: SetupActions.REMOVE_RACE_CODE_SUCCESS,
      payload: message,
    };
  }

  //=================================================================================//
  //  GENDER_CODES                                                               //
  //=================================================================================//
  //1--------------------------------------
  static FIND_GENDER_CODES = '[Common] Find Gender Codes';

  findGenderCodes(): Action {
    console.log('findGenderCodes');
    return {
      type: SetupActions.FIND_GENDER_CODES,
    };
  }
//2-------------------------------------------------------
  static FIND_GENDER_CODES_SUCCESS = '[Common] Find Gender Codes Success';

  findGenderCodesSuccess(codes): Action {
    console.log('findGenderCodesSuccess');
    return {
      type: SetupActions.FIND_GENDER_CODES_SUCCESS,
      payload: codes,
    };
  }
//3-----------------------------------------------------
  static SAVE_GENDER_CODE = '[Setup] Save Gender Code';

  saveGenderCode(code): Action {
    console.log('saveGenderCode');
    return {
      type: SetupActions.SAVE_GENDER_CODE,
      payload: code,
    };
  }
//4--------------------------------------------------------------
  static SAVE_GENDER_CODE_SUCCESS = '[Setup] Save Gender Code Success';

  saveGenderCodeSuccess(message): Action {
    console.log('saveGenderCodeSuccess');
    return {
      type: SetupActions.SAVE_GENDER_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_GENDER_CODE = '[Setup] Update Gender Code';

  updateGenderCode(code): Action {
    console.log('updateGenderCode');
    return {
      type: SetupActions.UPDATE_GENDER_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_GENDER_CODE_SUCCESS = '[Setup] Update Gender Code  Success';

  updateGenderCodeSuccess(message): Action {
    console.log('updateGenderCodeSuccess');
    return {
      type: SetupActions.UPDATE_GENDER_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_GENDER_CODE = '[Setup] Remove Gender Code';

  removeGenderCode(code): Action {
    console.log('removeGenderCode');
    return {
      type: SetupActions.REMOVE_GENDER_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_GENDER_CODE_SUCCESS = '[Setup] Remove Gender Code  Success';

  removeGenderCodeSuccess(message): Action {
    console.log('removeGenderCodeSuccess');
    return {
      type: SetupActions.REMOVE_GENDER_CODE_SUCCESS,
      payload: message,
    };
  }
//==========================================================================================
//      ETHNICITY CODE
//==========================================================================================
  //1-------------------------------------------------------------
  static FIND_ETHNICITY_CODES = '[Setup] Find Ethnicity Codes';

  findEthnicityCodes(): Action {
    console.log('findEthnicityCodes');
    return {
      type: SetupActions.FIND_ETHNICITY_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_ETHNICITY_CODES_SUCCESS = '[Setup] Find Ethnicity Codes Success';

  findEthnicityCodesSuccess(codes): Action {
    console.log('findEthnicityCodesSuccess');
    return {
      type: SetupActions.FIND_ETHNICITY_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_ETHNICITY_CODE = '[Setup] Save Ethnicity Code';

  saveEthnicityCode(code): Action {
    console.log('saveEthnicityCode');
    return {
      type: SetupActions.SAVE_ETHNICITY_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_ETHNICITY_CODE_SUCCESS = '[Setup] Save Ethnicity Code Success';

  saveEthnicityCodeSuccess(message): Action {
    console.log('saveEthnicityCodeSuccess');
    return {
      type: SetupActions.SAVE_ETHNICITY_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_ETHNICITY_CODE = '[Setup] Update Ethnicity Code';

  updateEthnicityCode(code): Action {
    console.log('updateEthnicityCode');
    return {
      type: SetupActions.UPDATE_ETHNICITY_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_ETHNICITY_CODE_SUCCESS = '[Setup] Update Ethnicity Code  Success';

  updateEthnicityCodeSuccess(message): Action {
    console.log('updateEthnicityCodeSuccess');
    return {
      type: SetupActions.UPDATE_ETHNICITY_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_ETHNICITY_CODE = '[Setup] Remove Ethnicity Code';

  removeEthnicityCode(code): Action {
    console.log('removeEthnicityCode');
    return {
      type: SetupActions.REMOVE_ETHNICITY_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_ETHNICITY_CODE_SUCCESS = '[Setup] Remove Ethnicity Code  Success';

  removeEthnicityCodeSuccess(message): Action {
    console.log('removeEthnicityCodeSuccess');
    return {
      type: SetupActions.REMOVE_ETHNICITY_CODE_SUCCESS,
      payload: message,
    };
  }

//==========================================================================================
//      NATIONALITY CODE
//==========================================================================================
  //1-------------------------------------------------------------
  static FIND_NATIONALITY_CODES = '[Setup] Find Nationality Codes';

  findNationalityCodes(): Action {
    console.log('findNationalityCodes');
    return {
      type: SetupActions.FIND_NATIONALITY_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_NATIONALITY_CODES_SUCCESS = '[Setup] Find Nationality Codes Success';

  findNationalityCodesSuccess(codes): Action {
    console.log('findNationalityCodesSuccess');
    return {
      type: SetupActions.FIND_NATIONALITY_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_NATIONALITY_CODE = '[Setup] Save Nationality Code';

  saveNationalityCode(code): Action {
    console.log('saveNationalityCode');
    return {
      type: SetupActions.SAVE_NATIONALITY_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_NATIONALITY_CODE_SUCCESS = '[Setup] Save Nationality Code Success';

  saveNationalityCodeSuccess(message): Action {
    console.log('saveNationalityCodeSuccess');
    return {
      type: SetupActions.SAVE_NATIONALITY_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_NATIONALITY_CODE = '[Setup] Update Nationality Code';

  updateNationalityCode(code): Action {
    console.log('updateNationalityCode');
    return {
      type: SetupActions.UPDATE_NATIONALITY_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_NATIONALITY_CODE_SUCCESS = '[Setup] Update Nationality Code  Success';

  updateNationalityCodeSuccess(message): Action {
    console.log('updateNationalityCodeSuccess');
    return {
      type: SetupActions.UPDATE_NATIONALITY_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_NATIONALITY_CODE = '[Setup] Remove Nationality Code';

  removeNationalityCode(code): Action {
    console.log('removeNationalityCode');
    return {
      type: SetupActions.REMOVE_NATIONALITY_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_NATIONALITY_CODE_SUCCESS = '[Setup] Remove Nationality Code  Success';

  removeNationalityCodeSuccess(message): Action {
    console.log('removeNationalityCodeSuccess');
    return {
      type: SetupActions.REMOVE_NATIONALITY_CODE_SUCCESS,
      payload: message,
    };
  }
  //==========================================================================================
  //      FIELD CODE
  //==========================================================================================
  //1-------------------------------------------------------------
  static FIND_FIELD_CODES = '[Setup] Find Field Codes';

  findFieldCodes(): Action {
    console.log('findFieldCodes');
    return {
      type: SetupActions.FIND_FIELD_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_FIELD_CODES_SUCCESS = '[Setup] Find Field Codes Success';

  findFieldCodesSuccess(codes): Action {
    console.log('findFieldCodesSuccess');
    return {
      type: SetupActions.FIND_FIELD_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_FIELD_CODE = '[Setup] Save Field Code';

  saveFieldCode(code): Action {
    console.log('saveFieldCode');
    return {
      type: SetupActions.SAVE_FIELD_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_FIELD_CODE_SUCCESS = '[Setup] Save Field Code Success';

  saveFieldCodeSuccess(message): Action {
    console.log('saveFieldCodeSuccess');
    return {
      type: SetupActions.SAVE_FIELD_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_FIELD_CODE = '[Setup] Update Field Code';

  updateFieldCode(code): Action {
    console.log('updateFieldCode');
    return {
      type: SetupActions.UPDATE_FIELD_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_FIELD_CODE_SUCCESS = '[Setup] Update Field Code  Success';

  updateFieldCodeSuccess(message): Action {
    console.log('updateFieldCodeSuccess');
    return {
      type: SetupActions.UPDATE_FIELD_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_FIELD_CODE = '[Setup] Remove Field Code';

  removeFieldCode(code): Action {
    console.log('removeFieldCode');
    return {
      type: SetupActions.REMOVE_FIELD_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_FIELD_CODE_SUCCESS = '[Setup] Remove Field Code  Success';

  removeFieldCodeSuccess(message): Action {
    console.log('removeFieldCodeSuccess');
    return {
      type: SetupActions.REMOVE_FIELD_CODE_SUCCESS,
      payload: message,
    };
  }

  //==========================================================================================
  //      FACULTY CODE
  //==========================================================================================
  //1-------------------------------------------------------------
  static FIND_FACULTY_CODES = '[Setup] Find Faculty Codes';

  findFacultyCodes(): Action {
    console.log('findFacultyCodes');
    return {
      type: SetupActions.FIND_FACULTY_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_FACULTY_CODES_SUCCESS = '[Setup] Find Faculty Codes Success';

  findFacultyCodesSuccess(codes): Action {
    console.log('findFacultyCodesSuccess');
    return {
      type: SetupActions.FIND_FACULTY_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_FACULTY_CODE = '[Setup] Save Faculty Code';

  saveFacultyCode(code): Action {
    console.log('saveFacultyCode');
    return {
      type: SetupActions.SAVE_FACULTY_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_FACULTY_CODE_SUCCESS = '[Setup] Save Faculty Code Success';

  saveFacultyCodeSuccess(message): Action {
    console.log('saveFacultyCodeSuccess');
    return {
      type: SetupActions.SAVE_FACULTY_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_FACULTY_CODE = '[Setup] Update Faculty Code';

  updateFacultyCode(code): Action {
    console.log('updateFacultyCode');
    return {
      type: SetupActions.UPDATE_FACULTY_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_FACULTY_CODE_SUCCESS = '[Setup] Update Faculty Code  Success';

  updateFacultyCodeSuccess(message): Action {
    console.log('updateFacultyCodeSuccess');
    return {
      type: SetupActions.UPDATE_FACULTY_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_FACULTY_CODE = '[Setup] Remove Faculty Code';

  removeFacultyCode(code): Action {
    console.log('removeFacultyCode');
    return {
      type: SetupActions.REMOVE_FACULTY_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_FACULTY_CODE_SUCCESS = '[Setup] Remove Faculty Code  Success';

  removeFacultyCodeSuccess(message): Action {
    console.log('removeFacultyCodeSuccess');
    return {
      type: SetupActions.REMOVE_FACULTY_CODE_SUCCESS,
      payload: message,
    };
  }

//==========================================================================================
//     STUDY MODE
//==========================================================================================
  //1-------------------------------------------------------------
  static SAVE_STUDY_MODE = '[Setup] Save Study Mode';

  saveStudyMode(code): Action {
    console.log('saveStudyMode');
    return {
      type: SetupActions.SAVE_STUDY_MODE,
      payload: code,
    };
  }
//2-----------------------------------------------
  static SAVE_STUDY_MODE_SUCCESS = '[Setup] Save Study Mode Success';

  saveStudyModeSuccess(message): Action {
    console.log('saveStudyModeSuccess');
    return {
      type: SetupActions.SAVE_STUDY_MODE_SUCCESS,
      payload: message,
    };
  }
//3-----------------------------------------------------
  static FIND_STUDY_MODES = '[Common] Find Study Modes';

  findStudyModes(): Action {
    console.log('findStudyModes');
    return {
      type: SetupActions.FIND_STUDY_MODES,
    };
  }
//4-----------------------------------------------------------------
  static FIND_STUDY_MODES_SUCCESS = '[Common] Find Study Modes Success';

  findStudyModesSuccess(codes): Action {
    console.log('findStudyModesSuccess');
    return {
      type: SetupActions.FIND_STUDY_MODES_SUCCESS,
      payload: codes,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_STUDY_MODE = '[Setup] Update Study Mode';

  updateStudyMode(code): Action {
    console.log('updateStudyMode');
    return {
      type: SetupActions.UPDATE_STUDY_MODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_STUDY_MODE_SUCCESS = '[Setup] Update Study Mode  Success';

  updateStudyModeSuccess(message): Action {
    console.log('updateStudyModeSuccess');
    return {
      type: SetupActions.UPDATE_STUDY_MODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_STUDY_MODE = '[Setup] Remove Study Mode';

  removeStudyMode(code): Action {
    console.log('removeStudyModeCode');
    return {
      type: SetupActions.REMOVE_STUDY_MODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_STUDY_MODE_SUCCESS = '[Setup] Remove Study Mode  Success';

  removeStudyModeSuccess(message): Action {
    console.log('removeStudyModeSuccess');
    return {
      type: SetupActions.REMOVE_STUDY_MODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  DISABILITY_CODES                                                               //
//=================================================================================//
//1-------------------------------------------------------------
  static FIND_DISABILITY_CODES = '[Setup] Find Disability Codes';

  findDisabilityCodes(): Action {
    console.log('findDisabilityCodes');
    return {
      type: SetupActions.FIND_DISABILITY_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_DISABILITY_CODES_SUCCESS = '[Setup] Find Disability Codes Success';

  findDisabilityCodesSuccess(codes): Action {
    console.log('findDisabilityCodesSuccess');
    return {
      type: SetupActions.FIND_DISABILITY_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_DISABILITY_CODE = '[Setup] Save Disability Code';

  saveDisabilityCode(code): Action {
    console.log('saveDisabilityCode');
    return {
      type: SetupActions.SAVE_DISABILITY_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_DISABILITY_CODE_SUCCESS = '[Setup] Save Disability Code Success';

  saveDisabilityCodeSuccess(message): Action {
    console.log('saveDisabilityCodeSuccess');
    return {
      type: SetupActions.SAVE_DISABILITY_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_DISABILITY_CODE = '[Setup] Update Disability Code';

  updateDisabilityCode(code): Action {
    console.log('updateDisabilityCode');
    return {
      type: SetupActions.UPDATE_DISABILITY_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_DISABILITY_CODE_SUCCESS = '[Setup] Update Disability Code  Success';

  updateDisabilityCodeSuccess(message): Action {
    console.log('updateDisabilityCodeSuccess');
    return {
      type: SetupActions.UPDATE_DISABILITY_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_DISABILITY_CODE = '[Setup] Remove Disability Code';

  removeDisabilityCode(code): Action {
    console.log('removeDisabilityCode');
    return {
      type: SetupActions.REMOVE_DISABILITY_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_DISABILITY_CODE_SUCCESS = '[Setup] Remove Disability Code  Success';

  removeDisabilityCodeSuccess(message): Action {
    console.log('removeDisabilityCodeSuccess');
    return {
      type: SetupActions.REMOVE_DISABILITY_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  SCHOOL_CODES                                                                   //
//=================================================================================//

  static FIND_SCHOOL_CODES = '[Setup] Find School Codes';

  findSchoolCodes(): Action {
    console.log('findSchoolCodes');
    return {
      type: SetupActions.FIND_SCHOOL_CODES,
    };
  }

  static FIND_SCHOOL_CODES_SUCCESS = '[Setup] Find School Codes Success';

  findSchoolCodesSuccess(codes): Action {
    console.log('findSchoolCodesSuccess');
    return {
      type: SetupActions.FIND_SCHOOL_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_SCHOOL_CODE = '[Setup] Save School Code';

  saveSchoolCode(code): Action {
    console.log('saveSchoolCode');
    return {
      type: SetupActions.SAVE_SCHOOL_CODE,
      payload: code,
    };
  }

  static SAVE_SCHOOL_CODE_SUCCESS = '[Setup] Save School Code Success';

  saveSchoolCodeSuccess(message): Action {
    console.log('saveSchoolCodeSuccess');
    return {
      type: SetupActions.SAVE_SCHOOL_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_SCHOOL_CODE = '[Setup] Update School Code';

  updateSchoolCode(code): Action {
    console.log('updateSchoolCode');
    return {
      type: SetupActions.UPDATE_SCHOOL_CODE,
      payload: code,
    };
  }

  static UPDATE_SCHOOL_CODE_SUCCESS = '[Setup] Update School Code  Success';

  updateSchoolCodeSuccess(message): Action {
    console.log('updateSchoolCodeSuccess');
    return {
      type: SetupActions.UPDATE_SCHOOL_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_SCHOOL_CODE = '[Setup] Remove School Code';

  removeSchoolCode(code): Action {
    console.log('removeSchoolCode');
    return {
      type: SetupActions.REMOVE_SCHOOL_CODE,
      payload: code,
    };
  }

  static REMOVE_SCHOOL_CODE_SUCCESS = '[Setup] Remove School Code  Success';

  removeSchoolCodeSuccess(message): Action {
    console.log('removeSchoolCodeSuccess');
    return {
      type: SetupActions.REMOVE_SCHOOL_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  STUDY CENTER CODES                                                                   //
//=================================================================================//
//1----------------------------------------------------------------------------
    static FIND_STUDY_CENTER_CODES = '[Setup] Find Study Center Codes';

  findStudyCenterCodes(): Action {
    console.log('findStudyCenterCodes');
    return {
      type: SetupActions.FIND_STUDY_CENTER_CODES,
    };
  }
//2-----------------------------------------------------------------------------------
  static FIND_STUDY_CENTER_CODES_SUCCESS = '[Setup] Study Center Codes Success';

  findStudyCenterCodesSuccess(codes): Action {
    console.log('findStudyCenterCodesSuccess');
    return {
      type: SetupActions.FIND_STUDY_CENTER_CODES_SUCCESS,
      payload: codes,
    };
  }
//3---------------------------------------------------------------------------------------
  static SAVE_STUDY_CENTER_CODE = '[Setup] Save Study Center Code';

  saveStudyCenterCode(code): Action {
    console.log('saveStudyCenterCode');
    return {
      type: SetupActions.SAVE_STUDY_CENTER_CODE,
      payload: code,
    };
  }
//4----------------------------------------------------------------------------------------
 static SAVE_STUDY_CENTER_CODE_SUCCESS = '[Setup] Save Study Center Code Success';

  saveStudyCenterCodeSuccess(message): Action {
    console.log('saveStudyCenterCodeSuccess');
    return {
      type: SetupActions.SAVE_STUDY_CENTER_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------------------------------
  static UPDATE_STUDY_CENTER_CODE = '[Setup] Update Study Center Code';

  updateStudyCenterCode(code): Action {
    console.log('updateStudyCenterCode');
    return {
      type: SetupActions.UPDATE_STUDY_CENTER_CODE,
      payload: code,
    };
  }
//6------------------------------------------------------------------------------------------------
  static UPDATE_STUDY_CENTER_CODE_SUCCESS = '[Setup] Update Study Center Code Success';

  updateStudyCenterCodeSuccess(message): Action {
    console.log('updateStudyCenterCodeSuccess');
    return {
      type: SetupActions.UPDATE_STUDY_CENTER_CODE_SUCCESS,
      payload: message,
    };
  }
//7-----------------------------------------------------------------------------------------------
static REMOVE_STUDY_CENTER_CODE = '[Setup] Remove Study Center Code';

  removeStudyCenterCode(code): Action {
    console.log('removeStudyCenterCode');
    return {
      type: SetupActions.REMOVE_STUDY_CENTER_CODE,
      payload: code,
    };
  }
//8-----------------------------------------------------------------------------------------
static REMOVE_STUDY_CENTER_CODE_SUCCESS = '[Setup] Remove Study Center Code Success';

  removeStudyCenterCodeSuccess(message): Action {
    console.log('removeStudyCenterCodeSuccess');
    return {
      type: SetupActions.REMOVE_STUDY_CENTER_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  DISTRICT_CODES                                                               //
//=================================================================================//
//1-------------------------------------------------------------
  static FIND_DISTRICT_CODES = '[Setup] Find District Codes';

  findDistrictCodes(): Action {
    console.log('findDistrictCodes');
    return {
      type: SetupActions.FIND_DISTRICT_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_DISTRICT_CODES_SUCCESS = '[Setup] Find District Codes Success';

  findDistrictCodesSuccess(codes): Action {
    console.log('findDistrictCodesSuccess');
    return {
      type: SetupActions.FIND_DISTRICT_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_DISTRICT_CODE = '[Setup] Save District Code';

  saveDistrictCode(code): Action {
    console.log('saveDistrictCode');
    return {
      type: SetupActions.SAVE_DISTRICT_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_DISTRICT_CODE_SUCCESS = '[Setup] Save District Code Success';

  saveDistrictCodeSuccess(message): Action {
    console.log('saveDistrictCodeSuccess');
    return {
      type: SetupActions.SAVE_DISTRICT_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_DISTRICT_CODE = '[Setup] Update District Code';

  updateDistrictCode(code): Action {
    console.log('updateDistrictCode');
    return {
      type: SetupActions.UPDATE_DISTRICT_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_DISTRICT_CODE_SUCCESS = '[Setup] Update District Code  Success';

  updateDistrictCodeSuccess(message): Action {
    console.log('updateDistrictCodeSuccess');
    return {
      type: SetupActions.UPDATE_DISTRICT_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_DISTRICT_CODE = '[Setup] Remove District Code';

  removeDistrictCode(code): Action {
    console.log('removeDistrictCode');
    return {
      type: SetupActions.REMOVE_DISTRICT_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_DISTRICT_CODE_SUCCESS = '[Setup] Remove District Code  Success';

  removeDistrictCodeSuccess(message): Action {
    console.log('removeDistrictCodeSuccess');
    return {
      type: SetupActions.REMOVE_DISTRICT_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  DUN_CODES                                                                      //
//=================================================================================//
//1-------------------------------------------------------------
  static FIND_DUN_CODES = '[Setup] Find Dun Codes';

  findDunCodes(): Action {
    console.log('findDunCodes');
    return {
      type: SetupActions.FIND_DUN_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_DUN_CODES_SUCCESS = '[Setup] Find Dun Codes Success';

  findDunCodesSuccess(codes): Action {
    console.log('findDunCodesSuccess');
    return {
      type: SetupActions.FIND_DUN_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_DUN_CODE = '[Setup] Save Dun Code';

  saveDunCode(code): Action {
    console.log('saveDunCode');
    return {
      type: SetupActions.SAVE_DUN_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_DUN_CODE_SUCCESS = '[Setup] Save Dun Code Success';

  saveDunCodeSuccess(message): Action {
    console.log('saveDunCodeSuccess');
    return {
      type: SetupActions.SAVE_DUN_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_DUN_CODE = '[Setup] Update Dun Code';

  updateDunCode(code): Action {
    console.log('updateDunCode');
    return {
      type: SetupActions.UPDATE_DUN_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_DUN_CODE_SUCCESS = '[Setup] Update Dun Code  Success';

  updateDunCodeSuccess(message): Action {
    console.log('updateDunCodeSuccess');
    return {
      type: SetupActions.UPDATE_DUN_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_DUN_CODE = '[Setup] Remove Dun Code';

  removeDunCode(code): Action {
    console.log('removeDunCode');
    return {
      type: SetupActions.REMOVE_DUN_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_DUN_CODE_SUCCESS = '[Setup] Remove Dun Code  Success';

  removeDunCodeSuccess(message): Action {
    console.log('removeDunCodeSuccess');
    return {
      type: SetupActions.REMOVE_DUN_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  PARLIAMENT_CODES                                                                      //
//=================================================================================//
//1-------------------------------------------------------------
  static FIND_PARLIAMENT_CODES = '[Setup] Find Parliament Codes';

  findParliamentCodes(): Action {
    console.log('findParliamentCodes');
    return {
      type: SetupActions.FIND_PARLIAMENT_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_PARLIAMENT_CODES_SUCCESS = '[Setup] Find Parliament Codes Success';

  findParliamentCodesSuccess(codes): Action {
    console.log('findParliamentCodesSuccess');
    return {
      type: SetupActions.FIND_PARLIAMENT_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_PARLIAMENT_CODE = '[Setup] Save Parliament Code';

  saveParliamentCode(code): Action {
    console.log('saveParliamentCode');
    return {
      type: SetupActions.SAVE_PARLIAMENT_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_PARLIAMENT_CODE_SUCCESS = '[Setup] Save Parliament Code Success';

  saveParliamentCodeSuccess(message): Action {
    console.log('saveParliamentCodeSuccess');
    return {
      type: SetupActions.SAVE_PARLIAMENT_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_PARLIAMENT_CODE = '[Setup] Update Parliament Code';

  updateParliamentCode(code): Action {
    console.log('updateParliamentCode');
    return {
      type: SetupActions.UPDATE_PARLIAMENT_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_PARLIAMENT_CODE_SUCCESS = '[Setup] Update Parliament Code  Success';

  updateParliamentCodeSuccess(message): Action {
    console.log('updateParliamentCodeSuccess');
    return {
      type: SetupActions.UPDATE_PARLIAMENT_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_PARLIAMENT_CODE = '[Setup] Remove Parliament Code';

  removeParliamentCode(code): Action {
    console.log('removeParliamentCode');
    return {
      type: SetupActions.REMOVE_PARLIAMENT_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_PARLIAMENT_CODE_SUCCESS = '[Setup] Remove Parliament Code  Success';

  removeParliamentCodeSuccess(message): Action {
    console.log('removeParliamentCodeSuccess');
    return {
      type: SetupActions.REMOVE_PARLIAMENT_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  RESIDENCY_CODES
//=================================================================================//
//1-------------------------------------------------------------
  static FIND_RESIDENCY_CODES = '[Setup] Find Residency Codes';

  findResidencyCodes(): Action {
    console.log('findResidencyCodes');
    return {
      type: SetupActions.FIND_RESIDENCY_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_RESIDENCY_CODES_SUCCESS = '[Setup] Find Residency Codes Success';

  findResidencyCodesSuccess(codes): Action {
    console.log('findResidencyCodesSuccess');
    return {
      type: SetupActions.FIND_RESIDENCY_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_RESIDENCY_CODE = '[Setup] Save Residency Code';

  saveResidencyCode(code): Action {
    console.log('saveResidencyCode');
    return {
      type: SetupActions.SAVE_RESIDENCY_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_RESIDENCY_CODE_SUCCESS = '[Setup] Save Residency Code Success';

  saveResidencyCodeSuccess(message): Action {
    console.log('saveResidencyCodeSuccess');
    return {
      type: SetupActions.SAVE_RESIDENCY_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_RESIDENCY_CODE = '[Setup] Update Residency Code';

  updateResidencyCode(code): Action {
    console.log('updateResidencyCode');
    return {
      type: SetupActions.UPDATE_RESIDENCY_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_RESIDENCY_CODE_SUCCESS = '[Setup] Update Residency Code  Success';

  updateResidencyCodeSuccess(message): Action {
    console.log('updateResidencyCodeSuccess');
    return {
      type: SetupActions.UPDATE_RESIDENCY_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_RESIDENCY_CODE = '[Setup] Remove Residency Code';

  removeResidencyCode(code): Action {
    console.log('removeResidencyCode');
    return {
      type: SetupActions.REMOVE_RESIDENCY_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_RESIDENCY_CODE_SUCCESS = '[Setup] Remove Residency Code  Success';

  removeResidencyCodeSuccess(message): Action {
    console.log('removeResidencyCodeSuccess');
    return {
      type: SetupActions.REMOVE_RESIDENCY_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  LANGUAGE_CODES
//=================================================================================//
//1-------------------------------------------------------------
  static FIND_LANGUAGE_CODES = '[Setup] Find Language Codes';

  findLanguageCodes(): Action {
    console.log('findLanguageCodes');
    return {
      type: SetupActions.FIND_LANGUAGE_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_LANGUAGE_CODES_SUCCESS = '[Setup] Find Language Codes Success';

  findLanguageCodesSuccess(codes): Action {
    console.log('findLanguageCodesSuccess');
    return {
      type: SetupActions.FIND_LANGUAGE_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_LANGUAGE_CODE = '[Setup] Save Language Code';

  saveLanguageCode(code): Action {
    console.log('saveLanguageCode');
    return {
      type: SetupActions.SAVE_LANGUAGE_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_LANGUAGE_CODE_SUCCESS = '[Setup] Save Language Code Success';

  saveLanguageCodeSuccess(message): Action {
    console.log('saveLanguageCodeSuccess');
    return {
      type: SetupActions.SAVE_LANGUAGE_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_LANGUAGE_CODE = '[Setup] Update Language Code';

  updateLanguageCode(code): Action {
    console.log('updateLanguageCode');
    return {
      type: SetupActions.UPDATE_LANGUAGE_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_LANGUAGE_CODE_SUCCESS = '[Setup] Update Language Code  Success';

  updateLanguageCodeSuccess(message): Action {
    console.log('updateLanguageCodeSuccess');
    return {
      type: SetupActions.UPDATE_LANGUAGE_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_LANGUAGE_CODE = '[Setup] Remove Language Code';

  removeLanguageCode(code): Action {
    console.log('removeLanguageCode');
    return {
      type: SetupActions.REMOVE_LANGUAGE_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_LANGUAGE_CODE_SUCCESS = '[Setup] Remove Language Code  Success';

  removeLanguageCodeSuccess(message): Action {
    console.log('removeLanguageCodeSuccess');
    return {
      type: SetupActions.REMOVE_LANGUAGE_CODE_SUCCESS,
      payload: message,
    };
  }
//------------------------------------------------------------------------------------------

//=================================================================================//
//  SUBJECT_CODES                                                                   //
//=================================================================================//

  static FIND_SUBJECT_CODES = '[Setup] Find Subject Codes';

  findSubjectCodes(): Action {
    console.log('findSubjectCodes');
    return {
      type: SetupActions.FIND_SUBJECT_CODES,
    };
  }

  static FIND_SUBJECT_CODES_SUCCESS = '[Setup] Find Subject Codes Success';

  findSubjectCodesSuccess(codes): Action {
    console.log('findSubjectCodesSuccess');
    return {
      type: SetupActions.FIND_SUBJECT_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_SUBJECT_CODE = '[Setup] Save Subject Code';

  saveSubjectCode(code): Action {
    console.log('saveSubjectCode');
    return {
      type: SetupActions.SAVE_SUBJECT_CODE,
      payload: code,
    };
  }

  static SAVE_SUBJECT_CODE_SUCCESS = '[Setup] Save Subject Code Success';

  saveSubjectCodeSuccess(message): Action {
    console.log('saveSubjectCodeSuccess');
    return {
      type: SetupActions.SAVE_SUBJECT_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_SUBJECT_CODE = '[Setup] Update Subject Code';

  updateSubjectCode(code): Action {
    console.log('updateSubjectCode');
    return {
      type: SetupActions.UPDATE_SUBJECT_CODE,
      payload: code,
    };
  }

  static UPDATE_SUBJECT_CODE_SUCCESS = '[Setup] Update Subject Code  Success';

  updateSubjectCodeSuccess(message): Action {
    console.log('updateSubjectCodeSuccess');
    return {
      type: SetupActions.UPDATE_SUBJECT_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_SUBJECT_CODE = '[Setup] Remove Subject Code';

  removeSubjectCode(code): Action {
    console.log('removeSubjectCode');
    return {
      type: SetupActions.REMOVE_SUBJECT_CODE,
      payload: code,
    };
  }

  static REMOVE_SUBJECT_CODE_SUCCESS = '[Setup] Remove Subject Code  Success';

  removeSubjectCodeSuccess(message): Action {
    console.log('removeSubjectCodeSuccess');
    return {
      type: SetupActions.REMOVE_SUBJECT_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  GRADE_CODES                                                                      //
//=================================================================================//
//1-------------------------------------------------------------
  static FIND_GRADE_CODES = '[Setup] Find Grade Codes';

  findGradeCodes(): Action {
    console.log('findGradeCodes');
    return {
      type: SetupActions.FIND_GRADE_CODES,
    };
  }
//2-------------------------------------------------------------------------------------
  static FIND_GRADE_CODES_SUCCESS = '[Setup] Find Grade Codes Success';

  findGradeCodesSuccess(codes): Action {
    console.log('findGradeCodesSuccess');
    return {
      type: SetupActions.FIND_GRADE_CODES_SUCCESS,
      payload: codes,
    };
  }
//3----------------------------------------------------------------
  static SAVE_GRADE_CODE = '[Setup] Save Grade Code';

  saveGradeCode(code): Action {
    console.log('saveGradeCode');
    return {
      type: SetupActions.SAVE_GRADE_CODE,
      payload: code,
    };
  }
//4------------------------------------------------------------------------------
  static SAVE_GRADE_CODE_SUCCESS = '[Setup] Save Grade Code Success';

  saveGradeCodeSuccess(message): Action {
    console.log('saveGradeCodeSuccess');
    return {
      type: SetupActions.SAVE_GRADE_CODE_SUCCESS,
      payload: message,
    };
  }
//5------------------------------------------------------------------
  static UPDATE_GRADE_CODE = '[Setup] Update Grade Code';

  updateGradeCode(code): Action {
    console.log('updateGradeCode');
    return {
      type: SetupActions.UPDATE_GRADE_CODE,
      payload: code,
    };
  }
//6----------------------------------------------------------------------------------
  static UPDATE_GRADE_CODE_SUCCESS = '[Setup] Update Grade Code  Success';

  updateGradeCodeSuccess(message): Action {
    console.log('updateGradeCodeSuccess');
    return {
      type: SetupActions.UPDATE_GRADE_CODE_SUCCESS,
      payload: message,
    };
  }
//7------------------------------------------------------------------
  static REMOVE_GRADE_CODE = '[Setup] Remove Grade Code';

  removeGradeCode(code): Action {
    console.log('removeGradeCode');
    return {
      type: SetupActions.REMOVE_GRADE_CODE,
      payload: code,
    };
  }
//8------------------------------------------------------------------------------------
  static REMOVE_GRADE_CODE_SUCCESS = '[Setup] Remove Grade Code  Success';

  removeGradeCodeSuccess(message): Action {
    console.log('removeGradeCodeSuccess');
    return {
      type: SetupActions.REMOVE_GRADE_CODE_SUCCESS,
      payload: message,
    };
  }

//=================================================================================//
//  EMPLOYMENT_TYPE_CODES                                                                      //
//=================================================================================//

  static FIND_EMPLOYMENT_TYPE_CODES = '[Setup] Find Employment Type Codes';

  findEmploymentTypeCodes(): Action {
    console.log('findEmploymentTypeCodes');
    return {
      type: SetupActions.FIND_EMPLOYMENT_TYPE_CODES,
    };
  }

  static FIND_EMPLOYMENT_TYPE_CODES_SUCCESS = '[Setup] Find Employment Type Codes Success';

  findEmploymentTypeCodesSuccess(codes): Action {
    console.log('findEmploymentTypeCodesSuccess');
    return {
      type: SetupActions.FIND_EMPLOYMENT_TYPE_CODES_SUCCESS,
      payload: codes,
    };
  }

  static SAVE_EMPLOYMENT_TYPE_CODE = '[Setup] Save Employment Type Code';

  saveEmploymentTypeCode(code): Action {
    console.log('saveEmploymentTypeCode');
    return {
      type: SetupActions.SAVE_EMPLOYMENT_TYPE_CODE,
      payload: code,
    };
  }

  static SAVE_EMPLOYMENT_TYPE_CODE_SUCCESS = '[Setup] Save Employment Type Code Success';

  saveEmploymentTypeCodeSuccess(message): Action {
    console.log('saveEmploymentTypeCodeSuccess');
    return {
      type: SetupActions.SAVE_EMPLOYMENT_TYPE_CODE_SUCCESS,
      payload: message,
    };
  }

  static UPDATE_EMPLOYMENT_TYPE_CODE = '[Setup] Update Employment Type Code';

  updateEmploymentTypeCode(code): Action {
    console.log('updateEmploymentTypeCode');
    return {
      type: SetupActions.UPDATE_EMPLOYMENT_TYPE_CODE,
      payload: code,
    };
  }

  static UPDATE_EMPLOYMENT_TYPE_CODE_SUCCESS = '[Setup] Update Employment Type Code  Success';

  updateEmploymentTypeCodeSuccess(message): Action {
    console.log('updateEmploymentTypeCodeSuccess');
    return {
      type: SetupActions.UPDATE_EMPLOYMENT_TYPE_CODE_SUCCESS,
      payload: message,
    };
  }

  static REMOVE_EMPLOYMENT_TYPE_CODE = '[Setup] Remove Employment Type Code';

  removeEmploymentTypeCode(code): Action {
    console.log('removeEmploymentTypeCode');
    return {
      type: SetupActions.REMOVE_EMPLOYMENT_TYPE_CODE,
      payload: code,
    };
  }

  static REMOVE_EMPLOYMENT_TYPE_CODE_SUCCESS = '[Setup] Remove Employment Type Code  Success';

  removeEmploymentTypeCodeSuccess(message): Action {
    console.log('removeEmploymentTypeCodeSuccess');
    return {
      type: SetupActions.REMOVE_EMPLOYMENT_TYPE_CODE_SUCCESS,
      payload: message,
    };
  }
}
