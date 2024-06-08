package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    boolean existsByName(String name);
}
