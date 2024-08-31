package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.MoviePeopleRequest;
import com.example.cinematicket.dtos.responses.MoviePeopleResponse;
import com.example.cinematicket.entities.MoviePeople;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MoviePeopleMapper {
    MoviePeople toMoviePeople(MoviePeopleRequest request);

    MoviePeopleResponse toMoviePeopleResponse(MoviePeople moviePeople);

    void updateMoviePeople(@MappingTarget MoviePeople moviePeople, MoviePeopleRequest request);
}
