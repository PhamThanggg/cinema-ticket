package com.example.cinematicket.dtos.responses.schedule;

import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import com.example.cinematicket.dtos.responses.movie.MovieBookedResponse;
import com.example.cinematicket.dtos.responses.movie.MovieResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleBookedResponse {
    CinemaRoomResponse cinemaRooms;

    MovieBookedResponse movies;

    LocalDate screeningDate;

    LocalDateTime startTime;

    LocalDateTime endTime;
}
