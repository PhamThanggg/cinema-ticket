package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.seat.SeatReservationRequest;
import com.example.cinematicket.dtos.responses.SeatReservationResponse;
import com.example.cinematicket.entities.SeatReservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatReservationMapper {
    SeatReservation toSeatReservation(SeatReservationRequest request);

    SeatReservationResponse toSeatReservationResponse(SeatReservation data);

    void updateSeatReservation(@MappingTarget SeatReservation data, SeatReservationRequest request);
}
