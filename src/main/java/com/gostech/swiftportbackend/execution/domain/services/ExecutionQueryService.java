package com.gostech.swiftportbackend.execution.domain.services;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetAllExecutionsQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetExecutionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExecutionQueryService {
    Optional<Execution> handle(GetExecutionByIdQuery query);
    List<Execution> handle(GetAllExecutionsQuery query);
}
