package com.se.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.rental.entity.enums.OfferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double price;
    private String message;
    @CreatedDate
    private LocalDateTime offerDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @Transient
    private List<String> actions;
    public List<String> getActions() {
        List<String> actions = new ArrayList<>();
        if(!getStatus().name().equals(OfferStatus.ACCEPTED.name())){
            actions.add("Accept Offer");
            actions.add("Reject Offer");
        }
        return actions;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    private Property property;
}
