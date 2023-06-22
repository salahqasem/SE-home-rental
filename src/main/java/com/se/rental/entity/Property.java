package com.se.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
@ToString(exclude = "user")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String location;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private double price;

    private double area;

    private int rooms;

    private int clickCount;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status = PropertyStatus.ACTIVE;

    @OneToMany(mappedBy = "property")
    private List<Offer> offers;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Picture> pictures;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;
}
