package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "GENDER_NOT_BLANK")
    @Pattern(regexp = "^(?i)nam|nữ$", message = "GENDER_FORMAT")
    private String gender;

    @Size(min = 10, max = 10, message = "PHONE_VALID")
    @Pattern(
            regexp = "^(\\+84|0)(3[2-9]|5[6|8|9]|7[0-9]|8[1-9]|9[0-9])[0-9]{7}$",
            message = "PHONE_FORMAT"
    )
    private String phone;

    @JsonProperty("date_of_birth")
    @NotBlank(message = "DATE_OF_BIRTH")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "DATE_FORMAT")
    private String dateOfBirth;

}
