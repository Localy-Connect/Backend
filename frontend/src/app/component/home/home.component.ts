import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TownService } from '../../services/town/town.service';
import { AuthService } from '../../services/auth/auth.service';
import { CreateTownDialogComponent } from '../create-town-dialog/create-town-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  towns: any[] = [];
  searchTerm: string = '';
  userTownId: string | null = null;

  constructor(
    private townService: TownService,
    private authService: AuthService,
    private router: Router,
    public dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.userTownId = this.authService.getUserTownId();
    if (this.userTownId) {
      this.router.navigate(['/town', this.userTownId]);
    } else {
      this.getTownList();
    }
  }

  getTownList(): void {
    this.townService.getAllTowns().subscribe({
      next: (data) => {
        this.towns = data;
      },
      error: (err) => {
        console.error('Error fetching towns', err);
      }
    });
  }

  joinTown(townId: number): void {
    this.townService.joinTown(townId).subscribe({
      next: () => {
        this.router.navigate(['/town', townId]);
      },
      error: (err) => {
        console.error('Error joining town', err);
      }
    });
  }

  openCreateTownDialog(): void {
    const dialogRef = this.dialog.open(CreateTownDialogComponent, {
      width: '250px'
    });

    dialogRef.afterClosed().subscribe({
      next: (result) => {
        if (result) {
          this.townService.createTown(result).subscribe({
            next: (response) => {
              this.getTownList();
            },
            error: (error) => {
              this.snackBar.open("Town couldn't be created. Try a new name.", 'Close', {
                duration: 3000
              });
            }
          });
        }
      },
      error: (err) => {
        console.error('Error closing dialog', err);
      }
    });
  }

  filteredTowns(): any[] {
    if (!this.searchTerm) {
      return this.towns;
    }
    return this.towns.filter(town => 
      town.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
