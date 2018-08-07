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
import {SubjectCodeEditorDialog} from './dialog/subject-code-editor.dialog';
@Component({
  selector: 'pams-subject-list.page',
  templateUrl: './subject-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SubjectCodeListPage implements OnInit{
  private SUBJECT_CODES = "setupModuleState.subjectCodes".split(".");
  private subjectCodes$: Observable<SubjectCode[]>;
  private creatorDialogRef: MdDialogRef<SubjectCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.subjectCodes$ = this.store.select(...this.SUBJECT_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findSubjectCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Subjek"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:SubjectCode): void {
    this.showDialog(code);
  }
  delete(code: SubjectCode): void {
    this.store.dispatch(this.actions.removeSubjectCode(code))
  }

  private showDialog(code:SubjectCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(SubjectCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.subjectCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
