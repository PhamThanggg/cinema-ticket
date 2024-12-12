package com.example.cinematicket.services.user;

import com.example.cinematicket.dtos.requests.UserCreationRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.responses.UserResponse;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    UserResponse getMyInfo();

    Page<UserResponse> getAllUsers(int page, int limit);

    Page<UserResponse> searchUsers(String name, String email, Long roleId, int page, int limit);

    Long getCountUsers();

    UserResponse updateUser(Long id,UserUpdateRequest request);

    UserResponse updateRole(Long id, Set<Long> roleIds);

    void deleteUser(Long userId);

}
