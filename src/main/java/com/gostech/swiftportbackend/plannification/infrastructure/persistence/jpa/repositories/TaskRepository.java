package com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);
    List<Task> findByActivityId(Long id);
    Optional<Task> findById(Long id);
}
