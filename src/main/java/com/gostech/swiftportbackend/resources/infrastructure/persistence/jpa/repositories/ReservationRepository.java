package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ReservationId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ResourceReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<Reservation> findByReservationId(ReservationId reservationId);
    List<Reservation> findByResourceReference(ResourceReference resourceReference);
}
