package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.ScheduleRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import com.example.cinematicket.services.schedule.ScheduleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public ApiResponse<List<ScheduleResponse>> getAllCinemaSeat(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<ScheduleResponse> scheduleResponses = scheduleService
                .getAllSchedule(page, limit)
                .getContent();
        int totalCinema = scheduleResponses.size();
        return ApiResponse.<List<ScheduleResponse>>builder()
                .message("Total genre: " + totalCinema)
                .result(scheduleResponses)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ScheduleResponse>> getAllCinema(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_id") Long cinemaId
    ){
        return null;
    }

    @GetMapping("/{id}")
    public ApiResponse<ScheduleResponse> getCinemaById(@PathVariable("id") Long id){
        return ApiResponse.<ScheduleResponse>builder()
                .result(scheduleService.findById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ScheduleResponse> updateCinemaById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ScheduleRequest request
    ){
        return ApiResponse.<ScheduleResponse>builder()
                .result(scheduleService.updateSchedule(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCinemaById(@PathVariable("id") Long id){
        scheduleService.deleteSchedule(id);
        return ApiResponse.<String>builder()
                .result("Gender has been deleted")
                .build();
    }
}
