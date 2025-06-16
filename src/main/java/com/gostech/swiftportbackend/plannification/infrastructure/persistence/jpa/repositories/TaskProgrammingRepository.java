package com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskProgrammingRepository extends JpaRepository<TaskProgramming, Long> {
}
