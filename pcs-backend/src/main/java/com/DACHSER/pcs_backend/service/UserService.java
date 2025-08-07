package com.DACHSER.pcs_backend.service;

import com.DACHSER.pcs_backend.dto.UserCreateDto;
import com.DACHSER.pcs_backend.dto.UserDto;

import java.util.List;

public interface UserService {
    void initRoleAndUser();

    UserDto createUser(UserCreateDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

//    String getEncodedPassword(String password);
}
