package com.gostech.swiftportbackend.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Authenticated User Resource
 * @param id the user id
 * @param username the username
 * @param token the authentication token
 * @param roles the list of role names
 */
public record AuthenticatedUserResource(Long id, String username, String token, List<String> roles) {
} 