import {CarrierDto} from "./CarrierDto";

export class UserDto {
    username? :string;
    email?:string;
    phoneNumber?:string;
    country?:string;
    Objectif?:string;
    carrier?:CarrierDto;
}
