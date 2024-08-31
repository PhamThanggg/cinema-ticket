package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.MoviePeople;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface MoviePeopleRepository extends JpaRepository<MoviePeople, Long> {
    Set<MoviePeople> findByIdIn(Set<Long> ids);

    @Query("SELECT m FROM MoviePeople m WHERE " +
            "(:nameSearch IS NULL OR :nameSearch = '' OR m.name LIKE %:nameSearch%)")
    Page<MoviePeople> findName( @Param("nameSearch") String nameSearch, Pageable pageable);

    Boolean existsByIdAndNameAndDateOfBirth(Long Id, String name, LocalDate dateOfBirth);
}
