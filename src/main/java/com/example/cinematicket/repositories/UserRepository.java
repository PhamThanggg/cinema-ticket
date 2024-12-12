package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u " +
            "JOIN u.roles r " +
            "WHERE (:name IS NULL OR :name = '' OR u.fullName LIKE %:name%) " +
            "AND (:email IS NULL OR :email = '' OR u.email LIKE %:email%) " +
            "AND (:roleId IS NULL OR r.id = :roleId)")
    Page<User> findByFullNameEmail(@Param("name") String name,
                                   @Param("email") String email,
                                   @Param("roleId") Long roleId,
                                   PageRequest pageRequest);
}
