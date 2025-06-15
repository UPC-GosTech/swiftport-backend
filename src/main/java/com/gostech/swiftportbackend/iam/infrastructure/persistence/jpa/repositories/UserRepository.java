package com.gostech.swiftportbackend.iam.infrastructure.persistence.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gostech.swiftportbackend.iam.domain.model.aggregates.User;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.EmailAddress;
/**
 * User Repository
 * <p>
 * This repository is responsible for managing User entities in the database.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find a user by username
     * @param username the username to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Find a user by email
     * @param email the email to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmail(EmailAddress email);
    
    /**
     * Check if a user exists by username
     * @param username the username to check
     * @return true if user exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Check if a user exists by email
     * @param email the email to check
     * @return true if user exists, false otherwise
     */
    boolean existsByEmail(EmailAddress email);
    
    /**
     * Find a user by username and tenant ID
     * @param username the username to search for
     * @param tenantId the tenant ID
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsernameAndTenantId(String username, Long tenantId);
    
    /**
     * Find all users by tenant ID
     * @param tenantId the tenant ID
     * @return List of users for the tenant
     */
    List<User> findAllByTenantId(Long tenantId);
    
    /**
     * Find a user by ID and tenant ID
     * @param id the user ID
     * @param tenantId the tenant ID
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByIdAndTenantId(Long id, Long tenantId);
    
    /**
     * Check if user exists by username and tenant
     * @param username the username to check
     * @param tenantId the tenant ID
     * @return true if user exists, false otherwise
     */
    boolean existsByUsernameAndTenantId(String username, Long tenantId);
    
    /**
     * Check if user exists by email and tenant
     * @param email the email to check
     * @param tenantId the tenant ID
     * @return true if user exists, false otherwise
     */
    boolean existsByEmailAndTenantId(EmailAddress email, Long tenantId);
} 