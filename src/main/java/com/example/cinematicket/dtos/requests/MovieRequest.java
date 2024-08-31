package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
    @JsonProperty("name_movie")
    String nameMovie;

    String producer;

    @JsonProperty("title_movie")
    String titleMovie;

    String duration;

    String language;

    @JsonProperty("age_limit")
    int ageLimit;

    String status;

    String description;

    @JsonProperty("premiere_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime premiereDate;

    @Builder.Default
    String starRating = "0";

    Set<Long> genres;

    @JsonProperty("movie_people")
    Set<Long> moviePeople;
}
