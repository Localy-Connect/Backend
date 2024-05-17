import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TownService} from "../../services/town/town.service";
import {AuthService} from "../../services/auth/auth.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  towns: any[] = [];
  userTownId: string | null = null;

  constructor(private townService: TownService, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.userTownId = this.authService.getUserTownId();
    if (this.userTownId) {
      this.router.navigate(['/town', this.userTownId]);
    } else {
      this.townService.getAllTowns().subscribe(data => {
        this.towns = data;
      });
    }
  }

  joinTown(townId: number): void {
    this.townService.joinTown(townId).subscribe(() => {
      window.location.reload();
    });
  }
}
