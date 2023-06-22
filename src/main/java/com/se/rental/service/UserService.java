package com.se.rental.service;

import com.se.rental.service.impl.AwesomeUserDetails;
import com.se.rental.entity.Offer;
import com.se.rental.entity.Property;
import com.se.rental.entity.User;

import com.se.rental.entity.dto.request.UserDtoRequest;
import com.se.rental.entity.enums.UserStatus;

import java.util.List;

public interface UserService {

    User getUser(String email, String password);

    User getUserById(long id);
    User findByEmail(String email);
    void saveUser(UserDtoRequest user);

    void saveUser(User user);

    void deleteUserById(long id);

    void updateUser(User user);

    List<Property> getPropertiesByUserId(long id);
     List<User> getLastTenCustomers();


    void updateStatus(long id, UserStatus status);
    public List<Offer> getOffers(AwesomeUserDetails user, long id);

    List<User> getAllUsers();

    String ToggleUserStatus(User user);

    String ResetPassword(User user);
}