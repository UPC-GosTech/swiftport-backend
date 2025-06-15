package com.gostech.swiftportbackend.iam.domain.services;

import com.gostech.swiftportbackend.iam.domain.model.commands.SeedRolesCommand;

/**
 * Role command service interface
 * This interface defines the contract for role command operations
 */
public interface RoleCommandService {
    
    /**
     * Handle seed roles command
     * @param command the seed roles command
     */
    void handle(SeedRolesCommand command);
} 