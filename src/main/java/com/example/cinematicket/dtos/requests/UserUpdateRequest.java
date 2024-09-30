package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    @JsonProperty("full_name")
    @Size(min = 1, max = 25, message = "NAME_VALID")
    private String fullName;

    private String image;

    @NotBlank(message = "GENDER_NOT_BLANK")
    private String gender;

    @Size(min = 10, message = "USERNAME_INVALID")
    private String phone;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    List<Long> roleId;
}
