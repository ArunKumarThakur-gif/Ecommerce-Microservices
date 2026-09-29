package com.auth_service.controller;

import com.auth_service.dto.LoginDto;
import com.auth_service.dto.RequestUserDto;
import com.auth_service.dto.ResponseUserDto;
import com.auth_service.entity.User;
import com.auth_service.enums.RoleType;
import com.auth_service.service.userService.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/signup-user")
    public ResponseEntity<?> signupUser(@RequestBody RequestUserDto user) {
        ResponseUserDto savedUser = userService.signup(user, RoleType.USER);

        if(savedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not saved");

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/signup-admin")
    public ResponseEntity<?> signupAdmin(@RequestBody RequestUserDto user) {
        ResponseUserDto savedUser = userService.signup(user, RoleType.ADMIN);

        if(savedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not saved");

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/signup-seller")
    public ResponseEntity<?> signupSeller(@RequestBody RequestUserDto user) {
        ResponseUserDto savedUser = userService.signup(user, RoleType.SELLER);

        if(savedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not saved");

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody RequestUserDto user) {
        LoginDto response = userService.signin(user);
        if(response == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not saved");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-user")
    public ResponseEntity<ResponseUserDto> getUserById(@RequestParam Long userId) {
        ResponseUserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
