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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Slf4j
public class CommentService implements ICommentService {
    UserRepository userRepository;
    MovieRepository movieRepository;
    CommentRepository commentRepository;
    CommentMapper commentMapper;

    @Override
    @PreAuthorize("hasRole('USER') or hasAuthority('MANAGE_SEAT')")
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
    @PostAuthorize("returnObject.id == authentication.id or hasRole('ADMIN')")
    public Page<CommentResponse> getMyComment(Long movie, int page, int limit) {
        User user = getMyInfoLogin();

        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        return commentRepository.findByMovieIdAndUserId(movie, user.getId(), pageable)
                .map(commentMapper::toCommentResponse);
    }

    @Override
    @PostAuthorize("returnObject.userId.toString() == authentication.principal.getClaimAsString('id')")
    public CommentResponse updateComment(CommentRequest request, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));

        return commentMapper.toCommentResponse(commentRepository.save(comment));
    }

    @Override
    @PreAuthorize("@securityService.isCommentOwner(#id, authentication) or hasRole('ADMIN')")
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    @PostAuthorize("returnObject.email == authentication.name")
    private User getMyInfoLogin(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
