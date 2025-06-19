package com.gostech.swiftportbackend.plannification.interfaces.rest.controllers;

import com.gostech.swiftportbackend.plannification.domain.model.queries.GetActivityByIdQuery;
import com.gostech.swiftportbackend.plannification.domain.model.queries.GetAllActivitiesQuery;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityCommandService;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityQueryService;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.ActivityResource;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.CreateActivityResource;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.ActivityResourceFromEntityAssembler;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.CreateActivityCommandFromResourceAssembler;
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
@RequestMapping(value = "api/v1/activity", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Activity", description = "Available Activity Endpoints")
public class ActivityController {
    private final ActivityCommandService activityCommandService;
    private final ActivityQueryService activityQueryService;

    public ActivityController(ActivityCommandService activityCommandService, ActivityQueryService activityQueryService) {
        this.activityCommandService = activityCommandService;
        this.activityQueryService = activityQueryService;
    }

    @PostMapping
    @Operation(summary = "Create new activity", description = "Create new activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Activity not found")})
    public ResponseEntity<ActivityResource> createActivity(@RequestBody CreateActivityResource resource) {
        var createActivityCommand = CreateActivityCommandFromResourceAssembler.toCommandFromResource(resource);
        var activityId = activityCommandService.handle(createActivityCommand);
        if (activityId == null || activityId == 0L) return ResponseEntity.badRequest().build();
        var getActivityByIdQuery = new GetActivityByIdQuery(activityId);
        var activity = activityQueryService.handle(getActivityByIdQuery);
        if (activity.isEmpty()) return ResponseEntity.badRequest().build();
        var activityEntity = activity.get();
        var activityResource = ActivityResourceFromEntityAssembler.toResourceFromEntity(activityEntity);
        return new ResponseEntity<>(activityResource, HttpStatus.CREATED);
    }

    @GetMapping("/{activityId}")
    @Operation(summary = "Get activity by id", description = "Get activity by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity found"),
            @ApiResponse(responseCode = "404", description = "Activity not found")})
    public ResponseEntity<ActivityResource> getActivityById(@PathVariable Long activityId) {
        var getActivityByIdQuery = new GetActivityByIdQuery(activityId);
        var activity = activityQueryService.handle(getActivityByIdQuery);
        if (activity.isEmpty()) return ResponseEntity.badRequest().build();
        var activityEntity = activity.get();
        var activityResource = ActivityResourceFromEntityAssembler.toResourceFromEntity(activityEntity);
        return ResponseEntity.ok(activityResource);
    }

    @GetMapping
    @Operation(summary = "Get all activities", description = "Get all activities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity found"),
            @ApiResponse(responseCode = "404", description = "Activity not found")})
    public ResponseEntity<List<ActivityResource>> getAllActivities() {
        var activityList = activityQueryService.handle(new GetAllActivitiesQuery());
        if (activityList.isEmpty()) return ResponseEntity.badRequest().build();
        var activityResources = activityList.stream()
                .map(ActivityResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(activityResources);
    }
}
