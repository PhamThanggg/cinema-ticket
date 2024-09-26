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
public class GenreRequest {
    @NotBlank(message = "GENRE_NOT_NULL")
    @Size(min = 2,max = 30, message = "GENRE_VALID")
    String name;

    String status;
}
