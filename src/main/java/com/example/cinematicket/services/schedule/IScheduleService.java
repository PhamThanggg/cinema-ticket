package com.example.cinematicket.services.schedule;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.requests.ScheduleRequest;
import com.example.cinematicket.dtos.responses.ScheduleResponse;
import org.springframework.data.domain.Page;

public interface IScheduleService {
    ScheduleResponse createSchedule(ScheduleRequest request);

    ScheduleResponse findById(Long id);

    Page<ScheduleResponse> getAllSchedule(int page, int limit);

    Page<ScheduleResponse> searchSchedule(String search, int page, int limit);

    ScheduleResponse updateSchedule(Long id, ScheduleRequest request);

    void deleteSchedule(Long id);
}
