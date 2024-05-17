import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TownService } from "../../services/town/town.service";
import { AuthService } from "../../services/auth/auth.service";
import { MessageService } from "../../services/message/message.service";

@Component({
  selector: 'app-town',
  templateUrl: './town.component.html',
  styleUrls: ['./town.component.css']
})
export class TownComponent implements OnInit {
  town: any;
  messages: any[] = [];
  messageTitle: string = '';
  messageText: string = '';

  constructor(
    private route: ActivatedRoute,
    private townService: TownService,
    private authService: AuthService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.loadTown();
  }

  loadTown(): void {
    const townId = this.route.snapshot.paramMap.get('id');
    if (typeof townId === "string") {
      this.townService.getTownById(parseInt(townId)).subscribe((town) => {
        this.town = town;
        this.messages = town.messages;
      });
    }
  }

  createMessage(): void {
    const userId = this.authService.getUser()?.id;
    if (typeof userId === "number" && this.town && this.town.id) {
      this.messageService.createMessage(this.messageTitle, this.messageText, this.town.id).subscribe(() => {
        this.messageTitle = '';
        this.messageText = '';
        this.loadTown();
      });
    }
  }
}
