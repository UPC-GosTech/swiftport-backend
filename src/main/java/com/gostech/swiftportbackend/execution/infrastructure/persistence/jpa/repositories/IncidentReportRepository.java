package com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long> {
    boolean existsByTitle(String title);
    Optional<IncidentReport> findById(Long id);
    List<IncidentReport> findByExecutionId(Long id);

    List<IncidentReport> getByExecutionId(Long executionId);
}
