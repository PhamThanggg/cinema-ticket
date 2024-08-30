package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.TicketRequest;
import com.example.cinematicket.dtos.responses.CreateTicketRequest;
import com.example.cinematicket.dtos.responses.TicketResponse;
import com.example.cinematicket.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toTicket(TicketRequest request);
    TicketResponse toTicketResponse(Ticket ticket);
    CreateTicketRequest toCreateTicketRequest(Ticket ticket);
    void updateTicket(@MappingTarget Ticket ticket, TicketRequest request);
}
