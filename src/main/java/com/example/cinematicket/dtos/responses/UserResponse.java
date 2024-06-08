package com.example.cinematicket.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String status;

}
