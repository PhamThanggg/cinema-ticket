package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRoomResponse {
    Long id;

    String name;

    String status;

    Cinema cinema;

    RoomType roomType;
}
