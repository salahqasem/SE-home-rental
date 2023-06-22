package com.se.rental.service;

import com.se.rental.entity.Offer;
import com.se.rental.entity.enums.OfferStatus;

import java.util.List;

public interface OfferService {

    public Offer createOffer(Offer offer);
    public List<Offer> getAll();

    public List<Offer> getAllOffersForLoggedInUser();

    void updateStatus(long id, OfferStatus status);

}
