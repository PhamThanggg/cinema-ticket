package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    boolean existsByName(String name);

}

