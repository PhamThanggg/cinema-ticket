package com.example.cinematicket.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String image;
    private String gender;
    private String phone;
    private String dateOfBirth;
    private String email;
    private int status;
    private int star;
    private Set<RoleResponse> roles;
}
