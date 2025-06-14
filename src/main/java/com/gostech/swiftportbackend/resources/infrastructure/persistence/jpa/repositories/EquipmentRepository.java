package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EquipmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
    boolean existsByPlate(String plate);
    Optional<Equipment> findByEquipmentId(EquipmentId equipmentId);
}
