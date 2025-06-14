package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationsByResourceReference;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ReservationId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ResourceReference;
import com.gostech.swiftportbackend.resources.domain.services.ReservationQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return reservationRepository.findByReservationId(new ReservationId(query.reservationId()));
    }

    @Override
    public List<Reservation> handle(GetReservationsByResourceReference query) {
        return reservationRepository.findByResourceReference(new ResourceReference(query.resourceType(), query.resourceId()));
    }
}
