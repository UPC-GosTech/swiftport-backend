package com.gostech.swiftportbackend.iam.interfaces.rest.transform;

import com.gostech.swiftportbackend.iam.domain.model.entities.Role;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.RoleResource;

/**
 * Assembler to transform from Role entity to RoleResource
 */
public class RoleResourceFromEntityAssembler {
    
    /**
     * Transform from Role entity to RoleResource
     * @param entity the Role entity
     * @return the RoleResource
     */
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(
                entity.getId(),
                entity.getName().name()
        );
    }
} 