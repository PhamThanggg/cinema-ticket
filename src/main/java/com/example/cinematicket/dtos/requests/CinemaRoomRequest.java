package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.Cinema;
import com.example.cinematicket.entities.RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "ROOM_NAME_NOT_BLANK")
    @Size(min = 2,max = 60, message = "ROOM_NAME_VALID")
    String name;

    String status;

    @JsonProperty("id_cinema")
    @NotNull(message = "CINEMA_NOT_NULL")
    @Min(value = 1, message = "CINEMA_VALID")
    Long cinemaId;

    @JsonProperty("id_room_type")
    @NotNull(message = "")
    @Min(value = 1, message = "ROOM_TYPE_VALID")
    Long roomTypeId;
}
