import { NationalityCode } from './../../../shared/model/common/nationality-code.interface';
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
import {NationalityCodeEditorDialog} from './dialog/nationality-code-editor.dialog';

@Component({
  selector: 'pams-nationality-list.page',
  templateUrl: './nationality-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NationalityCodeListPage implements OnInit{
  private NATIONALITY_CODES = "setupModuleState.nationalityCodes".split(".");
  private nationalityCodes$: Observable<NationalityCode[]>;
  private creatorDialogRef: MdDialogRef<NationalityCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.nationalityCodes$ = this.store.select(...this.NATIONALITY_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findNationalityCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Kewarganegaraan"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:NationalityCode): void {
    this.showDialog(code);
  }
  delete(code: NationalityCode): void {
    this.store.dispatch(this.actions.removeNationalityCode(code))
  }

  private showDialog(code:NationalityCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(NationalityCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.nationalityCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
