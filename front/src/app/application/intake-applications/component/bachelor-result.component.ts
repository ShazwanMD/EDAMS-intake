import {BachelorResultEditorDialog} from './dialog/bachelor-result-editor.dialog';
import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnInit, ViewContainerRef} from '@angular/core';
import {IntakeApplicationActions} from '../intake-application.action';
import {Store} from '@ngrx/store';
import {ApplicationModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {IntakeApplication} from '../intake-application.interface';
import {Result} from '../result.interface';

@Component({
  selector: 'pams-bachelor-result',
  templateUrl: './bachelor-result.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class BachelorResultComponent {

  private creatorDialogRef: MdDialogRef<BachelorResultEditorDialog>;
  @Input() result: Result;
  @Input() intakeApplication: IntakeApplication;

  constructor(private actions: IntakeApplicationActions,
              private vcf: ViewContainerRef,
              private store: Store<ApplicationModuleState>,
              private dialog: MdDialog) {
  }

  delete(): void {
    this.store.dispatch(this.actions.deleteResult(this.intakeApplication, this.result));
  }

  editDialog(): void {
    console.log('showDialog');
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(BachelorResultEditorDialog, config);
    this.creatorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }
}
