package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.MovieRequest;
import com.example.cinematicket.dtos.responses.movie.MovieResponse;
import com.example.cinematicket.dtos.responses.movie.MovieScheduleResponse;
import com.example.cinematicket.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "moviePeople", ignore = true)
    Movie toMovie(MovieRequest request);

    MovieResponse toMovieResponse(Movie movie);

    MovieScheduleResponse toMovieScheduleResponse(Movie movie);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "moviePeople", ignore = true)
    void updateMovie(@MappingTarget Movie movie, MovieRequest request);

}
