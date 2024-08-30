package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.AreaRequest;
import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.responses.AreaResponse;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.entities.Area;
import com.example.cinematicket.entities.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    Area toArea(AreaRequest request);

    AreaResponse toAreaResponse(Area area);

    void updateArea(@MappingTarget Area area, AreaRequest request);
}
