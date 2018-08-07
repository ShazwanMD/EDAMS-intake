import { EmploymentTypeCodeEditorDialog } from './dialog/employment-type-code-editor.dialog';
import { EmploymentTypeCode } from './../../../shared/model/common/employment-type-code.interface';
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

@Component({
  selector: 'pams-employment-type-code-list.page',
  templateUrl: './employment-type-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmploymentTypeCodeListPage implements OnInit{
  private EMPLOYMENT_TYPE_CODES = "setupModuleState.employmentTypeCodes".split(".");
  private employmentTypeCodes$: Observable<EmploymentTypeCode[]>;
  private creatorDialogRef: MdDialogRef<EmploymentTypeCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.employmentTypeCodes$ = this.store.select(...this.EMPLOYMENT_TYPE_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findEmploymentTypeCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Penjawatan"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:EmploymentTypeCode): void {
    this.showDialog(code);
  }
  delete(code: EmploymentTypeCode): void {
    this.store.dispatch(this.actions.removeEmploymentTypeCode(code))
  }

  private showDialog(code:EmploymentTypeCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(EmploymentTypeCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.employmentTypeCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
