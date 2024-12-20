package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.ScheduleRequest;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import com.example.cinematicket.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule toSchedule(ScheduleRequest scheduleRequest);

    ScheduleResponse toScheduleResponse(Schedule schedule);

    void updateSchedule(@MappingTarget Schedule schedule, ScheduleRequest scheduleRequest);
}
