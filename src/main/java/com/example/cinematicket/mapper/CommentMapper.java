package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.CommentRequest;
import com.example.cinematicket.dtos.responses.CommentResponse;
import com.example.cinematicket.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequest request);

    @Mapping(source = "user.id", target = "userId")
    CommentResponse toCommentResponse(Comment comment);

    void updateComment(@MappingTarget Comment comment, CommentRequest request);
}
