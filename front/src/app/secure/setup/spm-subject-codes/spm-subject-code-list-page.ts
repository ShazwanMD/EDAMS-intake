import { SpmSubjectCodeEditorDialog } from './dialog/spm-subject-code-editor.dialog';
import { SubjectCode } from './../../../shared/model/common/subject-code.interface';
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
import { SpmSubjectCode } from '../../../shared/model/common/spm-subject-code.interface';

@Component({
  selector: 'pams-spm-subject-list.page',
  templateUrl: './spm-subject-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SpmSubjectCodeListPage implements OnInit{
  private SPM_SUBJECT_CODES = "setupModuleState.spmSubjectCodes".split(".");
  private spmSubjectCodes$: Observable<SpmSubjectCode[]>;
  private creatorDialogRef: MdDialogRef<SpmSubjectCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.spmSubjectCodes$ = this.store.select(...this.SPM_SUBJECT_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findSpmSubjectCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Subjek SPM"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:SpmSubjectCode): void {
    this.showDialog(code);
  }
  delete(code: SpmSubjectCode): void {
    this.store.dispatch(this.actions.removeSpmSubjectCode(code))
  }

  private showDialog(code:SpmSubjectCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(SpmSubjectCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.spmSubjectCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
