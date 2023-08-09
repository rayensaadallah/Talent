package com.example.talent.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDTO {
    private String username;
    private String password;

    public RegistrationDTO(){
        super();
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }
}
