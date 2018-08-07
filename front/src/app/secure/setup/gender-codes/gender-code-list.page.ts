import { GenderCode } from './../../../shared/model/common/gender-code.interface';
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
import {GenderCodeEditorDialog} from './dialog/gender-code-editor.dialog';

@Component({
  selector: 'pams-gender-list.page',
  templateUrl: './gender-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GenderCodeListPage implements OnInit{
  private GENDER_CODES = "setupModuleState.genderCodes".split(".");
  private genderCodes$: Observable<GenderCode[]>;
  private creatorDialogRef: MdDialogRef<GenderCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.genderCodes$ = this.store.select(...this.GENDER_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findGenderCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Jantina"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:GenderCode): void {
    this.showDialog(code);
  }
  delete(code: GenderCode): void {
    this.store.dispatch(this.actions.removeGenderCode(code))
  }

  private showDialog(code:GenderCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(GenderCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.genderCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
