package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllZonesQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetZoneByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ZoneId;
import com.gostech.swiftportbackend.resources.domain.services.ZoneQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneQueryServiceImpl implements ZoneQueryService {
    private final ZoneRepository zoneRepository;

    public ZoneQueryServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Optional<Zone> handle(GetZoneByIdQuery query) {
        return zoneRepository.findByZoneId(new ZoneId(query.zoneId()));
    }

    @Override
    public List<Zone> handle(GetAllZonesQuery query) {
        return zoneRepository.findAll();
    }
}
