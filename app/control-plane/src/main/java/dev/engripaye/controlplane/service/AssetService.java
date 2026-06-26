package dev.engripaye.controlplane.service;

import dev.engripaye.controlplane.dto.AssetResponse;
import dev.engripaye.controlplane.dto.CreateAssetRequest;
import dev.engripaye.controlplane.repository.AssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AssetService {

    private final AssetRepository repository;

    public AssetService(AssetRepository repository) {
        this.repository = repository;
    }

    public AssetResponse create(
            UUID organizationId,
            UUID projectId,
            CreateAssetRequest assetRequest
    ){
        return repository.create(
                organizationId,
                projectId,
                assetRequest
        );
    }

    public List<AssetResponse> list(UUID organizationID, UUID projectId){
        return repository.findAll(
                organizationID,
                projectId
        );
    }
}
