package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.CinemaRoomRequest;
import com.example.cinematicket.dtos.responses.CinemaRoomResponse;
import com.example.cinematicket.entities.CinemaRoom;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CinemaRoomMapper {
    CinemaRoom toCinemaRoom(CinemaRoomRequest request);

    CinemaRoomResponse toCinemaRoomResponse(CinemaRoom cinemaRoom);

    void updateCinemaRoom(@MappingTarget CinemaRoom cinema, CinemaRoomRequest request);
}