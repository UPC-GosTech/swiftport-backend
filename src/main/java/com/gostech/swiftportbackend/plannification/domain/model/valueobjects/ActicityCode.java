package com.gostech.swiftportbackend.plannification.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ActicityCode(String code) {
    public ActicityCode {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("code cannot be null");
        }
    }
}
