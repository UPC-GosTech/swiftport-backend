package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.FullName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByName(FullName name);
    Optional<Employee> findById(Long id);
    List<Employee> findByEmployeeStatus(Availability status);
}
