package com.gostech.swiftportbackend.resources.interfaces.rest.controllers;

import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllTeamsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetTeamByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetTeamMemberByIdQuery;
import com.gostech.swiftportbackend.resources.domain.services.TeamCommandService;
import com.gostech.swiftportbackend.resources.domain.services.TeamQueryService;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateTeamMemberResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateTeamResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.TeamMemberResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.TeamResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.AddTeamMemberCommandFromResourceAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.CreateTeamCommandFromResourceAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.TeamMemberResourceFromEntityAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.TeamResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/teams", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Teams", description = "Available Teams Endpoints")
public class TeamController {

    private final TeamCommandService teamCommandService;
    private final TeamQueryService teamQueryService;

    public TeamController(TeamCommandService teamCommandService, TeamQueryService teamQueryService) {
        this.teamCommandService = teamCommandService;
        this.teamQueryService = teamQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new team", description = "Create a new team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    public ResponseEntity<TeamResource> createTeam(@RequestBody CreateTeamResource resource) {
        System.out.println("Recibido: " + resource);
        var createTeamCommand = CreateTeamCommandFromResourceAssembler.toCommandFromResource(resource);
        var teamId = teamCommandService.handle(createTeamCommand);
        if (teamId == null || teamId == 0L) return ResponseEntity.badRequest().build();
        var getTeamByIdQuery = new GetTeamByIdQuery(teamId);
        var team = teamQueryService.handle(getTeamByIdQuery);
        if (team.isEmpty()) return ResponseEntity.notFound().build();
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(team.get());
        return new ResponseEntity<>(teamResource, HttpStatus.CREATED);
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "Get team by id", description = "Get team by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team found"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    public ResponseEntity<TeamResource> getTeam(@PathVariable Long teamId) {
        var getTeamByIdQuery = new GetTeamByIdQuery(teamId);
        var team = teamQueryService.handle(getTeamByIdQuery);
        if (team.isEmpty()) return ResponseEntity.notFound().build();
        var teamResource = TeamResourceFromEntityAssembler.toResourceFromEntity(team.get());
        return ResponseEntity.ok(teamResource);
    }

    @GetMapping
    @Operation(summary = "Get all teams", description = "Get all teams")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teams found"),
            @ApiResponse(responseCode = "404", description = "Teams not found")
    })
    public ResponseEntity<List<TeamResource>> getAllTeams() {
        var teams = teamQueryService.handle(new GetAllTeamsQuery());
        if (teams.isEmpty()) return ResponseEntity.notFound().build();
        var teamResources = teams.stream()
                .map(TeamResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(teamResources);
    }

    @PostMapping("/{teamId}/members")
    @Operation(summary = "Add team member to team", description = "Creates a new team member and adds it to the given team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team member added to team"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    public ResponseEntity<TeamMemberResource> addTeamMemberToTeam(@RequestBody CreateTeamMemberResource resource) {
        var command = AddTeamMemberCommandFromResourceAssembler.toCommandFromResource(resource);
        var teamMemberId = teamCommandService.handle(command);
        if (teamMemberId == null || teamMemberId == 0L) return ResponseEntity.badRequest().build();
        var query = new GetTeamMemberByIdQuery(teamMemberId);
        var teamMember = teamQueryService.handle(query);
        if (teamMember.isEmpty()) return ResponseEntity.notFound().build();
        var entity = teamMember.get();
        var teamMemberResource = TeamMemberResourceFromEntityAssembler.toResourceFromEntity(entity);
        return new ResponseEntity<>(teamMemberResource, HttpStatus.CREATED);
    }

    @GetMapping("/members/{memberId}")
    @Operation(summary = "Get team member by ID within a team", description = "Retrieve a specific team member from a given team by their employee ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team member found"),
            @ApiResponse(responseCode = "404", description = "Team or team member not found")
    })
    public ResponseEntity<TeamMemberResource> getTeamMemberById(@PathVariable Long memberId) {
        var query = new GetTeamMemberByIdQuery(memberId);
        var optionalMember = teamQueryService.handle(query);
        if (optionalMember.isEmpty()) return ResponseEntity.notFound().build();
        var resource = TeamMemberResourceFromEntityAssembler.toResourceFromEntity(optionalMember.get());
        return ResponseEntity.ok(resource);
    }

}
