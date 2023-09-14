import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Services/auth.service';
import { UserService } from 'src/app/Services/user.service';
import { CarrierDto } from 'src/app/model/CarrierDto';
import { UserDto } from 'src/app/model/UserDto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  selectedUser: UserDto = new UserDto;
  CarrierForUser!:CarrierDto;
  listmanagers: UserDto[] = [];
  listUsers: UserDto[] = [];

  constructor(private us: UserService) { }

  ngOnInit(): void {
    this.getAllUsers();
    this.getAllManagers();
  }
  
  getAllUsers() {
    this.us.getUsers().subscribe((response) => {
      this.listUsers = response;
    });
  }
  getAllManagers() {
    this.us.getManagers().subscribe((response) => {
      this.listmanagers = response;
    });
  }

  upgradeUser(username : string){
    console.log(username);
    this.us.userToManager(username).subscribe((Response)=>{
      console.log("Success"+username);
    })
  }

}
