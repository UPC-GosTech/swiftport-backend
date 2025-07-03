package com.gostech.swiftportbackend.iam.domain.model.aggregates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gostech.swiftportbackend.iam.domain.model.entities.Role;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.EmailAddress;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * User aggregate root for IAM context
 * This class represents the aggregate root for the User entity with multitenancy support.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    
    @NotBlank
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String passwordHash;
    
    @Embedded
    private EmailAddress email;
    
    @Size(max = 50)
    private String firstName;
    
    @Size(max = 50)
    private String lastName;
    
    @Column(nullable = false)
    private Boolean active;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", nullable = false)
    private Tenant tenant;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
    
    public User() {
        this.roles = new HashSet<>();
        this.active = true;
    }
    
    public User(String username, String passwordHash, EmailAddress email, 
                String firstName, String lastName, Tenant tenant) {
        this();
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenant = tenant;
    }
    
    public User(String username, String passwordHash, EmailAddress email, 
                String firstName, String lastName, Tenant tenant, List<Role> roles) {
        this(username, passwordHash, email, firstName, lastName, tenant);
        addRoles(roles);
    }
    
    public void activate() {
        this.active = true;
    }
    
    public void deactivate() {
        this.active = false;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
    
    public void updatePassword(String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }
    
    // Convenience method for email
    public String getEmail() {

        return email != null ? email.value() : null;
    }
    
    public void setEmail(String emailValue) {
        this.email = emailValue != null ? new EmailAddress(emailValue) : null;
    }
    
    /**
     * Add a role to the user
     * @param role the role to add
     * @return the user with the added role
     */
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }
    
    /**
     * Add a list of roles to the user
     * @param roles the list of roles to add
     * @return the user with the added roles
     */
    public User addRoles(List<Role> roles) {
        if (roles != null) {
            this.roles.addAll(new HashSet<>(roles));
        }
        return this;
    }
}
