package com.gostech.swiftportbackend.iam.interfaces.rest.resources;

/**
 * Sign In Resource
 * @param username the username
 * @param password the password
 */
public record SignInResource(String username, String password) {
} 