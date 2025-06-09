package com.jwt.example.JwtExample.controllers;

import com.jwt.example.JwtExample.models.JwtRequest;
import com.jwt.example.JwtExample.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User> getUsers(){
        System.out.println("User Printing");
        return userService.getUser();
    }
    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal){
    return principal.getName();
    }
}
