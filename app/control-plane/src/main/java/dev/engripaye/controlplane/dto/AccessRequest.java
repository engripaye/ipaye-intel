package dev.engripaye.controlplane.dto;

import dev.engripaye.controlplane.model.AssetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tools.jackson.databind.JsonNode;

public record AccessRequest (
        @NotBlank
        @Size(max = 160)
        String name,

        @NotNull
        AssetType Type,

        @NotNull
        JsonNode context

){


}
