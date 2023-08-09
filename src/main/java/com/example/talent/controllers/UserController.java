package com.example.talent.controllers;

import com.example.talent.models.Users;
import com.example.talent.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Optional<Users> getUserInfo(HttpServletRequest request) {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();
        Jwt jwt = jwtAuthenticationToken.getToken();
        String username = jwt.getClaimAsString("sub");
        return  userService.getInfo(username);
    }
    
}
