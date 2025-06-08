package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

public record ReservationId(Long reservationId) {
    public ReservationId {
        if (reservationId == null || reservationId < 1) {
            throw new IllegalArgumentException("reservationId cannot be null or less than 1");
        }
    }
}
