import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../../services/auth/auth.service';
import { UsersService } from '../../services/user/users.service';
import { ChangePasswordDialogComponent } from '../change-password-dialog/change-password-dialog.component';
import { ChangeUserDataDialogComponent } from '../change-user-data-dialog/change-user-data-dialog.component';
import { User } from "../../model/model";

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
    private router: Router,
    public dialog: MatDialog
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
        console.error(err);
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
          console.error(error);
        }
      });
    } else {
      console.error();
      this.router.navigate(['/signin']);
    }
  }

  openChangePasswordDialog(): void {
    const dialogRef = this.dialog.open(ChangePasswordDialogComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Password was changed successfully');
      }
    });
  }

  openChangeUserDataDialog(): void {
    if (this.user) {
      const dialogRef = this.dialog.open(ChangeUserDataDialogComponent, {
        width: '400px',
        data: this.user
      });

      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          console.log('User data was updated successfully');
        }
      });
    }
  }
}
