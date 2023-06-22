package com.se.rental.controller;

import com.se.rental.entity.User;
import com.se.rental.entity.dto.request.UserDtoRequest;
import com.se.rental.service.OtpService;
import com.se.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/authenticate/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OtpService otpService;

    @PostMapping({"", "/"})
    public ResponseEntity<String> createUser(@RequestBody UserDtoRequest user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("user created Successfully.");
        } catch (Throwable t) {
            return errorEntity(t.getMessage());
        }
    }

    private ResponseEntity<String> errorEntity(String msg) {
        return ResponseEntity.internalServerError().body(msg);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping({"/otp" , "/otp/"})
    public ResponseEntity<String> sendOTP(@RequestBody Map<String, String > body) {
        User user = userService.findByEmail(body.get("email"));
        if (user != null) {
            otpService.createOTP(body.get("email"));
            return ResponseEntity.ok("We sent an email. Please follow the instructions on it.");
        }

        return new ResponseEntity<>("Email not found.", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping({"/otp/reset", "/otp/reset/"})
    public ResponseEntity<String> resetPasswordOTP(@RequestBody Map<String, String > body) {

        if(otpService.canResetPassword(body.get("email"), body.get("otp"))) {
            User user = userService.findByEmail(body.get("email"));
            user.setPassword(body.get("password"));
            userService.saveUser(user);
            return ResponseEntity.ok("Password reset successfully.");
        }

        return new ResponseEntity<>("Email or Otp is not valid.", HttpStatus.UNAUTHORIZED);
    }
}
