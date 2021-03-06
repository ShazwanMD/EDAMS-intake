import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {LanguageCode} from '../../../shared/model/common/language-code.interface';

@Component({
  selector: 'pams-language-code-select',
  templateUrl: './language-code-select.component.html',
  styleUrls: ['./language-code-select.scss'],
})
export class LanguageCodeSelectComponent implements OnInit {

  private LANGUAGE_CODE: string[] = 'commonModuleState.languageCodes'.split('.');
  private languageCodes$: Observable<LanguageCode[]>;
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.languageCodes$ = this.store.select(...this.LANGUAGE_CODE);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findLanguageCodes());
  }

  selectChangeEvent(event: LanguageCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

