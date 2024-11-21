package com.example.cinematicket.dtos.responses;

import com.example.cinematicket.entities.MovieRoleType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoviePeopleResponse {
    Long id;

    MovieRoleType roleType;

    String name;

    LocalDate dateOfBirth;

    String nationality;

    String description;

    String image;
}
