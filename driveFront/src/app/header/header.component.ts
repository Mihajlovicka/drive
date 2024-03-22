import {Component, ElementRef, AfterViewInit, Renderer2} from '@angular/core';
import { Location } from '@angular/common';
import {Router} from "@angular/router";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(private router:Router,
              public service:UserService) {}


  home(){
    this.router.navigate(['']);
  }

  login() {
    this.router.navigate(['/login']);
  }

  register() {
    this.router.navigate(['/register']);
  }

  logout()
  {
    this.service.logOut()
    this.home()
  }

  allRides() {
    this.router.navigate(['/rides']);
  }
}
