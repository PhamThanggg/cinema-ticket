package com.example.cinematicket.dtos.requests.seat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatReservationRequest {
    Long userId;

    Set<Long> seatIds;

    Long scheduleId;

    int status;

    @JsonProperty("reservation_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime reservationTime;

    @JsonProperty("expiry_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime expiryTime;
}
