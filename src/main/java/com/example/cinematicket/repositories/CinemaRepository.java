package com.example.cinematicket.repositories;

import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.entities.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    boolean existsByName(String name);

    Page<Cinema> findByNameContaining(String name, PageRequest request);
}
