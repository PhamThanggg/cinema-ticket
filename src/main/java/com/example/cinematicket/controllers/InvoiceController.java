package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.invoice.InvoiceRequest;
import com.example.cinematicket.dtos.requests.ListTicketRequest;
import com.example.cinematicket.dtos.requests.invoice.InvoiceUpdateRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.InvoiceResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.services.invoice.InvoiceService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public PageResponse<List<InvoiceResponse>> getAll(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam(name = "invoiceId", required = false) Long invoiceId,
            @RequestParam(name = "movieName", required = false) String movieName,
            @RequestParam(name = "cinemaId", required = false) Long cinemaId,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "date", required = false) LocalDate date
    ) {
        Page<InvoiceResponse> userResponses = invoiceService.getAllInvoice(page, limit, invoiceId, movieName, cinemaId, status, date);
        return PageResponse.<List<InvoiceResponse>>builder()
                .currentPage(userResponses.getNumber())
                .totalPages(userResponses.getTotalPages())
                .totalElements(userResponses.getTotalElements())
                .pageSize(userResponses.getSize())
                .result(userResponses.getContent())
                .build();
    }

    @GetMapping("/my-invoice")
    public PageResponse<List<InvoiceResponse>> getAllMyInvoice(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        Page<InvoiceResponse> Responses = invoiceService.getMyInvoice(page, limit);
        return PageResponse.<List<InvoiceResponse>>builder()
                .currentPage(Responses.getNumber())
                .totalPages(Responses.getTotalPages())
                .totalElements(Responses.getTotalElements())
                .pageSize(Responses.getSize())
                .result(Responses.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<InvoiceResponse> getById(@PathVariable("id") Long id) {
        return ApiResponse.<InvoiceResponse>builder()
                .result(invoiceService.getInvoiceById(id))
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
