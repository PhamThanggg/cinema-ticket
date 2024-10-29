package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name="items")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    Long id;

    @Column(name = "id_cinema")
    Long idCinema;

    @Column(name = "item_name")
    String name;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "item_description")
    String description;

    @Column(name = "item_price")
    double price;
}
