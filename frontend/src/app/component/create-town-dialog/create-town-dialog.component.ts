import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-create-town-dialog',
  templateUrl: './create-town-dialog.component.html',
  styleUrls: ['./create-town-dialog.component.css']
})
export class CreateTownDialogComponent {
  townName: string | undefined;

  constructor(public dialogRef: MatDialogRef<CreateTownDialogComponent>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
