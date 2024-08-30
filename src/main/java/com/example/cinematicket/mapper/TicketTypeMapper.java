package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.TicketTypeRequest;
import com.example.cinematicket.dtos.responses.TicketTypeResponse;
import com.example.cinematicket.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    TicketType toTicketPrice(TicketTypeRequest request);

    TicketTypeResponse toTicketPriceResponse(TicketType ticketPrice);

    void updateTicketPrice(@MappingTarget TicketType ticketPrice, TicketTypeRequest request);
}
