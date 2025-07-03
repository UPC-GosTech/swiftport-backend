package com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.plannification.domain.model.aggregates.Activity;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActicityCode;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    boolean existsByActivityCode(ActicityCode activityCode);
    Optional<Activity> findById(Long id);
    List<Activity> findByActivityStatus(ActivityStatus activityStatus);
}
