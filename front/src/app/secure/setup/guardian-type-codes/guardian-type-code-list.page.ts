
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
import { GuardianTypeCode } from '../../../shared/model/common/guardian-type-code.interface';
import { GuardianTypeCodeEditorDialog } from './dialog/guardian-type-code-editor.dialog';

@Component({
  selector: 'pams-guardian-type-code-list.page',
  templateUrl: './guardian-type-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GuardianTypeCodeListPage implements OnInit{
  private GUARDIAN_TYPE_CODES = "setupModuleState.guardianTypeCodes".split(".");
  private guardianTypeCodes$: Observable<GuardianTypeCode[]>;
  private creatorDialogRef: MdDialogRef<GuardianTypeCodeEditorDialog>;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {


    this.guardianTypeCodes$ = this.store.select(...this.GUARDIAN_TYPE_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findGuardianTypeCodes());
    this.store.dispatch(this.actions.changeTitle("Kod Jenis Waris"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:GuardianTypeCode): void {
    this.showDialog(code);
  }
  delete(code: GuardianTypeCode): void {
    this.store.dispatch(this.actions.removeGuardianTypeCode(code))
  }

  private showDialog(code:GuardianTypeCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(GuardianTypeCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.guardianTypeCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
