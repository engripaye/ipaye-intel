package dev.engripaye.controlplane.controller;

import dev.engripaye.controlplane.dto.AssetResponse;
import dev.engripaye.controlplane.dto.CreateAssetRequest;
import dev.engripaye.controlplane.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/assets")
public class AssetController {

    private final AssetService assetService;


    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssetResponse create(
            @RequestHeader("X-Organization-Id")
            UUID organizationId,

            @PathVariable
            UUID projectId,

            @Valid
            @RequestBody
            CreateAssetRequest request
    ){
        return assetService.create(
                organizationId,
                projectId,
                request
        );
    }

    @GetMapping
    public List<AssetResponse> list (
            @RequestHeader ("X-Organization-Id")
            UUID organizationId,

            @PathVariable
            UUID projectId
    ){
        return assetService.list(
                organizationId,
                projectId
        );
    }
}
