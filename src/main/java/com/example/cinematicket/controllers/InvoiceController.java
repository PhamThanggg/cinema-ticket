package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.InvoiceRequest;
import com.example.cinematicket.dtos.requests.ListTicketRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.services.invoice.InvoiceService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/invoice")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceController {
    InvoiceService invoiceService;

    @PostMapping("")
    public ApiResponse<InvoiceResponse> create(@RequestBody @Valid ListTicketRequest request) {
        return ApiResponse.<InvoiceResponse>builder()
                .message("create successfully")
                .result(invoiceService.createInvoice(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<InvoiceResponse>> getAll(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<InvoiceResponse> userResponses = invoiceService.getAllInvoice(page, limit).getContent();
        return ApiResponse.<List<InvoiceResponse>>builder()
                .result(userResponses)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<InvoiceResponse> getById(@PathVariable("id") Long id) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(invoiceService.getInvoiceById(id))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<InvoiceResponse> update(
            @PathVariable("userId") Long id,
            @RequestBody @Valid InvoiceRequest request) {
        return ApiResponse.<InvoiceResponse>builder()
                .message("update successfully")
                .result(invoiceService.updateInvoice(id, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> delete(@PathVariable("userId") Long id) {
        invoiceService.deleteInvoice(id);
        return ApiResponse.<String>builder()
                .message("Delete successfully")
                .build();
    }
}
