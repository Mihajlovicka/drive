import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {User} from "../model/user";
import {catchError, map, of, tap} from "rxjs";
import { environment } from '../environments/environment';

@Injectable()
export class UserService {

  host: string = environment.apiUrl + 'auth/'

  constructor(private http: HttpClient) {
  }

  register(user: User) {
     return this.http.post(`${this.host}register`, user)
  }

  login(email: string, password: string) {
    return this.http.post(`${this.host}login`, {email: email, password: password}).pipe(
      map((response: any) => {
        localStorage.setItem("email", response.email)
        localStorage.setItem("jwt", response.jwt)
        return response;
      })
    )
  }

  getLogged() {
    if (localStorage.getItem("email") !== undefined && localStorage.getItem("email") !== '')
      return localStorage.getItem("email")
    return null;
  }

  isLoggedIn(){
    return this.getLogged() != null;
  }

  isLoggedInObs() {
    return of(this.isLoggedIn()).pipe(tap((v) => console.log(v)));
  }

  logOut() {
    localStorage.removeItem("email")
    localStorage.removeItem("jwt")
  }

  public getToken():string|null{
    return localStorage.getItem('jwt');
  }

}
