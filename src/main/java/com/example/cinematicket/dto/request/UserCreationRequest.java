package com.example.cinematicket.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @JsonProperty("full_name")
    @Size(min = 1, max = 25, message = "name must be between 1 and 25 characters")
    private String fullName;

    private String gender;

    @Size(min = 10, message = "USERNAME_INVALID")
    private String phone;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email is not in correct format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String repassword;

    private String status;

    @NotNull(message = "Role id is required")
    private Long role;
}
