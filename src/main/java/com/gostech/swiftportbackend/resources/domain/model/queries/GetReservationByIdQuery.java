package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetReservationByIdQuery(Long reservationId) {
    public GetReservationByIdQuery {
        if (reservationId == null || reservationId <= 0) {
            throw new IllegalArgumentException("reservationId can't be null or empty");
        }
    }
}
