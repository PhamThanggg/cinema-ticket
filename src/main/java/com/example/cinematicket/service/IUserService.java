package com.example.cinematicket.service;

import com.example.cinematicket.dto.request.UserCreationRequest;
import com.example.cinematicket.dto.request.UserUpdateRequest;
import com.example.cinematicket.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    String login(String email, String password);

    UserResponse getMyInfo();

    Page<UserResponse> getAllUsers(int page, int limit);

    //List<UserResponse> getAllUsers();

    Page<UserResponse> searchUsers(String search, int page, int limit);

    Long getCountUsers();

    UserResponse updateUser(Long id,UserUpdateRequest request);
    void deleteUser(Long userId);

}
