package com.example.talent.controllers;

import com.example.talent.dtos.RegistrationDTO;
import com.example.talent.dtos.UserDto;
import com.example.talent.models.Users;
import com.example.talent.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/")
    public String helloUserController(){
        return "User level access";
    }
    @GetMapping("/info")
    public UserDto getUserInfo() {
        return userService.getUserByToken();
    }

    @DeleteMapping("/deleteMyAccount")
    public void removeUser() {
        userService.removeMyaccount();
    }
    @PutMapping("/update")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return userService.update(userDto);
    }

}
