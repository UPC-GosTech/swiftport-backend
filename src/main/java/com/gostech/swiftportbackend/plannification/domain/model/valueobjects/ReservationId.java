package com.gostech.swiftportbackend.plannification.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ReservationId(Long reservationId) {
    public ReservationId {
        if (reservationId == null || reservationId <= 0) {
            throw new IllegalArgumentException("Reservation Id can't be null or less than one");
        }
    }
}
