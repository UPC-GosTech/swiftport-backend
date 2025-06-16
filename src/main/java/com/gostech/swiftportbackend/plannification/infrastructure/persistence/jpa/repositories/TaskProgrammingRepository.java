package com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskProgrammingRepository extends JpaRepository<TaskProgramming, Long> {
    List<TaskProgramming> findByTaskId(Long id);
}
