package com.se.rental.repository;

import com.se.rental.entity.Offer;
import com.se.rental.entity.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer,Long> {
    Offer findById(long id);

    List<Offer> findAll();

    @Query(value = "select * from Offer p where p.property_id=:id",nativeQuery = true)
    List<Offer> findAllById(long id);

    @Query("select p from Offer o join o.property p where p.contractType = 'RENT' order by o.lastModifiedDate desc limit  10")
    List<Property> lastTenRentedProperties();

}
