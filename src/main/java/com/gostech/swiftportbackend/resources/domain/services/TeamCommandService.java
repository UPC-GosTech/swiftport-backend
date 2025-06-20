package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.AddTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.DeleteTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;

import java.util.Optional;

public interface TeamCommandService {
    Long handle(CreateTeamCommand command);
    Long handle(AddTeamMemberCommand command);
    Optional<TeamMember> handle(DeleteTeamMemberCommand command);
}
