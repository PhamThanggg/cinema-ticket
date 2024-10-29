package com.example.cinematicket.services.ticket;

import com.example.cinematicket.dtos.requests.TicketRequest;
import com.example.cinematicket.dtos.responses.TicketResponse;
import com.example.cinematicket.entities.Ticket;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface ITicketService {
    List<Ticket> createTicket(List<Ticket> tickets);
    TicketResponse getTicketById(Long id);

    Page<TicketResponse> getAllTicket(int page, int limit);

    Page<TicketResponse> searchTicket(String name, int page, int limit);

    TicketResponse updateTicket(Long id, TicketRequest request);

    void deleteTicket(Long id);
}
