package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);

    List<Item> findByIdCinema(Long cinemaId);

    List<Item> findByIdInAndIdCinema(Set<Long> ids, Long cinemaId);
}
