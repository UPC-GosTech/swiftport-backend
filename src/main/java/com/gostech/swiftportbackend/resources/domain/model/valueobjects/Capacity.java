package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public record Capacity(BigDecimal tons, Integer passengers) {

    public Capacity {
        if (tons == null || tons.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Tons cannot be null or less than or equal to 0");
        }
        if (passengers == null || passengers <= 0) {
            throw new IllegalArgumentException("Passengers cannot be null or less than or equal to 0");
        }
    }

}
