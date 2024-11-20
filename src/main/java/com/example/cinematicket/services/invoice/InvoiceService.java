package com.example.cinematicket.services.invoice;

import com.example.cinematicket.dtos.requests.ListTicketRequest;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.entities.*;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.InvoiceMapper;
import com.example.cinematicket.repositories.*;
import com.example.cinematicket.services.invoiceItem.InvoiceItemService;
import com.example.cinematicket.services.ticket.TicketService;
import com.example.cinematicket.services.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class InvoiceService implements IInvoiceService {
    UserRepository userRepository;
    UserService userService;
    ScheduleRepository scheduleRepository;
    CinemaSeatRepository cinemaSeatRepository;
    TicketTypeRepository ticketTypeRepository;
    InvoiceRepository invoiceRepository;
    InvoiceMapper invoiceMapper;
    TicketService ticketService;
    InvoiceItemService invoiceItemService;
    @Override
    @Transactional
    public InvoiceResponse createInvoice(ListTicketRequest request) {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Schedule schedule = scheduleRepository.findById(request.getScheduleID())
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXISTS));

        TicketType ticketType = ticketTypeRepository.findById(request.getTicketTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_TYPE_NOT_EXISTS));

        LocalDateTime timeNow = LocalDateTime.now();
        if(schedule.getStartTime().isBefore(timeNow)){
            throw new RuntimeException("Bạn không thể đặt vé khi đã đến giờ chiếu");
        }

        int listSize = request.getCinemaSeatId().size();
        Set<Long> cinemaSeatIds = new HashSet<>(request.getCinemaSeatId());

        if(ticketService.isCinemaSeatBooked(cinemaSeatIds, request.getScheduleID())){
            throw new RuntimeException("The chair has been booked");
        }

        Map<Long, CinemaSeat> cinemaSeatMap = cinemaSeatRepository.findAllById(cinemaSeatIds).stream()
                .collect(Collectors.toMap(CinemaSeat::getId, cinemaSeat -> cinemaSeat));

        for (int i = 0; i < listSize; i++) {
            // Kiểm tra sự tồn tại của từng đối tượng
            if (!cinemaSeatMap.containsKey(request.getCinemaSeatId().get(i))) {
                throw new RuntimeException("CinemaSeat ID " + request.getCinemaSeatId() + " not exists.");
            }
        }

        // tao hoa don
        Invoice invoice = Invoice.builder()
                .user(user)
                .schedule(schedule)
                .totalAmount(request.getTotalAmount())
                .paymentExpirationTime(request.getPaymentExpirationTime())
                .build();
        Invoice invoiceResult = invoiceRepository.save(invoice);

        // Chuyển đổi danh sách ID thành danh sách Ticket
        List<Ticket> tickets = IntStream.range(0, request.getCinemaSeatId().size())
                .mapToObj(i -> {
                    Ticket ticket = new Ticket();
                    ticket.setCinemaSeat(cinemaSeatMap.get(request.getCinemaSeatId().get(i)));
                    ticket.setTicketType(ticketType);
                    ticket.setInvoice(invoiceResult);
                    return ticket;
                })
                .collect(Collectors.toList());

        // tao vé
        List<Ticket> ticketRequests = ticketService.createTicket(tickets);
        // tao item
        List<InvoiceItem> items = invoiceItemService.create(request.getInvoiceItems(), request.getCinemaId(), invoice.getId());

        invoiceResult.setTickets(ticketRequests);
        invoiceResult.setInvoiceItems(items);

       return invoiceMapper.toInvoiceResponse(invoiceResult);
    }

    @Override
    @PostAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET') or returnObject.userId.toString() == authentication.principal.getClaimAsString('id')")
    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_EXISTS));

        return invoiceMapper.toInvoiceResponse(invoice);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET')")
    public Page<InvoiceResponse> getAllInvoice(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return invoiceRepository.findAll(pageable).map(invoiceMapper::toInvoiceResponse);
    }


    public Page<InvoiceResponse> getMyInvoice(int page, int limit) {
        var user = userService.getMyInfo();
        Pageable pageable = PageRequest.of(page, limit);

        return invoiceRepository.findByUserId(pageable, user.getId()).map(invoiceMapper::toInvoiceResponse);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET')")
    public Page<InvoiceResponse> searchInvoice(String name, int page, int limit) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET') or @securityService.isInvoiceOwner(#id, authentication)")
    public InvoiceResponse updateInvoice(Long id, LocalDateTime paymentTime, Double amountPaid, int status) {
        Invoice invoice = invoiceRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_EXISTS));

        invoice.setPaymentTime(paymentTime);
        invoice.setAmountPaid(amountPaid);
        invoice.setStatus(status);
        return invoiceMapper.toInvoiceResponse(invoiceRepository.save(invoice));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MANAGE_TICKET') or @securityService.isInvoiceOwner(#id, authentication)")
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
