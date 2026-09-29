package com.user_service.clients;

import com.user_service.dto.ResponseUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-SERVICE")
public interface UserClient {

    @GetMapping("/get-user")
    public ResponseEntity<ResponseUserDto> getUserById(@RequestParam Long userId);
}
