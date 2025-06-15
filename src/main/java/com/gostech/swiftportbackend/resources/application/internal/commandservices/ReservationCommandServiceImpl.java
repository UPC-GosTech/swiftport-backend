package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.domain.services.ReservationCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Long handle(CreateReservationCommand command) {
        var reservation = new Reservation(command);
        try {
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving reservation: %s".formatted(e.getMessage()));
        }
        return reservation.getId();
    }
}
