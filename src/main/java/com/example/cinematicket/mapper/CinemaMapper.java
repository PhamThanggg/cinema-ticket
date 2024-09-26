package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    Cinema toCinema(CinemaRequest request);

    @Mapping(source = "area.id", target = "areaId")
    CinemaResponse toCinemaResponse(Cinema cinema);

    void updateCinema(@MappingTarget Cinema cinema, CinemaRequest request);
}
