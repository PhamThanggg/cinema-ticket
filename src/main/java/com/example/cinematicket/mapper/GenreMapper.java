package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.GenreRequest;
import com.example.cinematicket.dtos.requests.RoomTypeRequest;
import com.example.cinematicket.dtos.responses.GenreResponse;
import com.example.cinematicket.dtos.responses.RoomTypeResponse;
import com.example.cinematicket.entities.Genre;
import com.example.cinematicket.entities.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toRoomType(GenreRequest request);
    GenreResponse toRoomTypeResponse(Genre genre);
    void updateGere(@MappingTarget Genre genre, GenreRequest request);
}
