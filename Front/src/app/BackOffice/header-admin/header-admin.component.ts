import { Component } from '@angular/core';
import {AuthService} from "../../Services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header-admin',
  templateUrl: './header-admin.component.html',
  styleUrls: ['./header-admin.component.css']
})
export class HeaderAdminComponent {
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
