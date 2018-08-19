import { StpmResultCode } from './../../../../shared/model/common/stpm-result-code.interface';
import { Result } from '../../../../shared/model/application/result.interface';
import { Component, ViewContainerRef, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { ApplicationModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { IntakeApplicationActions } from '../intake-application.action';
import { IntakeApplication } from '../../../../shared/model/application/intake-application.interface';
import { ResultType } from '../../../../shared/model/application/result-type.enum';
import { GradeCode } from '../../../../shared/model/common/grade-code.interface';
import { StpmSubjectCode } from '../../../../shared/model/common/stpm-subject-code.interface';

@Component({
    selector: 'pams-stpm-result-creator',
    templateUrl: './stpm-result-creator.dialog.html',
})

export class StpmResultCreatorDialog implements OnInit {

    private _intakeApplication: IntakeApplication;
    private editForm: FormGroup;
    private edit: boolean = false;
    private _stpmResultCode: StpmResultCode;

    constructor(private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<ApplicationModuleState>,
        private actions: IntakeApplicationActions,
        private dialog: MdDialogRef<StpmResultCreatorDialog>) {
    }

    set stpmResultCode(value: StpmResultCode) {
        this._stpmResultCode = value;
        this.edit = true;
    }

    set intakeApplication(value: IntakeApplication) {
        this._intakeApplication = value;
    }

    ngOnInit(): void {
        this.editForm = this.formBuilder.group({
            id: null,
            graduationYear: 0,
            aggregate: '',
            gradeCode: <GradeCode>{},
            stpmSubjectCode: <StpmSubjectCode>{},
            resultType: ResultType.STPM,

        });
        if (this.edit) this.editForm.patchValue(this._stpmResultCode);
    }

    submit(stpmResultCode: StpmResultCode, isValid: boolean) {
        console.log("AddStpmResult");
        this.store.dispatch(this.actions.addStpmResultCode(this._intakeApplication, stpmResultCode));
        this.dialog.close();
    }
}
