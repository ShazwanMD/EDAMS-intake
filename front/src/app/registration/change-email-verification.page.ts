import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';

import {Store} from '@ngrx/store';
import {RegistrationModuleState} from '.';
import {RegistrationActions} from './registration.action';

@Component({
  selector: 'pams-change-email-verification-page',
  templateUrl: './change-email-verification.page.html',
})

export class ChangeEmailVerificationPage implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private store: Store<RegistrationModuleState>,
              private actions: RegistrationActions) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { token: string }) => {
      let token: string = params.token;
      this.store.dispatch(this.actions.verifyUser(token));
    });
  }
}
