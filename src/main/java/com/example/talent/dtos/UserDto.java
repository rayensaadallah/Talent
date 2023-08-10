package com.example.talent.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    // private String password;
    private String email;
    private String phoneNumber;
    private String country;
    private String Objectif;
    private CarrierDto carrier;
}
