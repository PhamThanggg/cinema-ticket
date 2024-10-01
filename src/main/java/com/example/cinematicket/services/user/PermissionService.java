package com.example.cinematicket.services.user;

import com.example.cinematicket.dtos.requests.PermissionRequest;
import com.example.cinematicket.dtos.responses.PermissionResponse;
import com.example.cinematicket.entities.Permission;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.PermissionMapper;
import com.example.cinematicket.repositories.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @PostAuthorize("hasRole('ADMIN'")
    public PermissionResponse create(PermissionRequest request) {
        if(permissionRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.PERMISSION_EXISTS);
        }

        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @PostAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @PostAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
