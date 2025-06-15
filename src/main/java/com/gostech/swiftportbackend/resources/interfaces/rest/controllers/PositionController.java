package com.gostech.swiftportbackend.resources.interfaces.rest.controllers;

import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllPositionsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetPositionByIdQuery;
import com.gostech.swiftportbackend.resources.domain.services.PositionCommandService;
import com.gostech.swiftportbackend.resources.domain.services.PositionQueryService;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreatePositionResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.PositionResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.CreatePositionCommandFromResourceAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.PositionResourceFromEntityAssembler;
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
@RequestMapping(value = "api/v1/positions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Positions", description = "Available Positions Endpoints")
public class PositionController {
    private final PositionCommandService positionCommandService;
    private final PositionQueryService positionQueryService;

    public PositionController(PositionCommandService positionCommandService, PositionQueryService positionQueryService) {
        this.positionCommandService = positionCommandService;
        this.positionQueryService = positionQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new position", description = "Create a new position")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Position created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Position not found")})
    public ResponseEntity<PositionResource> createPosition(@RequestBody CreatePositionResource resource) {
        System.out.println("Recibido: " + resource);
        var createPositionCommand = CreatePositionCommandFromResourceAssembler.toCommandFromResource(resource);
        var positionId = positionCommandService.handle(createPositionCommand);
        if (positionId == null || positionId == 0L) return ResponseEntity.badRequest().build();
        var getPositionByIdQuery = new GetPositionByIdQuery(positionId);
        var position = positionQueryService.handle(getPositionByIdQuery);
        if (position.isEmpty()) return ResponseEntity.notFound().build();
        var positionEntity = position.get();
        var positionResource = PositionResourceFromEntityAssembler.toResourceFromEntity(positionEntity);
        return new ResponseEntity<>(positionResource, HttpStatus.CREATED);
    }

    @GetMapping("/{positionId}")
    @Operation(summary = "Get position by id", description = "Get position by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Position found"),
            @ApiResponse(responseCode = "404", description = "Position not found")})
    public ResponseEntity<PositionResource> getPosition(@PathVariable Long positionId) {
        var getPositionByIdQuery = new GetPositionByIdQuery(positionId);
        var position = positionQueryService.handle(getPositionByIdQuery);
        if (position.isEmpty()) return ResponseEntity.notFound().build();
        var positionEntity = position.get();
        var positionResource = PositionResourceFromEntityAssembler.toResourceFromEntity(positionEntity);
        return ResponseEntity.ok(positionResource);
    }

    @GetMapping
    @Operation(summary = "Get all positions", description = "Get all positions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Positions found"),
            @ApiResponse(responseCode = "404", description = "Positions not found")})
    public ResponseEntity<List<PositionResource>> getAllPositions() {
        var positions = positionQueryService.handle(new GetAllPositionsQuery());
        if (positions.isEmpty()) return ResponseEntity.notFound().build();
        var positionResources = positions.stream()
                .map(PositionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(positionResources);
    }
}
