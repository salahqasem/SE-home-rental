package com.se.rental.controller;

import com.se.rental.service.UserService;
import com.se.rental.entity.Offer;
import com.se.rental.entity.Picture;
import com.se.rental.entity.Property;
import com.se.rental.entity.PropertyStatus;
import com.se.rental.service.PropertyService;
import com.se.rental.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/property")
@CrossOrigin("*")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    UserService userService;

    @PostMapping(value = {"", "/"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createProperty(@ModelAttribute Property property, @RequestParam("imgs") MultipartFile[] files) throws IOException {
        List<Picture> pictures = propertyService.saveFiles(files).stream().map(s -> {
            Picture p = new Picture();
            p.setPath(s);
            return p;
        }).collect(Collectors.toList());
        property.setPictures(pictures);
        property.setUser(userService.findByEmail(Util.getLoggedInUserName()));
        propertyService.saveProperty(property);
        return ResponseEntity.ok("Property created successfully.");
    }

    @PutMapping({"/{id}/clicks", "/{id}/clicks/"})
    public ResponseEntity<String> updateClicks(@PathVariable("id") long id) {
        if (propertyService.updateClicks(id)) {
            return ResponseEntity.ok("Success.");
        } else {
            return new ResponseEntity("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable long id) {
        return propertyService.getPropertyById(id);
    }

    @GetMapping({"", "/"})
    public List<Property> getProperties(@RequestParam(required = false) Map<String, String> searchCriteria) {
        return propertyService.findAllWithFilters(searchCriteria);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public List<Property> getPropertiesForUser(@RequestParam(required = false) Map<String, String> searchCriteria) {
        return propertyService.findPropertiesForLoggedInUser();
    }

    @PutMapping({"/", ""})
    public void updateProperty(@RequestBody Property property) {
        propertyService.updateProperty(property);
    }

    @PutMapping("/{id}/{status}")
    public void updateStatus(@PathVariable long id, @PathVariable PropertyStatus status) {
        propertyService.updateStatus(id, status);
    }


    @GetMapping("/{id}/offers")
    public List<Offer> getOffers(@PathVariable Long id) {
        return propertyService.getOffers(id);
    }

    @PutMapping("/{id}/cancel-contingency")
    public void cancelContingency(@PathVariable Long id) {
        propertyService.cancelContingency(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable long id) {
        propertyService.deletePropertyById(id);
    }
}
