package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllReservationsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationsByResourceReference;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetAllReservationsQuery query);
    List<Reservation> handle(GetReservationsByResourceReference getReservationsByResourceReference);
}
