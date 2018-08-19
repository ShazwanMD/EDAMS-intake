import { StpmSubjectCodeEditorDialog } from './dialog/stpm-subject-code-editor.dialog';
import { Observable } from 'rxjs/Observable';
import { Component, ChangeDetectionStrategy, OnInit, ViewContainerRef } from "@angular/core";
import { StpmSubjectCode } from '../../../shared/model/common/stpm-subject-code.interface';
import { MdDialogRef, MdDialog, MdDialogConfig } from '@angular/material';
import { SetupActions } from '../setup.action';
import { Store } from '@ngrx/store';
import { SetupModuleState } from '../index';


@Component({
    selector: 'pams-stpm-subject-list.page',
    templateUrl: './stpm-subject-code-list.page.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
  })
  export class StpmSubjectCodeListPage implements OnInit{
    private STPM_SUBJECT_CODES = "setupModuleState.stpmSubjectCodes".split(".");
    private stpmSubjectCodes$: Observable<StpmSubjectCode[]>;
    private creatorDialogRef: MdDialogRef<StpmSubjectCodeEditorDialog>;
  
    constructor(private actions: SetupActions,
                private store: Store<SetupModuleState>,
                private vcf: ViewContainerRef,
                private dialog: MdDialog) {
  
  
      this.stpmSubjectCodes$ = this.store.select(...this.STPM_SUBJECT_CODES);
    }
    ngOnInit(): void {
      this.store.dispatch(this.actions.findStpmSubjectCodes());
      this.store.dispatch(this.actions.changeTitle("Kod Subjek STPM"));
    }
    createDialog(): void {
      this.showDialog(null);
    }
    editDialog(code:StpmSubjectCode): void {
      this.showDialog(code);
    }
    delete(code: StpmSubjectCode): void {
      this.store.dispatch(this.actions.removeStpmSubjectCode(code))
    }
  
    private showDialog(code:StpmSubjectCode): void {
      console.log("create");
      let config = new MdDialogConfig();
      config.viewContainerRef = this.vcf;
      config.role = 'dialog';
      config.width = '70%';
      config.height = '65%';
      config.position = {top: '0px'};
      this.creatorDialogRef = this.dialog.open(StpmSubjectCodeEditorDialog, config);
      if(code) this.creatorDialogRef.componentInstance.stpmSubjectCode = code; // set
      this.creatorDialogRef.afterClosed().subscribe(res => {
        console.log("close dialog");
      });
    }
  }
  