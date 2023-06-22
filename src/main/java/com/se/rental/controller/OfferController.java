package com.se.rental.controller;

import com.se.rental.entity.Offer;
import com.se.rental.entity.Property;
import com.se.rental.entity.dto.request.OfferStatusDto;
import com.se.rental.entity.enums.OfferStatus;
import com.se.rental.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/offers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping("/")
    public List<Offer> getOffers(){
        return offerService.getAll();
    }

    @GetMapping("/user")
    public List<Offer> getAllOffersForLoggedInUser(){
        return offerService.getAllOffersForLoggedInUser();
    }

    @PutMapping("/{id}")
    public void updateOfferStatus(@PathVariable long id, @RequestBody OfferStatusDto status) {
        offerService.updateStatus(id, status.getStatus());
    }
    @PostMapping({"/", ""})
    public Offer save(@Valid @RequestBody Map<String, String> params){
        Offer offer = new Offer();
        offer.setPrice(Integer.valueOf(params.get("price")));
        offer.setMessage(params.get("message"));
        Property property = new Property();
        property.setId(Long.valueOf(params.get("property_id")));
        offer.setProperty(property);
        offer.setStatus(OfferStatus.valueOf(params.get("status")));
        return offerService.createOffer(offer);
    }
}
