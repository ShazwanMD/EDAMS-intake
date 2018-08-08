import { Attachment } from '../../../../shared/model/application/attachment.interface';
import { Component, ViewContainerRef, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import {Store} from '@ngrx/store';
import {ApplicationModuleState} from '../../index';
import {MdDialogRef, MdSnackBar} from '@angular/material';
import {IntakeApplicationActions} from '../intake-application.action';
import {IntakeApplication} from '../../../../shared/model/application/intake-application.interface';
import { AttachmentType } from '../../../../shared/model/application/attachment-type.enum';
import { AttachmentHelper } from './attachment-helper.interface';

@Component({
  selector: 'pams-attachment-creator',
  templateUrl: './attachment-creator.dialog.html',
  })

export class AttachmentCreatorDialog implements OnInit {

  private _intakeApplication: IntakeApplication;
  private _attachment: Attachment;
  private createForm: FormGroup;
  private edit: boolean = false;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private viewContainerRef: ViewContainerRef,
    private store: Store<ApplicationModuleState>,
    private actions: IntakeApplicationActions,
    private snackBar: MdSnackBar,
    private dialog: MdDialogRef<AttachmentCreatorDialog>) {
  }

  set attachment(value: Attachment){
    this._attachment = value;
    this.edit = true;
  }

  set intakeApplication(value: IntakeApplication) {
    this._intakeApplication = value;
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<AttachmentHelper>{
      attachmentType: AttachmentType.BACHELOR,
    })
      ;
  }

  upload(attachmentHelper: AttachmentHelper, file: File) {
    if(confirm('Sila pastikan lampiran anda tidak melebihi 1 mb'))
    {
      if(file.type == 'application/pdf'){
      this.store.dispatch(this.actions.addAndCheckAttachment(this._intakeApplication, file, attachmentHelper.attachmentType));
      this.dialog.close();
      window.location.reload();

    }else{
      console.log("not pdf");

      let snackBarRef = this.snackBar.open('Lampiran fail mesti dalam format PDF','OK');
      snackBarRef.afterDismissed().subscribe(() => {
         //window.location.reload();
     });
    }

    }
   else {

   }
  }
}
