package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllEquipmentsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEquipmentByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEquipmentsByStatusQuery;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;
import com.gostech.swiftportbackend.resources.domain.services.EquipmentQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentQueryServiceImpl implements EquipmentQueryService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentQueryServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Optional<Equipment> handle(GetEquipmentByIdQuery query) {
        return equipmentRepository.findById(query.equipmentId());
    }

    @Override
    public List<Equipment> handle(GetAllEquipmentsQuery query) {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> handle(GetEquipmentsByStatusQuery query) {
        Availability status;
        switch (query.status()) {
            case "Available" -> status = Availability.AVAILABLE;
            case "Vacation" -> status = Availability.VACATION;
            case "Reserved" -> status = Availability.RESERVED;
            case "Unavailable" -> status = Availability.UNAVAILABLE;
            default -> {
                return equipmentRepository.findAll();
            }
        }
        return equipmentRepository.findByEquipmentStatus(status);
    }
}
