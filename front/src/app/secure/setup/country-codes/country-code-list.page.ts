import { CountryCode } from './../../../shared/model/common/country-code.interface';
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
import {CountryCodeEditorDialog} from './dialog/country-code-editor.dialog';

@Component({
  selector: 'pams-country-list.page',
  templateUrl: './country-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CountryCodeListPage implements OnInit{
  private COUNTRY_CODES = "setupModuleState.countryCodes".split(".");
  private countryCodes$: Observable<CountryCode[]>;
  private creatorDialogRef: MdDialogRef<CountryCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.countryCodes$ = this.store.select(...this.COUNTRY_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findCountryCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Negara"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:CountryCode): void {
    this.showDialog(code);
  }
  delete(code: CountryCode): void {
    this.store.dispatch(this.actions.removeCountryCode(code))
  }

  private showDialog(code:CountryCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(CountryCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.countryCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
