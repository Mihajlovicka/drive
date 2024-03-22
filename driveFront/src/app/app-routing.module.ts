import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {authGuard} from "./_auth/auth.guard";
import {ForbiddenComponent} from "./forbidden/forbidden.component";
import {NearestVehiclesComponent} from "./nearest-vehicles/nearest-vehicles.component";
import {RidesComponent} from "./rides/rides.component";

const routes: Routes = [
  {path:'', component: HomeComponent, canActivate: [authGuard]},
  {path:'login', component: LoginComponent},
  {path:'register', component: RegisterComponent},
  {path:'forbidden', component: ForbiddenComponent},
  {path:'nearest', component: NearestVehiclesComponent, canActivate: [authGuard]},
  {path:'rides', component: RidesComponent, canActivate: [authGuard]},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
