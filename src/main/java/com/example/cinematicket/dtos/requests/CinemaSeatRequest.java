package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaSeatRequest {
    @JsonProperty("name_seat")
    String name;

    @JsonProperty("id_seat_type")
    Long seatTypeId;

    @JsonProperty("id_cinema_room")
    Long cinemaRoomId;

    @JsonProperty("row_seat")
    String row;

    @JsonProperty("column_seat")
    String colum;
    String status;
}
