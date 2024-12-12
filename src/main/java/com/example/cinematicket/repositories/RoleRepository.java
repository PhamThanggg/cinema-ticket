package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    boolean existsByName(String name);

    Set<Role> findByIdIn(Set<Long> ids);

    @Query("SELECT r FROM Role r " +
            "WHERE (:name IS NULL OR :name = '' OR r.name LIKE %:name%)")
    Page<Role> searchName(@Param("name") String name,
                          PageRequest pageRequest);
}
