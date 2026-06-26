package dev.engripaye.controlplane.repository;

import dev.engripaye.controlplane.dto.AssetResponse;
import dev.engripaye.controlplane.mapper.AssetRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AssetRepository {

    private final JdbcClient jdbcClient;
    private final AssetRowMapper mapper;


    public AssetRepository(JdbcClient jdbcClient, AssetRowMapper mapper) {
        this.jdbcClient = jdbcClient;
        this.mapper = mapper;
    }

    public AssetResponse create(
            UUID organizationId,
            UUID projectId,
            CreateAssetRequest request
    ){

    }
}
