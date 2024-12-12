package com.example.cinematicket.dtos.requests.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Min(value = 0, message = "STATUS_LENGTH")
    @Max(value = 1, message = "STATUS_LENGTH")
    int status;
}
