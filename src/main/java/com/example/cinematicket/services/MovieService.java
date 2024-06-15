package com.example.cinematicket.services;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.MovieMapper;
import com.example.cinematicket.repositories.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MovieService implements IMovieService{
    MovieRepository movieRepository;
    MovieMapper movieMapper;
    @Override
    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = movieMapper.toMovie(request);

        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @Override
    public MovieResponse findById(Long id) {
        Movie movie = movieRepository.findById(id).
                orElseThrow(()-> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        return movieMapper.toMovieResponse(movie);
    }

    @Override
    public Page<MovieResponse> getAllMovie(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return movieRepository.findAll(pageable).map(movieMapper::toMovieResponse);
    }

    @Override
    public Page<MovieResponse> searchMovie(String nameMovie, int page, int limit) {
        return null;
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id).
                orElseThrow(()-> new AppException(ErrorCode.MOVIE_EXISTED));

        movieMapper.updateMovie(movie, request);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
