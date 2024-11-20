package com.example.cinematicket.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaResponse {
    Long id;
    Long areaId;
    String name;
    String address;
    int status;
    LocalDate createdDate;
}
