package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.TicketTypeRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.TicketTypeResponse;
import com.example.cinematicket.services.ticket.TicketTypeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/ticketType")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketTypeController {
    TicketTypeService ticketTypeService;
    @PostMapping("")
    public ApiResponse<TicketTypeResponse> create(@RequestBody @Valid TicketTypeRequest request) {
        return ApiResponse.<TicketTypeResponse>builder()
                .message("create successfully")
                .result(ticketTypeService.createTicketType(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<TicketTypeResponse>> getAll() {
        List<TicketTypeResponse> userResponses = ticketTypeService.getAllTicketType();
        return ApiResponse.<List<TicketTypeResponse>>builder()
                .result(userResponses)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TicketTypeResponse> getById(@PathVariable("id") Long id) {
        return ApiResponse.<TicketTypeResponse>builder()
                .result(ticketTypeService.findById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TicketTypeResponse> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid  TicketTypeRequest request) {
        return ApiResponse.<TicketTypeResponse>builder()
                .message("update successfully")
                .result(ticketTypeService.updateTicketType(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Long id) {
        ticketTypeService.deleteTicketType(id);
        return ApiResponse.<String>builder()
                .message("Delete successfully")
                .build();
    }
}
