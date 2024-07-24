package com.example.cinematicket.services;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.MovieImage;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMovieService {
    MovieResponse createMovie(MovieRequest request) throws IOException;

    MovieResponse findById(Long id);

    Page<MovieResponse> getAllMovie(int page, int limit);

    Page<MovieResponse> searchMovie(String nameMovie, String genre, int page, int limit);

    MovieResponse updateMovie(Long id, MovieRequest request);

    void deleteMovie(Long id);

    public  List<MovieImageResponse> createMovieImage(Long movieId, List<MultipartFile> files) throws IOException;
}
