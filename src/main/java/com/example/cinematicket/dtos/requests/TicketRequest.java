package com.example.cinematicket.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketRequest {
    @NotNull(message = "SCHEDULE_NOT_NULL")
    @Min(value = 1, message = "SCHEDULE_INVALID")
    Long scheduleID;

    @NotNull(message = "CINEMA_SEAT_BLANK")
    @Min(value = 1, message = "CINEMA_SEAT_VALID")
    Long cinemaSeatId;

    @NotNull(message = "TICKET_TYPE_NOT_NULL")
    @Min(value = 1, message = "TICKET_TYPE_INVALID")
    Long ticketTypeId;
}
