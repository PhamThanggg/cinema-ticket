package com.example.cinematicket.repositories;

import com.example.cinematicket.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByMovieId(Long movieId, Pageable pageable);

    Page<Comment> findByMovieIdAndUserId(Long movieId, Long userId, Pageable pageable);
}
