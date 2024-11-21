package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.ScheduleRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import com.example.cinematicket.services.schedule.ScheduleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/schedule")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ScheduleController {
    ScheduleService scheduleService;
    @PostMapping("")
    public ApiResponse<ScheduleResponse> create(@RequestBody @Valid ScheduleRequest request){
        return ApiResponse.<ScheduleResponse>builder()
                .message("Create successfully")
                .result(scheduleService.createSchedule(request))
                .build();
    }

    @GetMapping("/search")
    public PageResponse<List<ScheduleResponse>> getAllSchedule(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("roomId") Long roomId,
            @RequestParam("screeningDate") LocalDate screeningDate
    ){
        Page<ScheduleResponse> scheduleResponses = scheduleService.searchSchedule(roomId, screeningDate, page, limit);
        return PageResponse.<List<ScheduleResponse>>builder()
                .currentPage(scheduleResponses.getNumber())
                .totalPages(scheduleResponses.getTotalPages())
                .totalElements(scheduleResponses.getTotalElements())
                .pageSize(scheduleResponses.getSize())
                .result(scheduleResponses.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ScheduleResponse> getScheduleById(@PathVariable("id") Long id){
        return ApiResponse.<ScheduleResponse>builder()
                .result(scheduleService.findById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ScheduleResponse> updateScheduleById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ScheduleRequest request
    ){
        return ApiResponse.<ScheduleResponse>builder()
                .result(scheduleService.updateSchedule(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteScheduleById(@PathVariable("id") Long id){
        scheduleService.deleteSchedule(id);
        return ApiResponse.<String>builder()
                .result("Gender has been deleted")
                .build();
    }
}
