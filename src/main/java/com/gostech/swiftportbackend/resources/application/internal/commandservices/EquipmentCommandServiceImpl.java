package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.exceptions.EquipmentNotFoundException;
import com.gostech.swiftportbackend.resources.domain.exceptions.EquipmentNotSavedException;
import com.gostech.swiftportbackend.resources.domain.exceptions.EquipmentPlateAlreadyExistsException;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateEquipmentStatusCommand;
import com.gostech.swiftportbackend.resources.domain.services.EquipmentCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import com.gostech.swiftportbackend.shared.domain.exceptions.TenantNotFoundException;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipmentCommandServiceImpl implements EquipmentCommandService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentCommandServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Long handle(CreateEquipmentCommand command) {
        if (equipmentRepository.existsByPlate(command.plate()))
            throw new EquipmentPlateAlreadyExistsException(command.plate());

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new TenantNotFoundException();
        }

        var equipment = new Equipment(tenantId, command);
        try {
            equipmentRepository.save(equipment);
        } catch (Exception e) {
            throw new EquipmentNotSavedException(e.getMessage());
        }
        return equipment.getId();
    }

    @Override
    public Optional<Equipment> handle(UpdateEquipmentStatusCommand command) {
        Equipment equipment = equipmentRepository.findById(command.equipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(command.equipmentId()));
        try {
            equipment.updateEquipmentStatus(command.status());
            equipmentRepository.save(equipment);
        } catch (Exception e) {
            throw new EquipmentNotSavedException(e.getMessage());
        }
        return Optional.of(equipment);
    }
}
