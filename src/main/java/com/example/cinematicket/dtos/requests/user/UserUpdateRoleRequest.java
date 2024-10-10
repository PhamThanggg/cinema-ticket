package com.example.cinematicket.dtos.requests.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRoleRequest {
    @NotEmpty(message = "Role IDs must not be empty")
    private Set<Long> roleIds;
}
