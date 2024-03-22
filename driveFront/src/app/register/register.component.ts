import { Component } from '@angular/core';
import {UserService} from "../service/user.service";
import {User} from "../model/user";
import {Router} from "@angular/router";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  user: User = new User();
  constructor(private service:UserService,
              private router:Router) {
  }

  onSubmit() {
    this.service.register(this.user).subscribe((res => {
      alert("Registration successful")
      this.router.navigate(['login']);
    }))
  }

}
