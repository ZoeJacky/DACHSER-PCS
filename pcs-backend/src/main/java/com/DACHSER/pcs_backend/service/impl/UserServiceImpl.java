package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.config.RoleUserConfig;
import com.DACHSER.pcs_backend.dto.UserCreateDto;
import com.DACHSER.pcs_backend.dto.UserDto;
import com.DACHSER.pcs_backend.entity.Role;
import com.DACHSER.pcs_backend.entity.User;
import com.DACHSER.pcs_backend.exception.ResourceNotFoundException;
import com.DACHSER.pcs_backend.mapper.UserMapper;
import com.DACHSER.pcs_backend.repository.RoleRepository;
import com.DACHSER.pcs_backend.repository.UserRepository;
import com.DACHSER.pcs_backend.service.JwtService;
import com.DACHSER.pcs_backend.service.UserService;
import com.DACHSER.pcs_backend.util.JwtUtil;
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
    private JwtUtil jwtUtil;
    private RoleUserConfig roleUserConfig;

    @Override
    public void initRoleAndUser() {
        for (RoleUserConfig.RoleData roleData : roleUserConfig.getRoles()) {
            Role role = new Role();
            role.setRoleName(roleData.getRoleName());
            role.setRoleDescribtion(roleData.getRoleDescription());
            roleRepository.save(role);
        }

        for (RoleUserConfig.UserData userData : roleUserConfig.getUsers()) {
            User user = new User();
            user.setUserName(userData.getUserName());
            user.setPassword(passwordEncoder.encode(userData.getPassword()));  // Encrypt password
            user.setEmail(userData.getEmail());

            Set<Role> userRoles = new HashSet<>();
            for (String roleName : userData.getRoles()) {
                Role role = roleRepository.findByRoleName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found with given name: " + roleName));
                userRoles.add(role);
            }

            user.setRole(userRoles);
            userRepository.save(user);
        }
    }

    @Override
    public UserDto createUser(UserCreateDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        if(user.getRole()!=null){
            Set<Role> roles = user.getRole();
            Set<Role> saveRoles = new HashSet<>();
            for(Role role:roles){
                Role roleDB = roleRepository.findByRoleName(role.getRoleName()).
                        orElseThrow(()->
                                new ResourceNotFoundException("Role is not found with given name:"+role.getRoleName()));
                saveRoles.add(roleDB);
            }
            user.setRole(saveRoles);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }else{
            Role role = roleRepository.findByRoleName("User").
                    orElseThrow(()->new ResourceNotFoundException("User Role is not found!"));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRole(roles);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        User savedUser =  userRepository.save(user);

//        Role role = roleRepository.findByRoleName("User").
//                orElseThrow(()->new ResourceNotFoundException("User Role is not found!"));
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        user.setRole(roles);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        User savedUser =  userRepository.save(user); //???

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public UserDto getUserByName(String name) {
        User user = userRepository.findByUserName(name).orElseThrow(
                ()->new ResourceNotFoundException("User not found with given name:"+name));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserByToken(String token) {
        String userName = jwtUtil.getUsernameFromToken(token);
        return getUserByName(userName);
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
