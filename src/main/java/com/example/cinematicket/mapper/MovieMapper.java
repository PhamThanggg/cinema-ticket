package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "moviePeople", ignore = true)
    Movie toMovie(MovieRequest request);

    @Mapping(target = "imageIds", expression = "java(mapImageIds(movie.getImages()))")
    @Mapping(target = "genreIds", expression = "java(mapGenreIds(movie.getGenres()))")
    @Mapping(target = "moviePeopleIds", expression = "java(mapMoviePeopleIds(movie.getMoviePeople()))")
    MovieResponse toMovieResponse(Movie movie);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "moviePeople", ignore = true)
    void updateMovie(@MappingTarget Movie movie, MovieRequest request);

    default Set<Long> mapImageIds(Set<MovieImage> images) {
        return images.stream()
                .map(MovieImage::getId)
                .collect(Collectors.toSet());
    }

    default Set<Long> mapGenreIds(Set<Genre> genres) {
        return genres.stream()
                .map(Genre::getId)
                .collect(Collectors.toSet());
    }

    default Set<Long> mapMoviePeopleIds(Set<MoviePeople> moviePeople) {
        return moviePeople.stream()
                .map(MoviePeople::getId)
                .collect(Collectors.toSet());
    }
}
