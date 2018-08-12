import { MdSnackBar } from '@angular/material';
import { Staff } from './../../../identity/staff.interface';
import { OnChanges, Input, Output, EventEmitter, SimpleChange, Component, ChangeDetectionStrategy } from '@angular/core';
import { TdDataTableSortingOrder, ITdDataTableSortChangeEvent, IPageChangeEvent } from '@covalent/core';
import { TdDataTableService } from '@covalent/core/data-table/services/data-table.service';

@Component({
    selector: 'pams-umkcee-staff-list',
    templateUrl: './umkcee-staff.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
  })
export class UMKCEEStaffListComponent implements OnChanges {
    private columns: any[] = [
        { name: 'identityNo', label: 'No Staf' },
        { name: 'name', label: 'Nama' },
        { name: 'facultyCode.descriptionMs', label: 'Kod Jabatan' },
        { name: 'email', label: 'Emel Rasmi' },
        { name: 'staffCategory', label: 'Kategori' },
        { name: 'action', label: '' }
    ];

    filteredData: any[];
    filteredTotal: number;
    searchTerm: string = '';
    fromRow: number = 1;
    currentPage: number = 1;
    pageSize: number = 5;
    sortBy: string = 'identityNo';
    sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

    @Input() staffs: Staff[];
    @Output() editDialog: EventEmitter<Staff> = new EventEmitter<Staff>();
    @Output() delete: EventEmitter<Staff> = new EventEmitter<Staff>();

    constructor(private _dataTableService: TdDataTableService,
        private snackBar: MdSnackBar) {

    }

    ngOnChanges(changes: { [propName: string]: SimpleChange }) {
        if (changes['staffs']) {
            this.filteredData = changes['staffs'].currentValue;
            this.filteredTotal = changes['staffs'].currentValue.length;
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
        let newData: any[] = this.staffs;
        newData = this._dataTableService.filterData(newData, this.searchTerm, true);
        this.filteredTotal = newData.length;
        newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
        newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
        this.filteredData = newData;
    }







}