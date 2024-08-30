package com.example.cinematicket.services.ticket;

import com.example.cinematicket.dtos.requests.TicketTypeRequest;
import com.example.cinematicket.dtos.responses.TicketTypeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITicketTypeService {
    TicketTypeResponse createTicketType(TicketTypeRequest request);

    TicketTypeResponse findById(Long id);
    List<TicketTypeResponse> getAllTicketType();

    Page<TicketTypeResponse> searchTicketType(String search, int page, int limit);

    TicketTypeResponse updateTicketType(Long id, TicketTypeRequest request);

    void deleteTicketType(Long id);
}
