package dev.engripaye.controlplane.controller;

import dev.engripaye.controlplane.dto.CreateScan;
import dev.engripaye.controlplane.dto.Scan;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/scans")
public class ScanController {

    private final JdbcClient db;


    public ScanController(JdbcClient db) {
        this.db = db;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    Scan create(
            @RequestHeader("X-Organization-Id") UUID organizationId,
            @PathVariable UUID projectId,
            @Valid @RequestBody CreateScan request
    ){
        return db.sql("""
                INSERT INTO scans(organization_id, project_id, asset_id)
                SELECT :organizationId, :projectId, a.id
                FROM assets a
                WHERE a.id = :assetId AND a.project_id = :projectId
                  AND a.organization_id = :organizationId
                RETURNING id, organization_id, project_id, asset_id, status,
                          scanner_version, error_message, queued_at, started_at, completed_at
                """)
                .param("organizationId", organizationId)
                .param("projectId", projectId)
                .param("assetId", request.assetId())
                .query(Scan.class).single();
    }
}
