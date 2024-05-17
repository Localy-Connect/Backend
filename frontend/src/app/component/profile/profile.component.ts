import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { User } from '../../services/auth/auth.models';
import {UsersService} from "../../services/user/users.service";

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
    this.authService.isLoggedIn().subscribe(loggedIn => {
      if (!loggedIn) {
        this.router.navigate(['/signin']);
      } else {
        this.fetchUserData();
      }
    });
  }

  fetchUserData(): void {
    this.userService.getUserById(this.authService.getUser()?.id).subscribe(
      data => {
        this.user = data;
      },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }
}
