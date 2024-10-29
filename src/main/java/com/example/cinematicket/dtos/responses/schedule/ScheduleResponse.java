package com.example.cinematicket.dtos.responses.schedule;

import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
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
public class ScheduleResponse {
    Long id;

    CinemaRoomResponse cinemaRooms;

    Long scheduleId;

    Long movieIds;

    LocalDate screeningDate;

    LocalDateTime startTime;

    LocalDateTime endTime;

    double price;

    int status;
}
