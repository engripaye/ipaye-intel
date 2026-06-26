package dev.engripaye.controlplane.dto;

import dev.engripaye.controlplane.model.AssetType;
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
