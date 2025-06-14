package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;
import com.gostech.swiftportbackend.resources.domain.services.EquipmentCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentCommandServiceImpl implements EquipmentCommandService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentCommandServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Long handle(CreateEquipmentCommand command) {
        if (equipmentRepository.existsByPlate(command.plate()))
            throw new IllegalArgumentException("Equipment with plate %s already exists".formatted(command.plate()));
        var equipment = new Equipment(command);
        try {
            equipmentRepository.save(equipment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving equipment: %s".formatted(e.getMessage()));
        }
        return equipment.getEquipmentId().equipmentId();
    }
}
