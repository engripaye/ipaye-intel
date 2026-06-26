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
        return db.sql("""
                INSERT INTO assets (
                    organization_id,
                    project_id,
                    name,
                    type,
                    content
                )
                SELECT
                    :organizationId,
                    p.id,
                    :name,
                    CAST(:type AS asset_type),
                    CAST(:content AS jsonb)
                FROM projects p
                WHERE p.id = :projectId
                  AND p.organization_id = :organizationId
                RETURNING
                    id,
                    organization_id,
                    project_id,
                    name,
                    type,
                    content,
                    created_at,
                    updated_at
                """)
                .param("organizationId", organizationId)
                .param("projectId", projectId)
                .param("name", request.name())
                .param("type", request.type().name())
                .param("content", request.content().toString())
                .query(mapper)
                .single();


    }

    public List<AssetResponse> findAll(UUID organizationId,
                                       UUID projectId) {

        return db.sql("""
                SELECT
                    id,
                    organization_id,
                    project_id,
                    name,
                    type,
                    content,
                    created_at,
                    updated_at
                FROM assets
                WHERE organization_id = :organizationId
                  AND project_id = :projectId
                ORDER BY created_at DESC
                """)
                .param("organizationId", organizationId)
                .param("projectId", projectId)
                .query(mapper)
                .list();

    }
}
