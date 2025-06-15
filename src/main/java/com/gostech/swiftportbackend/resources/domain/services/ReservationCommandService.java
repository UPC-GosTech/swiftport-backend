package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;

public interface ReservationCommandService {
    Long handle(CreateReservationCommand command);
}
