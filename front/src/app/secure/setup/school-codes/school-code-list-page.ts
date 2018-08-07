import { SchoolCode } from './../../../shared/model/common/school-code.interface';
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
import {SchoolCodeEditorDialog} from './dialog/school-code-editor.dialog';
@Component({
  selector: 'pams-school-list.page',
  templateUrl: './school-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SchoolCodeListPage implements OnInit{
  private SCHOOL_CODES = "setupModuleState.schoolCodes".split(".");
  private schoolCodes$: Observable<SchoolCode[]>;
  private creatorDialogRef: MdDialogRef<SchoolCodeEditorDialog>;
  
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
              
              
    this.schoolCodes$ = this.store.select(...this.SCHOOL_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findSchoolCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Sekolah"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:SchoolCode): void {
    this.showDialog(code);
  }
  delete(code: SchoolCode): void {
    this.store.dispatch(this.actions.removeSchoolCode(code))
  }
  
  private showDialog(code:SchoolCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(SchoolCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.schoolCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}