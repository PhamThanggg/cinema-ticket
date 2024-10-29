package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaScheduleResponse {
    Long cinemaId;
    String cinemaName;
    List<ScheduleResponse> schedules;

    public CinemaScheduleResponse(Long cinemaId, String cinemaName) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.schedules = new ArrayList<>();
    }

    public void addSchedule(ScheduleResponse schedule) {
        this.schedules.add(schedule);
    }
}
