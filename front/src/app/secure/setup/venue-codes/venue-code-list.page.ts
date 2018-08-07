import { VenueCode } from './../../../shared/model/common/venue-code.interface';
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
import {VenueCodeEditorDialog} from './dialog/venue-code-editor.dialog';
@Component({
  selector: 'pams-venue-list.page',
  templateUrl: './venue-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class VenueCodeListPage implements OnInit{
  private VENUE_CODES = "setupModuleState.venueCodes".split(".");
  private venueCodes$: Observable<VenueCode[]>;
  private creatorDialogRef: MdDialogRef<VenueCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.venueCodes$ = this.store.select(...this.VENUE_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findVenueCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Lokasi"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:VenueCode): void {
    this.showDialog(code);
  }
  delete(code: VenueCode): void {
    this.store.dispatch(this.actions.removeVenueCode(code))
  }

  private showDialog(code:VenueCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(VenueCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.venueCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
