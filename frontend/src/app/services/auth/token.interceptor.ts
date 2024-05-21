import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Router} from '@angular/router';
import {EMPTY, Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AuthService} from "./auth.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private router: Router, public auth: AuthService) {
  }

  private whitelist: Array<string> = ['/api/login']

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.whitelist.includes(request.url)) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.auth.getToken()}`
        }
      });
      return next.handle(request).pipe(catchError(error => {
        if (error.status === 401) {
          this.auth.logout();
          return EMPTY;
        } else {
          throw error;
        }
      }));
    }
    return next.handle(request);
  }
}
