package com.example.cinematicket.dtos.responses.cinemaSeat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaSeatBookedResponse {
    Long id;

    String name;
}