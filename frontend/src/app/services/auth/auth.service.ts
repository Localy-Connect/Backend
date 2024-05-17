import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {BehaviorSubject, Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {SigninResponse, SignupResponse, User} from './auth.models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';
  private loggedIn = new BehaviorSubject<boolean>(false);
  private currentUser = new BehaviorSubject<User | null>(null);

  constructor(private http: HttpClient, private router: Router) {
    const token = this.getToken();
    const user = this.getUser();
    if (token && user) {
      this.loggedIn.next(true);
      this.currentUser.next(user);
    }
  }

  signin(credentials: { name: string; password: string }): Observable<SigninResponse> {
    return this.http.post<SigninResponse>(`${this.apiUrl}/signin`, credentials).pipe(
      tap(response => {
        this.setToken(response.token);
        this.setUser(response.user);
        this.loggedIn.next(true);
        this.currentUser.next(response.user);
        if (response.user.town) {
          this.router.navigate([`/town/${response.user.town.id}`]);
        } else {
          this.router.navigate(['/home']);
        }
      })
    );
  }

  signup(user: { name: string; email: string; phoneNr: string; password: string }): Observable<SignupResponse> {
    return this.http.post<SignupResponse>(`${this.apiUrl}/signup`, user).pipe(
      tap(() => {
        this.router.navigate(['/signin']);
      })
    );
  }

  logout(): void {
    this.removeToken();
    this.removeUser();
    this.loggedIn.next(false);
    this.currentUser.next(null);
    this.router.navigate(['/signin']);
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  private setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  private removeToken(): void {
    localStorage.removeItem('token');
  }

  private setUser(user: User): void {
    localStorage.setItem('user', JSON.stringify(user));
  }

  getUser(): User | null {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  private removeUser(): void {
    localStorage.removeItem('user');
  }

  getUserTownId(): string | null {
    const user = this.getUser();
    return user && user.town ? user.town.id.toString() : null;
  }
}
