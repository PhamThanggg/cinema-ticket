package com.example.cinematicket.dtos.responses.movie;

import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.MoviePeopleResponse;
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

    String nation;

    int status;

    String description;

    String starRating;

    LocalDateTime premiereDate;

    Set<MovieImageResponse> images;

    Set<GenreResponse> genres;

    Set<MoviePeopleResponse> moviePeople;
}
