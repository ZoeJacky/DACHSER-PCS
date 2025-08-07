package com.DACHSER.pcs_backend.controller;

import com.DACHSER.pcs_backend.dto.UserCreateDto;
import com.DACHSER.pcs_backend.dto.UserDto;
import com.DACHSER.pcs_backend.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }

    @PostMapping({"/createUser"})
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
