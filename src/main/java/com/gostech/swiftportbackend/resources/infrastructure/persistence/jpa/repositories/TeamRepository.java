package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    boolean existsByName(String name);
    Optional<Team> findById(Long id);
}
