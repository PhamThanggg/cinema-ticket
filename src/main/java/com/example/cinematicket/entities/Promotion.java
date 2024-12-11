package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name="promotion")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    String name;

    String description;

    int discount;

    int count;

    String promotionType;

    String discountType;

    Integer min;

    Integer max;

    LocalDate startDate;

    LocalDate endDate;
}
