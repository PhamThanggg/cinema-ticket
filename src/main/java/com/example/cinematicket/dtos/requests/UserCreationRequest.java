package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @JsonProperty("full_name")
    @NotBlank(message = "NAME_NOT_BLANK")
    @Size(min = 1, max = 30, message = "NAME_VALID")
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

    @NotBlank(message = "DATE_OF_BIRTH")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "DATE_FORMAT")
    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @NotBlank(message = "EMAIL_NOT_BLANK")
    @Email(message = "EMAIL_FORMAT")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "EMAIL_FORMAT")
    @Size(min = 10, max = 64, message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "PASSWORD_NOT_BLANK")
    @Size(min = 8, max = 64, message = "PASSWORD_INVALID")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,64}$",
            message = "PASSWORD_FORMAT"
    )
    private String password;

    @NotBlank(message = "REPASSWORD_NOT_BLANK")
    @Size(min = 8, max = 64, message = "PASSWORD_INVALID")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,64}$",
            message = "PASSWORD_FORMAT"
    )
    private String repassword;

    private int status;

}
