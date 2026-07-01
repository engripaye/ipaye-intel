package dev.engripaye.controlplane.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateScan(
        @NotNull UUID assetId
) {
}
