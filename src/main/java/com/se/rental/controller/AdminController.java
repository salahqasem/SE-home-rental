package com.se.rental.controller;

import com.se.rental.service.UserService;
import com.se.rental.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
public class AdminController {


    @Autowired
    UserService userService;


    @GetMapping
    public String getAdmin(){
        return "Welcome to Admin";
    }


    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PutMapping("/toggleuserstatus")
    public ResponseEntity<String> ToggleUserStatus(@RequestBody User user) {
        return ResponseEntity.ok(userService.ToggleUserStatus(user));
    }

    @PutMapping("/resetpassword")
    public ResponseEntity<String> ResetPassword(@RequestBody User user) {
        return ResponseEntity.ok(userService.ResetPassword(user));
    }


}
