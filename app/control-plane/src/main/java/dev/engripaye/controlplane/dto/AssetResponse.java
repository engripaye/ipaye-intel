package dev.engripaye.controlplane.dto;

import dev.engripaye.controlplane.model.AssetType;
import tools.jackson.databind.JsonNode;

import java.time.Instant;
import java.util.UUID;

public record AssetResponse (

    UUID id,
    UUID organization,
    UUID projectId,
    String name,
    AssetType Type,
    JsonNode content,
    Instant createdAt,
    Instant updatedAt

){

}
