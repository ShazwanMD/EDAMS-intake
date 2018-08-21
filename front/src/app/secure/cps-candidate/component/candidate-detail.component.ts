import { Result } from './../../../shared/model/application/result.interface';
import { Attachment } from './../../../shared/model/application/attachment.interface';
import { Candidate } from '../../../shared/model/admission/candidate.interface';
import { Employment } from '../../../shared/model/application/employment.interface';
import { IntakeApplication } from '../../../shared/model/application/intake-application.interface';
import { Language } from '../../../shared/model/application/language.interface';
import { Referee } from '../../../shared/model/application/referee.interface';
import { ApplicationModuleState } from '../../application';
import { IntakeApplicationActions } from '../../application/intake-applications/intake-application.action';
import { IntakeActions } from '../../policy/intakes/intake.action';
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';
import {MdSnackBar, MdDialogRef, MdDialogConfig, MdDialog} from '@angular/material';
import { AdmissionActions } from "../../admission/admission.action";
import { SpmResultCode } from '../../../shared/model/common/spm-result-code.interface';
import { StpmResultCode } from '../../../shared/model/common/stpm-result-code.interface';
import { DiplomaResultCode } from '../../../shared/model/application/diploma-result.interface';

@Component({
  selector: 'pams-candidate-detail',
  templateUrl: './candidate-detail.component.html',
})

export class CandidateProfileComponent implements OnInit {


  private INTAKE_APPLICATION: string[] = 'applicationModuleState.intakeApplication'.split('.');
  private EMPLOYMENTS: string[] = 'applicationModuleState.employments'.split('.');
  private LANGUAGES: string[] = 'applicationModuleState.languages'.split('.');
  private REFEREES: string[] = 'applicationModuleState.referees'.split('.');
  private ATTACHMENTS: string[] = 'applicationModuleState.attachments'.split('.');
  private RESULTS: string[] = 'applicationModuleState.results'.split('.');
  private SPM_RESULT_CODES: string[] = 'applicationModuleState.spmResultCodes'.split('.');
  private STPM_RESULT_CODES: string[] = 'applicationModuleState.stpmResultCodes'.split('.');
  private DIPLOMA_RESULT_CODES: string[] = 'applicationModuleState.diplomaResultCodes'.split('.');

  private intakeApplication$: Observable<IntakeApplication>;
  private employments$: Observable<Employment>;
  private languages$: Observable<Language>;
  private referees$: Observable<Referee>;
  private attachments$: Observable<Attachment>;
  private applicationForm: FormGroup;
  private results$: Observable<Result>;
  private spmResultCodes$: Observable<SpmResultCode>;
  private stpmResultCodes$: Observable<StpmResultCode>;
  private diplomaResultCodes$: Observable<DiplomaResultCode>;


  @Input() candidate: Candidate;
  @Input() intakeApplication: IntakeApplication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private vcf: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private admissionActions: AdmissionActions,
              private dialog: MdDialog,
              private snackBar: MdSnackBar,
              private store: Store<ApplicationModuleState>) {

    this.intakeApplication$ = this.store.select(...this.INTAKE_APPLICATION);
    this.employments$ = this.store.select(...this.EMPLOYMENTS);
    this.languages$ = this.store.select(...this.LANGUAGES);
    this.referees$ = this.store.select(...this.REFEREES);
    this.results$ = this.store.select(...this.RESULTS);        
    this.attachments$ = this.store.select(...this.ATTACHMENTS);
    this.spmResultCodes$ = this.store.select(...this.SPM_RESULT_CODES);
    this.stpmResultCodes$ = this.store.select(...this.STPM_RESULT_CODES);
    this.diplomaResultCodes$ = this.store.select(...this.DIPLOMA_RESULT_CODES); 
  }

  ngOnInit(): void {
    let referenceNo: string = this.candidate.application.referenceNo;
    this.store.dispatch(this.actions.findIntakeApplicationByReferenceNo(referenceNo));

  }

}
