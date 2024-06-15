package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.services.CinemaService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/cinema")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CinemaController {
    CinemaService cinemaService;

    @PostMapping("")
    public ApiResponse<CinemaResponse> create(@RequestBody @Valid CinemaRequest request){
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

    @GetMapping("/search")
    public ApiResponse<List<CinemaResponse>> getAllCinema(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<CinemaResponse> cinemaResponses = cinemaService.searchCinema(name, page, limit).getContent();
        Long totalCinema = cinemaService.totalCinema();
        return ApiResponse.<List<CinemaResponse>>builder()
                .message("Total cinema: " + totalCinema)
                .result(cinemaResponses)
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
            @RequestBody CinemaRequest request
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
