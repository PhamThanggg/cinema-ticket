package com.example.cinematicket.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "CINEMA_SEAT_NAME_BLANK")
    @Size(min = 2,max = 30, message = "CINEMA_SEAT_NAME_VALID")
    String name;

    @JsonProperty("id_seat_type")
    @NotNull(message = "SEAT_TYPE_NOT_NULL")
    @Min(value = 1, message = "SEAT_TYPE_VALID")
    Long seatTypeId;

    @JsonProperty("id_cinema_room")
    @NotNull(message = "CINEMA_ROOM_NOT_NULL")
    @Min(value = 1, message = "CINEMA_ROOM_VALID")
    Long cinemaRoomId;

    @NotBlank(message = "ROW_NOT_NULL")
    @Size(min = 1,max = 30, message = "ROW_VALID")
    @JsonProperty("row_seat")
    String row;

    @NotBlank(message = "COLUM_NOT_NULL")
    @Size(min = 1,max = 30, message = "COLUM_VALID")
    @JsonProperty("column_seat")
    String colum;

    int status;
}
