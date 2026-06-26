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

    private final JdbcClient db;
    private final AssetRowMapper mapper;

    public AssetRepository(JdbcClient db,
                           AssetRowMapper mapper) {

        this.db = db;
        this.mapper = mapper;

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
