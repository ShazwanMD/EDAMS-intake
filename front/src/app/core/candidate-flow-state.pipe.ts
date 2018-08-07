import { FlowState } from './flow-state.enum';
import { Observable } from 'rxjs/Observable';
import { Pipe, PipeTransform } from '@angular/core';


@Pipe({name: 'candidateFlowStatePipe'})
export class CandidateFlowStatePipe implements PipeTransform{
    
    transform(value: FlowState): any {
        if(!value){
            return value;
        }

        switch(FlowState[value.toString()]){
            case FlowState.DRAFTED:{
                return 'SELECTED';
            }
            case FlowState.VERIFIED:{
                return 'RECOMMENDED';
            }
            case FlowState.PUBLISHED:{
                return 'OFFERED';
            }
            case FlowState.COMPLETED:{
                return 'REGISTERED';
            }
            default:{
                return value;
            }
        }
    }
}