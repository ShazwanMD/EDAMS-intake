import { GradeCode } from './../../../shared/model/common/grade-code.interface';
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
import {GradeCodeEditorDialog} from './dialog/grade-code-editor.dialog';

@Component({
  selector: 'pams-grade-list.page',
  templateUrl: './grade-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GradeCodeListPage implements OnInit{
  private GRADE_CODES = "setupModuleState.gradeCodes".split(".");
  private gradeCodes$: Observable<GradeCode[]>;
  private creatorDialogRef: MdDialogRef<GradeCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.gradeCodes$ = this.store.select(...this.GRADE_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findGradeCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Gred"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:GradeCode): void {
    this.showDialog(code);
  }
  delete(code: GradeCode): void {
    this.store.dispatch(this.actions.removeGradeCode(code))
  }

  private showDialog(code:GradeCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(GradeCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.gradeCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
