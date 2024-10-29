package com.example.cinematicket.dtos.requests.seat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatAutoRequest {
    @JsonProperty("id_seat_type")
    @NotNull(message = "SEAT_TYPE_NOT_NULL")
    @Min(value = 1, message = "SEAT_TYPE_VALID")
    Long seatTypeId;

    @JsonProperty("id_cinema_room")
    @NotNull(message = "CINEMA_ROOM_NOT_NULL")
    @Min(value = 1, message = "CINEMA_ROOM_VALID")
    Long cinemaRoomId;

    @Min(value = 1, message = "ROW_MIN")
    @Max(value = 30, message = "ROW_MAX")
    @JsonProperty("row_seat")
    int row = 1;

    @Min(value = 1, message = "COLUMN_MIN")
    @Max(value = 30, message = "COLUMN_MAX")
    @JsonProperty("column_seat")
    int colum = 1;
}
