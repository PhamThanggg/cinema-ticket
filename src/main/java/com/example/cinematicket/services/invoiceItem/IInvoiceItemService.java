package com.example.cinematicket.services.invoiceItem;

import com.example.cinematicket.dtos.requests.InvoiceItemRequest;
import com.example.cinematicket.entities.InvoiceItem;
import com.example.cinematicket.entities.Item;

import java.util.List;
import java.util.Set;

public interface IInvoiceItemService {
    List<InvoiceItem> create(Set<InvoiceItemRequest> requests, Long cinemaId, Long invoiceId);
}
