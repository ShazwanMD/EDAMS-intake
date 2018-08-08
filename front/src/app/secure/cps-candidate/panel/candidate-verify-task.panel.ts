import { AdmissionCandidateModuleState } from '..';
import { Candidate } from '../../../shared/model/admission/candidate.interface';
import { CandidateTask } from '../../../shared/model/admission/candidate-task.interface';
import { IntakeActions } from '../../policy/intakes/intake.action';
import {
  Component, OnInit, Input,
} from '@angular/core';
import { MdSnackBar } from '@angular/material';
import {Router, ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import { ReportActions } from '../../../shared/report/report.action';
import { AdmissionCandidateActions } from "../admission-candidate.action";
import { IntakeApplication } from "../../../shared/model/application/intake-application.interface";
import { ApplicationModuleState } from "../../application/index";

@Component({
  selector: 'pams-candidate-verify-task',
  templateUrl: './candidate-verify-task.panel.html',
})
export class CandidateVerifyTaskPanel implements OnInit {
  
  @Input() candidateTask: CandidateTask;
  
  private CANDIDATE_BY_ID: string[] = 'admissionCandidateModuleState.candidate'.split('.');
  
  private candidate$: Observable<Candidate>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<AdmissionCandidateModuleState>,
              private snackBar: MdSnackBar,
              private reportActions: ReportActions,
              private intakeActions: IntakeActions,
              private actions: AdmissionCandidateActions) {
     
      this.candidate$ = this.store.select(...this.CANDIDATE_BY_ID);
  }

  ngOnInit(): void {
      console.log("candidateTask.candidate" + this.candidateTask.candidateIntake.id);
      this.store.dispatch(this.actions.findCandidateById(this.candidateTask.candidateIntake.id));
  }

  select(){
    if (confirm('Sahkan Bagi Membuat Penawaran?')) {
      this.store.dispatch(this.actions.completeCandidateTask(this.candidateTask));
      this.goBack();
    } else {
      // return false;
    }
  }
  
  
  reject(){
      this.store.dispatch(this.actions.removeCandidateTask(this.candidateTask));
      this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/cps-candidate']);
    window.location.reload();
  }

}
