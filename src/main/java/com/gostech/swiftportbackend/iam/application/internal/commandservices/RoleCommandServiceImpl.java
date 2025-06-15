package com.gostech.swiftportbackend.iam.application.internal.commandservices;

import com.gostech.swiftportbackend.iam.domain.model.commands.SeedRolesCommand;
import com.gostech.swiftportbackend.iam.domain.model.entities.Role;
import com.gostech.swiftportbackend.iam.domain.model.valueobjects.Roles;
import com.gostech.swiftportbackend.iam.domain.services.RoleCommandService;
import com.gostech.swiftportbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Role command service implementation
 * This service handles role command operations
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    
    private final RoleRepository roleRepository;
    
    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values())
                .forEach(role -> {
                    if (!roleRepository.existsByName(role)) {
                        roleRepository.save(new Role(role));
                    }
                });
    }
} 