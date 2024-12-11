package com.example.cinematicket.dtos.requests.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChangePasswordRequest {
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
    private String newPassword;

    @NotBlank(message = "REPASSWORD_NOT_BLANK")
    @Size(min = 8, max = 64, message = "PASSWORD_INVALID")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,64}$",
            message = "PASSWORD_FORMAT"
    )
    private String reNewPassword;
}
