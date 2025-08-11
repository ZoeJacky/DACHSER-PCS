package com.DACHSER.pcs_backend.entity;

import com.DACHSER.pcs_backend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private UserDto userDto;
    private String jwtToken;

//    public JwtResponse(User user, String jwtToken) {
//        this.user = user;
//        this.jwtToken = jwtToken;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getJwtToken() {
//        return jwtToken;
//    }
//
//    public void setJwtToken(String jwtToken) {
//        this.jwtToken = jwtToken;
//    }
}
