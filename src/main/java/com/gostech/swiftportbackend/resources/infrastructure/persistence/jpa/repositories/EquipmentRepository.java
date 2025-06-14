package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EquipmentId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository {
    boolean existsByPlate(String plate);
    Optional<Equipment> findByEmployeeId(EquipmentId equipmentId);
}
