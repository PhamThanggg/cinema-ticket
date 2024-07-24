package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.nio.file.Files;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
    @JsonProperty("name_movie")
    String nameMovie;

    @JsonProperty("title_movie")
    String titleMovie;

    String duration;

    String language;

    String director;

    String performers;

    @JsonProperty("age_limit")
    int ageLimit;

    String status;

    String description;

    @Builder.Default
    String starRating = "0";

    Set<Long> genres;

}
