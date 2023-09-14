import { Component } from '@angular/core';
import {AuthService} from "../../Services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header-user',
  templateUrl: './header-user.component.html',
  styleUrls: ['./header-user.component.css']
})
export class HeaderUserComponent {
  user = {
    username: this.auth.username
  };
  constructor(private auth : AuthService,private router : Router) {
  }

  logout() {
    this.auth.logout();
    this.router.navigateByUrl("/login");
  }


}
