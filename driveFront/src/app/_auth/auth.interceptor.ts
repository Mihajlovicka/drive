import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {
  constructor(private service: UserService,
              private router:Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>>
  {

    if (req.headers.get('No-Auth') === 'True') {
      return next.handle(req.clone());
    }

    const token = this.service.getToken();
    if(token == null) return next.handle(req.clone());
    req = this.addToken(req, token);
    return next.handle(req).pipe(
      catchError(
        (err:any) => {
          console.log(err.status);
          console.log(err);
          if(err.status === 401){
            this.router.navigate(['/login']);
          }else if(err.status === 403){
            this.router.navigate(['/forbidden']);
          } else if(err.status == 500){
            alert(err.error.message)
          }
          return throwError(err.error);
        }
      )
    )

  }

  private addToken(req: HttpRequest<any>, token: string){
    return req.clone(
      {
        setHeaders:{
          Authorization:`Bearer ${token}`
        },withCredentials: true
      }
    );
  }

}
