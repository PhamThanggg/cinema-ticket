package com.example.cinematicket.dtos.responses;

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
public class InvoiceResponse {
    Long id;

    Long userId;

    Set<Long> ticketIds;

    Set<Long> itemIds;

    Double totalAmount;

    LocalDateTime reservationTime;

    LocalDateTime paymentExpirationTime;

    LocalDateTime paymentTime;

    Double amountPaid;

    String status;

}
