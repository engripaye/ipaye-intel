package dev.engripaye.controlplane.dto;

import java.time.Instant;
import java.util.UUID;

public record Finding(
        UUID id,
        UUID assetId,
        UUID scanId,
        String ruleId,
        String title,
        String description,
        String severity,
        String category,
        String location,
        String evidence,
        String remediation,
        String fingerprint,
        Instant createdAt
) {
}
