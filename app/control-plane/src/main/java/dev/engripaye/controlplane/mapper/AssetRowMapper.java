package dev.engripaye.controlplane.mapper;

import dev.engripaye.controlplane.dto.AssetResponse;
import dev.engripaye.controlplane.model.AssetType;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class AssetRowMapper implements RowMapper<AssetResponse> {

    private final ObjectMapper objectMapper;


    public AssetRowMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public AssetResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new AssetResponse(
                rs.getObject("id", UUID.class),
                rs.getObject("organization_id", UUID.class),
                rs.getObject("project_id", UUID.class),
                rs.getString("name"),
                AssetType.valueOf(rs.getString("type")),
                parse(rs.getString("content")),
                rs.getTimestamp("created_At").toInstant(),
                rs.getTimestamp("updated_at").toInstant()
        );

    }

    private JsonNode parse(String json){
        try {
            return objectMapper.readTree(json);
        }catch (Exception ex){
            throw new DataRetrievalFailureException(
                    "Stored asset content is invalid JSON",
                    ex
            );
        }
    }
}
