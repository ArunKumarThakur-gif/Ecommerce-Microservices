package com.order_service.clients;

import com.order_service.dto.ResponseAddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("PROFILE-CLIENT")
public interface UserProfileClient {

    @GetMapping("/getDefualtAddress")
    ResponseEntity<ResponseAddressDto> getDefaultAddress(Long addressId);
}
