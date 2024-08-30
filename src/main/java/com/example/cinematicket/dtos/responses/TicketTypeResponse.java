package com.example.cinematicket.dtos.responses;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketTypeResponse {
    Long id;

    String name;

    double ticketPrice;

    String description;

    float discount ;

    String status;
}
