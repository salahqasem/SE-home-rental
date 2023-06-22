package com.se.rental.repository;

import com.se.rental.entity.Otp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends CrudRepository<Otp, Long> {

    Optional<Otp> findByEmailAndOtp(String email, String otp);

}
