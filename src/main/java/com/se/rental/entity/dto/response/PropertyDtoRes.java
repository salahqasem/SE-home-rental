package com.se.rental.entity.dto.response;


import com.se.rental.entity.Property;
import com.se.rental.entity.PropertyStatus;
import com.se.rental.entity.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDtoRes {
    private long id;
    private double price;
    private String title;
    private double area;
    private PropertyType propertyType;
    private List<String> actions;
    private Property details;
    private boolean offered;
    private PropertyStatus status;
    private List<ImageDTO> images;
}
