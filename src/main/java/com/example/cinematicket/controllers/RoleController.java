package com.example.cinematicket.controllers;

import com.example.cinematicket.dtos.requests.RoleRequest;
import com.example.cinematicket.dtos.responses.ApiResponse;
import com.example.cinematicket.dtos.responses.PageResponse;
import com.example.cinematicket.dtos.responses.RoleResponse;
import com.example.cinematicket.dtos.responses.UserResponse;
import com.example.cinematicket.services.user.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @GetMapping("/search")
    PageResponse<List<RoleResponse>> getRoleSearch(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        Page<RoleResponse> response = roleService.searchName(name, page, limit);
        return PageResponse.<List<RoleResponse>>builder()
                .currentPage(response.getNumber())
                .totalPages(response.getTotalPages())
                .totalElements(response.getTotalElements())
                .pageSize(response.getSize())
                .result(response.getContent())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<RoleResponse> update(
            @RequestBody RoleRequest request,
            @PathVariable Long id) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ApiResponse.<Void>builder().build();
    }
}
