package com.example.cinematicket.services.user;

import com.example.cinematicket.dtos.requests.UserCreationRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.responses.UserResponse;
import org.springframework.data.domain.Page;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    UserResponse getMyInfo();

    Page<UserResponse> getAllUsers(int page, int limit);

    //List<UserResponse> getAllUsers();

    Page<UserResponse> searchUsers(String search, int page, int limit);

    Long getCountUsers();

    UserResponse updateUser(Long id,UserUpdateRequest request);
    void deleteUser(Long userId);

}
