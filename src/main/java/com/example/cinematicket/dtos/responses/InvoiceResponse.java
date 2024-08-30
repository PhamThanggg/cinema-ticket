package com.example.cinematicket.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceResponse {
    Long id;

    UserResponse user;

    List<CreateTicketRequest> tickets;

    Double TotalAmount;

    LocalDateTime reservationTime;

    LocalDateTime paymentExpirationTime;

    LocalDateTime paymentTime;

    Double amountPaid;

    String status;

}
