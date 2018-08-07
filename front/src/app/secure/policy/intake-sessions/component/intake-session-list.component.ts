import {Observable} from 'rxjs';
import {
  Component,
  Input,
  EventEmitter,
  Output,
  ChangeDetectionStrategy,
  AfterViewInit,
  ViewContainerRef,
  OnChanges, SimpleChanges, SimpleChange
} from '@angular/core';
import {
  TdDataTableSortingOrder,
  TdDataTableService,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent
} from '@covalent/core';
import {IntakeSessionActions} from '../intake-session.action';
import {Store} from '@ngrx/store';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {PolicyModuleState} from '../../index';
import {IntakeSessionEditorDialog} from './intake-session-editor.dialog';
import {MdSnackBar} from '@angular/material';
import {IntakeSession} from '../../../../shared/model/policy/intake-session.interface';

@Component({
  selector: 'pams-intake-session-list',
  templateUrl: './intake-session-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class IntakeSessionListComponent implements AfterViewInit, OnChanges {

  @Input() intakeSessions: IntakeSession[];
  @Output() view = new EventEmitter<IntakeSession>();

  private INTAKE_SESSION = 'policyModuleState.intakeSession'.split('.');
  private intakeSession$: Observable<IntakeSession>;
  private creatorDialogRef: MdDialogRef<IntakeSessionEditorDialog>;

  private columns: any[] = [
    {name: 'code', label: 'Kod'},
    {name: 'descriptionMs', label: 'Keterangan BM'},
    {name: 'descriptionEn', label: 'Keterangan EN'},
    {name: 'year', label: 'Tahun'},
    {name: 'current', label: 'Status'},
    {name: 'label', label: 'Label'},
    {name: 'action', label: ''},
  ];

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  constructor(private actions: IntakeSessionActions,
              private store: Store<PolicyModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dataTableService: TdDataTableService,
              private snackBar: MdSnackBar) {
    this.intakeSession$ = this.store.select(...this.INTAKE_SESSION);
  }


  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    console.log("changes",changes,changes['intakeSessions']);
      if (changes['intakeSessions']){
      this.filteredData = changes['intakeSessions'].currentValue; 
      this.filteredTotal = changes['intakeSessions'].currentValue.length;
      this.filter();
    }
  }

  ngAfterViewInit(): void {
    this.store.dispatch(this.actions.findIntakeSessions());
    this.filteredData = this.intakeSessions;
    this.filteredTotal = this.intakeSessions.length;
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
    let newData: any[] = this.intakeSessions;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  createDialog(): void {
    this.showDialog(null);
  }

  editDialog(code: IntakeSession): void {
    this.showDialog(code);
  }

  delete(id: IntakeSession): void {
    this.store.dispatch(this.actions.removeIntakeSession(id));
  }

  private showDialog(code: IntakeSession): void {
    console.log('create');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(IntakeSessionEditorDialog, config);
    if (code) this.creatorDialogRef.componentInstance.intakeSession = code; // set
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}
