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
import { NationalityCode } from './../../../../shared/model/common/nationality-code.interface';


@Component({
  selector: 'pams-nationality-code-list',
  templateUrl: './nationality-code.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NationalityCodesComponent implements OnChanges {

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'descriptionMs', label: 'DescriptionMs'},
    {name: 'descriptionEn', label: 'DescriptionEn'},
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

  @Input() nationalityCodes: NationalityCode[];
  @Output() editDialog: EventEmitter<NationalityCode> = new EventEmitter<NationalityCode>();
  @Output() delete: EventEmitter<NationalityCode> = new EventEmitter<NationalityCode>();

  constructor(private _dataTableService: TdDataTableService,
              private snackBar: MdSnackBar) {
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['nationalityCodes']){
        this.filteredData = changes['nationalityCodes'].currentValue; 
        this.filteredTotal = changes['nationalityCodes'].currentValue.length;
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
    let newData: any[] = this.nationalityCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}