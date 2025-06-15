package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllEmployeesQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEmployeeByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.resources.domain.services.EmployeeQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeQueryServiceImpl implements EmployeeQueryService {
    private final EmployeeRepository employeeRepository;

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {
        return employeeRepository.findByEmployeeId(new EmployeeId(query.employeeId()));
    }

    @Override
    public List<Employee> handle(GetAllEmployeesQuery query) {
        return employeeRepository.findAll();
    }
}
