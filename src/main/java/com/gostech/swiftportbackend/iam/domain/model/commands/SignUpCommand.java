package com.gostech.swiftportbackend.iam.domain.model.commands;

import java.util.List;

/**
 * Command to sign up a new user
 * @param username the username
 * @param password the password
 * @param email the email
 * @param firstName the first name
 * @param lastName the last name
 * @param roles the list of role names
 */
public record SignUpCommand(String username, String password, String email, 
                           String firstName, String lastName, List<String> roles) {
    public SignUpCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
    }
} 