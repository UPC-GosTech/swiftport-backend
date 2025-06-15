package com.gostech.swiftportbackend.resources.interfaces.rest.controllers;

import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationsByResourceReference;
import com.gostech.swiftportbackend.resources.domain.services.ReservationCommandService;
import com.gostech.swiftportbackend.resources.domain.services.ReservationQueryService;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateReservationResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.ReservationResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
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
@RequestMapping(value = "api/v1/reservations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reservations", description = "Available Reservations Endpoints")
public class ReservationController {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    public ReservationController(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new reservation", description = "Create a new reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reservation created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")})
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource) {
        System.out.println("Recibido: " + resource);
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var reservationId = reservationCommandService.handle(createReservationCommand);
        if (reservationId == null || reservationId == 0L) return ResponseEntity.badRequest().build();
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);
        if (reservation.isEmpty()) return ResponseEntity.notFound().build();
        var reservationEntity = reservation.get();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservationEntity);
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{reservationId}")
    @Operation(summary = "Get reservation by id", description = "Get reservation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation found"),
            @ApiResponse(responseCode = "404", description = "Reservation not found")})
    public ResponseEntity<ReservationResource> getReservation(@PathVariable Long reservationId) {
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);
        if (reservation.isEmpty()) return ResponseEntity.notFound().build();
        var reservationEntity = reservation.get();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservationEntity);
        return ResponseEntity.ok(reservationResource);
    }

    @GetMapping("/by-resource/{resourceType}/{resourceId}")
    @Operation(summary = "Get reservations by resource reference", description = "Get reservations by resource type and ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservations found"),
            @ApiResponse(responseCode = "404", description = "Reservations not found")
    })
    public ResponseEntity<List<ReservationResource>> getReservationsByResourceReference(
            @PathVariable String resourceType,
            @PathVariable Long resourceId) {
        var query = new GetReservationsByResourceReference(resourceId, resourceType);
        var reservations = reservationQueryService.handle(query);
        if (reservations.isEmpty()) return ResponseEntity.notFound().build();
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reservationResources);
    }

}
