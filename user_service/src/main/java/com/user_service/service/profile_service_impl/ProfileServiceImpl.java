package com.user_service.service.profile_service_impl;

import com.user_service.clients.UserClient;
import com.user_service.dto.ResponseUserDto;
import com.user_service.dto.ResponseUserProfileDto;
import com.user_service.dto.UserDto;
import com.user_service.dto.UserProfileDto;
import com.user_service.entity.UserProfile;
import com.user_service.service.profile_service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserClient userClient;

    @Transactional
    @Override
    public ResponseUserProfileDto registerProfile(UserProfileDto profileDto, Long userId) {
        ResponseUserDto user = userClient.getUserById(userId).getBody();

        ///  create profile for the user
        UserProfile.builder()
                .id(user)
    }
}
