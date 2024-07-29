package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.Movie;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieImageResponse {
    Long id;

    String imageUrl;

}
