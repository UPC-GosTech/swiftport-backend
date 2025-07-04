package com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.execution.domain.model.entities.ExecutionEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExecutionEquipmentRepository extends JpaRepository<ExecutionEquipment, Long> {
    Optional<ExecutionEquipment> findById(Long id);
}
