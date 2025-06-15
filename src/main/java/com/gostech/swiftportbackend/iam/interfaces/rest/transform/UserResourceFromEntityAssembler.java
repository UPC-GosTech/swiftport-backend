package com.gostech.swiftportbackend.iam.interfaces.rest.transform;

import com.gostech.swiftportbackend.iam.domain.model.aggregates.User;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.UserResource;

import java.util.stream.Collectors;

/**
 * Assembler to transform from User entity to UserResource
 */
public class UserResourceFromEntityAssembler {
    
    /**
     * Transform from User entity to UserResource
     * @param entity the User entity
     * @return the UserResource
     */
    public static UserResource toResourceFromEntity(User entity) {
        var roleNames = entity.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
        
        return new UserResource(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getActive(),
                roleNames
        );
    }
} 