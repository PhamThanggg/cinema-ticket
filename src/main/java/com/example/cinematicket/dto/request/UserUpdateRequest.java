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
public class UserUpdateRequest {
    @JsonProperty("full_name")
    @Size(min = 1, max = 25, message = "name must be between 1 and 25 characters")
    private String fullName;

    private String image;

    private String gender;

    @Size(min = 10, message = "USERNAME_INVALID")
    private String phone;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

}
