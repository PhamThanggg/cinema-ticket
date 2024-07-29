package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);
    Set<Genre> findByIdIn(Set<Long> ids);
}
