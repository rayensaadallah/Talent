package com.example.talent.controllers;

import com.example.talent.dtos.UserDto;
import com.example.talent.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String helloAdmineController(){
        return "Admin level access";
    }

    @GetMapping("/users")
    public List<UserDto> getusers() {
        List<UserDto> list = userService.getAllUsersByRoles("USER");
        return list;
    }
    @GetMapping("/managers")
    public List<UserDto> getmanagers() {
        List<UserDto> list = userService.getAllUsersByRoles("MANAGER");
        return list;
    }
    @PutMapping("/UserToManager/{username}")
    public void UserToManager(@PathVariable("username") String username ) throws RoleNotFoundException {
         userService.userToManager(username);
    }

}
