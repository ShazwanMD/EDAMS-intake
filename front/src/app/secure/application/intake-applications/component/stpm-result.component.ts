import { StpmResultCode } from './../../../../shared/model/common/stpm-result-code.interface';
import {StpmResultEditorDialog} from '../dialog/stpm-result-editor.dialog';
import { Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnInit, ViewContainerRef, OnChanges, SimpleChange } from '@angular/core';
import {IntakeApplicationActions} from '../intake-application.action';
import {Store} from '@ngrx/store';
import {ApplicationModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';
import {Result} from '../../../../shared/model/application/result.interface';
import { TdDataTableSortingOrder, TdDataTableService, ITdDataTableSortChangeEvent, IPageChangeEvent } from '@covalent/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { StpmResultCreatorDialog } from '../dialog/stpm-result-creator.dialog';

@Component({
  selector: 'pams-stpm-result',
  templateUrl: './stpm-result.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class StpmResultComponent implements OnChanges {

  private columns: any[] = [
    {name: 'graduationYear', label: 'Tahun'},
    {name: 'stpmSubjectCode.description', label: 'Subjek'},
    {name: 'gradeCode.description', label: 'Gred'},
    {name: 'action', label: ''}
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'stpmSubjectCode.description';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;
  @Input() stpmResultCodes: StpmResultCode[];
  @Input() intakeApplication: IntakeApplication;
  // @Output() editDialog: EventEmitter<SpmResultCode> = new EventEmitter<SpmResultCode>();
  // @Output() delete: EventEmitter<SpmResultCode> = new EventEmitter<SpmResultCode>();
  
  private creatorDialogRef: MdDialogRef<StpmResultCreatorDialog>;
  private editorDialogRef: MdDialogRef<StpmResultEditorDialog>;
  
  constructor(private _dataTableService: TdDataTableService,
              private store: Store<ApplicationModuleState>,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private vcf: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
  }
  
  create(): void {
    this.showDialog(null);
  }
  
  edit(stpmResultCode: StpmResultCode): void {
    this.showEditorDialog(stpmResultCode);
  }

  showEditorDialog(stpmResultCode: StpmResultCode): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(StpmResultEditorDialog, config);
    this.editorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
    if (stpmResultCode) this.editorDialogRef.componentInstance.stpmResultCode = stpmResultCode;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
  
  showDialog(stpmResultCode: StpmResultCode): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.creatorDialogRef = this.dialog.open(StpmResultCreatorDialog, config);
    this.creatorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
    if (stpmResultCode) this.creatorDialogRef.componentInstance.stpmResultCode = stpmResultCode;
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
  
  
  
  delete(code: StpmResultCode): void {
    console.log("delete"+ this.intakeApplication.referenceNo);
    this.store.dispatch(this.actions.deleteStpmResultCode(this.intakeApplication, code))
  }
  
  
  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['stpmResultCodes']){
        this.filteredData = changes['stpmResultCodes'].currentValue; 
        this.filteredTotal = changes['stpmResultCodes'].currentValue.length;
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
    let newData: any[] = this.stpmResultCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

}
