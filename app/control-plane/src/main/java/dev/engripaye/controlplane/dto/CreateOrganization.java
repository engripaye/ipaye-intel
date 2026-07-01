package dev.engripaye.controlplane.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateOrganization(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Size(max = 80) @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$")
        String slug
) {
}
