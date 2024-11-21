package com.example.cinematicket.services.schedule;

import com.example.cinematicket.dtos.requests.ScheduleRequest;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IScheduleService {
    ScheduleResponse createSchedule(ScheduleRequest request);

    ScheduleResponse findById(Long id);

    Page<ScheduleResponse> searchSchedule(Long roomId, LocalDate screeningDate, int page, int limit);

    ScheduleResponse updateSchedule(Long id, ScheduleRequest request);

    void deleteSchedule(Long id);
}
