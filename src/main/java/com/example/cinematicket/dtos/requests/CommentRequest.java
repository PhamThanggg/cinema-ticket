package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {

    @JsonProperty("id_movie")
    @NotNull(message = "MOVIE_NOT_NULL")
    @Min(value = 1, message = "MOVIE_VALID")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long idMovie;

    @JsonProperty("comment_text")
    @NotBlank(message = "COMMENT_NOT_NULL")
    @Size(min = 2,max = 200, message = "COMMENT_INVALID")
    String commentText;

    int status;
}
