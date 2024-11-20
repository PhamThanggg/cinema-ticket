package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.CinemaRoomRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.services.cinemaRoom.CinemaRoomService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/cinema_room")
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
    public PageResponse<List<CinemaRoomResponse>> getAllCinema(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_id") Long cinemaId
    ){
        Page<CinemaRoomResponse> cinemaResponses = cinemaRoomService
                .getAllCinemaRoom(page, limit, cinemaId);
        Long totalCinema = cinemaRoomService.totalCinemaRoom(cinemaId);
        return PageResponse.<List<CinemaRoomResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .currentPage(cinemaResponses.getNumber())
                .totalPages(cinemaResponses.getTotalPages())
                .totalElements(cinemaResponses.getTotalElements())
                .pageSize(cinemaResponses.getSize())
                .result(cinemaResponses.getContent())
                .build();
    }

    @GetMapping("/search")
    public PageResponse<List<CinemaRoomResponse>> getAllCinema(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_id") Long cinemaId
    ){
        Page<CinemaRoomResponse> cinemaResponses = cinemaRoomService.searchCinemaRoom(name, cinemaId, page, limit);
        Long totalCinema = name==null
                ?cinemaRoomService.totalCinemaRoom(cinemaId)
                :cinemaRoomService.totalCinemaRoomSearch(name);
        return PageResponse.<List<CinemaRoomResponse>>builder()
                .message("Total cinema room: " + totalCinema)
                .currentPage(cinemaResponses.getNumber())
                .totalPages(cinemaResponses.getTotalPages())
                .totalElements(cinemaResponses.getTotalElements())
                .pageSize(cinemaResponses.getSize())
                .result(cinemaResponses.getContent())
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
            @RequestBody @Valid CinemaRoomRequest request
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
