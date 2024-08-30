package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketTypeRequest {
    String name;

    @JsonProperty("ticket_price")
    double ticketPrice;

    String description;

    float discount ;

    String status;
}
