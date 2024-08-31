package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {
    @JsonProperty("id_cinema")
    Long idCinema;

    @JsonProperty("item_name")
    String name;

    @JsonProperty("item_description")
    String description;

    @JsonProperty("item_price")
    double price;
}
