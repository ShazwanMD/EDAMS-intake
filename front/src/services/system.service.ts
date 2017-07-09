import {Injectable} from '@angular/core';
import {Response, Http, Headers, RequestOptions} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {Module} from '../app/shared/model/system/module.interface';
import {AuthenticationService} from './authentication.service';
import {AuthenticatedUser} from "../app/shared/model/identity/authenticated-user.interface";

@Injectable()
export class SystemService {

  constructor(private http: Http,
              private _http: HttpInterceptorService,
              private authnService: AuthenticationService) {
  }

  // ====================================================================================================
  // MODULES
  // ====================================================================================================

  findAuthorizedModules(): Observable<Module[]> {
    let headers: Headers = new Headers({'Authorization': 'Bearer ' + this.authnService.token});
    let options: RequestOptions = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/system/modules/authorized', options)
      .map((res: Response) => <Module[]>res.json());
  }

  findAuthenticatedUser(): Observable<AuthenticatedUser> {
    let headers: Headers = new Headers({'Authorization': 'Bearer ' + this.authnService.token});
    let options: RequestOptions = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/system/authenticatedUser', options)
      .map((res: Response) => <AuthenticatedUser>res.json());
  }
}
