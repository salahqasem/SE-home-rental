package com.se.rental.entity.dto.request;

import com.se.rental.entity.dto.response.PropertyDtoRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {
    private Long id;
    private String name;
    List<PropertyDtoRes> properties;
}
