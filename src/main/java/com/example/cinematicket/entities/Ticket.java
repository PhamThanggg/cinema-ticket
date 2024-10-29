package com.example.cinematicket.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name="tickets")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    Long id;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_cinema_seat")
    CinemaSeat cinemaSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket_type")
    TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_invoice")
    Invoice invoice;
}
