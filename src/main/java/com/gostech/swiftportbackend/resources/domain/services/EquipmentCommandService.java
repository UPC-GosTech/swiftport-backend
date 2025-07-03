package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateEquipmentStatusCommand;

import java.util.Optional;

public interface EquipmentCommandService {
    Long handle(CreateEquipmentCommand command);
    Optional<Equipment> handle(UpdateEquipmentStatusCommand command);
}
