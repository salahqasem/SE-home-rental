package com.se.rental.controller;

import com.se.rental.entity.Location;
import com.se.rental.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/locations")
@CrossOrigin("*")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @PostMapping({"", "/"})
    public ResponseEntity<String> createLocation(@RequestBody Location location) {
        locationRepository.save(location);
        return ResponseEntity.ok("Location created successfully.");
    }

}
