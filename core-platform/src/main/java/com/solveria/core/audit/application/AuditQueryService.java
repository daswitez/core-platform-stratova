package com.solveria.core.audit.application;

import com.solveria.core.audit.domain.model.AuditLog;
import com.solveria.core.audit.infrastructure.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * Servicio de consulta de auditoría funcional.
 *
 * Application Layer:
 * - No contiene lógica de negocio compleja
 * - Orquesta consultas
 * - No conoce infraestructura externa
 */
@Service
public class AuditQueryService {

    private final AuditLogRepository repository;

    public AuditQueryService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public List<AuditLog> findByTenant(String tenantId) {
        return repository.findByTenantId(tenantId);
    }

    public List<AuditLog> findByPeriod(Instant from, Instant to) {
        return repository.findByOccurredAtBetween(from, to);
    }
}
