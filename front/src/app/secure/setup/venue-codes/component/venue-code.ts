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
import { MdSnackBar } from '@angular/material';
import { VenueCode } from './../../../../shared/model/common/venue-code.interface';
@Component({
  selector: 'pams-venue-code-list',
  templateUrl: './venue-code.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class VenueCodesComponent implements OnChanges {
  private columns: any[] = [
    { name: 'code', label: 'Kod' },
    { name: 'registrationDate', label: 'Tarikh Pendaftaran' },
    { name: 'registrationLocation', label: 'Lokasi Pendaftaran' },
    { name: 'startTimeEn', label: 'Waktu Mula (EN)' },
    { name: 'startTimeMs', label: 'Waktu Mula (BM)' },
    { name: 'endTimeEn', label: 'Waktu Tamat (EN)' },
    { name: 'endTimeMs', label: 'Waktu Tamat (BM)' },
    { name: 'action', label: '' }
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;
  @Input() venueCodes: VenueCode[];
  @Output() editDialog: EventEmitter<VenueCode> = new EventEmitter<VenueCode>();
  @Output() delete: EventEmitter<VenueCode> = new EventEmitter<VenueCode>();
  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
  }
  ngOnChanges(changes: { [propName: string]: SimpleChange }) {
    if (changes['venueCodes']) {
      this.filteredData = changes['venueCodes'].currentValue;
      this.filteredTotal = changes['venueCodes'].currentValue.length;
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
    let newData: any[] = this.venueCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}