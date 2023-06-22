package com.se.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.rental.entity.enums.UserRole;
import com.se.rental.entity.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rental_user")
@ToString(exclude = "property")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(message = "Email should be valid.")
    @NotBlank(message = "Email field is required.")
    private String email;


    @NotBlank(message = "Name is required.")
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Offer> offers;
    //@NotBlank(message = "Password is required.")
    private String password;

  
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

  
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Property> properties = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<FavoriteList> favoriteLists = new ArrayList<>();

    public void addRole(Role role){
        this.roles.add(role);
    }
}
