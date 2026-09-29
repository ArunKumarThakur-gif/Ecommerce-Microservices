package com.auth_service.service.userService;

import com.auth_service.entity.User;
import com.auth_service.repo.UserRepository;
import com.auth_service.web_config.CustomUserDetails;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public @Nonnull  UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("user not found")
        );

        return new CustomUserDetails(user);
    }
}
