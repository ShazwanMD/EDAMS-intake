import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {IntakeActions} from '../intake.action';
import {PolicyModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {MdDialogConfig, MdDialog, MdDialogRef, MdSnackBar} from '@angular/material';
import {ProgramOfferingEditorDialog} from '../dialog/program-offering-editor.dialog';

import {ActivatedRoute} from '@angular/router';
import {ProgramOfferingListEditorDialog} from '../dialog/program-offering-list-editor.dialog';
import {Intake} from '../../../../shared/model/policy/intake.interface';
import {ProgramOffering} from '../../../../shared/model/policy/program-offering.interface';

@Component({
  selector: 'pams-program-offering-list',
  templateUrl: './program-offering-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProgramOfferingListComponent {

  @Input() intake: Intake;
  @Input() intakes: Intake;
  @Input() programOfferings: ProgramOffering;

  private editorDialogRef: MdDialogRef<ProgramOfferingEditorDialog>;
  private editorDialogRef2: MdDialogRef<ProgramOfferingListEditorDialog>;

  constructor(private store: Store<PolicyModuleState>,
              private route: ActivatedRoute,
              private actions: IntakeActions,
              private vcf: ViewContainerRef,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
  }

  ngOnInit(): void {
  }

  editDialog(programOfferings) {
      console.log('programOffering :' + programOfferings);
      this.showDialog2(programOfferings);
  }

  delete(programOffering: ProgramOffering): void {
    if (confirm('Confirm to delete this program offering?')) {
    // let snackBarRef = this.snackBar.open('Confirm to delete this program offering?', 'Ok');
    // snackBarRef.afterDismissed().subscribe(() => {
      console.log('delete program offering :' + programOffering.id);
      this.store.dispatch(this.actions.deleteProgramOffering(this.intake, programOffering));
    };
  }

  filter(): void {
  }

  showDialog(): void {
      console.log('showDialog program offering list');
      let config = new MdDialogConfig();
      config.viewContainerRef = this.vcf;
      config.role = 'dialog';
      config.width = '65%';
      config.height = '46%';
      config.position = {top: '0px'};
      this.editorDialogRef = this.dialog.open(ProgramOfferingEditorDialog, config);
      this.editorDialogRef.componentInstance.intake = this.intake;

      this.editorDialogRef.afterClosed().subscribe((res) => {
        console.log('closeDialog');
        // reload program offerings
        this.store.dispatch(this.actions.findProgramOfferings(this.intake));
    });
  }

  private showDialog2(programOffering: ProgramOffering): void {
    console.log('showDialog2 code :' + programOffering.programCode.id);
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.editorDialogRef2 = this.dialog.open(ProgramOfferingListEditorDialog, config);
    if (programOffering) this.editorDialogRef2.componentInstance.programOffering = programOffering; // set
    if (this.intake) this.editorDialogRef2.componentInstance.intake = this.intake;
    this.editorDialogRef2.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // reload program offerings
      this.store.dispatch(this.actions.findProgramOfferings(this.intake));
    });

  }
}
