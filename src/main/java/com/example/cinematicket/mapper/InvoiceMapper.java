package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.InvoiceRequest;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.entities.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice toInvoice(InvoiceRequest request);
    InvoiceResponse toInvoiceResponse(Invoice invoice);
    void updateInvoice(@MappingTarget Invoice invoice, InvoiceRequest request);
}
