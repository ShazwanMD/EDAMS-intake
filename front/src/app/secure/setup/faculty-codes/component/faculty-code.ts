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
import { FacultyCode } from './../../../../shared/model/common/faculty-code.interface';


@Component({
  selector: 'pams-faculty-code-list',
  templateUrl: './faculty-code.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FacultyCodesComponent implements OnChanges {

  private columns: any[] = [
    {name: 'code', label: 'Kod'},
    {name: 'descriptionMs', label: 'Keterangan BM'},
    {name: 'descriptionEn', label: 'Keterangan EN'},
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

  @Input() facultyCodes: FacultyCode[];
  @Output() editDialog: EventEmitter<FacultyCode> = new EventEmitter<FacultyCode>();
  @Output() delete: EventEmitter<FacultyCode> = new EventEmitter<FacultyCode>();

  constructor(private _dataTableService: TdDataTableService,
              private snackBar: MdSnackBar) {
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['facultyCodes']){
        this.filteredData = changes['facultyCodes'].currentValue; 
        this.filteredTotal = changes['facultyCodes'].currentValue.length;
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
    let newData: any[] = this.facultyCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}