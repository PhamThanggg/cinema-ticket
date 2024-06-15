package com.example.cinematicket.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    @JsonProperty("id_movie")
    Long id;

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

    @JsonProperty("image_movie")
    String image;

    String trailer;

    String status;

    String description;

}
