import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from "../../../services/auth/auth.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  user: { name: string; email: string; phoneNr: string; password: string } = { name: '', email: '', phoneNr: '', password: '' };
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  signup(): void {
    this.authService.signup(this.user).subscribe(
      () => this.router.navigate(['/signin']),
      () => this.errorMessage = 'SignUp failed'
    );
  }
}
