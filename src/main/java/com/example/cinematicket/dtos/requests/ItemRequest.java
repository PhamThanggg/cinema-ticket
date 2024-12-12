package com.example.cinematicket.dtos.requests;

import com.example.cinematicket.exceptions.ValidDoubleMax;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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
    @Max(value = Integer.MAX_VALUE, message = "VALUE_TOO_LARGE")
    Long idCinema;

    @JsonProperty("item_name")
    @NotBlank(message = "ITEM_NOT_NULL")
    @Size(min = 2,max = 100, message = "ITEM_INVALID")
    String name;

    @JsonProperty("image_url")
    @Size(min = 1,max = 255, message = "IMAGE_INVALID")
    String imageUrl;

    @JsonProperty("item_description")
    @Size(max = 500, message = "DESCRIPTION_INVALID")
    String description;

    @JsonProperty("item_price")
    @NotNull(message = "PRICE_NOT_NULL")
    @Min(value = 0, message = "PRICE_INVALID")
    @ValidDoubleMax(message = "VALUE_TOO_LARGE")
    double price;
}
