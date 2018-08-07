import { EthnicityCode } from './../../../shared/model/common/ethnicity-code.interface';
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
import {EthnicityCodeEditorDialog} from './dialog/ethnicity-code-editor.dialog';
@Component({
  selector: 'pams-ethnicity-list.page',
  templateUrl: './ethnicity-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EthnicityCodeListPage implements OnInit{
  private ETHNICITY_CODES = "setupModuleState.ethnicityCodes".split(".");
  private ethnicityCodes$: Observable<EthnicityCode[]>;
  private creatorDialogRef: MdDialogRef<EthnicityCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.ethnicityCodes$ = this.store.select(...this.ETHNICITY_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findEthnicityCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Etnik"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:EthnicityCode): void {
    this.showDialog(code);
  }
  delete(code: EthnicityCode): void {
    this.store.dispatch(this.actions.removeEthnicityCode(code))
  }

  private showDialog(code:EthnicityCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(EthnicityCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.ethnicityCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
