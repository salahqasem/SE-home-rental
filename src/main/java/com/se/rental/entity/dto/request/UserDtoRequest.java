package com.se.rental.entity.dto.request;

import com.se.rental.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRequest {
    private long id;
    @Email(message = "Please enter a valid email message")
    @NotEmpty(message = "Email field cannot be empty")
    private String email;
    @NotEmpty(message = "First Name cannot be empty")
    private String name;
    @NotEmpty(message = "password cannot be Empty")
    private String password;

    @NotEmpty(message = "Please provide user role")
    private UserRole role;
}
