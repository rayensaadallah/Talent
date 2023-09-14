import {Data} from "@angular/router";
import {UserDto} from "./UserDto";

export class CarrierDto{
   title?:string;
   level?:string ;
   needed_days?: any;
   date_Start?: Date;
   type?: string;
    users?:UserDto[];
}
