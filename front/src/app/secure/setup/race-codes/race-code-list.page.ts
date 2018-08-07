import { RaceCode } from './../../../shared/model/common/race-code.interface';
import {
  Component,
  Input,
  EventEmitter,
  Output,
  ChangeDetectionStrategy,
  OnInit,
  AfterViewInit,
  ViewContainerRef,
} from '@angular/core';
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {RaceCodeEditorDialog} from './dialog/race-code-editor.dialog';
@Component({
  selector: 'pams-race-list.page',
  templateUrl: './race-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RaceCodeListPage implements OnInit{
  private RACE_CODES = "setupModuleState.raceCodes".split(".");
  private raceCodes$: Observable<RaceCode[]>;
  private creatorDialogRef: MdDialogRef<RaceCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.raceCodes$ = this.store.select(...this.RACE_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findRaceCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Bangsa"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:RaceCode): void {
    this.showDialog(code);
  }
  delete(code: RaceCode): void {
    this.store.dispatch(this.actions.removeRaceCode(code))
  }

  private showDialog(code:RaceCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(RaceCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.raceCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
