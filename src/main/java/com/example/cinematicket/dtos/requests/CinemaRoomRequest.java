package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRoomRequest {
    @JsonProperty("name_cinema_room")
    String name;

    String status;

    @JsonProperty("id_cinema")
    Long cinemaId;

    @JsonProperty("id_room_type")
    Long roomTypeId;
}
