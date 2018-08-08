import {Employment} from '../../../../shared/model/application/employment.interface';
import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnInit, ViewContainerRef} from '@angular/core';
import {IntakeApplicationActions} from '../intake-application.action';
import {Store} from '@ngrx/store';
import {ApplicationModuleState} from '../../index';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {EmploymentEditorDialog} from '../dialog/employment-editor.dialog';

@Component({
  selector: 'pams-employment-list',
  templateUrl: './employment-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class EmploymentListComponent implements OnInit {

  @Input() employments: Employment[];
  @Input() intakeApplication: IntakeApplication;

  private selectedRows: Employment[];
  private editorDialogRef: MdDialogRef<EmploymentEditorDialog>;
  private columns: any[] = [
    {name: 'designation', label: 'Pekerjaan'},
    {name: 'employer', label: 'Majikan'}, 
    {name: 'employmentSectorCode.descriptionMs', label: 'Kategori Majikan'},
    {name: 'startDate', label: 'tarikh Mula'},
    {name: 'endDate', label: 'Tarikh Tamat'},
    {name: 'workingDuration', label: 'Tempoh Perkhidmatan'},
  ];

  constructor(private actions: IntakeApplicationActions,
              private vcf: ViewContainerRef,
              private store: Store<ApplicationModuleState>,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.employments.filter((value) => value.selected);
  }

  create(): void {
    this.showDialog(null);
  }

  edit(employment: Employment): void {
    this.showDialog(employment);
  }

  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i: number = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.deleteEmployment(this.intakeApplication, this.selectedRows[i]));
    }
    this.selectedRows = [];
  }

  filter(): void {
  }

  selectRow(employment: Employment): void {
  }

  selectAllRows(employments: Employment[]): void {
  }

  showDialog(employment: Employment): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(EmploymentEditorDialog, config);
    this.editorDialogRef.componentInstance.intakeApplication = this.intakeApplication;
    if (employment) this.editorDialogRef.componentInstance.employment = employment;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}
