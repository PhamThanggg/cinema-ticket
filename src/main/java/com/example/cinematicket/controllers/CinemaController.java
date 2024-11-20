package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.dtos.responses.CinemaScheduleResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.services.cinema.CinemaService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/cinema")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CinemaController {
    CinemaService cinemaService;

    @PostMapping("")
    public ApiResponse<CinemaResponse> create(
            @RequestBody @Valid CinemaRequest request){
        return ApiResponse.<CinemaResponse>builder()
                .message("Create successfully")
                .result(cinemaService.createCinema(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<CinemaResponse>> getAllCinema(){
            return ApiResponse.<List<CinemaResponse>>builder()
                .result(cinemaService.getAllCinema())
                .build();
    }

    @GetMapping("/schedule")
    public ApiResponse<List<CinemaScheduleResponse>> getCinemaSchedule(
            @RequestParam(required = false) Long cinemaId,
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) Long areaId,
            @RequestParam(required = false) LocalDate screeningDate
    ){
        return ApiResponse.<List<CinemaScheduleResponse>>builder()
                .result(cinemaService.getCinemaSchedule(cinemaId, movieId, areaId, screeningDate))
                .build();
    }

    @GetMapping("/area/{areaId}")
    public ApiResponse<List<CinemaResponse>> getAllCinemaArea(
        @PathVariable("areaId") Long areaId
    ){
        return ApiResponse.<List<CinemaResponse>>builder()
                .result(cinemaService.getAllCinema(areaId))
                .build();
    }

    @GetMapping("/search")
    public PageResponse<List<CinemaResponse>> getAllCinema(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        Page<CinemaResponse> cinemaResponses = cinemaService.searchCinema(name, page, limit);
        Long totalCinema = cinemaService.totalCinema();
        return PageResponse.<List<CinemaResponse>>builder()
                .message("Total cinema: " + totalCinema)
                .currentPage(cinemaResponses.getNumber())
                .totalPages(cinemaResponses.getTotalPages())
                .totalElements(cinemaResponses.getTotalElements())
                .pageSize(cinemaResponses.getSize())
                .result(cinemaResponses.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CinemaResponse> getCinemaById(@PathVariable("id") Long id){
        return ApiResponse.<CinemaResponse>builder()
                .result(cinemaService.getCinemaById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CinemaResponse> updateCinemaById(
            @PathVariable("id") Long id,
            @RequestBody @Valid CinemaRequest request
    ){
        return ApiResponse.<CinemaResponse>builder()
                .result(cinemaService.updateCinema(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCinemaById(@PathVariable("id") Long id){
        cinemaService.deleteCinema(id);
        return ApiResponse.<String>builder()
                .result("Cinema has been deleted")
                .build();
    }

}
