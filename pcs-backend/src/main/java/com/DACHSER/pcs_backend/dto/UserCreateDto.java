package com.DACHSER.pcs_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private List<RoleDto> roles;
}
