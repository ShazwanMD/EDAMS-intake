import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { StaffType } from '../../../identity/staff-type.enum';
import { ActorType } from '../../../identity/actor-type.enum';

@Component({
  selector: 'pams-actor-type-select',
  templateUrl: './actor-type-select.component.html',
})
export class ActorTypeSelectComponent implements OnInit {

  private actorTypes: ActorType[] = <ActorType[]>[];
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor() {
    for (let n in ActorType) {
      if (typeof ActorType[n] === 'string')
        this.actorTypes.push(ActorType[n.toString()]);
    }
  }

  ngOnInit() {
  }

  selectChangeEvent(event: ActorType) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

