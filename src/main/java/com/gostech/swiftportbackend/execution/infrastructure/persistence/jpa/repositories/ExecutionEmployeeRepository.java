package com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.execution.domain.model.entities.ExecutionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExecutionEmployeeRepository extends JpaRepository<ExecutionEmployee, Long> {
    Optional<ExecutionEmployee> findById(Long id);
}
