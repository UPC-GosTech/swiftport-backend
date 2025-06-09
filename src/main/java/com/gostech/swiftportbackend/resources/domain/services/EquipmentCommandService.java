package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;

import java.util.Optional;

public interface EquipmentCommandService {
    Optional<Equipment> handle(CreateEquipmentCommand command);
}
