package com.gostech.swiftportbackend.iam.application.internal.commandservices;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gostech.swiftportbackend.iam.application.internal.outboundservices.hashing.HashingService;
import com.gostech.swiftportbackend.iam.application.internal.outboundservices.tokens.TokenService;
import com.gostech.swiftportbackend.iam.domain.model.aggregates.User;
import com.gostech.swiftportbackend.iam.domain.model.commands.SignUpCommand;
import com.gostech.swiftportbackend.iam.domain.model.entities.Role;
import com.gostech.swiftportbackend.iam.domain.model.valueobjects.Roles;
import com.gostech.swiftportbackend.iam.domain.services.UserCommandService;
import com.gostech.swiftportbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.gostech.swiftportbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;

/**
 * User command service implementation
 * This service handles user command operations with multitenancy support
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserCommandServiceImpl.class);
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    
    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                                 HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }
    
    @Override
    public ImmutablePair<User, String> handle(SignUpCommand command) {
        logger.info("Starting sign-up process for username: {}", command.username());
        
        // Check if user already exists
        if (userRepository.existsByUsername(command.username())) {
            logger.error("Username already exists: {}", command.username());
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(command.email())) {
            logger.error("Email already exists: {}", command.email());
            throw new RuntimeException("Email already exists");
        }
        
        // Get roles
        List<Role> roles = new ArrayList<>();
        logger.info("Processing roles: {}", command.roles());
        
        if (command.roles() != null && !command.roles().isEmpty()) {
            for (String roleName : command.roles()) {
                try {
                    logger.debug("Processing role: {}", roleName);
                    Roles roleEnum = Roles.valueOf(roleName);
                    Role role = roleRepository.findByName(roleEnum)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                    roles.add(role);
                    logger.debug("Role added successfully: {}", roleName);
                } catch (IllegalArgumentException e) {
                    logger.error("Invalid role: {}", roleName, e);
                    throw new RuntimeException("Invalid role: " + roleName);
                }
            }
        } else {
            // Default role
            logger.info("No roles specified, using default role");
            Role defaultRole = roleRepository.findByName(Roles.ROLE_LOGISTIC_OPERATOR)
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            roles.add(defaultRole);
        }
        
        // Create user with current tenant
        Long tenantId = TenantContext.getCurrentTenantId();
        logger.info("Current tenant ID: {}", tenantId);
        
        String hashedPassword = hashingService.encode(command.password());
        logger.debug("Password hashed successfully");
        
        User user = new User(
                command.username(),
                hashedPassword,
                command.email(),
                command.firstName(),
                command.lastName(),
                tenantId,
                roles
        );
        
        logger.info("User entity created, attempting to save...");
        
        // Save user
        User savedUser = userRepository.save(user);
        logger.info("User saved successfully with ID: {}", savedUser.getId());
        
        // Generate token
        String token = tokenService.generateToken(savedUser.getUsername(), tenantId);
        logger.info("Token generated successfully for user: {}", savedUser.getUsername());
        
        return ImmutablePair.of(savedUser, token);
    }
} 