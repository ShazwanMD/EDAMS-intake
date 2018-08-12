import {
    Component, 
    Input, 
    EventEmitter, 
    Output, 
    ChangeDetectionStrategy, 
    AfterViewInit, 
    OnChanges, 
    SimpleChange
} from '@angular/core';
import {
  TdDataTableSortingOrder,
  TdDataTableService,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent
} from '@covalent/core';
import {MdSnackBar} from '@angular/material';
import { SpmSubjectCode } from '../../../../shared/model/common/spm-subject-code.interface';
@Component({
  selector: 'pams-spm-subject-code-list',
  templateUrl: './spm-subject-code.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SpmSubjectCodesComponent implements OnChanges {
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    // {name: 'descriptionMs', label: 'DescriptionMs'},
    {name: 'id', label: 'DescriptionEn'},
    {name: 'action', label: ''}
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;
  @Input() spmSubjectCodes: SpmSubjectCode[];
  @Output() editDialog: EventEmitter<SpmSubjectCode> = new EventEmitter<SpmSubjectCode>();
  @Output() delete: EventEmitter<SpmSubjectCode> = new EventEmitter<SpmSubjectCode>();
  constructor(private _dataTableService: TdDataTableService,
              private snackBar: MdSnackBar) {
  }
  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['spmSubjectCodes']){
        this.filteredData = changes['spmSubjectCodes'].currentValue; 
        this.filteredTotal = changes['spmSubjectCodes'].currentValue.length;
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
    let newData: any[] = this.spmSubjectCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}