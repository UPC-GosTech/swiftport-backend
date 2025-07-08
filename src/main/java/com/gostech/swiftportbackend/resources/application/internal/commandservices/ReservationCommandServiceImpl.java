package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.exceptions.ReservationNotSavedException;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.domain.services.ReservationCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Long handle(CreateReservationCommand command) {
        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new RuntimeException("Tenant context not found");
        }

        var reservation = new Reservation(tenantId, command);
        try {
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new ReservationNotSavedException(e.getMessage());
        }
        return reservation.getId();
    }
}
