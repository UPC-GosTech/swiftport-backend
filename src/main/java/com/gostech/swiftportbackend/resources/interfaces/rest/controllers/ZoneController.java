package com.gostech.swiftportbackend.resources.interfaces.rest.controllers;

import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllZonesQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetZoneByIdQuery;
import com.gostech.swiftportbackend.resources.domain.services.ZoneCommandService;
import com.gostech.swiftportbackend.resources.domain.services.ZoneQueryService;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateZoneResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.ZoneResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.CreateZoneCommandFromResourceAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.ZoneResourceFromEntityAssembler;
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
@RequestMapping(value = "api/v1/zones", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Zones", description = "Available Zones Endpoints")
public class ZoneController {

    private final ZoneCommandService zoneCommandService;
    private final ZoneQueryService zoneQueryService;

    public ZoneController(ZoneCommandService zoneCommandService, ZoneQueryService zoneQueryService) {
        this.zoneCommandService = zoneCommandService;
        this.zoneQueryService = zoneQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new zone", description = "Create a new zone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Zone created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Zone not found")
    })
    public ResponseEntity<ZoneResource> createZone(@RequestBody CreateZoneResource resource) {
        System.out.println("Recibido: " + resource);
        var createZoneCommand = CreateZoneCommandFromResourceAssembler.toCommandFromResource(resource);
        var zoneId = zoneCommandService.handle(createZoneCommand);
        if (zoneId == null || zoneId == 0L) return ResponseEntity.badRequest().build();
        var getZoneByIdQuery = new GetZoneByIdQuery(zoneId);
        var zone = zoneQueryService.handle(getZoneByIdQuery);
        if (zone.isEmpty()) return ResponseEntity.notFound().build();
        var zoneResource = ZoneResourceFromEntityAssembler.toResourceFromEntity(zone.get());
        return new ResponseEntity<>(zoneResource, HttpStatus.CREATED);
    }

    @GetMapping("/{zoneId}")
    @Operation(summary = "Get zone by id", description = "Get zone by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zone found"),
            @ApiResponse(responseCode = "404", description = "Zone not found")
    })
    public ResponseEntity<ZoneResource> getZone(@PathVariable Long zoneId) {
        var getZoneByIdQuery = new GetZoneByIdQuery(zoneId);
        var zone = zoneQueryService.handle(getZoneByIdQuery);
        if (zone.isEmpty()) return ResponseEntity.notFound().build();
        var zoneResource = ZoneResourceFromEntityAssembler.toResourceFromEntity(zone.get());
        return ResponseEntity.ok(zoneResource);
    }

    @GetMapping
    @Operation(summary = "Get all zones", description = "Get all zones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zones found"),
            @ApiResponse(responseCode = "404", description = "Zones not found")
    })
    public ResponseEntity<List<ZoneResource>> getAllZones() {
        var zones = zoneQueryService.handle(new GetAllZonesQuery());
        if (zones.isEmpty()) return ResponseEntity.notFound().build();
        var zoneResources = zones.stream()
                .map(ZoneResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(zoneResources);
    }
}
