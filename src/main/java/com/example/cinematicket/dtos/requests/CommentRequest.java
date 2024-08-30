package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    Long idMovie;

    @JsonProperty("comment_text")
    String commentText;

    String status;
}
