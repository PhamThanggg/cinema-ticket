package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByCinemaSeatIdInAndInvoiceScheduleId(Set<Long> cinemaSeatIds, Long scheduleId);
}