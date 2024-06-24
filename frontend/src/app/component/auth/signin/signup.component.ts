import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from "../../../services/auth/auth.service";
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from "@angular/forms";
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
      phoneNr: ['', [Validators.required, this.numericValidator]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  // Custom validator to check if the phone number is numeric
  numericValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) {
      return { required: true };
    }
    if (isNaN(value)) {
      return { notNumeric: true };
    }
    return null;
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
