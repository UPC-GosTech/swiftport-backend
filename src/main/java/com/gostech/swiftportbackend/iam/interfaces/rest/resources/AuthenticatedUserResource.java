package com.gostech.swiftportbackend.iam.interfaces.rest.resources;

/**
 * Authenticated User Resource
 * @param id the user id
 * @param username the username
 * @param token the authentication token
 */
public record AuthenticatedUserResource(Long id, String username, String token) {
} 