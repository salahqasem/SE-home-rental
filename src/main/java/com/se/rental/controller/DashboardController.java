package com.se.rental.controller;

import com.se.rental.entity.Property;
import com.se.rental.entity.User;
import com.se.rental.entity.dto.PropertiesByLocationDto;
import com.se.rental.service.PropertyService;
import com.se.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin("*")
public class DashboardController {


    @Autowired
    PropertyService propertyService;

    @Autowired
    UserService userService;

    @GetMapping("/recentprop")
    public ResponseEntity<List<Property>> lastTenProperties() {
        return ResponseEntity.ok(propertyService.getLastTenProperties());
    }

    @GetMapping("/recentcustomers")
    public ResponseEntity<List<User>> lastTenCustomers() {
        return ResponseEntity.ok(userService.getLastTenCustomers());
    }


    @GetMapping("/ownerproperties")
    public ResponseEntity<List<Property>> getAllOwnerProperties() {
        return ResponseEntity.ok(propertyService.findPropertiesForLoggedInUser());
    }


    @GetMapping("/allviewsbylocation")
    public ResponseEntity<List<PropertiesByLocationDto>> getAllOwnerPropertiesViewByLocation() {
        return ResponseEntity.ok(propertyService.getAllOwnerPropertiesViewByLocation());
    }


}
