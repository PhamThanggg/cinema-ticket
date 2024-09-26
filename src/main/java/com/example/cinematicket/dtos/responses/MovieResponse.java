package com.example.cinematicket.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    Long id;

    String nameMovie;

    String producer;

    String titleMovie;

    String duration;

    String language;

    int ageLimit;

    String trailer;

    String status;

    String description;

    String starRating;

    LocalDateTime premiereDate;

    Set<Long> imageIds;

    Set<Long> genreIds;

    Set<Long> moviePeopleIds;
}
