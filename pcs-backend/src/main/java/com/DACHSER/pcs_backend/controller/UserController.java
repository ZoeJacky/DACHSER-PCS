package com.DACHSER.pcs_backend.controller;

import com.DACHSER.pcs_backend.dto.UserCreateDto;
import com.DACHSER.pcs_backend.dto.UserDto;
import com.DACHSER.pcs_backend.service.UserService;
import com.DACHSER.pcs_backend.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final JwtUtil jwtUtil;
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/createUser"})
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping({"/user"})
    public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        UserDto user = userService.getUserByToken(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping({"/forAdmin"})
//    @PreAuthorize("hasRole('admin')")
//    public String forAdmin(){
//        return "This URL is only accessible to the Admin";
//    }
//
//    @GetMapping({"/forUser"})
//    @PreAuthorize("hasRole('user')")
//    public String forUser(){
//        return "This URL is only accessible to the User";
//    }
//
//    @GetMapping({"/forPaymentAdmin"})
//    @PreAuthorize("hasRole('payment-admin')")
//    public String forPaymentAdmin(){
//        return "This URL is only accessible to the Customer payment administration";
//    }
//
//    @GetMapping({"/forCostAdmin"})
//    @PreAuthorize("hasRole('cost-admin')")
//    public String forCostAdmin(){
//        return "This URL is only accessible to the Operational costs administration";
//    }
}
