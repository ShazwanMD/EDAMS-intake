import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { StaffType } from '../../../identity/staff-type.enum';
import { StaffCategoryType } from '../../../identity/staff-category-type.enum';

@Component({
  selector: 'pams-staff-type-select',
  templateUrl: './staff-type-select.component.html',
})
export class StaffTypeSelectComponent implements OnInit {

  private staffCategorys: StaffCategoryType[] = <StaffCategoryType[]>[];
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor() {
    for (let n in StaffCategoryType) {
      if (typeof StaffCategoryType[n] === 'string')
        this.staffCategorys.push(StaffCategoryType[n.toString()]);
    }
  }

  ngOnInit() {
  }

  selectChangeEvent(event: StaffCategoryType) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

