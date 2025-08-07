package com.DACHSER.pcs_backend.mapper;

import com.DACHSER.pcs_backend.dto.RoleDto;
import com.DACHSER.pcs_backend.entity.Role;

public class RoleMapper {
    public static RoleDto mapToRoleDto(Role role){
        return new RoleDto(
                role.getId(),
                role.getRoleName(),
                role.getRoleDescribtion()
        );

    }

    public static Role mapToRole(RoleDto roleDto){
        return new Role(
                roleDto.getId(),
                roleDto.getRoleName(),
                roleDto.getRoleDescribtion()
        );
    }
}
