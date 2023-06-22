package com.se.rental.controller;

import com.se.rental.entity.User;
import com.se.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserAuthController {

    @Autowired
    UserService userService;
    @GetMapping()
    public User getByEmail(@RequestParam("id") String id){
        return userService.findByEmail(id);
    }
}
