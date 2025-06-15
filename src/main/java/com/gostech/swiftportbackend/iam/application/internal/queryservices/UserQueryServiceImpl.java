package com.gostech.swiftportbackend.iam.application.internal.queryservices;

import com.gostech.swiftportbackend.iam.application.internal.outboundservices.hashing.HashingService;
import com.gostech.swiftportbackend.iam.application.internal.outboundservices.tokens.TokenService;
import com.gostech.swiftportbackend.iam.domain.model.aggregates.User;
import com.gostech.swiftportbackend.iam.domain.model.commands.SignInCommand;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetAllUsersQuery;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetUserByIdQuery;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetUserByUsernameQuery;
import com.gostech.swiftportbackend.iam.domain.services.UserQueryService;
import com.gostech.swiftportbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User query service implementation
 * This service handles user query operations with multitenancy support
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    
    public UserQueryServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }
    
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        // Filter by current tenant
        Long tenantId = TenantContext.getCurrentTenantId();
        return userRepository.findAllByTenantId(tenantId);
    }
    
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        // Filter by current tenant
        Long tenantId = TenantContext.getCurrentTenantId();
        return userRepository.findByIdAndTenantId(query.userId(), tenantId);
    }
    
    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        // Filter by current tenant
        Long tenantId = TenantContext.getCurrentTenantId();
        return userRepository.findByUsernameAndTenantId(query.username(), tenantId);
    }
    
    @Override
    public ImmutablePair<User, String> handle(SignInCommand command) {
        // Find user by username and current tenant
        Long tenantId = TenantContext.getCurrentTenantId();
        User user = userRepository.findByUsernameAndTenantId(command.username(), tenantId)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        
        // Verify password
        if (!hashingService.matches(command.password(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        // Check if user is active
        if (!user.isActive()) {
            throw new RuntimeException("User account is disabled");
        }
        
        // Generate token
        String token = tokenService.generateToken(user.getUsername(), tenantId);
        
        return ImmutablePair.of(user, token);
    }
} 