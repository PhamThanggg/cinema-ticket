package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);
    Set<Genre> findByIdIn(Set<Long> ids);

    @Query("SELECT m FROM Genre m WHERE " +
            "(:name IS NULL OR :name = '' OR m.name LIKE %:name%)")
    Page<Genre> findGenreOrName(@Param("name") String name,
                                 Pageable pageable);
}
