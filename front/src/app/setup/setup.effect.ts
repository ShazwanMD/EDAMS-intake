import {Injectable} from "@angular/core";
import {Effect, Actions} from '@ngrx/effects';
import {SetupActions} from "./setup.action";
import {CommonService} from "../../services/common.service";
import {from} from "rxjs/observable/from";

@Injectable()
export class SetupEffects {
  constructor(private actions$: Actions,
              private commonActions: SetupActions,
              private commonService: CommonService) {
  }

  @Effect() findBankCode$ = this.actions$
    .ofType(SetupActions.FIND_BANK_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findBankCodes())
    .map(codes => this.commonActions.findBankCodesSuccess(codes))

  @Effect() findGraduateCentres$ = this.actions$
    .ofType(SetupActions.FIND_GRADUATE_CENTRES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findGraduateCentres())
    .map(codes => this.commonActions.findGraduateCentresSuccess(codes))

  @Effect() saveGraduateCentre$ = this.actions$
    .ofType(SetupActions.SAVE_GRADUATE_CENTRE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveGraduateCentre(payload))
    .map(message => this.commonActions.saveGraduateCentreSuccess(message))
    .mergeMap(action => from([action, this.commonActions.findGraduateCentres()]));

   @Effect() findReligionCode$ = this.actions$
    .ofType(SetupActions.FIND_RELIGION_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findReligionCodes())
    .map(codes => this.commonActions.findReligionCodesSuccess(codes))
}