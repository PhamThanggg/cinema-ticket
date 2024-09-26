package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.CinemaRoom;
import com.example.cinematicket.entities.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleResponse {
    Long id;

    Long cinemaRoomIds;

    Long movieIds;

    LocalDate screeningDate;

    LocalDateTime startTime;

    LocalDateTime endTime;

    String status;
}
