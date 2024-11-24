package com.example.cinematicket.dtos.responses.cinemaSeat;

import com.example.cinematicket.dtos.responses.SeatReservationResponse;
import com.example.cinematicket.entities.SeatType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaSeatResponse {
    Long id;
    String name;
    SeatType seatType;
    Long cinemaRoomId;
    String row;
    String colum;
    int status;
    SeatReservationResponse seatReservations;
}
