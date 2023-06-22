package com.se.rental.service.impl;

import com.se.rental.repository.OfferRepository;
import com.se.rental.repository.PropertyRepository;
import com.se.rental.repository.UserRepository;
import com.se.rental.service.OfferService;
import com.se.rental.entity.Offer;
import com.se.rental.entity.PropertyStatus;
import com.se.rental.entity.User;
import com.se.rental.entity.enums.OfferStatus;
import com.se.rental.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PropertyRepository propertyRepo;
    @Override
    public Offer createOffer(Offer offer) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email=((AwesomeUserDetails)auth.getPrincipal()).getUsername();
        offer.setUser(userRepo.findByEmail(email));
        offer.setProperty(propertyRepo.findById(offer.getProperty().getId()));
        offer.setOfferDate(LocalDateTime.now());
        offer.setStatus(OfferStatus.OFFERED);
        if(!offer.getProperty().getStatus().equals(PropertyStatus.CONTINGENT)){
            offer.getProperty().setStatus(PropertyStatus.PENDING);
        }
        Offer o = offerRepo.save(offer);
        return o;
    }

    @Override
    public List<Offer> getAll() {
        return offerRepo.findAll();
    }

    @Override
    public List<Offer> getAllOffersForLoggedInUser() {

        User user = userRepo.findByEmail(Util.getLoggedInUserName());

        List<Long> propertiesIds= propertyRepo.findAllByUserId(user.getId());

        List<Offer> offers= new ArrayList<>();

        for(Long id : propertiesIds)
        {
            offers.addAll(offerRepo.findAllById(id));
        }

        return offers;
    }

    @Override
    public void updateStatus(long id, OfferStatus status) {
        Offer offer = offerRepo.findById(id);
        offer.setStatus(status);
        if(status.equals(OfferStatus.ACCEPTED)){
            offer.getProperty().setStatus(PropertyStatus.CONTINGENT);
        }
        offerRepo.save(offer);
    }


}
