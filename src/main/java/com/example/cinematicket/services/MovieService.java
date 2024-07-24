package com.example.cinematicket.services;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.Genre;
import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.entities.MovieImage;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.MovieImageMapper;
import com.example.cinematicket.mapper.MovieMapper;
import com.example.cinematicket.repositories.GenreRepository;
import com.example.cinematicket.repositories.MovieImageRepository;
import com.example.cinematicket.repositories.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MovieService implements IMovieService{
    MovieRepository movieRepository;
    MovieMapper movieMapper;
    MovieImageRepository movieImageRepository;
    MovieImageMapper movieImageMapper;
    GenreRepository genreRepository;
    CloudService cloudService;

    @Override
    public MovieResponse createMovie(MovieRequest request) throws IOException {
        List<Genre> listGenre = genreRepository.findByIdIn(request.getGenres());
        Set<Long> foundIds = listGenre.stream().map(Genre::getId).collect(Collectors.toSet());
        List<Long> missingGenreId = request.getGenres().stream()
                .filter(id -> !foundIds.contains(id)).toList();

        if(!missingGenreId.isEmpty()){
            String errorMessage = missingGenreId.stream()
                    .map(id -> "Genre with id = " + id)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Genre with id = " + errorMessage);
        }

        Movie movie = movieMapper.toMovie(request);
        movie.setGenres(listGenre);

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
    public Page<MovieResponse> searchMovie(String nameMovie, String genre, int page, int limit) {
        return null;
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id).
                orElseThrow(()-> new AppException(ErrorCode.MOVIE_EXISTED));

        List<Genre> listGenre = genreRepository.findByIdIn(request.getGenres());
        Set<Long> foundIds = listGenre.stream().map(Genre::getId).collect(Collectors.toSet());
        List<Long> missingGenreId = request.getGenres().stream()
                .filter(idG -> !foundIds.contains(id)).toList();

        if(!missingGenreId.isEmpty()){
            String errorMessage = missingGenreId.stream()
                    .map(idG -> "Genre with id = " + id)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Genre with id = " + errorMessage);
        }

        movieMapper.updateMovie(movie, request);
        movie.setGenres(listGenre);

        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieImageResponse> createMovieImage(
            Long movieId,
            List<MultipartFile> files) throws IOException {
        // k cho insert qua 5 anh cho 1 san pham
        int size = movieImageRepository.findByMovieId(movieId).size();
        if(size >= MovieImage.MAXIMUM_IMAGES_PER_MOVIE){
            throw new RuntimeException("Number of image must be < " + MovieImage.MAXIMUM_IMAGES_PER_MOVIE);
        }

        Movie existingMovie = movieRepository
                .findById(movieId)
                .orElseThrow(() -> new RuntimeException("Cannot find movie with id " + movieId));

        List<Map> listPathImage = cloudService.uploadFiles(files);
        List<MovieImageResponse> movieImageResponse = new ArrayList<>();
        for(Map list : listPathImage){
            MovieImage newMovieImage = MovieImage.builder()
                    .movie(existingMovie)
                    .imageUrl(list.get("url").toString())
                    .build();
            movieImageRepository.save(newMovieImage);
            movieImageResponse.add(movieImageMapper.toMovieImageResponse(newMovieImage));
        }

        return movieImageResponse;
    }
}
