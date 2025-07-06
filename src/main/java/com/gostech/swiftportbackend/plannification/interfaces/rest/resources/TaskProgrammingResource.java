package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ProgrammingStatus;

import java.time.LocalDateTime;

public record TaskProgrammingResource(Long taskProgrammingId, Long reservationId, ProgrammingStatus programmingStatus) {
}
