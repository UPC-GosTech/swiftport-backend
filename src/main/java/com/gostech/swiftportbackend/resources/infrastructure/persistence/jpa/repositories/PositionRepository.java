package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    boolean existsByTitle(String title);
    Optional<Position> findById(Long id);
}
