package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.MoviePeopleRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.MoviePeopleResponse;
import com.example.cinematicket.services.moviePeople.MoviePeopleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/moviePeople")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class MoviePeopleController {
    MoviePeopleService moviePeopleService;

    @PostMapping("")
    public ApiResponse<MoviePeopleResponse> create(
            @RequestBody @Valid MoviePeopleRequest request
            ) throws IOException {
        MoviePeopleResponse movieResponses = moviePeopleService.createMoviePeople(request);
        return ApiResponse.<MoviePeopleResponse>builder()
                .message("Create movie successfully")
                .result(movieResponses)
                .build();
    }


    @GetMapping("")
    public ApiResponse<List<MoviePeopleResponse>> searchMoviePeople(
            @RequestParam(value = "name", required = false) String nameMoviePeople,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<MoviePeopleResponse> movieResponse = moviePeopleService
                .getMoviePeopleALl(nameMoviePeople, page, limit)
                .getContent();
        return ApiResponse.<List<MoviePeopleResponse>> builder()
                .result(movieResponse)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<MoviePeopleResponse> getMoviePeopleById(@PathVariable("id") Long id){
        return ApiResponse.<MoviePeopleResponse>builder()
                .result(moviePeopleService.findById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<MoviePeopleResponse> updateMoviePeopleById(
            @PathVariable("id") Long id,
            @RequestBody @Valid MoviePeopleRequest request
    ){
        return ApiResponse.<MoviePeopleResponse>builder()
                .result(moviePeopleService.updateMoviePeople(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMoviePeopleById(@PathVariable("id") Long id){
        moviePeopleService.deleteMoviePeople(id);
        return ApiResponse.<String>builder()
                .result("Gender has been deleted")
                .build();
    }


}
