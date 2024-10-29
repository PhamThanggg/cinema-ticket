package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.dtos.responses.cinemaSeat.CinemaSeatResponse;
import com.example.cinematicket.dtos.responses.schedule.ScheduleResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTicketResponse {
    Long id;

    ScheduleResponse schedule;

    CinemaSeatResponse cinemaSeat;

    TicketTypeResponse ticketType;
}
