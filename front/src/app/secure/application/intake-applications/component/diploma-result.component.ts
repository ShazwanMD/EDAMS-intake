import { DiplomaResultCode } from './../../../../shared/model/application/diploma-result.interface';
import { DiplomaResultEditorDialog } from '../dialog/diploma-result-editor.dialog';
import { Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnInit, ViewContainerRef, SimpleChange, OnChanges } from '@angular/core';
import { IntakeApplicationActions } from '../intake-application.action';
import { Store } from '@ngrx/store';
import { ApplicationModuleState } from '../../index';
import { MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar } from '@angular/material';
import { IntakeApplication } from '../../../../shared/model/application/intake-application.interface';
import { Result } from '../../../../shared/model/application/result.interface';
import { ITdDataTableSortChangeEvent, IPageChangeEvent, TdDataTableSortingOrder, TdDataTableService } from '@covalent/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { DiplomaResultCreatorDialog } from '../dialog/diploma-result-creator.dialog';

@Component({
  selector: 'pams-diploma-result',
  templateUrl: './diploma-result.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class DiplomaResultComponent implements OnChanges {

  private columns: any[] = [
    {name: 'graduationYear', label: 'Tahun'},
    {name: 'institution', label: 'Institusi'},
    {name: 'program', label: 'Program'},
    {name: 'cgpa', label: 'Keputusan/cgpa'},
    {name: 'resultType', label: 'Jenis'},
    {name: 'action', label: ''}
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'graduationYear';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  private editorDialogRef: MdDialogRef<DiplomaResultEditorDialog>;
  private creatorDialogRef: MdDialogRef<DiplomaResultCreatorDialog>;
  
  @Input() diplomaResultCodes: DiplomaResultCode[];
  @Input() intakeApplication: IntakeApplication;

  constructor(private actions: IntakeApplicationActions,
    private _dataTableService: TdDataTableService,
    private vcf: ViewContainerRef,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private store: Store<ApplicationModuleState>,
    private dialog: MdDialog,
    private snackBar: MdSnackBar) {
  }

  create(): void {
    this.showDialog(null);
  }

  edit(diplomaResultCode: DiplomaResultCode): void {
    this.showEditorDialog(diplomaResultCode);
  }
  showEditorDialog(diplomaResultCode: DiplomaResultCode): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = { top: '65px' };
    this.editorDialogRef = this.dialog.open(DiplomaResultEditorDialog, config);
    this.editorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
    if (diplomaResultCode) this.editorDialogRef.componentInstance.diplomaResultCode = diplomaResultCode;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
  showDialog(diplomaResultCode: DiplomaResultCode): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = { top: '65px' };
    this.creatorDialogRef = this.dialog.open(DiplomaResultCreatorDialog, config);
    this.creatorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
    if (diplomaResultCode) this.creatorDialogRef.componentInstance.diplomaResultCode = diplomaResultCode;
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }



  delete(code: DiplomaResultCode): void {
    console.log("delete" + this.intakeApplication.referenceNo);
    this.store.dispatch(this.actions.deleteDiplomaResultCode(this.intakeApplication, code))
  }


  ngOnChanges(changes: { [propName: string]: SimpleChange }) {
    if (changes['diplomaResultCodes']) {
      this.filteredData = changes['diplomaResultCodes'].currentValue;
      this.filteredTotal = changes['diplomaResultCodes'].currentValue.length;
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
    let newData: any[] = this.diplomaResultCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}
