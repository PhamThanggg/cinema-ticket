package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.invoice.InvoiceRequest;
import com.example.cinematicket.dtos.requests.invoice.InvoiceUpdateRequest;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.entities.Invoice;
import com.example.cinematicket.entities.InvoiceItem;
import com.example.cinematicket.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toInvoice(InvoiceRequest request);
    @Mapping(source = "user.id", target = "userId")
    InvoiceResponse toInvoiceResponse(Invoice invoice);
    void updateInvoice(@MappingTarget Invoice invoice, InvoiceUpdateRequest request);

    default List<Long> mapTicketIds(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
    }

    default List<Long> mapItemIds(List<InvoiceItem> invoiceItems) {
        return invoiceItems.stream()
                .map(InvoiceItem::getId)
                .collect(Collectors.toList());
    }
}
