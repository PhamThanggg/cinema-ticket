package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.CinemaRoom;
import com.example.cinematicket.entities.SeatType;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    SeatType seatType;
    CinemaRoom cinemaRoom;
    String row;
    String colum;
    String status;
}
