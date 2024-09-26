package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListTicketRequest {
        @NotEmpty(message = "Schedule cannot be empty")
        List<Long> scheduleID;

        @NotEmpty(message = "Cinema Seat cannot be empty")
        List<Long> cinemaSeatId;

        @NotEmpty(message = "Ticket type cannot be empty")
        List<Long> ticketTypeId;

        Set<InvoiceItemRequest> invoiceItems;

        @JsonProperty("total_amount")
        @NotNull(message = "INVOICE_TOTAL_NOT_NULL")
        @Min(value = 0, message = "INVOICE_TOTAL_INVALID")
        Double TotalAmount;

        @JsonProperty("cinema_id")
        @NotNull(message = "CINEMA_NOT_NULL")
        @Min(value = 1, message = "CINEMA_VALID")
        Long cinemaId;
}
