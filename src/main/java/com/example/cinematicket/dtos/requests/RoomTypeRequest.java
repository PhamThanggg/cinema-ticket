package com.example.cinematicket.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeRequest {
    @NotBlank(message = "NAME_NOT_BLANK")
    @Size(min = 1, max = 50, message = "NAME_VALID")
    String name;
    String status;
}
