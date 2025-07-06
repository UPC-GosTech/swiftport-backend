package com.gostech.swiftportbackend.plannification.application.internal.outboundservice.acl;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ReservationId;
import com.gostech.swiftportbackend.resources.interfaces.acl.ReservationsContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ExternalReservationService {
    private final ReservationsContextFacade reservationsContextFacade;

    public ExternalReservationService(ReservationsContextFacade reservationsContextFacade) {
        this.reservationsContextFacade = reservationsContextFacade;
    }

    public Optional<ReservationId> createReservation(String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
        var reservationId = reservationsContextFacade.createReservation(resourceType, resourceId, start, end);
        return reservationId == 0L ? Optional.empty() : Optional.of(new ReservationId(reservationId));
    }

    public Optional<ReservationId> fetchReservationByResourceReference(Long resourceId, String resourceType) {
        var reservationId = reservationsContextFacade.fetchReservationByResourceReference(resourceId, resourceType);
        return reservationId == 0L ? Optional.empty() : Optional.of(new ReservationId(reservationId));
    }

    public Boolean overlaps(Long reservationId, LocalDateTime start, LocalDateTime end) {
        return reservationsContextFacade.overlapsReservationDate(reservationId, start, end);
    }

    public Boolean updateReservationTimeInterval(Long reservationId, LocalDateTime start, LocalDateTime end) {
        return reservationsContextFacade.updateReservationTimeInterval(reservationId, start, end);
    }
}
