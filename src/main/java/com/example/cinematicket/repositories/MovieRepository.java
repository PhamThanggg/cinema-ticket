package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
