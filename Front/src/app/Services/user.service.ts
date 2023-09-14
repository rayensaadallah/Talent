import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserDto} from "../model/UserDto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8089/';

  constructor(private http: HttpClient) { }


  getUserInfo(): Observable<UserDto> {
    return this.http.get<UserDto>(`${this.baseUrl}user/info`);
  }

  removeUser(): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}user/deleteMyAccount`);
  }

  updateUser(userDto: UserDto): Observable<UserDto> {
    return this.http.put<UserDto>(`${this.baseUrl}user/update`, userDto);
  }
  getUsers(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>(`${this.baseUrl}admin/users`);
  }

  getManagers(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>(`${this.baseUrl}admin/managers`);
  }

  userToManager(username: string): Observable<void> {
    const url = `${this.baseUrl}/admin/UserToManager/${username}`;
    return this.http.put<void>(url, {});
  }
}
