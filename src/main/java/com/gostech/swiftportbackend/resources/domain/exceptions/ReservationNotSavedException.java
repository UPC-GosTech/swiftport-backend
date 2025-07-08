package com.gostech.swiftportbackend.resources.domain.exceptions;

public class ReservationNotSavedException extends RuntimeException {
    public ReservationNotSavedException(String message) {
        super("Error saving reservation: " + message);
    }
}
