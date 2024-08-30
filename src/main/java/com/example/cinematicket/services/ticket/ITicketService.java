package com.example.cinematicket.services.ticket;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.requests.InvoiceRequest;
import com.example.cinematicket.dtos.requests.TicketRequest;
import com.example.cinematicket.dtos.responses.CreateTicketRequest;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.dtos.responses.TicketResponse;
import com.example.cinematicket.entities.Ticket;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITicketService {
    List<CreateTicketRequest> createTicket(List<Ticket> tickets);
    TicketResponse getTicketById(Long id);

    Page<TicketResponse> getAllTicket(int page, int limit);

    Page<TicketResponse> searchTicket(String name, int page, int limit);

    TicketResponse updateTicket(Long id, TicketRequest request);

    void deleteTicket(Long id);
}
