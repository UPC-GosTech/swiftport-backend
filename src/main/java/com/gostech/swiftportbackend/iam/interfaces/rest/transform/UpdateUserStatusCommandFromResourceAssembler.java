package com.gostech.swiftportbackend.iam.interfaces.rest.transform;

import com.gostech.swiftportbackend.iam.domain.model.commands.UpdateUserStatusCommand;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.UpdateUserStatusResource;

/**
 * Assembler to transform from UpdateUserStatusResource to UpdateUserStatusCommand
 */
public class UpdateUserStatusCommandFromResourceAssembler {
    
    /**
     * Transform from UpdateUserStatusResource to UpdateUserStatusCommand
     * @param userId the user ID
     * @param resource the UpdateUserStatusResource
     * @return the UpdateUserStatusCommand
     */
    public static UpdateUserStatusCommand toCommandFromResource(Long userId, UpdateUserStatusResource resource) {
        return new UpdateUserStatusCommand(userId, resource.active());
    }
} 