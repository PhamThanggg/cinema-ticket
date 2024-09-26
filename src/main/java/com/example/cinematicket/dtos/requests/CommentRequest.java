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
public class CommentRequest {

    @JsonProperty("id_movie")
    @NotNull(message = "MOVIE_NOT_NULL")
    @Min(value = 1, message = "MOVIE_VALID")
    Long idMovie;

    @JsonProperty("comment_text")
    @NotBlank(message = "COMMENT_NOT_NULL")
    @Size(min = 2,max = 200, message = "COMMENT_INVALID")
    String commentText;

    String status;
}
