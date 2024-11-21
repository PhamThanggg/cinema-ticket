package com.example.cinematicket.dtos.responses.movie;

import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.MoviePeopleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieScheduleResponse {
    Long id;

    String nameMovie;
}
