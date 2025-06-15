package com.gostech.swiftportbackend.iam.interfaces.rest.transform;

import com.gostech.swiftportbackend.iam.domain.model.commands.SignUpCommand;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.SignUpResource;

/**
 * Assembler to transform from SignUpResource to SignUpCommand
 */
public class SignUpCommandFromResourceAssembler {
    
    /**
     * Transform from SignUpResource to SignUpCommand
     * @param resource the SignUpResource
     * @return the SignUpCommand
     */
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(
                resource.username(),
                resource.password(),
                resource.email(),
                resource.firstName(),
                resource.lastName(),
                resource.roles()
        );
    }
} 