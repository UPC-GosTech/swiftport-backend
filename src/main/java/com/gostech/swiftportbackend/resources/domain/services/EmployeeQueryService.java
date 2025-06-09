package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllEmployeesQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEmployeeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryService {
    Optional<Employee> handle(GetEmployeeByIdQuery query);
    List<Employee> handle(GetAllEmployeesQuery query);
}
