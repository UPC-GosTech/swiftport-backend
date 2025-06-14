package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TeamId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository {
    boolean existsByName(String name);
    Optional<Team> findByTeamId(TeamId teamId);
}
