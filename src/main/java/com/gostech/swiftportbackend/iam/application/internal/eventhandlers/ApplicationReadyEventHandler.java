package com.gostech.swiftportbackend.iam.application.internal.eventhandlers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.gostech.swiftportbackend.iam.domain.model.commands.SeedRolesCommand;
import com.gostech.swiftportbackend.iam.domain.model.commands.SignUpCommand;
import com.gostech.swiftportbackend.iam.domain.services.RoleCommandService;
import com.gostech.swiftportbackend.iam.domain.services.UserCommandService;
import com.gostech.swiftportbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;

/**
 * Application Ready Event Handler
 * This handler is responsible for seeding initial data when the application starts
 */
@Service
public class ApplicationReadyEventHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    
    private final RoleCommandService roleCommandService;
    private final UserCommandService userCommandService;
    private final UserRepository userRepository;
    
    public ApplicationReadyEventHandler(RoleCommandService roleCommandService, 
                                      UserCommandService userCommandService,
                                      UserRepository userRepository) {
        this.roleCommandService = roleCommandService;
        this.userCommandService = userCommandService;
        this.userRepository = userRepository;
    }
    
    /**
     * Handle application ready event
     * Seeds initial roles and default admin user when the application is ready
     * @param event the application ready event
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        LOGGER.info("Application ready event received. Seeding initial data...");
        
        try {
            // Set default tenant context for seeding
            TenantContext.setCurrentTenantId(1L);
            
            // Seed roles
            roleCommandService.handle(new SeedRolesCommand());
            LOGGER.info("Initial roles seeded successfully");
            
            // Seed default admin user if it doesn't exist
            if (!userRepository.existsByUsername("admin")) {
                SignUpCommand adminCommand = new SignUpCommand(
                    "admin",
                    "admin123",
                    "admin@swiftport.com",
                    "System",
                    "Administrator",
                    List.of("ROLE_ADMIN")
                );
                
                userCommandService.handle(adminCommand);
                LOGGER.info("Default admin user created successfully");
            } else {
                LOGGER.info("Default admin user already exists, skipping creation");
            }
            
        } catch (Exception e) {
            LOGGER.error("Error seeding initial data", e);
        } finally {
            // Clear tenant context
            TenantContext.clear();
        }
    }
} 