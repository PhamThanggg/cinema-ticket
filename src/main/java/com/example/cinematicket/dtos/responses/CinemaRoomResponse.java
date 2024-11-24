package com.example.cinematicket.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRoomResponse {
    Long id;

    String name;

    int status;

    LocalDate createdDate;

    Long roomTypeId;

    CinemaResponse cinema;
}
