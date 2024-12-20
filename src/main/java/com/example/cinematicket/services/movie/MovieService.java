package com.example.cinematicket.services.movie;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.dtos.responses.movie.MovieResponse;
import com.example.cinematicket.dtos.responses.movie.MovieScheduleResponse;
import com.example.cinematicket.entities.Genre;
import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.entities.MovieImage;
import com.example.cinematicket.entities.MoviePeople;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.MovieImageMapper;
import com.example.cinematicket.mapper.MovieMapper;
import com.example.cinematicket.repositories.GenreRepository;
import com.example.cinematicket.repositories.MovieImageRepository;
import com.example.cinematicket.repositories.MoviePeopleRepository;
import com.example.cinematicket.repositories.MovieRepository;
import com.example.cinematicket.services.uploadFile.CloudService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MovieService implements IMovieService {
    MovieRepository movieRepository; // tiêm movieRepository vào lớp để sử dụng
    MovieMapper movieMapper;
    MovieImageRepository movieImageRepository;
    MovieImageMapper movieImageMapper;
    MoviePeopleRepository moviePeopleRepository;
    GenreRepository genreRepository;
    CloudService cloudService;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public MovieResponse createMovie(MovieRequest request) throws IOException {
        if(movieRepository.existsByProducerAndDurationAndNameMovie(
                request.getProducer(), request.getDuration(), request.getNameMovie())){
            throw new AppException(ErrorCode.MOVIE_EXISTED);
        }

        Set<Genre> listGenre = genreRepository.findByIdIn(request.getGenres());
        Set<MoviePeople> moviePeople = moviePeopleRepository.findByIdIn(request.getMoviePeople());

        Set<Long> foundIds = listGenre.stream().map(Genre::getId).collect(Collectors.toSet());
        Set<Long> foundMoviePeopleIds = moviePeople.stream().map(MoviePeople::getId).collect(Collectors.toSet());

        List<Long> missingGenreId = request.getGenres().stream()
                .filter(id -> !foundIds.contains(id)).toList();

        List<Long> missMoviePeopleId = request.getMoviePeople().stream()
                .filter(id -> !foundMoviePeopleIds.contains(id)).toList();

        if(!missingGenreId.isEmpty()){
            String errorMessage = missingGenreId.stream()
                    .map(id -> "Genre with id = " + id)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Genre with id = " + errorMessage);
        }

        if(!missMoviePeopleId.isEmpty()){
            String errorMessage = missMoviePeopleId.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Movie people with id = " + errorMessage);
        }

        Movie movie = movieMapper.toMovie(request);
        movie.setGenres(listGenre);
        movie.setMoviePeople(moviePeople);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @Override
    public MovieResponse findById(Long id) {
        Movie movie = movieRepository.findById(id).
                orElseThrow(()-> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        return movieMapper.toMovieResponse(movie);
    }

    public List<MovieScheduleResponse> getAllMovieShowNow() {
        return movieRepository.findMovieShowNow().stream().map(movieMapper::toMovieScheduleResponse).toList();
    }

    @Override
    public Page<MovieResponse> getAllMovie(int page, int limit, int status) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return movieRepository.findByStatus(status, pageable).map(movieMapper::toMovieResponse);
    }

    @Override
    public Page<MovieResponse> getAllMovie(int page, int limit, Long genreId, String name, Integer status, Long areaId) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return movieRepository.findMoviesByAreaIdAndStatus(areaId, genreId, name, status, pageable).map(movieMapper::toMovieResponse);
    }

    @Override
    public Page<MovieResponse> searchMovieOrGenre(String nameMovie, Set<Integer> genreId, Integer status, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return movieRepository.findMovieOrGenre(nameMovie, status, genreId, pageable).map(movieMapper::toMovieResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public MovieResponse updateMovie(Long idG, MovieRequest request) {
        Movie movie = movieRepository.findById(idG).
                orElseThrow(()-> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        if(!request.getDuration().equals(movie.getDuration())
            || !request.getProducer().equals(movie.getProducer())
            || !request.getNameMovie().equals(movie.getNameMovie())
        ){
            if(movieRepository.existsByProducerAndDurationAndNameMovie(
                    request.getProducer(), request.getDuration(), request.getNameMovie())){
                throw new AppException(ErrorCode.MOVIE_EXISTED);
            }
        }

        Set<Genre> listGenre = genreRepository.findByIdIn(request.getGenres());
        Set<MoviePeople> moviePeople = moviePeopleRepository.findByIdIn(request.getMoviePeople());

        Set<Long> foundIds = listGenre.stream().map(Genre::getId).collect(Collectors.toSet());
        Set<Long> foundMoviePeopleIds = moviePeople.stream().map(MoviePeople::getId).collect(Collectors.toSet());

        List<Long> missingGenreId = request.getGenres().stream()
                .filter(id -> !foundIds.contains(id)).toList();

        List<Long> missMoviePeopleId = request.getMoviePeople().stream()
                .filter(id -> !foundMoviePeopleIds.contains(id)).toList();

        if(!missingGenreId.isEmpty()){
            String errorMessage = missingGenreId.stream()
                    .map(id -> "Genre with id = " + id)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Genre with id = " + errorMessage);
        }

        if(!missMoviePeopleId.isEmpty()){
            String errorMessage = missMoviePeopleId.stream()
                    .map(id -> "Genre with id = " + id)
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Genre with id = " + errorMessage);
        }

        movieMapper.updateMovie(movie, request);
        movie.setGenres(listGenre);
        movie.setMoviePeople(moviePeople);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public List<MovieImageResponse> createMovieImage(
            Long movieId,
            List<MultipartFile> files) throws IOException {
        // k cho insert qua 5 anh cho 1 san pham
        int size = movieImageRepository.findByMovieId(movieId).size();
        if(size >= MovieImage.MAXIMUM_IMAGES_PER_MOVIE){
            throw new AppException(ErrorCode.IMAGE_EXISTS);
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

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    @Transactional
    public void deleteMovieImage(Set<Long> movieId) throws IOException {
        Set<MovieImage> movieImages = movieImageRepository.findByIdIn(movieId);
        if(movieImages.isEmpty())
            throw new RuntimeException("Image does not exist");

        Set<Long> imageId = new HashSet<>();
        for (MovieImage list : movieImages){
            String publicId = getPublicId(list.getImageUrl());
            if(publicId != null){
                cloudService.deleteImage(publicId);
            }
            imageId.add(list.getId());
        }

        movieImageRepository.deleteByIdIn(imageId);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public MovieResponse createMovieVideo(Long movieId, MultipartFile file) throws IOException {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow(()-> new AppException(ErrorCode.MOVIE_EXISTED));

        Map video = cloudService.uploadVideo(file);
        String videoPath = video.get("url").toString();
        movie.setTrailer(videoPath);

        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public MovieResponse createMovieVideoLink(Long movieId, String path) {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow(()-> new AppException(ErrorCode.MOVIE_EXISTED));

        movie.setTrailer(path);
        return movieMapper.toMovieResponse(movieRepository.save(movie));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_MOVIE')")
    public void updateVideo(Long movieId) {

    }

    private String getPublicId(String imagePath){
        if (imagePath == null || !imagePath.contains("/")) {
            throw new RuntimeException("Invalid path");
        }

        String[] pathSegments = imagePath.split("/");

        if (pathSegments.length > 0) {
            String publicIdWithFormat = pathSegments[pathSegments.length-1];
            String[] publicIdSegments = publicIdWithFormat.split("\\.");
            return publicIdSegments[0];
        }

        return null;
    }

}
