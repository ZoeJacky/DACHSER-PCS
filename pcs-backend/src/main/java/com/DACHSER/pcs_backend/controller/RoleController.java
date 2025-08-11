package com.DACHSER.pcs_backend.controller;

import com.DACHSER.pcs_backend.dto.RoleDto;
import com.DACHSER.pcs_backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class RoleController {
    private RoleService roleService;

    @PostMapping({"/createRole"})
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto){
        RoleDto savedRole = roleService.createRole(roleDto);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }
}
