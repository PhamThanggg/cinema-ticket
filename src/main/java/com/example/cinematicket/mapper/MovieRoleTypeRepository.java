package com.example.cinematicket.mapper;

import com.example.cinematicket.entities.MovieRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRoleTypeRepository extends JpaRepository<MovieRoleType, Long> {

}
