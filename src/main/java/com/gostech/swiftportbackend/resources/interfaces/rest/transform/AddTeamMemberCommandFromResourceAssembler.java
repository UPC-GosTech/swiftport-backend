package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.AddTeamMemberCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateTeamMemberResource;

public class AddTeamMemberCommandFromResourceAssembler {
    public static AddTeamMemberCommand toCommandFromResource(CreateTeamMemberResource resource) {
        return new AddTeamMemberCommand(
                resource.employeeId(),
                resource.teamId()
        );
    }
}
