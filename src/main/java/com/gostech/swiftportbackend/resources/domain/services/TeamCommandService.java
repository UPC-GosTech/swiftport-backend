package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.AddTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;

public interface TeamCommandService {
    Long handle(CreateTeamCommand command);
    Long handle(AddTeamMemberCommand command);
}
