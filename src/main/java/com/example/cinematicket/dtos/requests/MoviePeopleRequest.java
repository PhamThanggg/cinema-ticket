package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.MovieRoleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoviePeopleRequest {
    @JsonProperty("id_role_type")
    @NotNull(message = "ROLE_TYPE_NOT_NULL")
    @Min(value = 1, message = "ROLE_TYPE_INVALID")
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long idRoleType;

    @NotBlank(message = "MOVIE_PP_NOT_BLANK")
    @Size(min = 2, max = 60, message = "MOVIE_PP_INVALID")
    String name;

    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "DATE_VALID")
    @Past(message = "DATE_VALID_PAST")
    LocalDate dateOfBirth;

    String nationality;

    String description;

    String image;
}
