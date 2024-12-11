package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.dtos.responses.item.InvoiceItemBookedResponse;
import com.example.cinematicket.dtos.responses.schedule.ScheduleBookedResponse;
import com.example.cinematicket.entities.InvoiceItem;
import com.example.cinematicket.entities.Ticket;
import jakarta.persistence.OneToMany;
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

    ScheduleBookedResponse schedule;

    PromotionResponse promotion;

    List<TicketResponse> tickets;

    List<InvoiceItemBookedResponse> invoiceItems;

    Double totalAmount;

    LocalDateTime reservationTime;

    LocalDateTime paymentExpirationTime;

    LocalDateTime paymentTime;

    Double amountPaid;

    int status;

    Integer star;

}
