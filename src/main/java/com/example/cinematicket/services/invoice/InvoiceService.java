package com.example.cinematicket.services.invoice;

import com.example.cinematicket.dtos.requests.InvoiceRequest;
import com.example.cinematicket.dtos.requests.ListTicketRequest;
import com.example.cinematicket.dtos.responses.CreateTicketResponse;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.entities.*;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.InvoiceMapper;
import com.example.cinematicket.repositories.*;
import com.example.cinematicket.services.invoiceItem.InvoiceItemService;
import com.example.cinematicket.services.ticket.TicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

        if (request.getCinemaSeatId().size() != request.getTicketTypeId().size() || request.getScheduleID().size() != request.getTicketTypeId().size()) {
            throw new IllegalArgumentException("Thong tin cac ve chua đủ!!!");
        }

        int listSize = request.getScheduleID().size();
        // Lấy tất cả các ID cần thiết từ các yêu cầu
        Set<Long> scheduleIds = new HashSet<>(request.getScheduleID());
        Set<Long> cinemaSeatIds = new HashSet<>(request.getCinemaSeatId());
        Set<Long> ticketTypeIds = new HashSet<>(request.getTicketTypeId());

        // check ghế có ai đặt chưa
        if(ticketService.isCinemaSeatBooked(cinemaSeatIds, scheduleIds)){
            throw new RuntimeException("The chair has been booked");
        }
        if(request.getScheduleID().size() > cinemaSeatIds.size()){
            throw new RuntimeException("Do not place them in duplicate");
        }

        // Truy vấn tất cả các đối tượng từ cơ sở dữ liệu
        Map<Long, Schedule> scheduleMap = scheduleRepository.findAllById(scheduleIds).stream()
                .collect(Collectors.toMap(Schedule::getId, schedule -> schedule));
        Map<Long, CinemaSeat> cinemaSeatMap = cinemaSeatRepository.findAllById(cinemaSeatIds).stream()
                .collect(Collectors.toMap(CinemaSeat::getId, cinemaSeat -> cinemaSeat));
        Map<Long, TicketType> ticketTypeMap = ticketTypeRepository.findAllById(ticketTypeIds).stream()
                .collect(Collectors.toMap(TicketType::getId, ticketType -> ticketType));

        // Kiểm tra và xử lý lỗi
        for (int i = 0; i < listSize; i++) {
            // Kiểm tra sự tồn tại của từng đối tượng
            if (!scheduleMap.containsKey(request.getScheduleID().get(i))) {
                throw new RuntimeException("Schedule ID " + request.getScheduleID() + " not exists");
            }

            if (!cinemaSeatMap.containsKey(request.getCinemaSeatId().get(i))) {
                throw new RuntimeException("CinemaSeat ID " + request.getCinemaSeatId() + " not exists.");
            }

            if (!ticketTypeMap.containsKey(request.getTicketTypeId().get(i))) {
                throw new RuntimeException("TicketType ID " + request.getTicketTypeId() + " not exists.");
            }
        }

        // tao hoa don
        Invoice invoice = Invoice.builder()
                .user(user)
                .totalAmount(request.getTotalAmount())
                .build();
        Invoice invoiceResult = invoiceRepository.save(invoice);

        // Chuyển đổi danh sách ID thành danh sách Ticket
        List<Ticket> tickets = IntStream.range(0, request.getScheduleID().size())
                .mapToObj(i -> {
                    Ticket ticket = new Ticket();
                    ticket.setSchedule(scheduleMap.get(request.getScheduleID().get(i)));
                    ticket.setCinemaSeat(cinemaSeatMap.get(request.getCinemaSeatId().get(i)));
                    ticket.setTicketType(ticketTypeMap.get(request.getTicketTypeId().get(i)));
                    ticket.setInvoice(invoiceResult);
                    return ticket;
                })
                .collect(Collectors.toList());

        // tao vé
        List<CreateTicketResponse> ticketRequests = ticketService.createTicket(tickets);
        // tao item
        List<Item> items = invoiceItemService.create(request.getInvoiceItems(), request.getCinemaId(), invoice.getId());

       return invoiceMapper.toInvoiceResponse(invoiceResult);
    }

    @Override
    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_EXISTS));

        return invoiceMapper.toInvoiceResponse(invoice);
    }

    @Override
    public Page<InvoiceResponse> getAllInvoice(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return invoiceRepository.findAll(pageable).map(invoiceMapper::toInvoiceResponse);
    }

    @Override
    public Page<InvoiceResponse> searchInvoice(String name, int page, int limit) {
        return null;
    }

    @Override
    public InvoiceResponse updateInvoice(Long id, InvoiceRequest request) {
        Invoice invoice = invoiceRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.INVOICE_NOT_EXISTS));

        invoiceMapper.updateInvoice(invoice, request);
        return invoiceMapper.toInvoiceResponse(invoiceRepository.save(invoice));
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
