package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllEquipmentsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEquipmentByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEquipmentsByStatusQuery;

import java.util.List;
import java.util.Optional;

public interface EquipmentQueryService {
    Optional<Equipment> handle(GetEquipmentByIdQuery query);
    List<Equipment> handle(GetAllEquipmentsQuery query);
    List<Equipment> handle(GetEquipmentsByStatusQuery query);
}
