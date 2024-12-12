package com.example.cinematicket.services.user;

import com.example.cinematicket.dtos.requests.RoleRequest;
import com.example.cinematicket.dtos.responses.RoleResponse;
import com.example.cinematicket.entities.Role;
import com.example.cinematicket.exceptions.AppException;
import com.example.cinematicket.exceptions.ErrorCode;
import com.example.cinematicket.mapper.RoleMapper;
import com.example.cinematicket.repositories.PermissionRepository;
import com.example.cinematicket.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse create(RoleRequest request) {
        if(roleRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.ROLE_EXISTS);
        }

        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissionIds());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<RoleResponse> searchName(String name, int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        return roleRepository.searchName(name, pageRequest).map(roleMapper::toRoleResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse update(Long roleId, RoleRequest request) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new AppException(ErrorCode.ROLE_NOT_EXISTS)
        );

        if(role.getName().equals("ADMIN") || role.getName().equals("USER")){
            throw new RuntimeException("Bạn k thể sửa role này");
        }

        if(!role.getName().equals(request.getName())){
            if(roleRepository.existsByName(request.getName())){
                throw new AppException(ErrorCode.ROLE_EXISTS);
            }
        }

        roleMapper.updateRole(role, request);

        var permissions = permissionRepository.findAllById(request.getPermissionIds());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.ROLE_NOT_EXISTS)
        );

        if(role.getName().equals("ADMIN") || role.getName().equals("USER")){
            throw new RuntimeException("Bạn k thể xóa role này");
        }
        roleRepository.deleteById(id);
    }
}
