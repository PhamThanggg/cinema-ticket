package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.Ticket;
import com.example.cinematicket.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceRequest {
    @JsonProperty("total_amount")
    @NotNull(message = "INVOICE_TOTAL_NOT_NULL")
    @Positive(message = "INVOICE_TOTAL_INVALID")
    Double TotalAmount;

    @JsonProperty("reservation_time")
    LocalDateTime reservationTime;

    @JsonProperty("payment_expiration_time")
    LocalDateTime paymentExpirationTime;

    @JsonProperty("payment_time")
    LocalDateTime paymentTime;

    @JsonProperty("amount_paid")
    Double amountPaid;

    int status;
}
