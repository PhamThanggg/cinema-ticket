package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.CinemaSeatResponse;
import com.example.cinematicket.entities.CinemaSeat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CinemaSeatMapper {
    CinemaSeat toCinemaSeat(CinemaSeatRequest request);

    CinemaSeatResponse toCinemaSeatResponse(CinemaSeat cinemaSeat);

    void updateCinemaSeat(@MappingTarget CinemaSeat seat, CinemaSeatRequest request);
}
