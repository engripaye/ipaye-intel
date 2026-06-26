package dev.engripaye.controlplane.api;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/assets")
public class AssetController {

    private final JdbcClient jdbcClient;
    private final ObjectMapper objectMapper;

    public AssetController(JdbcClient jdbcClient, ObjectMapper objectMapper){
        this.jdbcClient = jdbcClient;
        this.objectMapper = objectMapper;
    }

    public enum AssetType{
        API_SPEC, JWT_CONFIG, OAUTH_CONFIG, SOURCE_TEXT, CLOUD_CONFIG
    }


    
}
