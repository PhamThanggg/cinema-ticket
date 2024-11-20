package com.example.cinematicket.services.movie;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.movie.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IMovieService {
    MovieResponse createMovie(MovieRequest request) throws IOException;

    MovieResponse findById(Long id);

    Page<MovieResponse> getAllMovie(int page, int limit, int status);

    Page<MovieResponse> getAllMovie(int page, int limit, int status, Long areaId);

    Page<MovieResponse> searchMovieOrGenre(String nameMovie, Set<Integer> genreId, Integer status, int page, int limit);

    MovieResponse updateMovie(Long id, MovieRequest request);

    void deleteMovie(Long id);

    List<MovieImageResponse> createMovieImage(Long movieId, List<MultipartFile> files) throws IOException;

    void deleteMovieImage(Set<String> movieId) throws IOException;

    MovieResponse createMovieVideo(Long movieId, MultipartFile file) throws IOException;

    MovieResponse createMovieVideoLink(Long movieId, String path);

    void updateVideo(Long movieId);
}
