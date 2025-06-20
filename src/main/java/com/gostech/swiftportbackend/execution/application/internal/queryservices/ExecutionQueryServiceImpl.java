package com.gostech.swiftportbackend.execution.application.internal.queryservices;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetAllExecutionsQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetExecutionByIdQuery;
import com.gostech.swiftportbackend.execution.domain.services.ExecutionQueryService;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExecutionQueryServiceImpl implements ExecutionQueryService {
    private final ExecutionRepository executionRepository;

    public ExecutionQueryServiceImpl(ExecutionRepository executionRepository) {
        this.executionRepository = executionRepository;
    }

    @Override
    public Optional<Execution> handle(GetExecutionByIdQuery query) {
        return executionRepository.findById(query.id());
    }

    @Override
    public List<Execution> handle(GetAllExecutionsQuery query) {
        return executionRepository.findAll();
    }
}
