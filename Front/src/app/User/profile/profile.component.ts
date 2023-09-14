import { Component, OnInit } from '@angular/core';
import { UserDto } from "../../model/UserDto";
import { UserService } from "../../Services/user.service";
import { Router } from "@angular/router";
import { CarrierDto } from 'src/app/model/CarrierDto';
import { CarrierService } from 'src/app/Services/carrier.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userDto!: UserDto;
  carrierOptions: CarrierDto[] = [];

  constructor(
    private userService: UserService,
    private carrierservice: CarrierService,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.getuser();
    this.loadCarrierOptions();
  }

  getuser(): void {
    this.userService.getUserInfo().subscribe((response: UserDto) => {
      this.userDto = response;
    });
  }

  delete(): void {
    this.userService.removeUser().subscribe(() => {
      this.route.navigateByUrl("/login");
    });
  }

  edit(u: UserDto): void {
    this.userService.updateUser(u).subscribe(
      () => {
        this.getuser();
      },
      (error) => {
        console.error("Error updating user:", error);
      }
    );
  }
  
  loadCarrierOptions(): void {
    this.carrierservice.getAllCarriers().subscribe(
      (carriers: CarrierDto[]) => {
        this.carrierOptions = carriers.filter(carrier => carrier.title !== undefined);
      },
      (error) => {
        console.error("Error fetching carrier options:", error);
      }
    );
  }
}
