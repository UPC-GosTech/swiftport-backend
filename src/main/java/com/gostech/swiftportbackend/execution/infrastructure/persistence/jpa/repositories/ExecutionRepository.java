package com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.TaskProgrammingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExecutionRepository extends JpaRepository<Execution, Long> {
    boolean existsByTaskProgrammingId(TaskProgrammingId taskProgrammingId);
    Optional<Execution> findById(Long id);
}
