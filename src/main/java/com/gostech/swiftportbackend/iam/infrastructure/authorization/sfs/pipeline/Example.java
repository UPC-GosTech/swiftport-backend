package com.gostech.swiftportbackend.iam.infrastructure.authorization.sfs.pipeline;

import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import org.springframework.stereotype.Component;

/**
 * Ejemplo de cómo usar TenantContext en servicios y repositorios
 * Este archivo muestra las mejores prácticas para integrar multitenancy
 */
@Component
public class Example {
    
    private final TenantContextHelper tenantContextHelper;
    
    public Example(TenantContextHelper tenantContextHelper) {
        this.tenantContextHelper = tenantContextHelper;
    }
    
    /**
     * Ejemplo 1: Usar TenantContext directamente en un servicio
     */
    public void exemploUsoDirecto() {
        // Obtener tenant ID del contexto actual
        Long tenantId = TenantContext.getCurrentTenantId();
        
        // Usar en queries JPA (ejemplo conceptual)
        // vehicleRepository.findByTenantId(new TenantId(tenantId));
        
        System.out.println("Current tenant ID: " + tenantId);
    }
    
    /**
     * Ejemplo 2: Usar TenantContextHelper para obtener información
     */
    public void exemploUsoHelper() {
        // Verificar si hay contexto válido
        if (tenantContextHelper.hasValidTenantContext()) {
            Long tenantId = tenantContextHelper.getCurrentTenantId();
            
            // Usar en lógica de negocio
            // processForTenant(new TenantId(tenantId));
            
            System.out.println("Processing for tenant ID: " + tenantId);
        }
    }
    
    /**
     * Ejemplo 3: Ejecutar operación en contexto específico
     */
    public String exemploOperacionEnContextoEspecifico() {
        return tenantContextHelper.executeInTenantContext(2L, () -> {
            // Esta operación se ejecuta con tenant ID = 2
            Long currentTenant = TenantContext.getCurrentTenantId();
            return "Operation executed for tenant ID: " + currentTenant;
        });
    }
    
    /**
     * Ejemplo 4: Cómo modificar tus queries existentes
     * ANTES (sin multitenancy):
     * public List<Vehicle> getAllVehicles() {
     *     return vehicleRepository.findAll();
     * }
     * 
     * DESPUÉS (con multitenancy):
     * public List<Vehicle> getAllVehicles() {
     *     Long tenantId = TenantContext.getCurrentTenantId();
     *     return vehicleRepository.findByTenantId(new TenantId(tenantId));
     * }
     */
    
    /**
     * Ejemplo 5: Cómo crear nuevas entidades con tenant
     * ANTES:
     * public Vehicle createVehicle(CreateVehicleCommand command) {
     *     Vehicle vehicle = new Vehicle(command);
     *     return vehicleRepository.save(vehicle);
     * }
     * 
     * DESPUÉS:
     * public Vehicle createVehicle(CreateVehicleCommand command) {
     *     Long tenantId = TenantContext.getCurrentTenantId();
     *     Vehicle vehicle = new Vehicle(command.vehicleId(), new TenantId(tenantId), 
     *                                   command.plateNumber(), command.type(), 
     *                                   command.tons(), command.passengers(), 
     *                                   command.status());
     *     return vehicleRepository.save(vehicle);
     * }
     */
}
