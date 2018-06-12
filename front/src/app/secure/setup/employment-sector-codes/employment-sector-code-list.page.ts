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

import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';

@Component({
  selector: 'pams-employment-sector-code-list.page',
  templateUrl: './employment-sector-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmploymentSectorCodeListPage implements OnInit{
  private EMPLOYMENT_SECTOR_CODES = "setupModuleState.employmentSectorCodes".split(".");
  private employmentSectorCodes$: Observable<EmploymentSectorCode[]>;
  private creatorDialogRef: MdDialogRef<EmploymentSectorCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'descriptionMs', label: 'DescriptionMs'},
    {name: 'descriptionEn', label: 'DescriptionEn'},
    {name: 'action', label: ''}
  ];
  private employmentSectorCodes: EmploymentSectorCode[];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dataTableService: TdDataTableService) {
    this.employmentSectorCodes$ = this.store.select(...this.EMPLOYMENT_SECTOR_CODES);
    this.employmentSectorCodes$.subscribe(EmploymentSectorCodes=>this.employmentSectorCodes = EmploymentSectorCodes)
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findEmploymentSectorCodes());
    this.store.dispatch(this.actions.changeTitle("Employment Sector Codes"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:EmploymentSectorCode): void {
    this.showDialog(code);
  }
  delete(code: EmploymentSectorCode): void {
    this.store.dispatch(this.actions.removeEmploymentSectorCode(code));
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
    console.log('filter');
    let newData: any[] = this.employmentSectorCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  deactivate(event): void {
    // this.store.dispatch(this.actions.removeGraduateCenter())
    console.log('event' + event);
  }

  private showDialog(center:EmploymentSectorCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(EmploymentSectorCodeEditorDialog, config);
    if(center) this.creatorDialogRef.componentInstance.employmentSectorCode = center; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
