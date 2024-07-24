package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.MovieImage;
import com.example.cinematicket.services.MovieService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/movie")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class MovieController {
    MovieService movieService;

    @PostMapping("")
    public ApiResponse<MovieResponse> create(
            @RequestBody @Valid MovieRequest request
            ) throws IOException {
        MovieResponse movieResponses = movieService.createMovie(request);
        return ApiResponse.<MovieResponse>builder()
                .message("Create movie successfully")
                .result(movieResponses)
                .build();
    }

    @PostMapping(value = "upload_images/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<List<MovieImageResponse>> uploadImages(
            @PathVariable("id") Long movieId,
            @ModelAttribute List<MultipartFile> files
    ) throws IOException {
        return ApiResponse.<List<MovieImageResponse>>builder()
                .message("Create movie successfully")
                .result(movieService.createMovieImage(movieId, files))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<MovieResponse>> getAllMovie(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<MovieResponse> movieResponses = movieService
                .getAllMovie(page, limit)
                .getContent();
        int totalCinema = movieResponses.size();
        return ApiResponse.<List<MovieResponse>>builder()
                .message("Total genre: " + totalCinema)
                .result(movieResponses)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<MovieResponse>> searchMovie(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return null;
    }

    @GetMapping("/{id}")
    public ApiResponse<MovieResponse> getMovieById(@PathVariable("id") Long id){
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.findById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<MovieResponse> updateMovieById(
            @PathVariable("id") Long id,
            @RequestBody MovieRequest request
    ){
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updateMovie(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMovieById(@PathVariable("id") Long id){
        movieService.deleteMovie(id);
        return ApiResponse.<String>builder()
                .result("Gender has been deleted")
                .build();
    }
}
