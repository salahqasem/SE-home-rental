package com.se.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class FavoriteList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "Favorite_PROPERTY", joinColumns = {
            @JoinColumn(name = "FavoriteList_id")}, inverseJoinColumns = {@JoinColumn(name = "property_id")})
    @Fetch(FetchMode.SUBSELECT)
    private List<Property> favoriteProperties = new ArrayList<>();
}
