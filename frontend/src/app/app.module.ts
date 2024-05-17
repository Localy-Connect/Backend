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
import {ProfileComponent} from "./component/user/profile.component";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HelpComponent,
    SigninComponent,
    SignupComponent,
    TownComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    RouterLinkWithHref,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatSnackBarModule
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
