import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { SigninResponse, SignupResponse, User } from './auth.models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient, private router: Router) { }

  signin(credentials: { name: string; password: string }): Observable<SigninResponse> {
    return this.http.post<SigninResponse>(`${this.apiUrl}/signin`, credentials).pipe(
      tap(response => {
        this.setToken(response.token);
        this.setUser(response.user);
        if (response.user.town) {
          this.router.navigate([`/town/${response.user.town.id}`]);
        } else {
          this.router.navigate(['/home']);
        }
      })
    );
  }

  signup(user: { password: string; phoneNr: string; name: string; email: string }): Observable<SignupResponse> {
    return this.http.post<SignupResponse>(`${this.apiUrl}/signup`, user).pipe(
      tap(() => {
        this.router.navigate(['/signin']);
      })
    );
  }

  logout(): void {
    this.removeToken();
    this.removeUser();
    this.router.navigate(['/signin']);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
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

  isLoggedIn(): boolean {
    return this.isAuthenticated() && !!this.getUserTownId();
  }
}
