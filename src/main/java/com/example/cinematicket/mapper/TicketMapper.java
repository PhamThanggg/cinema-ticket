package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.TicketRequest;
import com.example.cinematicket.dtos.responses.CreateTicketResponse;
import com.example.cinematicket.dtos.responses.TicketResponse;
import com.example.cinematicket.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toTicket(TicketRequest request);


    @Mapping(source = "ticketType.id", target = "ticketTypeId")
    @Mapping(source = "invoice.id", target = "invoiceId")
    TicketResponse toTicketResponse(Ticket ticket);
    CreateTicketResponse toCreateTicketResponse(Ticket ticket);
    void updateTicket(@MappingTarget Ticket ticket, TicketRequest request);
}
