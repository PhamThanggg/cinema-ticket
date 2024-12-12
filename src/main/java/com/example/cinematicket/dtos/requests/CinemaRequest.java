package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRequest {
    @JsonProperty("name_cinema")
    @NotBlank(message = "CINEMA_NANE_BLANK")
    @Size(min = 2,max = 60, message = "CINEMA_NAME_VALID")
    String name;

    @JsonProperty("area_id")
    @NotNull(message = "AREA_BLANK")
    @Min(value = 1, message = "AREA_VALID")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long idArea;

    @NotBlank(message = "ADDRESS_NOT_BLANK")
    @Size(min = 2,max = 255, message = "ADDRESS_VALID")
    String address;


    int status;
}
