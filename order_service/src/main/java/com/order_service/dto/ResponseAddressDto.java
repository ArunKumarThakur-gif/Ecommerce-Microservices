package com.order_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseAddressDto {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
