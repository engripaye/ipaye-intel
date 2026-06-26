package dev.engripaye.controlplane.repository;

import dev.engripaye.controlplane.dto.AssetResponse;
import dev.engripaye.controlplane.dto.CreateAssetRequest;
import dev.engripaye.controlplane.mapper.AssetRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AssetRepository {

    private final AssetRepository repository;

    public AssetRepository(AssetRepository repository) {
        this.repository = repository;
    }


    public AssetResponse create(
            UUID organizationId,
            UUID projectId,
            CreateAssetRequest request
    ){
        return repository.create(
                organizationId,
                projectId,
                request
        );


    }

    public List<AssetResponse> list(
        UUID organizationId,
        UUID projectId
    ){
        return repository.findAll(

        )
    }
}
