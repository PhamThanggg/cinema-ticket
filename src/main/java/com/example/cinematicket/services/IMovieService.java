package com.example.cinematicket.services;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.MovieResponse;
import org.springframework.data.domain.Page;

public interface IMovieService {
    MovieResponse createMovie(MovieRequest request);

    MovieResponse findById(Long id);

    Page<MovieResponse> getAllMovie(int page, int limit);

    Page<MovieResponse> searchMovie(String nameMovie, int page, int limit);

    MovieResponse updateMovie(Long id, MovieRequest request);

    void deleteMovie(Long id);
}
