package com.user_service.controller;

import com.user_service.dto.UserProfileDto;
import com.user_service.service.jwt_service.JwtService;
import com.user_service.service.profile_service_impl.ProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-profile")
public class ProfileController {

    private final ProfileServiceImpl profileService;
    private final JwtService jwtService;

    @PostMapping("/register-profile")
    public ResponseEntity<?> registerProfile(@RequestBody UserProfileDto profile, @RequestHeader("authorization") String header) {
        String token = header.substring(7);
        Long userId = jwtService.extractUserId(token);
        profileService.registerProfile(profile, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }
}
