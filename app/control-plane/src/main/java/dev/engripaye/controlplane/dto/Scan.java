package dev.engripaye.controlplane.dto;

import java.time.Instant;
import java.util.UUID;

public record Scan(
        UUID id, UUID organizationId, UUID projectId, UUID assetId, String status,
        String scannerVersion, String errorMessage, Instant queuedAt,
        Instant startedAt, Instant completedAt
) {
}
