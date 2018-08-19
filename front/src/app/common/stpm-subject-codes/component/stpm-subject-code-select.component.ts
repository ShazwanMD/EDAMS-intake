import { FormControl } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import { Component, OnInit,Input} from "@angular/core";
import { StpmSubjectCode } from "../../../shared/model/common/stpm-subject-code.interface";
import { CommonModuleState } from '../../index';
import { CommonActions } from '../../common.action';



@Component({
    selector: 'pams-stpm-subject-code-select',
    templateUrl: './stpm-subject-code-select.component.html',
    styleUrls: ['./stpm-subject-code-select.scss'],
})
export class StpmSubjectCodeSelectComponent implements OnInit {

    private STPM_SUBJECT_CODE: string[] = 'commonModuleState.stpmSubjectCodes'.split('.');
    @Input() placeholder: string;
    @Input() innerFormControl: FormControl;
    stpmSubjectCodes$: Observable<StpmSubjectCode[]>;

    constructor(private store: Store<CommonModuleState>,
        private actions: CommonActions) {
        this.stpmSubjectCodes$ = this.store.select(...this.STPM_SUBJECT_CODE);
    }

    ngOnInit() {
        this.store.dispatch(this.actions.findStpmSubjectCodes());
    }

    selectChangeEvent(event: StpmSubjectCode) {
        this.innerFormControl.setValue(event, {emitEvent: false});
    }
}

