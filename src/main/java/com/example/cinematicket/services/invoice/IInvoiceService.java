package com.example.cinematicket.services.invoice;

import com.example.cinematicket.dtos.requests.InvoiceRequest;
import com.example.cinematicket.dtos.requests.ListTicketRequest;
import com.example.cinematicket.dtos.requests.TicketRequest;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IInvoiceService {
    InvoiceResponse createInvoice(ListTicketRequest request);

    InvoiceResponse getInvoiceById(Long id);

    Page<InvoiceResponse> getAllInvoice(int page, int limit);

    Page<InvoiceResponse> searchInvoice(String name, int page, int limit);

    InvoiceResponse updateInvoice(Long id, InvoiceRequest request);

    void deleteInvoice(Long id);
}
