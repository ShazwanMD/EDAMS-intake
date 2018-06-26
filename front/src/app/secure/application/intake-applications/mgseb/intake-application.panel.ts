import { StudyCenterCode } from './../../../../shared/model/common/study-center-code.interface';
import { EmploymentTypeCode } from './../../../../shared/model/common/employment-type-code.interface';
import { EmploymentSectorCode } from './../../../../shared/model/common/employment-sector-code.interface';
import { ResidencyCode } from './../../../../shared/model/common/residency-code.interface';
import { Attachment } from './../../../../shared/model/application/attachment.interface';
import { Result } from '../../../../shared/model/application/result.interface';
import { ReligionCode } from '../../../../shared/model/common/religion-code.interface';
import { MaritalCode } from '../../../../shared/model/common/marital-code.interface';
import { RaceCode } from '../../../../shared/model/common/race-code.interface';
import { GenderCode } from '../../../../shared/model/common/gender-code.interface';
import { Referee } from '../../../../shared/model/application/referee.interface';
import { Employment } from '../../../../shared/model/application/employment.interface';
import { Component, OnInit, Input ,ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { ApplicationModuleState } from '../../index';
import { IntakeApplicationActions } from '../intake-application.action';
import { Observable} from 'rxjs/Observable';
import { IntakeApplication } from '../../../../shared/model/application/intake-application.interface';
import { Language } from '../../../../shared/model/application/language.interface';
import { NationalityCode } from '../../../../shared/model/common/nationality-code.interface';
import { DisabilityCode } from '../../../../shared/model/common/disability-code.interface';
import { EthnicityCode } from '../../../../shared/model/common/ethnicity-code.interface';
import { CountryCode } from '../../../../shared/model/common/country-code.interface';
import { StateCode } from '../../../../shared/model/common/state-code.interface';
import { MdSnackBar, MdSnackBarRef, MdTabChangeEvent, SimpleSnackBar, MdDialogConfig, MdDialogRef, MdDialog } from '@angular/material';
import { PromoCodeDialog } from "../dialog/promo-code.dialog";
import { GuardianType } from '../../../../shared/model/application/guardian-type.enum';

@Component({
  selector: 'pams-mgseb-intake-application',
  templateUrl: './intake-application.panel.html',
})

export class MgsebIntakeApplicationPanel implements OnInit {

  private editorDialogRef: MdDialogRef<PromoCodeDialog>; 

  [x: string]: any;

  @Input() intakeApplications: IntakeApplication;

  private TAB_INDEX: string[] = 'applicationModuleState.tabIndex'.split('.');
  private EMPLOYMENTS: string[] = 'applicationModuleState.employments'.split('.');
  private LANGUAGES: string[] = 'applicationModuleState.languages'.split('.');
  private REFEREES: string[] = 'applicationModuleState.referees'.split('.');
  private RESULTS: string[] = 'applicationModuleState.results'.split('.');
  private ATTACHMENTS: string[] = 'applicationModuleState.attachments'.split('.');

  private employments$: Observable<Employment>;
  private languages$: Observable<Language>;
  private referees$: Observable<Referee>;
  private results$: Observable<Result>;
  private attachments$: Observable<Attachment>;
  private applicationForm: FormGroup;
  private tabIndex$: Observable<number>;
  private _intakeApplication: IntakeApplication;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private vcf: ViewContainerRef,
    private dialog: MdDialog,
    private actions: IntakeApplicationActions,
    private snackBar: MdSnackBar,
    private store: Store<ApplicationModuleState>) {

    this.attachments$ = this.store.select(...this.ATTACHMENTS);
    this.employments$ = this.store.select(...this.EMPLOYMENTS);
    this.languages$ = this.store.select(...this.LANGUAGES);
    this.referees$ = this.store.select(...this.REFEREES);
    this.results$ = this.store.select(...this.RESULTS);
    this.tabIndex$ = this.store.select(...this.TAB_INDEX);
  }

  get intakeApplication(): IntakeApplication {
    return this._intakeApplication;
  }

  set intakeApplication(value: IntakeApplication) {
    this._intakeApplication = value;
  }

  ngOnInit(): void {
    this.applicationForm = this.formBuilder.group({
      id: [undefined],
      referenceNo: [''],
      researchTitle: ['', Validators.required],
      promoCode: [''],
      rank: [0],
      merit: [0],
      name: ['', Validators.required],
      credentialNo: ['', Validators.required],
      birthDate: [undefined, Validators.required],
      placeOfBirth: [''],
      mobile: ['', Validators.required],
      okuNo: [''],
      email:['', Validators.required],
      phone: [''],
      fax: [''],
      age: [0],
      passExpDate: [''],
      studyCenterCode: [<StudyCenterCode>{}, Validators.required],

      income: [''],
      position: [''],
      employmentSectorCode: [<EmploymentSectorCode>{}, Validators.required],
      employmentTypeCode: [<EmploymentTypeCode>{}, Validators.required],
      employerAddress1 : [''],
      employerAddress2 : [''],
      employerAddress3 : [''],
      employerPostcode : [''],
      employerState : [<StateCode>{}, Validators.required],
      employerNo : [''],


      guardianName : [''],
      guardianIdentityNo : [''],
      guardianPhoneNo : [''],
      guardianAddress1 : [''],
      guardianAddress2 : [''],
      guardianAddress3 : [''],
      guardianPostcode : [''],
      guardianState : [<StateCode>{}, Validators.required],
      guardianCountryCode: [<CountryCode>{}, Validators.required],
      guardianType: [<GuardianType>{}, Validators.required],

      mailingAddress1: ['', Validators.required],
      mailingAddress2: ['', Validators.required],
      mailingAddress3: [''],
      mailingPostcode: ['', Validators.required],
      officialAddress1: ['', Validators.required],
      officialAddress2: ['', Validators.required],
      officialAddress3: [''],
      officialPostcode: ['', Validators.required],
      genderCode: [<GenderCode>{}, Validators.required],
      maritalCode: [<MaritalCode>{}, Validators.required],
      disabilityCode: [<DisabilityCode>{}, Validators.required],
      ethnicityCode: [<EthnicityCode>{}, Validators.required],
      raceCode: [<RaceCode>{}, Validators.required],
      religionCode: [<ReligionCode>{}, Validators.required],
      nationalityCode: [<NationalityCode>{}, Validators.required],
      residencyCode: [<ResidencyCode>{}, Validators.required],
      mailingStateCode: [<StateCode>{}, Validators.required],
      mailingCountryCode: [<CountryCode>{}, Validators.required],
      officialStateCode: [<StateCode>{}, Validators.required],
      officialCountryCode: [<CountryCode>{}, Validators.required],
      verified: [true],
      sponsored: [true],
      selfSponsored: [true],
      paid: [true],

      spmResultAttached: [true],
      stpmResultAttached: [true],
      stamResultAttached: [true],
      muetResultAttached: [true],
      diplomaResultAttached: [true],
      bachelorResultAttached: [true],
      masterResultAttached: [true],
      phdResultAttached: [true],
      toeflResultAttached: [true],
      ieltsResultAttached: [true],
      languageResultAttached: [true],
      processingFeeAttached: [true],
      bankStatementAttached: [true],
      refereeFormAttached: [true],
      researchProposalAttached: [true],
      sponsorLetterAttached: [true],
      icCopyAttached: [true],
      passportCopyAttached: [true],
      processingReceipt: [true],
      foreignResult: [true],
      educationResult: [true],
      academic: [true],
      financialLetter: [true],
      researchProposal: [true],
      bankStatement: [true],
      refereeForm: [true],
      declared: [true, Validators.requiredTrue],
      passportImageAttached:[true],
      apelCertificateAttached:[true],
      employmentVerificationAttached:[true],
      copyAddress: [false],
    });
    this.applicationForm.patchValue(this._intakeApplication);
  }

  onTabChange(event: MdTabChangeEvent): void {
    console.log('tab change: ' + event.index);
    this.store.dispatch(this.actions.selectTabIndex(event.index));
    this.store.dispatch(this.actions.updateIntakeApplication(this.applicationForm.value));
  }

  submit(application: IntakeApplication, isValid: boolean) {
    if (confirm('Confirm to Submit this application?')) {
      this.store.dispatch(this.actions.submitIntakeApplication(application));
      let snackBarRef = this.snackBar.open('Your application has been successfully submitted!','', {duration: 3000,});
      snackBarRef.afterDismissed().subscribe(() => {
        this.goBack();
      });
      
    } else {
      return false;
    }
  }

   promoCodeDialog(intakeApplication : IntakeApplication): void {
     console.log('promoCodeDialog');
    let config: MdDialogConfig = new MdDialogConfig();
     config.viewContainerRef = this.vcf;
     config.role = 'dialog';
     config.width = '70%';
    config.height = '65%';
     config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(PromoCodeDialog, config);
    this.editorDialogRef.componentInstance.intakeApplications = intakeApplication;
     this.editorDialogRef.afterClosed().subscribe((res) => {
       console.log('close dialog');
    });
   }

  // copyAddress(application: IntakeApplication): void {
  //   this.store.dispatch(this.actions.updateIntakeApplication(this.applicationForm.value));
  //   let snackBarRef: MdSnackBarRef<SimpleSnackBar> = this.snackBar.open('Confirm to Copy this address?', 'Ok');
  //   snackBarRef.afterDismissed().subscribe(() => {
  //     if (!application.id) this.store.dispatch(this.actions.updateIntakeApplication(application));
  //     else this.store.dispatch(this.actions.copyAddressApplication(application));
  //     this.goBack();
  //   });
  // }

  // copyAddress(application: IntakeApplication) {
  //   // this.store.dispatch(this.actions.updateIntakeApplication(this.applicationForm.value));
  //   if (confirm('Confirm to copy this address?')) {
  //        this.store.dispatch(this.actions.copyAddressApplication(application));  
  //     this.goBack();
  //   } else {
  //     return false;
  //   }
  // }

  goBack(): void {
    this.router.navigate(['/secure']);
  }
}
