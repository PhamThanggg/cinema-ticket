package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name="ticket_types")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_price_id")
    Long id;

    String name;

    @Column(name = "ticket_price")
    double ticketPrice;

    String description;

    float discount ;

    String status;
}
