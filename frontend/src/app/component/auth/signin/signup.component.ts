import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from "../../../services/auth/auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  errorMessage: string = '';
  signupForm: FormGroup;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) {
    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNr: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.signupForm.invalid) {
      return;
    }

    const user = {
      name: this.signupForm.value.name,
      email: this.signupForm.value.email,
      phoneNr: this.signupForm.value.phoneNr,
      password: this.signupForm.value.password
    };

    const observer = {
      next: () => this.router.navigate(['/signin']),
      error: () => this.errorMessage = 'SignUp failed',
      complete: () => console.log('SignUp request completed')
    };

    this.authService.signup(user).pipe(
      tap(() => { })
    ).subscribe(observer);
  }
}
