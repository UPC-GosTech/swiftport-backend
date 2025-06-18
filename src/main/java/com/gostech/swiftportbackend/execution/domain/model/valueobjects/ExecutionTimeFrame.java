package com.gostech.swiftportbackend.execution.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public record ExecutionTimeFrame(LocalDateTime start, LocalDateTime end) {
    public ExecutionTimeFrame {
        if (start == null) {
            throw new IllegalArgumentException("start cannot be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("end cannot be null");
        }
    }

    public boolean overlaps(ExecutionTimeFrame other) {
        return (this.start.isAfter(other.start) && this.end.isBefore(other.end)) ||
                (this.start.isAfter(other.start) && this.start.isBefore(other.end)) ||
                (this.end.isAfter(other.start) && this.end.isBefore(other.end));
    }
}
