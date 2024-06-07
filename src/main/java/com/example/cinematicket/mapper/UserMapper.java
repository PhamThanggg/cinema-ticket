package com.example.cinematicket.mapper;

import com.example.cinematicket.dto.request.UserCreationRequest;
import com.example.cinematicket.dto.request.UserUpdateRequest;
import com.example.cinematicket.dto.response.UserResponse;
import com.example.cinematicket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
