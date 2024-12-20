package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class  MovieRequest {
    @JsonProperty("name_movie")
    @NotBlank(message = "MOVIE_NAME_NOT_BLANK")
    @Size(min = 1, max = 60, message = "MOVIE_NAME_VALID")
    String nameMovie;

    @NotBlank(message = "MOVIE_PRODUCER_NOT_BLANK")
    @Size(min = 1, max = 100, message = "MOVIE_PRODUCER_VALID")
    String producer;

    @JsonProperty("title_movie")
//    @NotBlank(message = "TITLE_NOT_BLANK")
    @Size(min = 1, max = 100, message = "")
    @Size(max = 100, message = "TITLE_VALID")
    String titleMovie;

    @NotBlank(message = "DURATION_NOT_BLANK")
    @Size(min = 1, max = 3, message = "")
    String duration;

    @NotBlank(message = "LANGUAGE_NOT_BLANK")
    @Size(min = 1, max = 45, message = "LANGUAGE_VALID")
    String language;

    @JsonProperty("age_limit")
    @NotNull(message = "AGE_NOT_NULL")
    @Min(value = 0, message = "AGE_VALID_MIN")
    @Max(value = 18, message = "AGE_VALID_MAX")
    int ageLimit;

    @Size(max = 255, message = "Trailer k được vượt quá 255 ký tự")
    String trailer;

    @Size(max = 45, message = "Nation k được vượt quá 45 ký tự")
    String nation;

    int status;

    @Size(max = 1000, message = "DESCRIPTION_INVALID")
    String description;

    @JsonProperty("premiere_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Future(message = "Premiere date must be in the future")
    LocalDateTime premiereDate;

    @Builder.Default
    @Pattern(regexp = "^[0-5]$", message = "START_VALID")
    String starRating = "0";

    @NotEmpty(message = "GENRE_NOT_NULL")
    Set<Long> genres;

    @JsonProperty("movie_people")
    Set<Long> moviePeople;
}
