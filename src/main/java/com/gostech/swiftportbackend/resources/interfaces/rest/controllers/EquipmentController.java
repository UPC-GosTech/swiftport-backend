package com.gostech.swiftportbackend.resources.interfaces.rest.controllers;

import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllEquipmentsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEquipmentByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEquipmentsByStatusQuery;
import com.gostech.swiftportbackend.resources.domain.services.EquipmentCommandService;
import com.gostech.swiftportbackend.resources.domain.services.EquipmentQueryService;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateEquipmentResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.EquipmentResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.UpdateEquipmentStatusResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.CreateEquipmentCommandFromResourceAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.EquipmentResourceFromEntityAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.UpdateEquipmentStatusCommandFromResourceAssembler;
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
@RequestMapping(value = "api/v1/equipment", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Equipment", description = "Available Equipment Endpoints")
public class EquipmentController {
    private final EquipmentCommandService equipmentCommandService;
    private final EquipmentQueryService equipmentQueryService;

    public EquipmentController(EquipmentCommandService equipmentCommandService, EquipmentQueryService equipmentQueryService) {
        this.equipmentCommandService = equipmentCommandService;
        this.equipmentQueryService = equipmentQueryService;
    }

    @PostMapping
    @Operation(summary = "Create new equipment", description = "Create new equipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipment created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Equipment not found")})
    public ResponseEntity<EquipmentResource> createEquipment(@RequestBody CreateEquipmentResource resource) {
        var createEquipmentCommand = CreateEquipmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var equipmentId = equipmentCommandService.handle(createEquipmentCommand);
        if (equipmentId == null || equipmentId == 0L) return ResponseEntity.badRequest().build();
        var getEquipmentByIdQuery = new GetEquipmentByIdQuery(equipmentId);
        var equipment = equipmentQueryService.handle(getEquipmentByIdQuery);
        if (equipment.isEmpty()) return ResponseEntity.notFound().build();
        var equipmentEntity = equipment.get();
        var equipmentResource = EquipmentResourceFromEntityAssembler.toResourceFromEntity(equipmentEntity);
        return new ResponseEntity<>(equipmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{equipmentId}")
    @Operation(summary = "Get equipment by id", description = "Get equipment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment found"),
            @ApiResponse(responseCode = "404", description = "Equipment not found")})
    public ResponseEntity<EquipmentResource> getEquipment(@PathVariable Long equipmentId) {
        var getEquipmentByIdQuery = new GetEquipmentByIdQuery(equipmentId);
        var equipment = equipmentQueryService.handle(getEquipmentByIdQuery);
        if (equipment.isEmpty()) return ResponseEntity.notFound().build();
        var equipmentEntity = equipment.get();
        var equipmentResource = EquipmentResourceFromEntityAssembler.toResourceFromEntity(equipmentEntity);
        return ResponseEntity.ok(equipmentResource);
    }

    @GetMapping
    @Operation(summary = "Get all equipment", description = "Get all equipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment found"),
            @ApiResponse(responseCode = "404", description = "Equipment not found")})
    public ResponseEntity<List<EquipmentResource>> getAllEquipment() {
        var equipmentList = equipmentQueryService.handle(new GetAllEquipmentsQuery());
        if (equipmentList.isEmpty()) return ResponseEntity.notFound().build();
        var equipmentResources = equipmentList.stream()
                .map(EquipmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(equipmentResources);
    }

    @PatchMapping("/{equipmentId}/status")
    @Operation(summary = "Update equipment status", description = "Updates the availability status of an equipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment status updated"),
            @ApiResponse(responseCode = "404", description = "Equipment not found")
    })
    public ResponseEntity<EquipmentResource> updateEquipmentStatus(@PathVariable Long equipmentId, @RequestBody UpdateEquipmentStatusResource resource) {
        var command = UpdateEquipmentStatusCommandFromResourceAssembler.toCommandFromResource(equipmentId, resource);
        var updatedEquipment = equipmentCommandService.handle(command);
        if (updatedEquipment.isEmpty()) return ResponseEntity.notFound().build();
        var equipmentEntity = updatedEquipment.get();
        var equipmentResource = EquipmentResourceFromEntityAssembler.toResourceFromEntity(equipmentEntity);
        return ResponseEntity.ok(equipmentResource);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get equipments by status", description = "Get all equipments filtered by availability status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipments found"),
            @ApiResponse(responseCode = "404", description = "No equipments found for the given status")
    })
    public ResponseEntity<List<EquipmentResource>> getEquipmentsByStatus(@PathVariable String status) {
        var equipments = equipmentQueryService.handle(new GetEquipmentsByStatusQuery(status));
        if (equipments.isEmpty()) return ResponseEntity.notFound().build();
        var resources = equipments.stream()
                .map(EquipmentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

}
