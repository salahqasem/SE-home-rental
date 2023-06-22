package com.se.rental.service;

import com.se.rental.entity.Location;

public interface LocationService {
    Location getLocationById(long id);

    void saveLocation(Location location);

    void deleteLocationById(long id);

    void updateLocation(Location location);
}
