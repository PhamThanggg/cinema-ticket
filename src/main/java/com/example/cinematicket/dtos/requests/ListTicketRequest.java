package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListTicketRequest {
        Long scheduleID;

        List<Long> cinemaSeatId;

        Long ticketTypeId;

        Set<InvoiceItemRequest> invoiceItems;

        @JsonProperty("total_amount")
        @NotNull(message = "INVOICE_TOTAL_NOT_NULL")
        @Min(value = 0, message = "INVOICE_TOTAL_INVALID")
        Double TotalAmount;

        @JsonProperty("cinema_id")
        @NotNull(message = "CINEMA_NOT_NULL")
        @Min(value = 1, message = "CINEMA_VALID")
        Long cinemaId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime paymentExpirationTime;

        String promotionId;

        Integer star;
}
