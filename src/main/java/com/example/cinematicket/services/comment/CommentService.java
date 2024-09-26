package com.example.cinematicket.services.comment;

import com.example.cinematicket.dtos.requests.CommentRequest;
import com.example.cinematicket.dtos.responses.CommentResponse;
import com.example.cinematicket.entities.Comment;
import com.example.cinematicket.entities.Movie;
import com.example.cinematicket.entities.User;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.CommentMapper;
import com.example.cinematicket.repositories.CommentRepository;
import com.example.cinematicket.repositories.MovieRepository;
import com.example.cinematicket.repositories.UserRepository;
import com.example.cinematicket.services.comment.ICommentService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class CommentService implements ICommentService {
    UserRepository userRepository;
    MovieRepository movieRepository;
    CommentRepository commentRepository;
    CommentMapper commentMapper;

    @Override
    public CommentResponse createComment(CommentRequest request) {
        User user = getMyInfoLogin();

        Movie movie = movieRepository.findById(request.getIdMovie())
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        Comment comment = commentMapper.toComment(request);
        comment.setUser(user);
        comment.setMovie(movie);
        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    @Override
    public Page<CommentResponse> getComment(Long movie, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        return commentRepository.findByMovieId(movie, pageable).map(commentMapper::toCommentResponse);
    }

    @Override
    public Page<CommentResponse> getMyComment(Long movie, int page, int limit) {
        User user = getMyInfoLogin();

        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        return commentRepository.findByMovieIdAndUserId(movie, user.getId(), pageable)
                .map(commentMapper::toCommentResponse);
    }

    @Override
    public CommentResponse updateComment(CommentRequest request, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        User user = getMyInfoLogin();
        if(!user.getId().equals(comment.getUser().getId())){
            throw new RuntimeException("You cannot update another user comment");
        }

        commentMapper.updateComment(comment, request);
        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        User user = getMyInfoLogin();
        if(!user.getId().equals(comment.getUser().getId())){
            throw new RuntimeException("You cannot delete another user comment");
        }
        commentRepository.deleteById(id);
    }

    private User getMyInfoLogin(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
