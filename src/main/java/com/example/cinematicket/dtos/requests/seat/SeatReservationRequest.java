package com.example.cinematicket.dtos.requests.seat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatReservationRequest {
    @NotNull(message = "USER_ID_NOT_NULL")
    @Positive(message = "ID_MIN")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long userId;

    @NotNull(message = "SEAT_ID_NOT_NULL")
    @NotEmpty(message = "ID_MIN")
    Set<Long> seatIds;

    @NotNull(message = "SCHEDULE_ID_NOT_NULL")
    @Positive(message = "ID_MIN")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long scheduleId;

    @Min(value = 0, message = "STATUS_LENGTH")
    @Max(value = 1, message = "STATUS_LENGTH")
    @NotNull(message = "STATUS_NOT_NULL")
    int status;

    @NotNull(message = "TIME_RESERVATION_NOT_NULL")
    @JsonProperty("reservation_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime reservationTime;

    @NotNull(message = "TIME_EXPIRY_NOT_NULL")
    @JsonProperty("expiry_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime expiryTime;
}
