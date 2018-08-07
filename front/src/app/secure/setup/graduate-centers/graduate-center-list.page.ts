import { GraduateCenter } from './../../../shared/model/common/graduate-center.interface';
import {
  Component,
  Input,
  EventEmitter,
  Output,
  ChangeDetectionStrategy,
  OnInit,
  AfterViewInit,
  ViewContainerRef,
} from '@angular/core';
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {GraduateCenterEditorDialog} from './dialog/graduate-center-editor.dialog';
import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';

@Component({
  selector: 'pams-graduate-list.page',
  templateUrl: './graduate-center-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GraduateCenterListPage implements OnInit{
  private GRADUATE_CENTERS = "setupModuleState.graduateCenters".split(".");
  private graduateCenters$: Observable<GraduateCenter[]>;
  private creatorDialogRef: MdDialogRef<GraduateCenterEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'descriptionMs', label: 'DescriptionMs'},
    {name: 'descriptionEn', label: 'DescriptionEn'},
    {name: 'action', label: ''}
  ];
  private graduateCenters: GraduateCenter[];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dataTableService: TdDataTableService) {
    this.graduateCenters$ = this.store.select(...this.GRADUATE_CENTERS);
    this.graduateCenters$.subscribe(GraduateCenters=>this.graduateCenters = GraduateCenters)
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findGraduateCenters());
    this.store.dispatch(this.actions.changeTitle("Pusat Pengijazahan"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:GraduateCenter): void {
    this.showDialog(code);
  }
  delete(code: GraduateCenter): void {
    this.store.dispatch(this.actions.removeGraduateCenter(code));
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
    console.log('filter');
    let newData: any[] = this.graduateCenters;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  deactivate(event): void {
    // this.store.dispatch(this.actions.removeGraduateCenter())
    console.log('event' + event);
  }

  private showDialog(center:GraduateCenter): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(GraduateCenterEditorDialog, config);
    if(center) this.creatorDialogRef.componentInstance.graduateCenter = center; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
