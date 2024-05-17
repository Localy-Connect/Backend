import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SigninComponent} from "./component/auth/signup/signin.component";
import {SignupComponent} from "./component/auth/signin/signup.component";
import {HomeComponent} from "./component/home/home.component";
import {TownComponent} from "./component/town/town.component";
import {HelpComponent} from "./component/help/help.component";
import {ProfileComponent} from "./component/profile/profile.component";

const routes: Routes = [
  { path: '', redirectTo: '/signin', pathMatch: 'full' },
  { path: 'signin', component: SigninComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'help', component: HelpComponent },
  { path: 'town/:id', component: TownComponent },
  { path: '**', redirectTo: '/signin' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
