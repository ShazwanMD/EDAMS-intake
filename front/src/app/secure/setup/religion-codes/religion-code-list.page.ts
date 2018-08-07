import { ReligionCode } from './../../../shared/model/common/religion-code.interface';
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
import {ReligionCodeEditorDialog} from './dialog/religion-code-editor.dialog';
@Component({
  selector: 'pams-religion-list.page',
  templateUrl: './religion-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReligionCodeListPage implements OnInit{
  private RELIGION_CODES = "setupModuleState.religionCodes".split(".");
  private religionCodes$: Observable<ReligionCode[]>;
  private creatorDialogRef: MdDialogRef<ReligionCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.religionCodes$ = this.store.select(...this.RELIGION_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findReligionCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Agama"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:ReligionCode): void {
    this.showDialog(code);
  }
  delete(code: ReligionCode): void {
    this.store.dispatch(this.actions.removeReligionCode(code))
  }

  private showDialog(code:ReligionCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(ReligionCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.religionCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
