package com.gostech.swiftportbackend.resources.application.acl;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllReservationsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetReservationsByResourceReference;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TimeInterval;
import com.gostech.swiftportbackend.resources.domain.services.ReservationCommandService;
import com.gostech.swiftportbackend.resources.domain.services.ReservationQueryService;
import com.gostech.swiftportbackend.resources.interfaces.acl.ReservationsContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationsContextFacadeImpl implements ReservationsContextFacade {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    public ReservationsContextFacadeImpl(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    public Long createReservation(String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
        var createReservationCommand = new CreateReservationCommand(resourceType, resourceId, start, end);
        return reservationCommandService.handle(createReservationCommand);
    }

    public Long fetchReservationByResourceReference(Long resourceId, String resourceType) {
        var getReservation = new GetReservationsByResourceReference(resourceId, resourceType);
        var reservation = reservationQueryService.handle(getReservation);
        return reservation.isEmpty() ? Long.valueOf(0L) : reservation.getFirst().getId();
    }

    public Boolean overlapsReservationDate(Long reservationId, LocalDateTime start, LocalDateTime end) {
        var getReservation = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservation);
        var getReservations = new GetAllReservationsQuery();
        List<Reservation> reservations = reservationQueryService.handle(getReservations);

        if (reservations.isEmpty() || reservation.isEmpty()) {
            return false;
        }

        return reservations.stream()
                .filter(r -> r.getResourceReference().equals(reservation.get().getResourceReference()))
                .anyMatch(r -> r.overlaps(new TimeInterval(start, end)));
    }

    public Boolean updateReservationTimeInterval(Long reservationId, LocalDateTime start, LocalDateTime end) {
        var getReservation = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservation);
        var getReservations = new GetAllReservationsQuery();
        List<Reservation> reservations = reservationQueryService.handle(getReservations);
        var coincidence = false;
        if (!reservations.isEmpty()) {
            coincidence = reservations.stream()
                    .filter(r -> r.getTimeInterval().equals(new TimeInterval(start, end)))
                    .anyMatch(r -> r.overlaps(reservation.get().getTimeInterval()));
        }
        return !coincidence;
    }
}
