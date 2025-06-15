package com.gostech.swiftportbackend.iam.interfaces.rest.transform;

import com.gostech.swiftportbackend.iam.domain.model.commands.SignInCommand;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.SignInResource;

/**
 * Assembler to transform from SignInResource to SignInCommand
 */
public class SignInCommandFromResourceAssembler {
    
    /**
     * Transform from SignInResource to SignInCommand
     * @param resource the SignInResource
     * @return the SignInCommand
     */
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(
                resource.username(),
                resource.password()
        );
    }
} 