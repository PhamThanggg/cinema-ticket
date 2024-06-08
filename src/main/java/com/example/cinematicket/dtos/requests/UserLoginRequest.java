package com.example.cinematicket.dtos.requests;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {
    private String email;
    private String password;
}
