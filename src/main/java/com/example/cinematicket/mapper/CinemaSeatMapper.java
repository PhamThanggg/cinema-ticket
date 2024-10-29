package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.seat.CinemaSeatRequest;
import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatResponse;
import com.example.cinematicket.entities.CinemaSeat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CinemaSeatMapper {
    CinemaSeat toCinemaSeat(CinemaSeatRequest request);

    @Mapping(source = "cinemaRoom.id", target = "cinemaRoomId")
    CinemaSeatResponse toCinemaSeatResponse(CinemaSeat cinemaSeat);

    List<CinemaSeatResponse> toCinemaSeatResponse(List<CinemaSeat> cinemaSeats);

    void updateCinemaSeat(@MappingTarget CinemaSeat seat, CinemaSeatRequest request);
}
