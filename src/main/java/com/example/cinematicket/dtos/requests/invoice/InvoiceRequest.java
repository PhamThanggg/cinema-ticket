package com.example.cinematicket.dtos.requests.invoice;

import com.example.cinematicket.entities.Ticket;
import com.example.cinematicket.entities.User;
import com.example.cinematicket.exceptions.ValidDoubleMax;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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
    @ValidDoubleMax(message = "VALUE_TOO_LARGE")
    Double TotalAmount;

    @JsonProperty("reservation_time")
    LocalDateTime reservationTime;

    @JsonProperty("payment_expiration_time")
    LocalDateTime paymentExpirationTime;

    @JsonProperty("payment_time")
    LocalDateTime paymentTime;

    @JsonProperty("amount_paid")
    @Positive(message = "INVOICE_TOTAL_INVALID")
    @ValidDoubleMax(message = "VALUE_TOO_LARGE")
    Double amountPaid;

    @Min(value = 0, message = "STATUS_LENGTH")
    @Max(value = 1, message = "STATUS_LENGTH")
    int status;
}
