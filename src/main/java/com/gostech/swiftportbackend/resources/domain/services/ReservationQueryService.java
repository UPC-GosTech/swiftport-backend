package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationsByResourceReference;

import java.util.List;

public interface ReservationQueryService {
    List<Reservation> handle(GetReservationsByResourceReference getReservationsByResourceReference);
}
