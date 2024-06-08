package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    Cinema toCinema(CinemaRequest request);

    CinemaResponse toCinemaResponse(Cinema cinema);

    void updateCinema(@MappingTarget Cinema cinema, CinemaRequest request);
}
