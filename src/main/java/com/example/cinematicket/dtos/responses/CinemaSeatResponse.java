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
    Long seatTypeId;
    Long cinemaRoomId;
    String row;
    String colum;
    int status;
}
