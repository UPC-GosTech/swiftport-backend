package com.gostech.swiftportbackend.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public record TimeInterval(LocalDateTime start, LocalDateTime end) {

    public TimeInterval {
        if (start == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
    }

    public boolean overlaps(TimeInterval other) {
        return ((this.start.isBefore(other.end) && this.end.isAfter(other.start)) ||
                (this.start == other.start && this.end == other.end) ||
                (this.start.isBefore(other.end) && this.end == other.end) ||
                (this.start == other.start && this.end.isAfter(other.start)) ||
                (this.start.isBefore(other.end) && this.start.isAfter(other.start)) ||
                (this.end.isAfter(other.start) && this.end.isBefore(other.end)));
    }
}