package com.DACHSER.pcs_backend.service.impl;

import com.DACHSER.pcs_backend.dto.RoleDto;
import com.DACHSER.pcs_backend.entity.Role;
import com.DACHSER.pcs_backend.mapper.RoleMapper;
import com.DACHSER.pcs_backend.repository.RoleRepository;
import com.DACHSER.pcs_backend.service.RoleService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = RoleMapper.mapToRole(roleDto);
//        Role roleDB = roleRepository.findByRoleName(role.getRoleName()).
//                orElseThrow(()->new EntityNotFoundException("Role not found with given name:"+role.getRoleName()));
        if(roleRepository.findByRoleName(role.getRoleName()).isPresent()){
            throw new EntityExistsException("Role already exists in Database with given name:"+role.getRoleName());
        }else{
            Role savedRole = roleRepository.save(role);
            return RoleMapper.mapToRoleDto(savedRole);
        }
//        Role savedRole = roleRepository.save(role);
//        return RoleMapper.mapToRoleDto(savedRole);
    }
}
