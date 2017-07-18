import { Employment } from '../../../shared/model/application/employment.interface';
import { IntakeApplication } from '../../../shared/model/application/intake-application.interface';
import { Language } from '../../../shared/model/application/language.interface';
import { Referee } from '../../../shared/model/application/referee.interface';
import { IntakeApplicationActions } from '../../application/intake-applications/intake-application.action';
import { IntakeActions } from '../../policy/intakes/intake.action';
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';
import {MdSnackBar, MdDialogRef, MdDialogConfig, MdDialog} from '@angular/material';

@Component({
  selector: 'pams-candidate-profile-select',
  templateUrl: './candidate-profile-select.dialog.html',
})

export class CandidateProfileSelectDialog implements OnInit {


  private dummyData: any[] = [
    {'subject': 'Bahasa Malaysia', 'grade': 'A+'},
    {'subject': 'Bahasa Inggeris', 'grade': 'B'},
    {'subject': 'Geografi', 'grade': 'C+'},
    {'subject': 'Sejarah', 'grade': 'D+'},
    {'subject': 'Matematik', 'grade': 'A+'},
    {'subject': 'Matematik Tambahan', 'grade': 'A+'},
    {'subject': 'Fizik', 'grade': 'A+'},
    {'subject': 'Biologi', 'grade': 'B+'},
    {'subject': 'Kimia', 'grade': 'B+'},
  ];

  private dummyColumns: any[] = [
    {name: 'subject', label: 'Subject'},
    {name: 'grade', label: 'Grade'},
  ];

  private INTAKE_APPLICATION: string[] = 'applicationModuleState.intakeApplication'.split('.');
  private EMPLOYMENTS: string[] = 'applicationModuleState.employments'.split('.');
  private LANGUAGES: string[] = 'applicationModuleState.languages'.split('.');
  private REFEREES: string[] = 'applicationModuleState.referees'.split('.');
  private ATTACHMENTS: string[] = 'applicationModuleState.attachments'.split('.');
  private SPM_RESULTS: string[] = 'applicationModuleState.spmResults'.split('.');
  private BACHELOR_RESULTS: string[] = 'applicationModuleState.bachelorResults'.split('.');
  private DIPLOMA_RESULTS: string[] = 'applicationModuleState.diplomaResults'.split('.');

  private intakeApplication$: Observable<IntakeApplication>;
  private employments$: Observable<Employment>;
  private languages$: Observable<Language>;
  private referees$: Observable<Referee>;
  private attachments$: Observable<Referee>;
  private applicationForm: FormGroup;

  @Input() intakeApplication: IntakeApplication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private vcf: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private intakeActions: IntakeActions,
              private dialog: MdDialog,
              private editorDialog: MdDialogRef<CandidateProfileSelectDialog>,
              private editorDialogRef: MdDialogRef<CandidateProfileSelectDialog>,
              private snackBar: MdSnackBar,
              private store: Store<CandidateProfileSelectDialog>) {

    this.intakeApplication$ = this.store.select(...this.INTAKE_APPLICATION);
    this.employments$ = this.store.select(...this.EMPLOYMENTS);
    this.languages$ = this.store.select(...this.LANGUAGES);
    this.referees$ = this.store.select(...this.REFEREES);
    this.attachments$ = this.store.select(...this.ATTACHMENTS);
  }

  ngOnInit(): void {
    let referenceNo: string = this.intakeApplication.referenceNo;
    this.store.dispatch(this.actions.findIntakeApplicationByReferenceNo(referenceNo));
  }

  select(intakeApplication: IntakeApplication) {
    let snackBarRef = this.snackBar.open('Confirm to Select This Applicant?', 'Ok');
    snackBarRef.afterDismissed().subscribe(() => {
      this.store.dispatch(this.actions.selectIntakeApplication(intakeApplication));
      this.editorDialog.afterClosed().subscribe((res) => {
        this.store.dispatch(this.intakeActions.findIntakeByReferenceNoAndBidStatus(intakeApplication.intake.referenceNo));
      });
      this.editorDialog.close();
    });
  }

  reject(intakeApplication: IntakeApplication) {
    this.showDialog(intakeApplication);
    //  this.editorDialog.close();
  }

  showDialog(intakeApplication): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '40%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(CandidateProfileSelectDialog, config);
    this.editorDialogRef.componentInstance.intakeApplication = intakeApplication;
    this.editorDialog.afterClosed().subscribe((res) => {
      this.store.dispatch(this.intakeActions.findIntakeByReferenceNoAndBidStatus(intakeApplication.intake.referenceNo));

    });
  }

}