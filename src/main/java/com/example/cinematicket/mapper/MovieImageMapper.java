package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.MovieImageRequest;
import com.example.cinematicket.dtos.responses.MovieImageResponse;
import com.example.cinematicket.entities.MovieImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieImageMapper {
    MovieImage toMovieImage(MovieImageRequest request);
    MovieImageResponse toMovieImageResponse(MovieImage movieImage);
    void updateMovieImage(@MappingTarget MovieImage movieImage, MovieImageRequest request);
}
