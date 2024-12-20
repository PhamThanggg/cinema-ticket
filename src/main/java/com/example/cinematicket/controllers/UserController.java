package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.UserCreationRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.requests.user.UserChangePasswordRequest;
import com.example.cinematicket.dtos.requests.user.UserUpdateRoleRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.dtos.responses.UserResponse;
import com.example.cinematicket.services.user.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(
            @RequestBody @Valid UserCreationRequest request
    ) {
        return ApiResponse.<UserResponse>builder()
                .message("register successfully")
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/myInfo")
    public UserResponse getMyInfo() {
        return userService.getMyInfo();
    }

    @GetMapping("")
    public ApiResponse<List<UserResponse>> getAllUser(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<UserResponse> userResponses = userService.getAllUsers(page, limit).getContent();
        int totalPages = userService.getAllUsers(page, limit).getTotalPages();
        return ApiResponse.<List<UserResponse>>builder()
                .message("TotalPages = " + totalPages)
                .result(userResponses)
                .build();
    }

    @GetMapping("/search")
    public PageResponse<List<UserResponse>> searchUser(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "roleId", required = false) Long roleId,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        Page<UserResponse> userResponses = userService.searchUsers(name,email, roleId, page, limit);
        return PageResponse.<List<UserResponse>>builder()
                .currentPage(userResponses.getNumber())
                .totalPages(userResponses.getTotalPages())
                .totalElements(userResponses.getTotalElements())
                .pageSize(userResponses.getSize())
                .result(userResponses.getContent())
                .build();
    }

    @GetMapping("/count_user")
    public ApiResponse<Long> getTotalUser(
    ) {
        return ApiResponse.<Long>builder()
                .result(userService.getCountUsers())
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable("userId") Long id,
            @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("update successfully")
                .result(userService.updateUser(id, request))
                .build();
    }

    @PutMapping("/change-pass")
    public ApiResponse<UserResponse> updatePasswordUser(
            @RequestBody @Valid UserChangePasswordRequest request) {
        var context = SecurityContextHolder.getContext();
        Jwt jwt = (Jwt) context.getAuthentication().getPrincipal();
        Long id = Long.parseLong(jwt.getClaimAsString("id"));

        return ApiResponse.<UserResponse>builder()
                .message("update successfully")
                .result(userService.updatePasswordUser(request, id))
                .build();
    }

    @PutMapping("/addRole/{userId}")
    public ApiResponse<UserResponse> updateUserRole(
            @PathVariable("userId") Long id,
            @RequestBody @Valid UserUpdateRoleRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("Update successfully")
                .result(userService.updateRole(id, request.getRoleIds()))
                .build();
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .message("Delete successfully")
                .build();
    }
}
