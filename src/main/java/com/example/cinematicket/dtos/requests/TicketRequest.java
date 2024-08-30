package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.dtos.responses.CinemaSeatResponse;
import com.example.cinematicket.dtos.responses.ScheduleResponse;
import com.example.cinematicket.dtos.responses.TicketTypeResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketRequest {
    Long scheduleID;

    Long cinemaSeatId;

    Long ticketTypeId;
}
