package com.se.rental.controller.public_access;

import com.se.rental.entity.Property;
import com.se.rental.service.PropertyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/properties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
public class MYPropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/")
    public List<Property> getProperties(@RequestParam(required = false) Map<String,String> searchCriteria) {

        if (searchCriteria.size() > 0) {
            System.out.println("Entered");
            return propertyService.findAllWithFilters(searchCriteria);
        }
        return propertyService.findAll();
    }
    @GetMapping("/{id}")
    public Property findOne(@PathVariable long id){
        return propertyService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable long id){
         propertyService.deletePropertyById(id);
    }

    @PutMapping
    public void updateProperty(@RequestBody Property property){
        propertyService.updateProperty(property);
    }

}
