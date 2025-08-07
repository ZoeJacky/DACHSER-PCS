package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.dto.UserCreateDto;
import com.DACHSER.pcs_backend.dto.UserDto;
import com.DACHSER.pcs_backend.entity.Role;
import com.DACHSER.pcs_backend.entity.User;
import com.DACHSER.pcs_backend.mapper.UserMapper;
import com.DACHSER.pcs_backend.repository.RoleRepository;
import com.DACHSER.pcs_backend.repository.UserRepository;
import com.DACHSER.pcs_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void initRoleAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescribtion("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescribtion("Default role for newly created record");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setPassword(passwordEncoder.encode("admin@pass"));
        adminUser.setEmail("admin@pcs.com");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);

    }

    @Override
    public UserDto createUser(UserCreateDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser =  userRepository.save(user); //???

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

//    @Override
//    public String getEncodedPassword(String password) {
//        return "";
//    }
//    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserDto createUser(UserCreateDto userDto) {
//        User user = UserMapper.mapToUser(userDto);
//        User savedUser =  userRepository.save(user); //???
//
//        return UserMapper.mapToUserDto(savedUser);
//    }

//    @Override
//    public void initRoleAndUser(){
//        Role adminRole = new Role();
//        adminRole.setRoleName("Admin");
//        adminRole.setRoleDescribtion("Admin role");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setRoleName("User");
//        userRole.setRoleDescribtion("Default role for newly created record");
//        roleRepository.save(userRole);
//
//        User adminUser = new User();
//        adminUser.setUserName("admin123");
//        adminUser.setPassword(getEncodedPassword("admin123"));
//        adminUser.setEmail("admin@pcs.com");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRole);
//        adminUser.setRole(adminRoles);
//        userRepository.save(adminUser);
//    }

//    @Override
//    public String getEncodedPassword(String password){
//        return passwordEncoder.encode(password);
//    }
}
