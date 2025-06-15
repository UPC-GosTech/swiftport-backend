package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;

public interface EquipmentCommandService {
    Long handle(CreateEquipmentCommand command);
}
