import { EmploymentSectorCodeEditorDialog } from './dialog/employment-sector-code-editor.dialog';
import { EmploymentSectorCode } from './../../../shared/model/common/employment-sector-code.interface';
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
  selector: 'pams-employment-sector-code-list.page',
  templateUrl: './employment-sector-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmploymentSectorCodeListPage implements OnInit{
  private EMPLOYMENT_SECTOR_CODES = "setupModuleState.employmentSectorCodes".split(".");
  private employmentSectorCodes$: Observable<EmploymentSectorCode[]>;
  private creatorDialogRef: MdDialogRef<EmploymentSectorCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.employmentSectorCodes$ = this.store.select(...this.EMPLOYMENT_SECTOR_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findEmploymentSectorCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Sektor Pekerjaan"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:EmploymentSectorCode): void {
    this.showDialog(code);
  }
  delete(code: EmploymentSectorCode): void {
    this.store.dispatch(this.actions.removeEmploymentSectorCode(code))
  }

  private showDialog(code:EmploymentSectorCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(EmploymentSectorCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.employmentSectorCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
