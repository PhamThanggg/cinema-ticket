package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRequest {
    @JsonProperty("name_cinema")
    String name;

    @JsonProperty("area_id")
    Long idArea;

    String address;

    String status;
}
