package com.example.cinematicket.dtos.requests.cinemaRoom;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRoomUpdateRequest {
    @JsonProperty("name_cinema_room")
    @NotBlank(message = "ROOM_NAME_NOT_BLANK")
    @Size(min = 2,max = 60, message = "ROOM_NAME_VALID")
    String name;

    @Min(value = 0, message = "STATUS_LENGTH")
    @Max(value = 2, message = "STATUS_LENGTH")
    @NotNull(message = "STATUS_NOT_NULL")
    int status;

    @JsonProperty("id_cinema")
    @NotNull(message = "CINEMA_NOT_NULL")
    @Min(value = 1, message = "CINEMA_VALID")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long cinemaId;

    @JsonProperty("id_room_type")
    @NotNull(message = "ROOM_TYPE_NOT_NULL")
    @Min(value = 1, message = "ROOM_TYPE_VALID")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long roomTypeId;
}
