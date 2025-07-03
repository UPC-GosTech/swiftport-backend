package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllEmployeesQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEmployeeByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEmployeesByStatusQuery;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;
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
        return employeeRepository.findById(query.employeeId());
    }

    @Override
    public List<Employee> handle(GetAllEmployeesQuery query) {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> handle(GetEmployeesByStatusQuery query) {
        Availability status;
        switch (query.status()) {
            case "Available" -> status = Availability.AVAILABLE;
            case "Vacation" -> status = Availability.VACATION;
            case "Reserved" -> status = Availability.RESERVED;
            case "Unavailable" -> status = Availability.UNAVAILABLE;
            default -> {
                return employeeRepository.findAll();
            }
        }
        return employeeRepository.findByEmployeeStatus(status);
    }
}
