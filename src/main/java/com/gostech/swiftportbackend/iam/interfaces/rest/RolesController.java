package com.gostech.swiftportbackend.iam.interfaces.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gostech.swiftportbackend.iam.domain.model.queries.GetAllRolesQuery;
import com.gostech.swiftportbackend.iam.domain.services.RoleQueryService;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.RoleResource;
import com.gostech.swiftportbackend.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * RolesController
 * <p>
 *     This controller is responsible for handling role-related requests.
 *     It exposes endpoints for role management.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Available Role Endpoints")
public class RolesController {
    
    private final RoleQueryService roleQueryService;

    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }

    /**
     * Get all roles
     * @return list of all roles
     */
    @GetMapping
    @Operation(summary = "Get all roles", description = "Get all available roles in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles retrieved successfully.")})
    public ResponseEntity<List<RoleResource>> getAllRoles() {
        var getAllRolesQuery = new GetAllRolesQuery();
        var roles = roleQueryService.handle(getAllRolesQuery);
        var roleResources = roles.stream()
                .map(RoleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(roleResources);
    }
} 