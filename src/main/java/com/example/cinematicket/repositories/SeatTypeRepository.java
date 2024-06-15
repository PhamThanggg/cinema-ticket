package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
}
