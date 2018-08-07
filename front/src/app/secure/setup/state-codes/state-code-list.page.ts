import { StateCode } from './../../../shared/model/common/state-code.interface';
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
import {StateCodeEditorDialog} from './dialog/state-code-editor.dialog';
@Component({
  selector: 'pams-state-list.page',
  templateUrl: './state-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class StateCodeListPage implements OnInit{
  private STATE_CODES = "setupModuleState.stateCodes".split(".");
  private stateCodes$: Observable<StateCode[]>;
  private creatorDialogRef: MdDialogRef<StateCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.stateCodes$ = this.store.select(...this.STATE_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findStateCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Negeri"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:StateCode): void {
    this.showDialog(code);
  }
  delete(code: StateCode): void {
    this.store.dispatch(this.actions.removeStateCode(code))
  }

  private showDialog(code:StateCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(StateCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.stateCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
