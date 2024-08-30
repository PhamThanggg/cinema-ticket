package com.example.cinematicket.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaSeatResponse {
    Long id;
    String name;
    SeatTypeResponse seatType;
    CinemaRoomResponse cinemaRoom;
    String row;
    String colum;
    String status;
}
