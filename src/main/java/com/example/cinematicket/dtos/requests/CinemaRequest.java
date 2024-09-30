package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    Long idArea;

    @NotBlank(message = "ADDRESS_NOT_BLANK")
    @Size(min = 2,max = 30, message = "ADDRESS_VALID")
    String address;

    int status;
}
