import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UsersService } from '../../services/user/users.service';
import { User } from '../../model/model';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-change-user-data-dialog',
  templateUrl: './change-user-data-dialog.component.html',
  styleUrls: ['./change-user-data-dialog.component.css']
})
export class ChangeUserDataDialogComponent {
  userData: User;

  constructor(
    public dialogRef: MatDialogRef<ChangeUserDataDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User,
    private userService: UsersService,
    private snackBar: MatSnackBar
  ) {
    this.userData = { ...data };
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    this.userService.updateUser(this.userData).subscribe({
      next: response => {
        console.log('User data updated successfully', response);
        this.dialogRef.close(true);
      },
      error: () => {
        this.snackBar.open("Profile couldn't be changed. Try again.", 'Close', {
          duration: 3000
        });
      }
    });
  }
}
