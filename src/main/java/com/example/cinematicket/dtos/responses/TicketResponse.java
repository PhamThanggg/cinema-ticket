package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.CinemaSeat;
import com.example.cinematicket.entities.Schedule;
import com.example.cinematicket.entities.TicketType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
    Long id;

    ScheduleResponse schedule;

    CinemaSeatResponse cinemaSeat;

    TicketTypeResponse ticketType;

    InvoiceResponse invoiceResponse;

}
