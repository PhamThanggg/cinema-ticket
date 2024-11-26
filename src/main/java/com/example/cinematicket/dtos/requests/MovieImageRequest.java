package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieImageRequest {
    @JsonProperty("image_url")
    @NotBlank(message = "IMAGE_NOT_NULL")
    @Size(min = 2,max = 500, message = "IMAGE_INVALID")
    String image;

    @JsonProperty("id_movie")
    @NotNull(message = "MOVIE_NOT_NULL")
    @Min(value = 1, message = "MOVIE_VALID")
    Long idMovie;
}
