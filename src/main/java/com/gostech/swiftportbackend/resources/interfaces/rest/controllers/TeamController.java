package com.gostech.swiftportbackend.resources.interfaces.rest.controllers;

import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllTeamsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetTeamByIdQuery;
import com.gostech.swiftportbackend.resources.domain.services.TeamCommandService;
import com.gostech.swiftportbackend.resources.domain.services.TeamQueryService;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateTeamResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.TeamResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.CreateTeamCommandFromResourceAssembler;
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
}
