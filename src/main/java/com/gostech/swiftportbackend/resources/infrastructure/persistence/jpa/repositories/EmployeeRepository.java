package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.FullName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByName(FullName name);
    boolean existsByNameAndIdIsNot(FullName name, Long id);
    Optional<Employee> findByEmployeeId(EmployeeId employeeId);
}
