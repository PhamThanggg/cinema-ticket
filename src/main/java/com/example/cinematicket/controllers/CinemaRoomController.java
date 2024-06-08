package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.CinemaRoomRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import com.example.cinematicket.services.CinemaRoomService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cinema_room")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CinemaRoomController {
    CinemaRoomService cinemaRoomService;

    @PostMapping("")
    public ApiResponse<CinemaRoomResponse> create(@RequestBody @Valid CinemaRoomRequest request){
        return ApiResponse.<CinemaRoomResponse>builder()
                .message("Create successfully")
                .result(cinemaRoomService.createCinemaRoom(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<CinemaRoomResponse>> getAllCinema(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_id") Long cinemaId
    ){
        List<CinemaRoomResponse> cinemaResponses = cinemaRoomService.getAllCinemaRoom(page, limit).getContent();
        Long totalCinema = cinemaRoomService.totalCinemaRoom(cinemaId);
        return ApiResponse.<List<CinemaRoomResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .result(cinemaResponses)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<CinemaRoomResponse>> getAllCinema(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_id") Long cinemaId
    ){
        List<CinemaRoomResponse> cinemaResponses = cinemaRoomService.searchCinemaRoom(name, page, limit).getContent();
        Long totalCinema = cinemaRoomService.totalCinemaRoom(cinemaId);
        return ApiResponse.<List<CinemaRoomResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .result(cinemaResponses)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CinemaRoomResponse> getCinemaById(@PathVariable("id") Long id){
        return ApiResponse.<CinemaRoomResponse>builder()
                .result(cinemaRoomService.getCinemaRoomById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CinemaRoomResponse> updateCinemaById(
            @PathVariable("id") Long id,
            @RequestBody CinemaRoomRequest request
    ){
        return ApiResponse.<CinemaRoomResponse>builder()
                .result(cinemaRoomService.updateCinemaRoom(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCinemaById(@PathVariable("id") Long id){
        cinemaRoomService.deleteCinemaRoom(id);
        return ApiResponse.<String>builder()
                .result("Cinema room has been deleted")
                .build();
    }
}
