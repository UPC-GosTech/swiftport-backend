package com.gostech.swiftportbackend.iam.domain.services;

import com.gostech.swiftportbackend.iam.domain.model.entities.Role;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetAllRolesQuery;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Role query service interface
 * This interface defines the contract for role query operations
 */
public interface RoleQueryService {
    
    /**
     * Handle get all roles query
     * @param query the get all roles query
     * @return list of all roles
     */
    List<Role> handle(GetAllRolesQuery query);
    
    /**
     * Handle get role by name query
     * @param query the get role by name query
     * @return optional role if found
     */
    Optional<Role> handle(GetRoleByNameQuery query);
} 