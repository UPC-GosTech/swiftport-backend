package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetVehicleByIdQuery(Long vehicleId) {
    public GetVehicleByIdQuery {
        if (vehicleId == null || vehicleId <= 0) {
            throw new IllegalArgumentException("VehicleId is required");
        }
    }
}
