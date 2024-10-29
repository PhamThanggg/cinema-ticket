package com.example.cinematicket.dtos.responses.item;

import com.example.cinematicket.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponse {
    Long id;

    String name;

    String imageUrl;

    String description;

    double price;
}
