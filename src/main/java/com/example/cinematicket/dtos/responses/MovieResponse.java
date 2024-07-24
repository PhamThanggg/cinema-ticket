package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.Genre;
import com.example.cinematicket.entities.MovieImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    String titleMovie;

    String duration;

    String language;

    String director;

    String performers;

    int ageLimit;

    List<MovieImage> images;

    String trailer;

    String status;

    String description;

    String starRating;

    List<Genre> genres;

}
