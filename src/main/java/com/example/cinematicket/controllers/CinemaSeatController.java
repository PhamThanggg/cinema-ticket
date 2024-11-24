package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.seat.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatResponse;
import com.example.cinematicket.services.cinemaSeat.CinemaSeatService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/cinema_seat")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CinemaSeatController {
    CinemaSeatService cinemaSeatService;
    @PostMapping("")
    public ApiResponse<CinemaSeatResponse> create(@RequestBody @Valid CinemaSeatRequest request){
        return ApiResponse.<CinemaSeatResponse>builder()
                .message("Create successfully")
                .result(cinemaSeatService.createCinemaSeat(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<CinemaSeatResponse>> getAllCinemaSeat(
            @RequestParam("schedule_id") Long scheduleId
    ){
        List<CinemaSeatResponse> cinemaResponses = cinemaSeatService
                .getCinemaSeat(scheduleId);
        int totalCinema = cinemaResponses.size();
        return ApiResponse.<List<CinemaSeatResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .result(cinemaResponses)
                .build();
    }

    @GetMapping("/seatBooked")
    public ApiResponse<List<CinemaSeatResponse>> getCinemaSeatBooked(
            @RequestParam("schedule_id") Long scheduleId,
            @RequestParam("status") int status
    ){
        List<CinemaSeatResponse> cinemaResponses = cinemaSeatService
                .getCinemaSeat(scheduleId, status);
        int totalCinema = cinemaResponses.size();
        return ApiResponse.<List<CinemaSeatResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .result(cinemaResponses)
                .build();
    }

    @GetMapping("/seatBought")
    public ApiResponse<List<CinemaSeatResponse>> getCinemaSeatBought(
            @RequestParam("schedule_id") Long scheduleId,
            @RequestParam("status") int status
    ){
        List<CinemaSeatResponse> cinemaResponses = cinemaSeatService
                .getCinemaSeatBought(scheduleId, status);
        int totalCinema = cinemaResponses.size();
        return ApiResponse.<List<CinemaSeatResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .result(cinemaResponses)
                .build();
    }

    @GetMapping("/room/{roomId}")
    public ApiResponse<List<CinemaSeatResponse>> getAllCinema(
            @PathVariable("roomId") Long roomId
    ){
        List<CinemaSeatResponse> cinemaResponses = cinemaSeatService
                .cinemaSeatByRoom(roomId);
        int totalCinema = cinemaResponses.size();
        return ApiResponse.<List<CinemaSeatResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .result(cinemaResponses)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CinemaSeatResponse> getCinemaById(@PathVariable("id") Long id){
        return ApiResponse.<CinemaSeatResponse>builder()
                .result(cinemaSeatService.getCinemaSeatById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CinemaSeatResponse> updateCinemaById(
            @PathVariable("id") Long id,
            @RequestBody @Valid CinemaSeatRequest request
    ){
        return ApiResponse.<CinemaSeatResponse>builder()
                .result(cinemaSeatService.updateCinemaSeat(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCinemaById(@PathVariable("id") Long id){
        cinemaSeatService.deleteCinemaSeat(id);
        return ApiResponse.<String>builder()
                .result("Cinema seat has been deleted")
                .build();
    }
}
