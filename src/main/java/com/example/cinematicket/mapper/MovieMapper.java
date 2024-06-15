package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.MovieResponse;
import com.example.cinematicket.entities.Genre;
import com.example.cinematicket.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie toMovie(MovieRequest request);
    MovieResponse toMovieResponse(Movie movie);
    void updateMovie(@MappingTarget Movie movie, MovieRequest request);
}
