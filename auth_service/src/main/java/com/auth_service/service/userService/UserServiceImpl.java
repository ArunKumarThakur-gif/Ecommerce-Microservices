package com.auth_service.service.userService;

import com.auth_service.dto.LoginDto;
import com.auth_service.dto.RequestUserDto;
import com.auth_service.dto.ResponseUserDto;
import com.auth_service.entity.Role;
import com.auth_service.entity.User;
import com.auth_service.enums.RoleType;
import com.auth_service.enums.UserStatus;
import com.auth_service.exception.RoleNotFoundException;
import com.auth_service.exception.UserAlreadyPresent;
import com.auth_service.exception.UserNotFoundException;
import com.auth_service.mapper.UserMapper;
import com.auth_service.repo.RoleRepository;
import com.auth_service.repo.UserRepository;
import com.auth_service.service.authService.AuthUtil;
import com.auth_service.web_config.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    @Transactional
    @Override
    public ResponseUserDto signup(RequestUserDto user, RoleType type) {

        /// handle existing user;
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyPresent("user already exist");
        }

        /// handle role
        Role role = roleRepository.findByRoleType(type).orElseThrow(
                () -> new RoleNotFoundException("role not exist")
        );

        System.out.println(role.getRoleType().name());

        /// hashing the plain password
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        /// creating a new user
        User newUser = User.builder()
                .email(user.getEmail())
                .password(hashedPassword)
                .roles(List.of(role))
                .status(UserStatus.ACTIVE)
                .build();

        /// save the user to db
        User savedUser = userRepository.save(newUser);

        /// return response Dto
        return UserMapper.toResponseDto(savedUser);
    }

    @Override
    public LoginDto signin(RequestUserDto dto) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        assert userDetails != null;
        User user = userDetails.user();
        String token = authUtil.generateAccessToken(user);

        return LoginDto.builder()
                .id(user.getId())
                .token(token)
                .build();
    }

    @Override
    public ResponseUserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );

        return UserMapper.toResponseDto(user);
    }
}
