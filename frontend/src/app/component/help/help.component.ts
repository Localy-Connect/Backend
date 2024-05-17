import { Component } from '@angular/core';

@Component({
  selector: 'app-help',
  templateUrl: './help.component.html',
  styleUrls: ['./help.component.css']
})
export class HelpComponent {
  sectionStates: { [key: string]: boolean } = {
    isIntroductionExpanded: false,
    isInstructionsExpanded: false,
    isTroubleshootingExpanded: false,
    isNavigationExpanded: false,
    isFunctionalityExpanded: false,
  };

  email: string = "lsc132428@stud.gibb.ch";
  supportAvailability: string = "29.05.2024";

  toggleSection(section: string) {
    for (let key in this.sectionStates) {
      if (this.sectionStates.hasOwnProperty(key)) {
        this.sectionStates[key] = false;
      }
    }
    this.sectionStates[section] = !this.sectionStates[section];
  }
}
