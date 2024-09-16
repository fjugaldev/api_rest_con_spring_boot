package com.mandalorian.api.auth;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignUpRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<String> roles;

}
