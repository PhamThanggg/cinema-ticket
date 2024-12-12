package com.example.cinematicket.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    @NotBlank(message = "ROLE_NOT_BLANK")
    @Size(min = 3,max = 30, message = "ROLE_INVALID")
    String name;

    @Size(max = 1000, message = "DESCRIPTION_INVALID")
    String description;

    Set<Long> permissionIds;
}
