import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from "../../../services/auth/auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  errorMessage: string = '';
  signinForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.signinForm = this.fb.group({
      name: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.signinForm.invalid) {
      return;
    }

    const user = {
      name: this.signinForm.value.name,
      password: this.signinForm.value.password
    };

    const observer = {
      next: () => this.router.navigate(['/home']),
      error: () => this.errorMessage = 'SignIn failed',
      complete: () => console.log('SignIn request completed')
    };

    this.authService.signin(user).pipe(
      tap(() => { })
    ).subscribe(observer);
  }
}
