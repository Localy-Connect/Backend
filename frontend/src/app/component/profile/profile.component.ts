import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { User } from '../../services/auth/auth.models';
import { UsersService } from '../../services/user/users.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User | null = null;

  constructor(
    private authService: AuthService,
    private userService: UsersService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.authService.isLoggedIn().subscribe({
      next: loggedIn => {
        if (!loggedIn) {
          this.router.navigate(['/signin']);
        } else {
          this.fetchUserData();
        }
      },
      error: err => {
        console.error('Error checking login status:', err);
        this.router.navigate(['/signin']);
      }
    });
  }

  fetchUserData(): void {
    const userId = this.authService.getUser()?.id;
    if (userId) {
      this.userService.getUserById(userId).subscribe({
        next: data => {
          this.user = data;
        },
        error: error => {
          console.error('Error fetching user data:', error);
        }
      });
    } else {
      console.error('User ID not found');
      this.router.navigate(['/signin']);
    }
  }
}
