package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.queries.CheckResourceAvailabilityByIdQuery;

public interface ResourceQueryService {
    Boolean handle(CheckResourceAvailabilityByIdQuery query);
}
