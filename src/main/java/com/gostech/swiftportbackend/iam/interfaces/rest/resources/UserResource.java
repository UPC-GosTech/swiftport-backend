package com.gostech.swiftportbackend.iam.interfaces.rest.resources;

import java.util.List;

/**
 * User Resource
 * @param id the user id
 * @param username the username
 * @param email the email
 * @param firstName the first name
 * @param lastName the last name
 * @param active whether the user is active
 * @param roles the list of role names
 */
public record UserResource(Long id, String username, String email, String firstName, 
                          String lastName, Boolean active, List<String> roles) {
} 