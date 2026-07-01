package dev.engripaye.controlplane.controller;

import dev.engripaye.controlplane.dto.Finding;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/findings")
public class FindingController {

    private final JdbcClient db;


    public FindingController(JdbcClient db) {
        this.db = db;
    }


    @GetMapping
    List<Finding> list(
            @RequestHeader("X-Organization-Id") UUID organizationId,
            @PathVariable UUID projectId,
            @RequestParam(required = false) String severity
    )
    {
        return db.sql("""
                SELECT id, asset_id, scan_id, rule_id, title, description, severity, category,
                       location, evidence, remediation, fingerprint, created_at
                FROM findings
                WHERE organization_id = :organizationId AND project_id = :projectId
                  AND (:severity IS NULL OR severity = CAST(:severity AS severity))
                ORDER BY CASE severity
                    WHEN 'CRITICAL' THEN 5 WHEN 'HIGH' THEN 4 WHEN 'MEDIUM' THEN 3
                    WHEN 'LOW' THEN 2 ELSE 1 END DESC, created_at DESC
                LIMIT 500
                """)
                .param("organizationId", organizationId)
                .param("projectId", projectId)
                .param("severity", severity == null ? null : severity.toUpperCase())
                .query(Finding.class).list();
    }


}
