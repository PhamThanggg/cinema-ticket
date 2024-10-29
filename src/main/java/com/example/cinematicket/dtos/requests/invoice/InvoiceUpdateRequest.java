package com.example.cinematicket.dtos.requests.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class InvoiceUpdateRequest {
    @JsonProperty("payment_time")
    LocalDateTime paymentTime;

    @JsonProperty("amount_paid")
    Double amountPaid;

    int status;
}
