package com.example.cinematicket.dtos.requests;


import com.example.cinematicket.exceptions.ValidDoubleMax;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleRequest {
    @JsonProperty("id_cinema_room")
    @NotNull(message = "CINEMA_ROOM_NOT_NULL")
    @Min(value = 1, message = "CINEMA_ROOM_VALID")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long cinemaRoomId;

    @JsonProperty("id_movie")
    @NotNull(message = "MOVIE_NOT_NULL")
    @Min(value = 1, message = "MOVIE_VALID")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long movieId;

    @JsonProperty("screening_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Screening Date is required")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "DATE_VALID")
    LocalDate screeningDate;

    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "TIME_START_NOT_NULL")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "DATE_VALID")
    LocalDateTime startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "TIME_END_NOT_NULL")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "DATE_VALID")
    LocalDateTime endTime;

    @NotNull(message = "PRICE_NOT_NULL")
    @Min(value = 1, message = "PRICE_INVALID")
    @ValidDoubleMax(message = "VALUE_TOO_LARGE")
    double price;

    int status;
}
