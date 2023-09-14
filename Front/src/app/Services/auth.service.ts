import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import jwtDecode from "jwt-decode";
import {Router} from "@angular/router";
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated: boolean = false;
  roles: any;
  username: any;
  accessToken!: any;

  private baseUrl = 'http://localhost:8089/auth'; // Replace with your server URL

  constructor(private http: HttpClient, private route: Router) {
  }

  registerUser(username: string, password: string): Observable<any> {
    const body = {username, password};
    return this.http.post(`${this.baseUrl}/register`, body, {responseType: 'text'});
  }

  loginUser(username: string, password: string): Observable<any> {
    const body = {username, password};
    return this.http.post(`${this.baseUrl}/login`, body);
  }
  loadprofile(data: any) {
    this.isAuthenticated = true;
    this.accessToken = data['jwt'];
    let decodedJwt: any = jwtDecode(this.accessToken);
    this.username = decodedJwt.sub;
    this.roles = decodedJwt.roles;
    console.log(this.roles);
    this.redirectBasedOnRoles();
  }
  private redirectBasedOnRoles() {
    if (this.roles.includes("ADMIN")) {
      this.route.navigateByUrl("/admin/home"); // Navigate to admin's home
    } else if (this.roles.includes("USER")) {
      this.route.navigateByUrl("/user/home"); // Navigate to user's home
    } else {
      this.route.navigateByUrl("/user"); // Navigate to a default route (for other roles)
    }
  }
  logout() {
    this.isAuthenticated=false ;
    this.accessToken= undefined;
    this.username=undefined;
    this.roles=undefined;
  }
}
