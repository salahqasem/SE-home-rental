package com.se.rental.service;

import com.se.rental.entity.Property;
import com.se.rental.entity.PropertyStatus;
import com.se.rental.entity.dto.PropertiesByLocationDto;
import com.se.rental.entity.Offer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PropertyService {

    Property getPropertyById(long id);


    List<Property> getLastTenProperties();


    List<Property> findAll();
  
    void saveProperty(Property property);
    Property findById(long id);
    void updateProperty(Property property);

    void deletePropertyById(long id);

    List<String> saveFiles(MultipartFile[] files) throws IOException;


    //List<Property> getAllOwnerProperties(Long userId);

    List<PropertiesByLocationDto> getAllOwnerPropertiesViewByLocation();

//    List<PropertiesByLocationDto> getAllOwnerPropertiesViewByLocation(Long userId);

    List<Property> findAllWithFilters(Map<String,String> searchCriteria);
    List<Property> findPropertiesForLoggedInUser();

    void updateStatus(long id, PropertyStatus status);

    public List<Offer> getOffers(long productId);

    public void cancelContingency(long id);

    boolean updateClicks(long id);
}
