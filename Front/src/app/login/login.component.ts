import { Component } from '@angular/core';
import {AuthService} from "../Services/auth.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router,private snackBar: MatSnackBar ) {
  }

  user = {
    name: '',
    password: '',
  };

  login() {
    console.log('Login clicked:', this.user);
    this.authService.loginUser(this.user.name, this.user.password).subscribe({
      next: data => {
        console.log(data);
        this.authService.loadprofile(data);
      }
    });
  }

  add() {
    console.log('Login clicked:', this.user);
    this.authService.registerUser(this.user.name, this.user.password).subscribe({
      next: data => {
        console.log(data);
      }
    });
  }
}
