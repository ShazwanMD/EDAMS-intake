import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {SetupModuleState} from './index';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';
import {AuthorizationService} from '../../../services/authorization.service';

@Component({
  selector: 'pams-setup-page',
  templateUrl: './setup.page.html',
})

export class SetupPage implements OnInit {

  private TITLE: string[] = 'setupModuleState.title'.split('.');
  private title$: Observable<string>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<SetupModuleState>,
              private authz: AuthorizationService) {
    this.title$ = this.store.select(...this.TITLE);
  }

  ngOnInit(): void {
    console.log('auth:' + JSON.stringify(this.authz.data));
    this.route.params.subscribe(() => {
    });
  }
}
