package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.CinemaRoomRequest;
import com.example.cinematicket.dtos.requests.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import com.example.cinematicket.dtos.responses.CinemaSeatResponse;
import com.example.cinematicket.services.CinemaSeatService;
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
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_room_id") Long cinemaRoomId
    ){
        List<CinemaSeatResponse> cinemaResponses = cinemaSeatService
                .getCinemaSeat(page, limit, cinemaRoomId)
                .getContent();
        int totalCinema = cinemaResponses.size();
        return ApiResponse.<List<CinemaSeatResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .result(cinemaResponses)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<CinemaSeatResponse>> getAllCinema(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_id") Long cinemaId
    ){
        return null;
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
            @RequestBody CinemaSeatRequest request
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
