package com.example.cinematicket.mapper;

import com.example.cinematicket.dtos.requests.RoleRequest;
import com.example.cinematicket.dtos.responses.RoleResponse;
import com.example.cinematicket.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

    void updateRole(@MappingTarget Role role, RoleRequest request);

}
