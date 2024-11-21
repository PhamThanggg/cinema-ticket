package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.movie.MovieResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.services.movie.MovieService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @PostMapping(value = "upload_video/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<MovieResponse> uploadVideo(
            @PathVariable("id") Long movieId,
            @ModelAttribute MultipartFile file
    ) throws IOException {
        return ApiResponse.<MovieResponse>builder()
                .message("Upload movie successfully")
                .result(movieService.createMovieVideo(movieId, file))
                .build();
    }

    @GetMapping("/show/{status}")
    public PageResponse<List<MovieResponse>> getMovieShowNow(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @PathVariable("status") int status
    ){
        Page<MovieResponse> moviePage = movieService.getAllMovie(page, limit, status);

        List<MovieResponse> movieResponses = moviePage.getContent();
        int totalCinema = movieResponses.size();
        return PageResponse.<List<MovieResponse>>builder()
                .message("Total genre: " + totalCinema)
                .currentPage(moviePage.getNumber())
                .totalPages(moviePage.getTotalPages())
                .totalElements(moviePage.getTotalElements())
                .pageSize(moviePage.getSize())
                .result(movieResponses)
                .build();
    }

    @GetMapping("/show-area/{status}/{areaId}")
    public PageResponse<List<MovieResponse>> getMovieArea(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @PathVariable("status") int status,
            @PathVariable("areaId") Long areaId
    ){
        Page<MovieResponse> moviePage = movieService.getAllMovie(page, limit, status, areaId);

        List<MovieResponse> movieResponses = moviePage.getContent();
        int totalCinema = movieResponses.size();
        return PageResponse.<List<MovieResponse>>builder()
                .message("Total genre: " + totalCinema)
                .currentPage(moviePage.getNumber())
                .totalPages(moviePage.getTotalPages())
                .totalElements(moviePage.getTotalElements())
                .pageSize(moviePage.getSize())
                .result(movieResponses)
                .build();
    }

    @GetMapping("/search")
    public PageResponse<List<MovieResponse>> searchMovie(
            @RequestParam(value = "nameMovie", required = false) String nameMovie,
            @RequestParam(value = "genreId", required = false) Set<Integer> genreId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        Page<MovieResponse> movieResponse = movieService
                .searchMovieOrGenre(nameMovie, genreId, status, page, limit);

        return PageResponse.<List<MovieResponse>> builder()
                .currentPage(movieResponse.getNumber())
                .totalPages(movieResponse.getTotalPages())
                .totalElements(movieResponse.getTotalElements())
                .pageSize(movieResponse.getSize())
                .result(movieResponse.getContent())
                .build();
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
            @RequestBody @Valid MovieRequest request
    ){
        return ApiResponse.<MovieResponse>builder()
                .result(movieService.updateMovie(id, request))
                .build();
    }

    @PostMapping("/update_videoPath/{id}")
    public ApiResponse<MovieResponse> updateVideo(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload)
    {
        String videoPath = (String) payload.get("videoPath");
        return ApiResponse.<MovieResponse>builder()
                .message("Create video path successfully")
                .result(movieService.createMovieVideoLink(id, videoPath))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMovieById(@PathVariable("id") Long id){
        movieService.deleteMovie(id);
        return ApiResponse.<String>builder()
                .result("Movie has been deleted")
                .build();
    }

    @DeleteMapping("/image")
    public ApiResponse<String> deleteImageById(@RequestParam("ids") Set<Long> ids) throws IOException {
        movieService.deleteMovieImage(ids);
        return ApiResponse.<String>builder()
                .result("Movie has been deleted")
                .build();
    }


}
