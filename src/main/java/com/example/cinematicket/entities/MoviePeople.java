package com.example.cinematicket.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Table(name="movie_people")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoviePeople {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_movie_people")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_role_type")
    MovieRoleType roleType;

    String name;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    String nationality;

    String description;

    String image;
}
