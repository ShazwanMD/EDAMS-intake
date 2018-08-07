import { Pipe, PipeTransform } from '@angular/core';


@Pipe({name: 'intakeSessionCurrentPipe'})
export class IntakeSessionCurrentPipe implements PipeTransform {

    transform(value: boolean): any {
        if(value){
            return 'BUKA';
        } 
        else if(!value){
            return 'TUTUP';
        }else{
            return 'TIDAK BERKENAAN'
        }
    }
}