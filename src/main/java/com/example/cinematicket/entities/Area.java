package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "areas")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    Long id;

    @Column(name = "area_name")
    String areaName;
}
