package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.Genre;
import com.example.cinematicket.entities.MovieImage;
import com.example.cinematicket.entities.MoviePeople;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
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

    Set<MovieImageResponse> images;

    Set<GenreResponse> genres;

    Set<MoviePeople> moviePeople;
}
