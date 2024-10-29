package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatBookedResponse;
import com.example.cinematicket.dtos.responses.schedule.ScheduleBookedResponse;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
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

    CinemaSeatBookedResponse cinemaSeat;

    Long ticketTypeId;

    Long invoiceId;

}
