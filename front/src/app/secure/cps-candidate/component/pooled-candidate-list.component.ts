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

@Component({
  selector: 'pams-pooled-candidate-list',
  templateUrl: './pooled-candidate-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledCandidateListComponent {

  private columns: any[] = [
                            {name: 'referenceNo', label: 'ReferenceNo'},
                            {name: 'candidateIntake.identityNo', label: 'Identity No'},
                            {name: 'candidateIntake.name', label: 'Name'},
                            {name: 'candidateIntake.programSelection.programCode.facultyCode.descriptionMs', label: 'Faculty'},
                            {name: 'intakeSession.descriptionMs', label: 'Intake'},
                            {name: 'flowState', label: 'Status'},
                            {name: 'action', label: ''},
  ];

  @Input() candidateTasks: CandidateTask[];
  @Output() claim = new EventEmitter<CandidateTask>();

  constructor(private snackBar: MdSnackBar,
    private router: Router,
    private route: ActivatedRoute,
    private _dataTableService: TdDataTableService) {
}

claimTask(task: CandidateTask): void {
  console.log('Emitting task');
  if (confirm('Claim candidate task?')) {
    this.claim.emit(task);
    window.location.reload();
  }
   else {
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
    this.filteredData = this.candidateTasks;
    this.filteredTotal = this.candidateTasks.length;
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
    let newData: any[] = this.candidateTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

}
