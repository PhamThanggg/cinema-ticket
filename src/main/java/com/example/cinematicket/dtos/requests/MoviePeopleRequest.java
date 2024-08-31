package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.MovieRoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoviePeopleRequest {
    @JsonProperty("id_role_type")
    Long idRoleType;

    String name;

    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth;

    String nationality;

    String description;

    String image;
}
