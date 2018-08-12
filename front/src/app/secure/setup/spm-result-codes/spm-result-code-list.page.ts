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
import { SpmResultCode } from '../../../shared/model/common/spm-result-code.interface';
import { SpmResultCodeEditorDialog } from './dialog/spm-result-code-editor.dialog';
import { TouchSequence } from 'selenium-webdriver';

@Component({
  selector: 'pams-spm-result-code-list.page',
  templateUrl: './spm-result-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SpmResultCodeListPage implements OnInit{
  private SPM_RESULT_CODES = "setupModuleState.spmResultCodes".split(".");
  private spmResultCodes$: Observable<SpmResultCode[]>;
  private creatorDialogRef: MdDialogRef<SpmResultCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.spmResultCodes$ = this.store.select(...this.SPM_RESULT_CODES);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findSpmResultCodes());
    this.store.dispatch(this.actions.changeTitle("Spm Result Codes"));
  }

  // createDialog(): void {
  //   this.showDialog(null);
  // }
  // editDialog(code:ProgramFieldCode): void {
  //   this.showDialog(code);
  // }
  // delete(code: ProgramFieldCode): void {
  //   this.store.dispatch(this.actions.removeProgramFieldCode(code))
  // }

  // private showDialog(code:ProgramFieldCode): void {
  //   console.log("create");
  //   let config = new MdDialogConfig();
  //   config.viewContainerRef = this.vcf;
  //   config.role = 'dialog';
  //   config.width = '70%';
  //   config.height = '65%';
  //   config.position = {top: '0px'};
  //   this.creatorDialogRef = this.dialog.open(ProgramFieldCodeEditorDialog, config);
  //   if(code) this.creatorDialogRef.componentInstance.programFieldCode = code; // set
  //   this.creatorDialogRef.afterClosed().subscribe(res => {
  //     console.log("close dialog");
  //   });
  // }
}
