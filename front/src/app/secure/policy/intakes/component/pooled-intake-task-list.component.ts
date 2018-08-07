import { Router, ActivatedRoute } from '@angular/router';

import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output, OnChanges, SimpleChange } from '@angular/core';
import {MdSnackBar, MdSnackBarRef, SimpleSnackBar} from '@angular/material';
import {IntakeTask} from '../../../../shared/model/policy/intake-task.interface';
import {
    IPageChangeEvent,
    ITdDataTableSortChangeEvent,
    TdDataTableService,
    TdDataTableSortingOrder
  } from '@covalent/core';



@Component({
  selector: 'pams-pooled-intake-task-list',
  templateUrl: './pooled-intake-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledIntakeTaskListComponent implements OnChanges{

  private columns: any[] = [
    {name: 'referenceNo', label: 'No Rujukan'},
    {name: 'projection', label: 'Unjuran'},
    {name: 'startDate', label: 'Tarikh Buka'},
    {name: 'endDate', label: 'Tarikh Tutup'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() intakeTasks: IntakeTask[];
  @Output() claim = new EventEmitter<IntakeTask>();

  constructor(private snackBar: MdSnackBar,
    private router: Router,
    private route: ActivatedRoute,
    private _dataTableService: TdDataTableService) {
}

claimTask(task: IntakeTask): void {
  console.log('Emitting task');
  if (confirm('Ambil Tindakan?')) {
    this.claim.emit(task);
  } else {
  }
}

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = 'referenceNo';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['intakeTasks']){
        this.filteredData = changes['intakeTasks'].currentValue; 
        this.filteredTotal = changes['intakeTasks'].currentValue.length;
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
    let newData: any[] = this.intakeTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

}

