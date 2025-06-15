package com.gostech.swiftportbackend.iam.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.gostech.swiftportbackend.iam.domain.model.commands.SeedRolesCommand;
import com.gostech.swiftportbackend.iam.domain.services.RoleCommandService;

/**
 * Application Ready Event Handler
 * This handler is responsible for seeding initial data when the application starts
 */
@Service
public class ApplicationReadyEventHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    
    private final RoleCommandService roleCommandService;
    
    public ApplicationReadyEventHandler(RoleCommandService roleCommandService) {
        this.roleCommandService = roleCommandService;
    }
    
    /**
     * Handle application ready event
     * Seeds initial roles when the application is ready
     * @param event the application ready event
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        LOGGER.info("Application ready event received. Seeding initial data...");
        
        try {
            // Seed roles
            roleCommandService.handle(new SeedRolesCommand());
            LOGGER.info("Initial roles seeded successfully");
            
        } catch (Exception e) {
            LOGGER.error("Error seeding initial data", e);
        }
    }
} 