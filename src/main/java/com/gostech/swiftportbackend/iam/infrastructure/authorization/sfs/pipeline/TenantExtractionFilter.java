package com.gostech.swiftportbackend.iam.infrastructure.authorization.sfs.pipeline;

import com.gostech.swiftportbackend.iam.infrastructure.tokens.jwt.services.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter to extract tenant information from JWT token and set it in TenantContext
 * This filter runs early in the filter chain to ensure tenant context is available
 * for subsequent request processing
 */
@Component
@Order(1)
public class TenantExtractionFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantExtractionFilter.class);
    
    private final TokenServiceImpl tokenService;

    public TenantExtractionFilter(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        try {
            // Extraer token del request
            String token = tokenService.getBearerTokenFrom(request);
            
            if (token != null && tokenService.validateToken(token)) {
                // Extraer tenantId del token
                Long tenantId = tokenService.getTenantIdFromToken(token);
                
                // Establecer en el contexto del hilo actual
                TenantContext.setCurrentTenantId(tenantId);
                
                LOGGER.debug("Tenant context set: tenantId={}", tenantId);
            } else {
                // Si no hay token válido, usar valor por defecto
                TenantContext.setCurrentTenantId(1L);
                LOGGER.debug("No valid token found, using default tenant context");
            }
            
            // Continuar con la cadena de filtros
            filterChain.doFilter(request, response);
            
        } catch (Exception e) {
            LOGGER.error("Error extracting tenant information from token", e);
            // En caso de error, usar valor por defecto
            TenantContext.setCurrentTenantId(1L);
            filterChain.doFilter(request, response);
        } finally {
            // Limpiar el contexto al final del request
            TenantContext.clear();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Filtrar todas las requests excepto recursos estáticos
        String path = request.getRequestURI();
        return path.startsWith("/static/") || 
               path.startsWith("/css/") || 
               path.startsWith("/js/") || 
               path.startsWith("/images/") ||
               path.startsWith("/actuator/health");
    }
} 