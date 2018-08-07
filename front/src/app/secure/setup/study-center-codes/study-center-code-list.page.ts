import { StudyCenterCode } from './../../../shared/model/common/study-center-code.interface';
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
import {StudyCenterCodeEditorDialog} from './dialog/study-center-code-editor.dialog';
@Component({
  selector: 'pams-study-center-list.page',
  templateUrl: './study-center-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class StudyCenterCodeListPage implements OnInit{
  private STUDY_CENTER_CODES = "setupModuleState.studyCenterCodes".split(".");
  private studyCenterCodes$: Observable<StudyCenterCode[]>;
  private creatorDialogRef: MdDialogRef<StudyCenterCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.studyCenterCodes$ = this.store.select(...this.STUDY_CENTER_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findStudyCenterCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Pusat Pengajian"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:StudyCenterCode): void {
    this.showDialog(code);
  }
  delete(code: StudyCenterCode): void {
    this.store.dispatch(this.actions.removeStudyCenterCode(code))
  }

  private showDialog(code:StudyCenterCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(StudyCenterCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.studyCenterCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
