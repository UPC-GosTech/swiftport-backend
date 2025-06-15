package com.gostech.swiftportbackend.iam.domain.services;

import com.gostech.swiftportbackend.iam.domain.model.aggregates.User;
import com.gostech.swiftportbackend.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * User command service interface
 * This interface defines the contract for user command operations
 */
public interface UserCommandService {
    
    /**
     * Handle sign up command
     * @param command the sign up command
     * @return pair containing the created user and generated token
     */
    ImmutablePair<User, String> handle(SignUpCommand command);
} 