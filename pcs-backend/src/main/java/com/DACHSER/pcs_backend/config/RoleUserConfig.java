package com.DACHSER.pcs_backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "user")  // Use the 'user' prefix for all roles and users
public class RoleUserConfig {

    private List<RoleData> roles;  // A list of roles
    private List<UserData> users;  // A list of users

    @Getter
    @Setter
    public static class RoleData {
        private String roleName;
        private String roleDescription;
    }

    @Getter
    @Setter
    public static class UserData {
        private String userName;
        private String password;
        private String email;
        private List<String> roles;  // List of role names that the user belongs to
    }
}

