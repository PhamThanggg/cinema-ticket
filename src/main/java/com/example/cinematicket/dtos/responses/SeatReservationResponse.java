package com.example.cinematicket.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatReservationResponse {
    Long id;

    Long userId;

    int status;

    @JsonProperty("reservation_time")
    LocalDateTime reservationTime;

    @JsonProperty("expiry_time")
    LocalDateTime expiryTime;
}
