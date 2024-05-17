import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from "../../../services/auth/auth.service";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  user: { name: string; password: string } = { name: '', password: '' };
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  signin(): void {
    this.authService.signin(this.user).subscribe(
      () => this.router.navigate(['/home']),
      () => this.errorMessage = 'SignIn failed'
    );
  }
}
