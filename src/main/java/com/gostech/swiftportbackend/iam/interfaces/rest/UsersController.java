package com.gostech.swiftportbackend.iam.interfaces.rest;

import com.gostech.swiftportbackend.iam.domain.model.queries.GetAllUsersQuery;
import com.gostech.swiftportbackend.iam.domain.model.queries.GetUserByIdQuery;
import com.gostech.swiftportbackend.iam.domain.services.UserQueryService;
import com.gostech.swiftportbackend.iam.interfaces.rest.resources.UserResource;
import com.gostech.swiftportbackend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UsersController
 * <p>
 *     This controller is responsible for handling user-related requests.
 *     It exposes endpoints for user management with multitenancy support.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Available User Endpoints")
public class UsersController {
    
    private final UserQueryService userQueryService;

    public UsersController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    /**
     * Get all users for current tenant
     * @return list of all users for the current tenant
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Get all users for the current tenant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully.")})
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * Get user by ID for current tenant
     * @param userId the user ID
     * @return the user resource if found
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Get user by ID for the current tenant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found.")})
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var userOptional = userQueryService.handle(getUserByIdQuery);
        
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(userOptional.get());
        return ResponseEntity.ok(userResource);
    }
} 