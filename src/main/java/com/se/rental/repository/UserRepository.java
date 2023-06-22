package com.se.rental.repository;

import com.se.rental.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    //@Query("SELECT u from User u where u.role = 'CUSTOMER' order by u.id desc Limit 10 ")
    @Query("select c from User c where c.role = 'CUSTOMER' order by 1 desc limit 10")
    List<User> getLastTenCustomers ();

    @Query("select c from User c where c.role ='CUSTOMER' or  c.role = 'OWNER' order by 1 desc")
    List<User> getAllUsersExceptAdmins ();


}
