package com.se.rental.entity.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private long id;
    @NotEmpty(message = "Please provide street.")
    private String street;
    @NotEmpty(message = "Please provide a city.")
    private String city;
    @NotEmpty(message = "Please provide a state.")
    private String state;
    @NotEmpty(message = "Please provide a zip code.")
    private String zipCode;
    @NotEmpty(message = "Please provide a country")
    private String country;
}
