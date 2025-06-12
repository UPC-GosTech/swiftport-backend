package com.gostech.swiftportbackend.iam.domain.model.aggregates;

import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * Tenant aggregate root for multi-tenancy support
 */
@Getter
@Entity
public class Tenant extends AuditableAbstractAggregateRoot<Tenant> {
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private Boolean active;
    
    private String description;
    
    public Tenant(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.active = true;
    }
    
    public Tenant() {}
    
    public void activate() {
        this.active = true;
    }
    
    public void deactivate() {
        this.active = false;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public String getIdentifier() {
        return this.code;
    }
}
