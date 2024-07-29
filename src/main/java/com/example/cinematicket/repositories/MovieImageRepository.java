package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
    List<MovieImage> findByMovieId(Long id);

    Set<MovieImage> findByIdIn(Set<String> id);

    void deleteByIdIn(Set<Long> id);
}
