import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HelpComponent} from './component/help/help.component';
import {AuthService} from "./services/auth/auth.service";
import {AuthGuard} from "./services/auth/auth.guard";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptor} from "./services/auth/token.interceptor";
import {ContentTypeInterceptor} from "./services/shared/contenttype.inetceptor";
import {SigninComponent} from "./component/auth/signup/signin.component";
import {HomeComponent} from "./component/home/home.component";
import {SignupComponent} from "./component/auth/signin/signup.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppRoutingModule} from "./app-routing.module";
import {RouterLinkWithHref} from "@angular/router";
import {TownComponent} from "./component/town/town.component";
import {ProfileComponent} from "./component/profile/profile.component";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {CreateTownDialogComponent} from "./component/create-town-dialog/create-town-dialog.component";
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatDialogActions, MatDialogClose} from "@angular/material/dialog";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardModule, MatCardTitle} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import {MatListModule} from "@angular/material/list";
import {MatLine} from "@angular/material/core";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HelpComponent,
    SigninComponent,
    SignupComponent,
    TownComponent,
    ProfileComponent,
    CreateTownDialogComponent
  ],
  imports: [
    BrowserModule,
    RouterLinkWithHref,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatFormField,
    MatDialogActions,
    MatButton,
    MatDialogClose,
    MatCardContent,
    MatCardTitle,
    MatCard,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatListModule,
    MatLine
  ],
  providers: [
    AuthService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ContentTypeInterceptor,
      multi: true
    },
    provideAnimationsAsync()

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
