package com.gostech.swiftportbackend.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Sign Up Resource
 * @param username the username
 * @param password the password
 * @param email the email
 * @param firstName the first name
 * @param lastName the last name
 * @param roles the list of role names
 */
public record SignUpResource(String username, String password, String email, 
                            String firstName, String lastName, List<String> roles) {
} 