package com.example.cinematicket.dtos.responses;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {
    UserResponse user;

    String commentText;

    LocalDateTime createdAt;

}
