import { Router, ActivatedRoute } from '@angular/router';
import { MdSnackBar } from '@angular/material';
import { CandidateTask } from '../../../shared/model/admission/candidate-task.interface';
import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {
    IPageChangeEvent,
    ITdDataTableSortChangeEvent,
    TdDataTableService,
    TdDataTableSortingOrder
  } from '@covalent/core';
import { Candidate } from "../../../shared/model/admission/candidate.interface";

@Component({
  selector: 'pams-archived-candidate-list',
  templateUrl: './archived-candidate-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedCandidateListComponent {

  private columns: any[] = [
    {name: 'referenceNo', label: 'No Rujukan'},
    {name: 'identityNo', label: 'No Kad Pengenalan'},
    {name: 'name', label: 'Nama'},
    {name: 'application.programSelection.programCode.facultyCode.descriptionMs', label: 'Fakulti'},
    {name: 'intake.intakeSession.descriptionMs', label: 'Sesi Pengambilan'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() candidate: Candidate[];
  @Output() view = new EventEmitter<Candidate>();

  constructor(private snackBar: MdSnackBar,
    private router: Router,
    private route: ActivatedRoute,
    private _dataTableService: TdDataTableService) {
  }

    viewDetail(task: Candidate): void {
      console.log('Emitting task');
      if (confirm('Lihat?')) {
       this.view.emit(task);
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

  ngAfterViewInit(): void {
    this.filteredData = this.candidate;
    this.filteredTotal = this.candidate.length;
    this.filter();
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
    let newData: any[] = this.candidate;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

}
