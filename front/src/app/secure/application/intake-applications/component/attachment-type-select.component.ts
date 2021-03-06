import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {AttachmentType} from '../../../../shared/model/application/attachment-type.enum';

@Component({
  selector: 'pams-attachment-type-select',
  templateUrl: './attachment-type-select.component.html',
  styleUrls: ['./attachment-type-select.scss'],
})
export class AttachmentTypeSelectComponent implements OnInit {

  private attachmentTypes: AttachmentType[] = <AttachmentType[]>[];
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor() {
    for (let n in AttachmentType) {
      if (typeof AttachmentType[n] === 'string')
        this.attachmentTypes.push(AttachmentType[n.toString()]);
    }
  }

  ngOnInit() {
  }

  selectChangeEvent(event: AttachmentType) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
