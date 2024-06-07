package com.example.cinematicket.dto.request;

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
