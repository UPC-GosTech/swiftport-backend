package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public record TimeInterval(LocalDateTime start, LocalDateTime end) {

    /**
     * class TimeInterval <<ValueObject>> {
     *         - LocalDateTime start
     *         - LocalDateTime end
     *         # TimeInterval(LocalDateTime start, LocalDateTime end)
     *         + boolean overlaps(TimeInterval other)
     *       }
     */

    public TimeInterval {
        if (start == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
    }
}
