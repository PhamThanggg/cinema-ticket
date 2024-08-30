package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.UserCreationRequest;
import com.example.cinematicket.dtos.requests.UserUpdateRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.UserResponse;
import com.example.cinematicket.services.user.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("register successfully")
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<List<UserResponse>> getMyInfo() {
        return null;
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
    public ApiResponse<List<UserResponse>> searchUser(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<UserResponse> userResponses = userService.searchUsers(name, page, limit).getContent();
        int totalPages = userService.getAllUsers(page, limit).getTotalPages();
        return ApiResponse.<List<UserResponse>>builder()
                .message("TotalPages = " + totalPages)
                .result(userResponses)
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
    public ApiResponse<UserResponse> updateUser(@PathVariable("userId") Long id, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("update successfully")
                .result(userService.updateUser(id, request))
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
