package dev.engripaye.controlplane.dto;

import java.time.Instant;
import java.util.UUID;

public record Project(
        UUID id,
        UUID organizationId,
        String name,
        String slug,
        Instant createdAt
) {
}
