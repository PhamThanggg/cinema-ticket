package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.PermissionRequest;
import com.example.cinematicket.dtos.responses.PermissionResponse;
import com.example.cinematicket.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface    PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
