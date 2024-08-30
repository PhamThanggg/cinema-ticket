package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.Ticket;
import com.example.cinematicket.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    Double TotalAmount;

    @JsonProperty("reservation_time")
    LocalDateTime reservationTime;

    @JsonProperty("payment_expiration_time")
    LocalDateTime paymentExpirationTime;

    @JsonProperty("payment_time")
    LocalDateTime paymentTime;

    @JsonProperty("amount_paid")
    Double amountPaid;

    String status;
}
