package com.user_service.service.profile_service;

import com.user_service.dto.ResponseUserProfileDto;
import com.user_service.dto.UserProfileDto;

public interface ProfileService {
    ResponseUserProfileDto registerProfile(UserProfileDto profileDto, Long userId);
}
