package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.InvoiceRequest;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.entities.Invoice;
import com.example.cinematicket.entities.InvoiceItem;
import com.example.cinematicket.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toInvoice(InvoiceRequest request);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "ticketIds", expression = "java(mapTicketIds(invoice.getTickets()))")
    @Mapping(target = "itemIds", expression = "java(mapItemIds(invoice.getInvoiceItems()))")
    InvoiceResponse toInvoiceResponse(Invoice invoice);
    void updateInvoice(@MappingTarget Invoice invoice, InvoiceRequest request);

    default Set<Long> mapTicketIds(Set<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getId)
                .collect(Collectors.toSet());
    }

    default Set<Long> mapItemIds(Set<InvoiceItem> invoiceItems) {
        return invoiceItems.stream()
                .map(InvoiceItem::getId)
                .collect(Collectors.toSet());
    }
}
