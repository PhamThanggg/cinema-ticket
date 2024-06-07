package com.example.cinematicket.repositoty;

import com.example.cinematicket.dto.response.UserResponse;
import com.example.cinematicket.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Page<User> findByFullNameContaining(String search, PageRequest pageRequest);
}
