package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.RoleDto;
import com.DACHSER.pcs_backend.dto.UserCreateDto;
import com.DACHSER.pcs_backend.dto.UserDto;
import com.DACHSER.pcs_backend.entity.Role;
import com.DACHSER.pcs_backend.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        List<RoleDto> roleDto = user.getRole().stream()
                .map(RoleMapper::mapToRoleDto)
                .toList();
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                roleDto
        );
    }

    public static User mapToUser(UserCreateDto userDto){
        Set<Role> role = userDto.getRoles().stream()
                .map(RoleMapper::mapToRole).collect(Collectors.toSet());
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getEmail(),
                userDto.getPassword(),
                role
        );
    }

}
