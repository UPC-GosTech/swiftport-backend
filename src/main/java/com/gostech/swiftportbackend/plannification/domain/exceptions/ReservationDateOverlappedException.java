package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class ReservationDateOverlappedException extends RuntimeException {
    public ReservationDateOverlappedException() {
        super("Reservation date overlaps");
    }
}
