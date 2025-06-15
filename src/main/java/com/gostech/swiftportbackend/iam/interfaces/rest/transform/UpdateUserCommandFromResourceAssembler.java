package com.gostech.swiftportbackend.iam.interfaces.rest.transform;

import com.gostech.swiftportbackend.iam.domain.model.commands.UpdateUserCommand;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.UpdateUserResource;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.EmailAddress;

/**
 * Assembler to convert UpdateUserResource to UpdateUserCommand
 */
public class UpdateUserCommandFromResourceAssembler {
    
    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(
            userId,
            new EmailAddress(resource.email()),
            resource.firstName(),
            resource.lastName(),
            resource.roles()
        );
    }
} 