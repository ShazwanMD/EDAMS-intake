import { DiplomaResult } from './../diploma-result-interface';
import { ReligionCode } from './../../../common/religion-codes/religion-code.interface';
import { MaritalCode } from './../../../common/marital-codes/marital-code.interface';
import { RaceCode } from './../../../common/race-codes/race-code.interface';
import { GenderCode } from './../../../common/gender-codes/gender-code.interface';
import { BachelorResult } from './../bachelor-result-interface';
import { SpmResult } from './../spm-result.interface';
import { Address } from './../address.interface';
import {Referee} from './../referee.interface';
import {Employment} from './../employment.interface';
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {ApplicationModuleState} from "../../index";
import {IntakeApplicationActions} from "../intake-application.action";
import {Observable} from "rxjs/Observable";
import {IntakeApplication} from "../intake-application.interface";
import {Language} from "../language.interface";
import { NationalityCode } from "../../../common/nationality-codes/nationality-code.interface";




@Component({
  selector: 'pams-intake-application',
  templateUrl: './intake-application.page.html',
})

export class CpsIntakeApplicationPage implements OnInit {

  private INTAKE_APPLICATION: string[] = "applicationModuleState.intakeApplication".split(".");
  private EMPLOYMENTS: string[] = "applicationModuleState.employments".split(".");
  private LANGUAGES: string[] = "applicationModuleState.languages".split(".");
  private REFEREES: string[] = "applicationModuleState.referees".split(".");
  private ADDRESSES: string[] = "applicationModuleState.addresses".split(".");
  private SPM_RESULTS: string[] = "applicationModuleState.spmResults".split(".");
  private BACHELOR_RESULTS: string[] = "applicationModuleState.bachelorResults".split(".");
  private DIPLOMA_RESULTS: string[] = "applicationModuleState.diplomaResults".split(".");


  private intakeApplication$: Observable<IntakeApplication>;
  private employments$: Observable<Employment>;
  private languages$: Observable<Language>;
  private referees$: Observable<Referee>;
  private addresses$: Observable<Address>;
  private spmResults$: Observable<SpmResult>;
  private bachelorResults$: Observable<BachelorResult>;
  private diplomaResults$: Observable<DiplomaResult>;
  private  applicationForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private vcf: ViewContainerRef,
              private actions: IntakeApplicationActions,
              private store: Store<ApplicationModuleState>) {

    this.intakeApplication$ = this.store.select(...this.INTAKE_APPLICATION);
    this.employments$ = this.store.select(...this.EMPLOYMENTS);
    this.languages$ = this.store.select(...this.LANGUAGES);
    this.referees$ = this.store.select(...this.REFEREES);
    this.addresses$ = this.store.select(...this.ADDRESSES);
    this.spmResults$ = this.store.select(...this.SPM_RESULTS);
    this.bachelorResults$ = this.store.select(...this.BACHELOR_RESULTS);
    this.diplomaResults$ = this.store.select(...this.DIPLOMA_RESULTS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findIntakeApplicationByReferenceNo(referenceNo));
    });

    this.applicationForm = this.formBuilder.group(<IntakeApplication>{
      id: null,
      referenceNo: '',
      researchTitle:'',
      rank: 0,
      merit: 0,
      name: '',
      credentialNo: '',
      birthDate: null,
      mobile: '',
      okuNo: '',
      email: '',
      phone: '',
      fax: '',
      age: 0,

      genderCode: <GenderCode>{},
      maritalCode: <MaritalCode>{},
      raceCode: <RaceCode>{},
      religionCode: <ReligionCode>{},
      nationalityCode: <NationalityCode>{},
      verified: false,
      sponsored: false,
      selfSponsored: false,
    });
    this.intakeApplication$.subscribe(intakeApplication => this.applicationForm.patchValue(intakeApplication));
  }

  onTabChange(): void {
    console.log("tab change");
    this.store.dispatch(this.actions.updateIntakeApplication(this.applicationForm.value));
  }
}
