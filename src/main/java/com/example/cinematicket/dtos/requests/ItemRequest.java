package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "CINEMA_NOT_NULL")
    @Min(value = 1, message = "CINEMA_VALID")
    Long idCinema;

    @JsonProperty("item_name")
    @NotBlank(message = "ITEM_NOT_NULL")
    @Size(min = 2,max = 100, message = "ITEM_INVALID")
    String name;

    @JsonProperty("image_url")
    String imageUrl;

    @JsonProperty("item_description")
    String description;

    @JsonProperty("item_price")
    @NotNull(message = "PRICE_NOT_NULL")
    @Min(value = 0, message = "PRICE_INVALID")
    double price;
}
