package com.example.cinematicket.services.invoice;

import com.example.cinematicket.dtos.requests.ListTicketRequest;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IInvoiceService {
    InvoiceResponse createInvoice(ListTicketRequest request);

    InvoiceResponse getInvoiceById(Long id);

    Page<InvoiceResponse> getAllInvoice(
            int page, int limit, Long invoiceId,
            String movieName, Long cinemaId,
            Integer status, LocalDate date
    );

    InvoiceResponse updateInvoice(
            Long id, LocalDateTime paymentTime,
            Double amountPaid, int status
    );
    void deleteInvoice(Long id);
}
