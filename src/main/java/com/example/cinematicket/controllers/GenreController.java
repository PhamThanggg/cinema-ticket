package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.services.movie.GenreService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/genre")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class GenreController {
    GenreService genreService;

    @PostMapping("")
    public ApiResponse<GenreResponse> create(@RequestBody @Valid GenreRequest request){
        return ApiResponse.<GenreResponse>builder()
                .message("Create successfully")
                .result(genreService.createGenre(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<GenreResponse>> getAllGenre(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<GenreResponse> genreResponses = genreService
                .getAllGenre(page, limit)
                .getContent();
        int totalGenre = genreResponses.size();
        return ApiResponse.<List<GenreResponse>>builder()
                .message("Total genre: " + totalGenre)
                .result(genreResponses)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<GenreResponse>> getAllGenre(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("cinema_id") Long cinemaId
    ){
        return null;
    }

    @GetMapping("/{id}")
    public ApiResponse<GenreResponse> getGenreById(@PathVariable("id") Long id){
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.findById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<GenreResponse> updateGenreById(
            @PathVariable("id") Long id,
            @RequestBody @Valid GenreRequest request
    ){
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.updateGenre(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteGenreById(@PathVariable("id") Long id){
        genreService.deleteGenre(id);
        return ApiResponse.<String>builder()
                .result("Gender has been deleted")
                .build();
    }
}
