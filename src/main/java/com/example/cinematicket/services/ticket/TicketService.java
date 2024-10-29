package com.example.cinematicket.services.ticket;

import com.example.cinematicket.dtos.requests.TicketRequest;
import com.example.cinematicket.dtos.responses.CreateTicketResponse;
import com.example.cinematicket.dtos.responses.TicketResponse;
import com.example.cinematicket.entities.Ticket;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.TicketMapper;
import com.example.cinematicket.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class TicketService implements ITicketService {
    TicketMapper ticketMapper;

    TicketRepository ticketRepository;

    @Override
    public List<Ticket> createTicket(List<Ticket> tickets) {
        return ticketRepository.saveAll(tickets);
    }
    @Override
    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_EXISTS));
        return ticketMapper.toTicketResponse(ticket);
    }

    @Override
    public Page<TicketResponse> getAllTicket(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return ticketRepository.findAll(pageable).map(ticketMapper::toTicketResponse);
    }

    @Override
    public Page<TicketResponse> searchTicket(String name, int page, int limit) {

        return null;
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET') or @securityService.isTicketOwner(#id, authentication)")
    public TicketResponse updateTicket(Long id, TicketRequest request) {

        return null;
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET') or @securityService.isTicketOwner(#id, authentication)")
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public boolean isCinemaSeatBooked(Set<Long> cinemaSeatId, Long scheduleId){
        return ticketRepository.existsByCinemaSeatIdInAndInvoiceScheduleId(cinemaSeatId,  scheduleId);
    }
}
