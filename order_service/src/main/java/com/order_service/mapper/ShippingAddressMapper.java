package com.order_service.mapper;

import com.order_service.dto.ShippingAddressDto;
import com.order_service.entity.ShippingAddress;
public class ShippingAddressMapper {

    public static ShippingAddress toEntity(ShippingAddressDto shippingAddressDto) {
        if (shippingAddressDto == null) return null;

        return ShippingAddress.builder()
                .street(shippingAddressDto.getStreet())
                .city(shippingAddressDto.getCity())
                .state(shippingAddressDto.getState())
                .postalCode(shippingAddressDto.getPostalCode())
                .country(shippingAddressDto.getCountry())
                .build();
    }

    public static ShippingAddressDto toDto(ShippingAddress shippingAddress) {
        if (shippingAddress == null) return null;

        return ShippingAddressDto.builder()
                .street(shippingAddress.getStreet())
                .city(shippingAddress.getCity())
                .state(shippingAddress.getState())
                .postalCode(shippingAddress.getPostalCode())
                .country(shippingAddress.getCountry())
                .build();
    }
}
