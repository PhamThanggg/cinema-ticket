package com.example.cinematicket.services.ticket;

import com.example.cinematicket.dtos.requests.TicketTypeRequest;
import com.example.cinematicket.dtos.responses.TicketTypeResponse;
import com.example.cinematicket.entities.TicketType;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.TicketTypeMapper;
import com.example.cinematicket.repositories.TicketTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketTypeService implements ITicketTypeService {
    TicketTypeRepository ticketTypeRepository;
    TicketTypeMapper ticketTypeMapper;

    @Override
    public TicketTypeResponse createTicketType(TicketTypeRequest request) {
        if(ticketTypeRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.TICKET_TYPE_EXISTS);

        TicketType ticketType = ticketTypeMapper.toTicketPrice(request);
        return ticketTypeMapper.toTicketPriceResponse(ticketTypeRepository.save(ticketType));
    }

    @Override
    public TicketTypeResponse findById(Long id) {
        TicketType ticketType = ticketTypeRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.TICKET_TYPE_NOT_EXISTS));

        return ticketTypeMapper.toTicketPriceResponse(ticketType);
    }

    @Override
    public List<TicketTypeResponse> getAllTicketType() {
        return ticketTypeRepository.findAll().stream().map(ticketTypeMapper::toTicketPriceResponse).toList();
    }

    @Override
    public Page<TicketTypeResponse> searchTicketType(String search, int page, int limit) {
        return null;
    }

    @Override
    public TicketTypeResponse updateTicketType(Long id, TicketTypeRequest request) {
        TicketType ticketType = ticketTypeRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.TICKET_TYPE_NOT_EXISTS));

        ticketTypeMapper.updateTicketPrice(ticketType, request);
        return ticketTypeMapper.toTicketPriceResponse(ticketTypeRepository.save(ticketType));
    }

    @Override
    public void deleteTicketType(Long id) {
        ticketTypeRepository.deleteById(id);
    }
}
