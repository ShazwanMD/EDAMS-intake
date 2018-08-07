import { IntakeSessionCurrentPipe } from './secure/policy/intake-sessions/intake-session-current.pipe';
import { CandidateFlowStatePipe } from './core/candidate-flow-state.pipe';
import {NgModule}      from '@angular/core';
import {FlowStatePipe} from './core/flow-state.pipe';
import {BidStatusPipe} from './secure/application/intake-applications/bid-status.pipe';

@NgModule({
  imports: [],
  declarations: [
    FlowStatePipe,
    BidStatusPipe,
    CandidateFlowStatePipe,
    IntakeSessionCurrentPipe,
  ],
  exports: [
    FlowStatePipe,
    BidStatusPipe,
    CandidateFlowStatePipe,
    IntakeSessionCurrentPipe,
  ],
})

export class PipeModule {

  static forRoot() {
    return {
      ngModule: PipeModule,
      providers: [],
    };
  }
}
