package com.se.rental.service.impl;


import com.se.rental.repository.RoleRepository;
import com.se.rental.repository.UserRepository;
import com.se.rental.service.UserService;
import com.se.rental.service.exception.UserNotFoundException;
import com.se.rental.entity.Offer;
import com.se.rental.entity.Property;
import com.se.rental.entity.User;
import com.se.rental.entity.dto.request.UserDtoRequest;
import com.se.rental.entity.enums.UserRole;
import com.se.rental.entity.enums.UserStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new UserNotFoundException("invalid email or password."));
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user with id: " + id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void saveUser(UserDtoRequest u) {
        String role = u.getRole().name();
        if (role.equals(UserRole.ADMIN.name())) {
            throw new RuntimeException("You can not register as an admin.");
        }
        User user = modelMapper.map(u, User.class);
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        user.addRole(roleRepository.findByRole(role));

         userRepository.save(user);
    }

    public void saveUser(User u) {
        String role = u.getRole().name();
        if (role.equals(UserRole.ADMIN.name())) {
            throw new RuntimeException("You can not register as an admin.");
        }
        User user = modelMapper.map(u, User.class);
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        user.addRole(roleRepository.findByRole(role));

        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getLastTenCustomers() {
        return userRepository.getLastTenCustomers();
    }

    @Override
    public List<Property> getPropertiesByUserId(long id) {
        return null;
    }
    @Override
    public void updateStatus(long id, UserStatus status) {
        User user = userRepository.findById(id).get();
        user.setStatus(status);
        userRepository.save(user);

    }

    @Override
    public List<Offer> getOffers(AwesomeUserDetails u, long id) {
        User user = userRepository.findByEmail(u.getUsername());
        return user.getOffers();
    }


    @Override
    public List<User> getAllUsers(){
        return userRepository.getAllUsersExceptAdmins();
    }

    @Override
    public String ToggleUserStatus(User user){
        User userTemp = userRepository.findById(user.getId()).get();
        userTemp.setStatus(user.getStatus());
        userRepository.save(userTemp);
        return  "updated Successfully";
    }

    @Override
    public  String ResetPassword(User user){
        User userTemp = userRepository.findById(user.getId()).get();
        userTemp.setPassword("");
        userRepository.save(userTemp);
        return  "updated Successfully";
    }
}
