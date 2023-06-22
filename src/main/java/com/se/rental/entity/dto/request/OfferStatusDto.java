package com.se.rental.entity.dto.request;


import com.se.rental.entity.enums.OfferStatus;
import lombok.Data;

@Data
public class OfferStatusDto {
    OfferStatus status;
}
