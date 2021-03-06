import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {CountryCode} from "../../../shared/model/common/country-code.interface";

@Component({
  selector: 'pams-country-code-select',
  templateUrl: './country-code-select.component.html',
  styleUrls: ['./country-code-select.scss'],
})
export class CountryCodeSelectComponent implements OnInit {

  private COUNTRY_CODE: string[] = 'commonModuleState.countryCodes'.split('.');
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  countryCodes$: Observable<CountryCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.countryCodes$ = this.store.select(...this.COUNTRY_CODE);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findCountryCodes());
  }

  selectChangeEvent(event: CountryCode): void {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

