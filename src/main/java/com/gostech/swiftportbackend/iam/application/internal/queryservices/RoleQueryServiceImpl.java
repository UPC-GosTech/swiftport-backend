package com.gostech.swiftportbackend.iam.application.internal.queryservices;

import com.gostech.swiftportbackend.iam.domain.model.entities.Role;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetAllRolesQuery;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetRoleByNameQuery;
import com.gostech.swiftportbackend.iam.domain.services.RoleQueryService;
import com.gostech.swiftportbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Role query service implementation
 * This service handles role query operations
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    
    private final RoleRepository roleRepository;
    
    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }
    
    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
} 