import { Component } from '@angular/core';
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string ='';
  password: string ='';

  constructor(private service:UserService,
              private router:Router) {
  }

  onSubmit() {
    this.service.login(this.email, this.password).subscribe({
      next: () => this.router.navigate(['']),
      error: () => alert("Password or email are incorrect.")
    })
  }
}
