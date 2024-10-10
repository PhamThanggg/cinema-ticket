package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.CommentRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.CommentResponse;
import com.example.cinematicket.services.comment.CommentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/movie/comment")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CommentController {
    CommentService commentService;

    @PostMapping("")
    public ApiResponse<CommentResponse> create(
            @RequestBody @Valid CommentRequest request
            ){
        return ApiResponse.<CommentResponse>builder()
                .message("Create successfully")
                .result(commentService.createComment(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<CommentResponse>> getAllComment(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("movieId") Long movieId
    ){
        List<CommentResponse> commentResponses = commentService
                .getComment(movieId, page, limit)
                .getContent();
        int totalComment = commentResponses.size();
        return ApiResponse.<List<CommentResponse>>builder()
                .message("Total comment: " + totalComment)
                .result(commentResponses)
                .build();
    }

    @GetMapping("/myComment")
    public ApiResponse<List<CommentResponse>> getCommentById(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            @RequestParam("movieId") Long movieId
    ){
        List<CommentResponse> commentResponses = commentService
                .getMyComment(movieId, page, limit)
                .getContent();
        int totalComment = commentResponses.size();
        return ApiResponse.<List<CommentResponse>>builder()
                .message("Total comment: " + totalComment)
                .result(commentResponses)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CommentResponse> updateCommentById(
            @PathVariable("id") Long id,
            @RequestBody @Valid CommentRequest request
    ){
        CommentResponse commentResponse = commentService.updateComment(request, id);

        return ApiResponse.<CommentResponse>builder()
                .result(commentResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCommentById(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ApiResponse.<String>builder()
                .result("Comment has been deleted")
                .build();
    }
}
