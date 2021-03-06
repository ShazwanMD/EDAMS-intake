import { Component, ChangeDetectionStrategy, OnChanges, SimpleChange,Input,Output,EventEmitter } from "@angular/core";
import { TdDataTableSortingOrder, TdDataTableService, ITdDataTableSortChangeEvent, IPageChangeEvent } from "@covalent/core";
import { MdSnackBar } from "@angular/material";
import { StpmSubjectCode } from '../../../../shared/model/common/stpm-subject-code.interface';



@Component({
    selector: 'pams-stpm-subject-code-list',
    templateUrl: './stpm-subject-code-list.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
  })
  export class StpmSubjectCodesComponent implements OnChanges {
    private columns: any[] = [
      {name: 'code', label: 'Kod Subjek'},
      {name: 'description', label: 'Nama Subjek'},
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
    @Input() stpmSubjectCodes: StpmSubjectCode[];
    @Output() editDialog: EventEmitter<StpmSubjectCode> = new EventEmitter<StpmSubjectCode>();
    @Output() delete: EventEmitter<StpmSubjectCode> = new EventEmitter<StpmSubjectCode>();
    constructor(private _dataTableService: TdDataTableService,
                private snackBar: MdSnackBar) {
    }
    ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
      if (changes['stpmSubjectCodes']){
          this.filteredData = changes['stpmSubjectCodes'].currentValue; 
          this.filteredTotal = changes['stpmSubjectCodes'].currentValue.length;
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
      let newData: any[] = this.stpmSubjectCodes;
      newData = this._dataTableService.filterData(newData, this.searchTerm, true);
      this.filteredTotal = newData.length;
      newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
      newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
      this.filteredData = newData;
    }
  }