import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {UserRegistration} from '../app/shared/model/registration/user-registration.interface';
import { environment } from '../environments/environment.prod';

@Injectable()
export class RegistrationService {

  private REGISTRATION_API: string = environment.endpoint + '/api/registration';

  constructor(private _http: HttpInterceptorService) {
  }

  registerUser(registration: UserRegistration): Observable<String> {
    return this._http.post(this.REGISTRATION_API + '/registerUser', JSON.stringify(registration))
      .flatMap((res: Response) => Observable.of(res.text()))
      .catch(this.handleError);
  }

  verifyUser(token: String): Observable<Boolean> {
    return this._http.get(this.REGISTRATION_API + '/verifyUser/' + token)
      .map((res: Response) => <Boolean>res.json());
  }
  
  forgetPassword(email: String): Observable<String> {
    console.log('user email: ' + email);
    return this._http.get(this.REGISTRATION_API + '/forgetPassword/' + email)
      .map((res: Response) => <String>res.text())
      .catch(this.handleError);
  }

  private handleError(error: Response | any) {
    let body: any = error.json();
    return Observable.throw(body);
  }
}
