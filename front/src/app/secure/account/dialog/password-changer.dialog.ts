import { AuthenticatedUser } from './../../../shared/model/identity/authenticated-user.interface';
import {PasswordChange} from '../../../shared/model/identity/password-change.interface';
import {User} from '../../identity/user.interface';
import {AccountActions} from '../account.action';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import {AccountModuleState} from '../index';
import { AuthenticationService } from "../../../../services/authentication.service";

@Component({
  selector: 'pams-password-changer',
  templateUrl: './password-changer.dialog.html',
})

export class PasswordChangerDialog implements OnInit {
  [x: string]: any;

  private changePasswordForm: FormGroup;
  private _user: User;
    private authenticatedUser: AuthenticatedUser;

  constructor(private formBuilder: FormBuilder,
              private dialog: MdDialogRef<PasswordChangerDialog>,
              private store: Store<AccountModuleState>,
              private authnService: AuthenticationService,
              private router: Router,
              private actions: AccountActions,
              private snackBar: MdSnackBar) {
  }

  set user(value: User) {
    this._user = value;
  }

  ngOnInit(): void {
    this.changePasswordForm = this.formBuilder.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      newPasswordAgain: ['', Validators.required],
    });
  }

    logout(): void {
    this.authnService.logout();
    this.router.navigate(['/login']);
  }

//   submit(change: PasswordChange, valid: boolean) {
//     this.store.dispatch(this.actions.changeUserPassword(change));
//     this.dialog.close();
//     this.logout();   
// } 

submit(change: PasswordChange, valid: boolean) {
   let snackBarRef = this.snackBar.open('Tukar Kata Laluan?', 'Ok');
    snackBarRef.afterDismissed().subscribe(() => {
      if(!change.newPassword)this.store.dispatch(this.actions.saveUser(change));
    this.store.dispatch(this.actions.changeUserPassword(change));
    this.dialog.close();
    this.logout();   
});
}
}
