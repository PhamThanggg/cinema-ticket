package com.example.cinematicket.services.comment;

import com.example.cinematicket.dtos.requests.CinemaRequest;
import com.example.cinematicket.dtos.requests.CommentRequest;
import com.example.cinematicket.dtos.responses.CinemaResponse;
import com.example.cinematicket.dtos.responses.CommentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICommentService {
    CommentResponse createComment(CommentRequest request);

    Page<CommentResponse> getComment(Long movie, int page, int limit);

    Page<CommentResponse> getMyComment(Long movie, int page, int limit);

    CommentResponse updateComment(CommentRequest request, Long movieId);

    void deleteComment(Long id);
}
