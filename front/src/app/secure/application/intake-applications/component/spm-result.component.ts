import { Observable } from 'rxjs/Observable';
import { Store } from '@ngrx/store';
import { MdDialogRef, MdDialogConfig, MdDialog } from '@angular/material';
import { IntakeApplication } from './../../../../shared/model/application/intake-application.interface';
import { SpmResultCode } from './../../../../shared/model/common/spm-result-code.interface';
import {
  Component, 
  Input, 
  EventEmitter, 
  Output, 
  ChangeDetectionStrategy, 
  AfterViewInit, 
  OnChanges, 
  SimpleChange,
  ViewContainerRef
} from '@angular/core';
import {
TdDataTableSortingOrder,
TdDataTableService,
ITdDataTableSortChangeEvent,
IPageChangeEvent
} from '@covalent/core';
import {MdSnackBar} from '@angular/material';
import { SpmSubjectCode } from '../../../../shared/model/common/spm-subject-code.interface';
import { SpmResultEditorDialog } from '../dialog/spm-result-editor.dialog';
import { ApplicationModuleState } from '../../index';
import { IntakeApplicationActions } from '../intake-application.action';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SpmResultCreatorDialog } from '../dialog/spm-result-creator.dialog';
import { MainResultCode } from '../../../../shared/model/common/main-result-code.interface';
@Component({
selector: 'pams-spm-result-code-list',
templateUrl: './spm-result.component.html',
changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SpmResultComponent implements OnChanges {
private columns: any[] = [
  {name: 'graduationYear', label: 'Tahun'},
  {name: 'spmSubjectCode.description', label: 'Subjek'},
  {name: 'gradeCode.description', label: 'Gred'},
  {name: 'action', label: ''}
];
filteredData: any[];
filteredTotal: number;
searchTerm: string = '';
fromRow: number = 1;
currentPage: number = 1;
pageSize: number = 5;
sortBy: string = 'spmSubjectCode.description';
sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

private SPM_MAIN_RESULT_CODE: string[] = 'applicationModuleState.spmMainResultCode'.split('.');

private spmMainResultCode$: Observable<MainResultCode>;


@Input() spmResultCodes: SpmResultCode[];
@Input() intakeApplication: IntakeApplication;

private creatorDialogRef: MdDialogRef<SpmResultCreatorDialog>;
private editorDialogRef: MdDialogRef<SpmResultEditorDialog>;

constructor(private _dataTableService: TdDataTableService,
            private store: Store<ApplicationModuleState>,
            private route: ActivatedRoute,
            private formBuilder: FormBuilder,
            private vcf: ViewContainerRef,
            private actions: IntakeApplicationActions,
            private dialog: MdDialog,
            private snackBar: MdSnackBar) {
              this.spmMainResultCode$ = this.store.select(...this.SPM_MAIN_RESULT_CODE);
}

create(): void {
  this.showDialog(null);
}

edit(spmResultCode: SpmResultCode): void {
  this.showEditorDialog(spmResultCode);
}
showEditorDialog(spmResultCode: SpmResultCode): void {
  let config = new MdDialogConfig();
  config.viewContainerRef = this.vcf;
  config.role = 'dialog';
  config.width = '50%';
  config.height = '60%';
  config.position = {top: '65px'};
  this.editorDialogRef = this.dialog.open(SpmResultEditorDialog, config);
  this.editorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
  if (spmResultCode) this.editorDialogRef.componentInstance.spmResultCode = spmResultCode;
  this.editorDialogRef.afterClosed().subscribe((res) => {
    console.log('close dialog');
  });
}

// showMainDialog(mainResultCode: MainResultCode): void {
//   let config = new MdDialogConfig();
//   config.viewContainerRef = this.vcf;
//   config.role = 'dialog';
//   config.width = '50%';
//   config.height = '60%';
//   config.position = {top: '65px'};
//   this.creatorMainDialogRef = this.dialog.open(SpmMainResultCreatorDialog, config);
//   this.creatorMainDialogRef.componentInstance.intakeApplication = this.intakeApplication;
//   if (mainResultCode) this.creatorMainDialogRef.componentInstance.mainResultCode = mainResultCode;
//   this.creatorMainDialogRef.afterClosed().subscribe((res) => {
//     console.log('close dialog');
//   });
// }

showDialog(spmResultCode: SpmResultCode): void {
  let config = new MdDialogConfig();
  config.viewContainerRef = this.vcf;
  config.role = 'dialog';
  config.width = '50%';
  config.height = '60%';
  config.position = {top: '65px'};
  this.creatorDialogRef = this.dialog.open(SpmResultCreatorDialog, config);
  this.creatorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
  if (spmResultCode) this.creatorDialogRef.componentInstance.spmResultCode = spmResultCode;
  this.creatorDialogRef.afterClosed().subscribe((res) => {
    console.log('close dialog');
  });
}



delete(code: SpmResultCode): void {
  console.log("delete"+ this.intakeApplication.referenceNo);
  this.store.dispatch(this.actions.deleteSpmResultCode(this.intakeApplication, code))
}


ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
  if (changes['spmResultCodes']){
      this.filteredData = changes['spmResultCodes'].currentValue; 
      this.filteredTotal = changes['spmResultCodes'].currentValue.length;
      this.filter();
    }
  }
sort(sortEvent: ITdDataTableSortChangeEvent): void {
  this.sortBy = sortEvent.name;
  this.sortOrder = sortEvent.order;
  this.filter();
}
search(searchTerm: string): void {
  this.searchTerm = searchTerm;
  this.filter();
}
page(pagingEvent: IPageChangeEvent): void {
  this.fromRow = pagingEvent.fromRow;
  this.currentPage = pagingEvent.page;
  this.pageSize = pagingEvent.pageSize;
  this.filter();
}
filter(): void {
  let newData: any[] = this.spmResultCodes;
  newData = this._dataTableService.filterData(newData, this.searchTerm, true);
  this.filteredTotal = newData.length;
  newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
  newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
  this.filteredData = newData;
}
}