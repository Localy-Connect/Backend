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
  };

  email: string = "lsc132428@stud.gibb.ch";
  supportAvailability: string = "29.05.2024";

  languages = ['English', 'Deutsch'];
  selectedLanguage = 'English';

  toggleSection(section: string) {
    for (const key in this.sectionStates) {
      if (Object.prototype.hasOwnProperty.call(this.sectionStates, key)) {
        this.sectionStates[key] = false;
      }
    }
    this.sectionStates[section] =!this.sectionStates[section];
  }

  changeLanguage(language: string) {
    this.selectedLanguage = language;
  }

  getContent() {
    if (this.selectedLanguage === 'Deutsch') {
      return {
        title: 'Benutzerhandbuch',
        languageLabel: 'Sprache:',
        introduction: 'Willkommen zum Benutzerhandbuch für unser System. Dieses Handbuch soll Ihnen helfen, das System effektiv zu nutzen und eventuelle Probleme zu beheben.',
        instructions: {
          title: 'Bedienungsanleitung',
          login: {
            title: 'Anmelden',
            steps: [
              'Öffnen Sie die Webseite.',
              'Geben Sie Ihren Benutzernamen und Ihr Passwort ein.',
              'Klicken Sie auf "Sign In".',
              'Falls Sie noch keinen Account haben, klicken Sie auf "Sign Up" im Sign-In-Bereich.'
            ]
          },
          navigation: {
            title: 'Navigation',
            steps: [
              'Um Ihre Account-Daten anzusehen, klicken Sie auf "Profile".',
              'Um das Benutzerhandbuch erneut anzusehen, klicken Sie im Footer auf "Help".',
              'Um zur Hauptfunktion zurückzukehren, klicken Sie im Header auf "Localy".',
              'Benutzer ohne zugeordnete Stadt werden zu einer Übersicht aller Städte weitergeleitet.',
              'Benutzer mit einer zugeordneten Stadt werden zu ihrer Stadt weitergeleitet, wo alle Nachrichten angezeigt werden.'
            ]
          },
          functionalities: {
            title: 'Funktionen',
            items: [
              'Sign In: Anmeldung mit Ihrem Account.',
              'Sign Out: Abmeldung von Ihrem Account.',
              'Join a Town: Einer Stadt beitreten.',
              'Create a Town: Eine Stadt erstellen.',
              'Leave a Town: Eine Stadt verlassen.',
              'Write a Message: Eine Nachricht in einer Stadt schreiben.',
              'View Town Messages: Nachrichten in der Stadt ansehen.',
              'View Profile: Ihre persönlichen Daten ansehen.',
              'View All Towns: Alle verfügbaren Städte ansehen.',
              'Change Password: Ihr Passwort ändern.',
              'Edit Profile: Ihr Profil bearbeiten.'
            ]
          }
        },
        troubleshooting: {
          title: 'Fehlerbehebung',
          steps: [
            'Wenn ein Fehler auftritt, laden Sie die Webseite neu.',
            'Falls der Fehler weiterhin besteht, melden Sie sich erneut an und versuchen Sie es nochmals.',
            'Wenn das Problem weiterhin besteht, senden Sie eine E-Mail mit einem Screenshot des Fehlers und einer Beschreibung, wie es dazu kam, an ' + this.email + ' (Support Verfügbarkeit bis ' + this.supportAvailability + ').'
          ]
        }
      };
    } else {
      return {
        title: 'User Manual',
        languageLabel: 'Language:',
        introduction: 'Welcome to the user manual for our system. This manual is designed to help you effectively use the system and resolve any issues.',
        instructions: {
          title: 'Instructions',
          login: {
            title: 'Login',
            steps: [
              'Open the website.',
              'Enter your username and password.',
              'Click on "Sign In".',
              'If you do not have an account, click on "Sign Up" in the Sign-In section.'
            ]
          },
          navigation: {
            title: 'Navigation',
            steps: [
              'To view your account data, click on "Profile".',
              'To view the user manual again, click on "Help" in the footer.',
              'To return to the main function, click on "Localy" in the header.',
              'Users without an assigned town will be redirected to an overview of all towns.',
              'Users with an assigned town will be redirected to their town where all messages are displayed.'
            ]
          },
          functionalities: {
            title: 'Functionalities',
            items: [
              'Sign In: Log in with your account.',
              'Sign Out: Log out of your account.',
              'Join a Town: Join a town.',
              'Create a Town: Create a town.',
              'Leave a Town: Leave a town.',
              'Write a Message: Write a message in a town.',
              'View Town Messages: View messages in the town.',
              'View Profile: View your personal data.',
              'View All Towns: View all available towns.',
              'Change Password: Change your password.',
              'Edit Profile: Edit your profile.'
            ]
          }
        },
        troubleshooting: {
          title: 'Troubleshooting',
          steps: [
            'If an error occurs, reload the webpage.',
            'If the error persists, log in again and try again.',
            'If the problem continues, send an email with a screenshot of the error and a description of how it occurred to ' + this.email + ' (Support availability until ' + this.supportAvailability + ').'
          ]
        }
      };
    }
  }
}
