import { EmploymentWorkingDuration } from './../../../../shared/model/application/employment-working-duration.enum';
import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'pams-employment-working-duration',
  templateUrl:'./employment-working-duration.component.html',
  styleUrls: ['./employment-working-duration.scss'],
})


export class EmploymentWorkingDurationComponent implements OnInit {

  private employmentWorkingDurations: EmploymentWorkingDuration[] = <EmploymentWorkingDuration[]>[];
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor() {
    for (let n in EmploymentWorkingDuration) {
      if (typeof EmploymentWorkingDuration[n] === 'string')
        this.employmentWorkingDurations.push(EmploymentWorkingDuration[n.toString()]);
    }
  }

  ngOnInit() {
  }

  selectChangeEvent(event: EmploymentWorkingDuration) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
