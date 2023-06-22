package com.se.rental.service.impl;

import com.se.rental.repository.LocationRepository;
import com.se.rental.service.LocationService;
import com.se.rental.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;


    @Override
    public Location getLocationById(long id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void deleteLocationById(long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public void updateLocation(Location location) {
        locationRepository.save(location);
    }
}
