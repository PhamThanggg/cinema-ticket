package com.example.cinematicket.dtos.responses.movie;

import com.example.cinematicket.dtos.responses.MovieImageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieBookedResponse {
    String nameMovie;

    int ageLimit;

    String description;

    Set<MovieImageResponse> images;
}
