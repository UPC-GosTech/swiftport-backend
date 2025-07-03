package com.gostech.swiftportbackend.iam.interfaces.rest.transform;

import java.util.List;
import java.util.stream.Collectors;

import com.gostech.swiftportbackend.iam.domain.model.aggregates.User;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.AuthenticatedUserResource;

/**
 * Assembler to transform from User entity and token to AuthenticatedUserResource
 */
public class AuthenticatedUserResourceFromEntityAssembler {
    
    /**
     * Transform from User entity and token to AuthenticatedUserResource
     * @param entity the User entity
     * @param token the authentication token
     * @return the AuthenticatedUserResource
     */
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        List<String> roleNames = entity.getRoles().stream()
                .map(role -> role.getStringName())
                .collect(Collectors.toList());
        
        return new AuthenticatedUserResource(
                entity.getId(),
                entity.getUsername(),
                token,
                roleNames
        );
    }
} 